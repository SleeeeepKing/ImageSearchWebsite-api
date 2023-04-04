package com.cytech.imagesearchwebsiteapi.common.domain.vm.filter;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CommandVM {
    private String reference;
    private Integer packageQuantity;
    private Integer parcelQuantity;
    private BigDecimal roughWeight;
    private String comment;
    private String transporter;
}
