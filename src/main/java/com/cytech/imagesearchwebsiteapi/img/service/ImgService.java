package com.cytech.imagesearchwebsiteapi.img.service;

import com.cytech.imagesearchwebsiteapi.img.domain.Img;
import com.cytech.imagesearchwebsiteapi.img.domain.dto.ImgDTO;
import com.cytech.imagesearchwebsiteapi.img.domain.dto.ImgData;
import com.cytech.imagesearchwebsiteapi.img.repository.ImgRepository;
import com.cytech.imagesearchwebsiteapi.utils.api.ApiCaller;
import com.cytech.imagesearchwebsiteapi.utils.api.domain.RequestBody;
import com.cytech.imagesearchwebsiteapi.utils.api.domain.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
public class ImgService {
    @Autowired
    private ImgRepository imgRepository;
    @Autowired
    private ApiCaller apiCaller;

    public List<ImgDTO> getRandomImgList() {
        List<Img> imgs = imgRepository.findRandomImg();
        List<ImgDTO> imgList = new ArrayList<>();

        imgs.forEach(img ->
        {
            try {
                imgList.add(new ImgDTO(convertImageToBase64(img.getUrl())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // 将返回的base64给前端

        return imgList;
    }

    /**
     * 计算两个向量的余弦相似度
     */

    public ImgDTO getImgList(String text) throws IOException {
        // todo 将strVex改成把id转换成向量
        Object doubleArr = apiCaller.callGet("http://localhost:8990/text2predict", new RequestBody("text", text)).getData();
        double[] strVex = ((double[][]) doubleArr)[0];
        List<Img> imgList = imgRepository.findAll();
        List<double[]> imgVexList = new ArrayList<>();
        imgList.forEach(img -> {
            String[] str = img.getValue().split(",");
            double[] imgVex = new double[str.length];
            for (int i = 0; i < str.length; i++) {
                imgVex[i] = Double.parseDouble(str[i]);
            }
            imgVexList.add(imgVex);
        });
        double maxCosineSimilarity = -1;
        double[] maxCosineSimilarityVector = null;
        for (double[] doubles : imgVexList) {
            double cosineSimilarity = cosine(strVex, doubles);
            if (cosineSimilarity > maxCosineSimilarity) {
                maxCosineSimilarity = cosineSimilarity;
                maxCosineSimilarityVector = doubles;
            }
        }
        // 找到对应的图片
        return new ImgDTO(convertImageToBase64(imgList.get(imgVexList.indexOf(maxCosineSimilarityVector)).getUrl()));
    }

    private String convertImageToBase64(String filePath) throws IOException {
        // Read image file into byte array
        File file = new File(filePath);
        byte[] imageData = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(imageData);
        fileInputStream.close();

        // Encode byte array into Base64 string

        return Base64.getEncoder().encodeToString(imageData);
    }

    private double cosine(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    private String doubleArrayToString(double[] vector) {
        String str = Arrays.toString(vector); // 将double[]转换为字符串
        return str.substring(1, str.length() - 1); // 去掉左右方括号
    }
    public void updateImgValue(String url) throws IOException {
        Img img = imgRepository.findByUrl(url);
        ImgData imgData = new ImgData(convertImageToBase64(img.getUrl()));
        // 用imageData转为json并作为入参调用api获取value
        RequestBody requestBody = new RequestBody("image_data", imgData.getImage_data());
        // 将value改成调用api返回的值并使用doubleArrayToString方法将double[]转换为字符串
        Response response = apiCaller.callPost("http://localhost:8990/image2predict", requestBody);
//        String value = doubleArrayToString(response.getData());
//        img.setValue(value);
//        imgRepository.save(img);
    }

    public void updateAllImgValue() {
        List<Img> imgs = imgRepository.findAll();
        imgs.forEach(img -> {
            try {
                updateImgValue(img.getUrl());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        imgRepository.saveAll(imgs);
    }

    private String objectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
