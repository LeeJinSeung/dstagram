package com.landvibe.dstagram.service;

import com.landvibe.dstagram.model.Post;
import com.landvibe.dstagram.model.PostResponse;

import java.util.List;

public interface PostService {
    List<PostResponse> getPosts(int skip, int limit);

    void createPost(String token, PostResponse postResponse);

    PostResponse updatePost(String token, int id, Post post);

    void deletePost(String token,int id);

    PostResponse getPost(int id);
}
