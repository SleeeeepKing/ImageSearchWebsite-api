package com.cytech.imagesearchwebsiteapi.utils.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBody {
    private String key;
    private String value;
}
