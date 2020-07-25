package com.landvibe.dstagram.service;

import com.landvibe.dstagram.repository.PostRepository;
import com.landvibe.dstagram.repository.ImageRepository;
import com.landvibe.dstagram.model.Image;
import com.landvibe.dstagram.model.Post;
import com.landvibe.dstagram.model.PostResponse;
import io.swagger.annotations.Authorization;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ImageRepository imageRepository;

    public PostServiceImpl(PostRepository postRepository, ImageRepository imageRepository) {
        this.postRepository = postRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<PostResponse> getPosts(int skip, int limit) {
        List<PostResponse> list = new ArrayList<>();
        List<Post> result = this.postRepository.findAll();
        System.out.println(skip + " " + limit);
        for(int i=skip * limit;i<result.size() && i<skip*limit+limit;i++) {
            List<Image> imageList = this.imageRepository.findByPid(result.get(i).getPid());
            List<String> strList = new ArrayList<>();
            System.out.println("imagelist size: " + imageList.size());
            for(int j=0;j<imageList.size();j++) {
                strList.add(imageList.get(j).getImageUrl());
                System.out.println("imagelist" + j + " " + imageList.get(j).getImageUrl());
            }
            PostResponse postResponse = new PostResponse();
            postResponse.setUid(result.get(i).getUid());
            postResponse.setImageUrl(strList);
            postResponse.setContents(result.get(i).getContents());
            postResponse.setCreated(result.get(i).getCreated());
            postResponse.setPid(result.get(i).getPid());
            postResponse.setUpdated(result.get(i).getUpdated());
            list.add(postResponse);
        }
        return list;
    }

    @Override
    public void createPost(PostResponse postResponse) {
        Post post = new Post();
        post.setContents(postResponse.getContents());
        post.setUid(postResponse.getUid());
        if (this.postRepository.findById(post.getPid()).isPresent()) {
            throw new RuntimeException("This post already exists: " + post.getPid());
        } else {
            this.postRepository.save(post);

            for(int i = 0; i< postResponse.getImageUrl().size(); i++) {
                Image image = new Image();
                image.setPid(post.getPid());
                image.setImageUrl(postResponse.getImageUrl().get(i));
                this.imageRepository.save(image);
            }
        }
    }

    @Override
    public Post updatePost(int id, Post post) {
        if (this.postRepository.findById(id).isPresent()) {
            post.setPid(id);
            return this.postRepository.save(post);
        } else {
            throw new RuntimeException("Not found post: " + id);
        }
    }

    @Override
    public void deletePost(int id) {
        if (this.postRepository.findById(id).isPresent()) {
            System.out.println("id is exist: " + id);
            this.imageRepository.deleteAllByPid(id);
            this.postRepository.deleteById(id);
        } else {
            throw new RuntimeException("Not found post: " + id);
        }
    }

    @Override
    public PostResponse getPost(int id) {
        if(this.postRepository.findById(id).isPresent()) {
            PostResponse postResponse = new PostResponse();
            Post result = this.postRepository.findById(id).get();
            List<Image> imageList = this.imageRepository.findByPid(id);
            List<String> strList = new ArrayList<>();
            for(int i=0;i<imageList.size();i++) {
                strList.add(imageList.get(i).getImageUrl());
            }
            postResponse.setUid(result.getUid());
            postResponse.setImageUrl(strList);
            postResponse.setContents(result.getContents());
            postResponse.setCreated(result.getCreated());
            postResponse.setPid(result.getPid());
            postResponse.setUpdated(result.getUpdated());

            return postResponse;
        }
        else {
            throw new RuntimeException("Not found post: " + id);
        }
    }
}
