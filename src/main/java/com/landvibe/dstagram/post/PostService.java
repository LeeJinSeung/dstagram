package com.landvibe.dstagram.post;

import com.landvibe.dstagram.model.Post;
import com.landvibe.dstagram.model.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getPosts();

    void createPost(PostDTO postDTO);

    Post updatePost(int id, Post post);

    void deletePost(int id);

    PostDTO getPost(int id);
}
