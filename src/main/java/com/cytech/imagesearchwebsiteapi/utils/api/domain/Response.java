package com.cytech.imagesearchwebsiteapi.utils.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private int code;
//    private String msg;
    private Object data;
}
