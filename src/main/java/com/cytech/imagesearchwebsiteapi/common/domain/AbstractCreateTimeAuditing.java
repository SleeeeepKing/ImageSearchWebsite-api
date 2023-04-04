package com.cytech.imagesearchwebsiteapi.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractCreateTimeAuditing {

    @ApiModelProperty(hidden = true)
    @CreatedDate
    protected LocalDateTime createdAt;
}
