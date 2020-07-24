package com.landvibe.dstagram.repository;

import com.landvibe.dstagram.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByPid(int pid);

    @Transactional
    void deleteAllByPid(int pid);
}
