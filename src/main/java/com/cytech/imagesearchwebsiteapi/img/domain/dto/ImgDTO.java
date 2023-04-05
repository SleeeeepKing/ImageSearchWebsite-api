package com.cytech.imagesearchwebsiteapi.img.domain.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImgDTO {
    @NotNull
    private String base64;
}
