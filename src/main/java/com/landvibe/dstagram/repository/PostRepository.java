package com.landvibe.dstagram.repository;

import com.landvibe.dstagram.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<List<Post>> findByUid(int uid);

    @Transactional
    void deleteAllByUid(int uid);
}
