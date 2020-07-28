package com.landvibe.dstagram.repository;

import com.landvibe.dstagram.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<List<Image>> findByPid(int pid);

    // pid를 가지는 image중에서 가장 작은 값을 가져오도록.
    // Optional<Image> findByPidMin(int pid);

    @Transactional
    void deleteAllByPid(int pid);
}
