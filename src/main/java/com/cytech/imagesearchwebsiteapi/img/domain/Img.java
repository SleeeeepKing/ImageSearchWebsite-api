package com.cytech.imagesearchwebsiteapi.img.domain;


import com.cytech.imagesearchwebsiteapi.common.domain.AbstractCreateTimeAuditing;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "img")
@ApiModel(value = "Tax", description = "税金")
public class Img extends AbstractCreateTimeAuditing {
    // lrn
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ApiModelProperty(value = "url")
    @NotNull
    private String url;

    @ApiModelProperty(value = "图片特征向量")
    private String value;


}
