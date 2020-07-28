package com.landvibe.dstagram.service;

import com.landvibe.dstagram.model.Post;
import com.landvibe.dstagram.model.PostResponse;

import java.util.List;

public interface PostService {
    List<PostResponse> getPosts(int skip, int limit);

    void createPost(PostResponse postResponse);

    PostResponse updatePost(int id, Post post);

    void deletePost(int id);

    PostResponse getPost(int id);
}
