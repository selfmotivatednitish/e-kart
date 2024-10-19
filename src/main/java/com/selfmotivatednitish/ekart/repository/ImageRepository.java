package com.selfmotivatednitish.ekart.repository;

import com.selfmotivatednitish.ekart.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductId(Long id);
}
