package com.cytech.imagesearchwebsiteapi.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @className: AbstractAuditing
 * @description: AbstractAuditing for base motify information(createdBy-name,createdAt-data,updatedBy-name,updatedAt-data)<br>
 **/
@MappedSuperclass
@Data
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractAuditing {

    @ApiModelProperty(hidden = true)
    @CreatedBy
    protected String createdBy;

    @ApiModelProperty(hidden = true)
    @CreatedDate
    protected LocalDateTime createdAt;

    @ApiModelProperty(hidden = true)
    @LastModifiedBy
    protected String updatedBy;

    @ApiModelProperty(hidden = true)
    @LastModifiedDate
    protected LocalDateTime updatedAt;

}
