package com.landvibe.dstagram.image;

import com.landvibe.dstagram.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByPid(int pid);
    void deleteByPid(int pid);
}
