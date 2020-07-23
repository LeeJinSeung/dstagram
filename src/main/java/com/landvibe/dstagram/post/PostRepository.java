package com.landvibe.dstagram.post;

import com.landvibe.dstagram.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
