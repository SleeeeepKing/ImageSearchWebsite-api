package com.cytech.imagesearchwebsiteapi.img.repository;

import com.cytech.imagesearchwebsiteapi.img.domain.Img;
import com.cytech.imagesearchwebsiteapi.utils.specification.SpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepository extends SpecificationRepository<Img, String> {

}
