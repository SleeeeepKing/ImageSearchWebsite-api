package com.cytech.imagesearchwebsiteapi.img.api;

import com.cytech.imagesearchwebsiteapi.img.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/imgs")
public class ImgController {
    @Autowired
    private ImgService imgService;

    @GetMapping(value = "/{id}")
    public List<List<Double>> getImgList(@PathVariable String id) {
        return imgService.getImgList(id);
    }
}
