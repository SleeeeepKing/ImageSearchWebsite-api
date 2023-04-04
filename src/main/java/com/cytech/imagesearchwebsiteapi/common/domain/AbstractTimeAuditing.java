package com.cytech.imagesearchwebsiteapi.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author jin xianguo<br>
 */
@MappedSuperclass
@Data
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractTimeAuditing {

    @ApiModelProperty(hidden = true)
    @CreatedDate()
    @Column(name = "created_at", updatable = false)
    protected LocalDateTime createdAt;

    @ApiModelProperty(hidden = true)
    @LastModifiedDate
    @Column(name = "updated_at", updatable = false)
    protected LocalDateTime updatedAt;


}
