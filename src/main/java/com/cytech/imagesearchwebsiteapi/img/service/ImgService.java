package com.cytech.imagesearchwebsiteapi.img.service;

import com.cytech.imagesearchwebsiteapi.img.domain.Img;
import com.cytech.imagesearchwebsiteapi.img.domain.dto.ImgDTO;
import com.cytech.imagesearchwebsiteapi.img.repository.ImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ImgService {
    @Autowired
    private ImgRepository imgRepository;

    public List<ImgDTO> getRandomImgList() throws IOException {
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

    public ImgDTO getImgList(String id) throws IOException {
        // todo 将strVex改成把id转换成向量
        double[] strVex = {1, 2, 3};
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
}
