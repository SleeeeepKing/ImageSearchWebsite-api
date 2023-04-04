package com.cytech.imagesearchwebsiteapi.img.domain;


import com.cytech.imagesearchwebsiteapi.common.domain.AbstractCreateTimeAuditing;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "img")
@ApiModel(value = "Tax", description = "税金")
public class Img extends AbstractCreateTimeAuditing {
    // lrn
    @Id
    @ApiModelProperty(value = "id")
    private String id;


}
