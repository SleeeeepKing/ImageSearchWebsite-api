package com.cytech.imagesearchwebsiteapi.img.repository;

import com.cytech.imagesearchwebsiteapi.img.domain.Img;
import com.cytech.imagesearchwebsiteapi.utils.specification.SpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImgRepository extends SpecificationRepository<Img, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM img ORDER BY random() LIMIT 5")
    public List<Img> findRandomImg();
}
