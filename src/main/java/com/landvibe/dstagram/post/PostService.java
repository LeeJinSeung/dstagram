package com.landvibe.dstagram.post;

import com.landvibe.dstagram.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getPosts();

    Post createPost(Post post);

    Post updatePost(int id, Post post);

    void deletePost(int id);

    Post getOnePost(int id);
}
