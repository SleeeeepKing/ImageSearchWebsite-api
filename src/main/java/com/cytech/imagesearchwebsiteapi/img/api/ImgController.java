package com.cytech.imagesearchwebsiteapi.img.api;

import com.cytech.imagesearchwebsiteapi.img.domain.dto.ImgDTO;
import com.cytech.imagesearchwebsiteapi.img.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/imgs")
public class ImgController {
    @Autowired
    private ImgService imgService;

    @GetMapping(value = "/{text}")
    public ImgDTO getImgList(@PathVariable String text) throws IOException {
        return imgService.getImgList(text);
    }

    @GetMapping(value = "/random")
    public List<ImgDTO> getRandomImgList() throws IOException {
        return imgService.getRandomImgList();
    }
}
