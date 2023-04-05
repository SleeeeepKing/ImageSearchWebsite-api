package com.cytech.imagesearchwebsiteapi.img.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ImgServiceTest {
    @Autowired
    private ImgService imgService;

    @Test
    void updateImgValue(){
        imgService.updateAllImgValue();
    }
}