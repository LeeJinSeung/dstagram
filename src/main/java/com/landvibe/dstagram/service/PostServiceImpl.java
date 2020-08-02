package com.landvibe.dstagram.service;

import com.landvibe.dstagram.config.JwtTokenUtil;
import com.landvibe.dstagram.exception.ForbiddenException;
import com.landvibe.dstagram.exception.ParameterNotFoundException;
import com.landvibe.dstagram.repository.PostRepository;
import com.landvibe.dstagram.repository.ImageRepository;
import com.landvibe.dstagram.model.Image;
import com.landvibe.dstagram.model.Post;
import com.landvibe.dstagram.model.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public PostServiceImpl(PostRepository postRepository, ImageRepository imageRepository, JwtTokenUtil jwtTokenUtil) {
        this.postRepository = postRepository;
        this.imageRepository = imageRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public List<PostResponse> getPosts(int skip, int limit) {
        List<PostResponse> list = new ArrayList<>();
        List<Post> result = this.postRepository.findAll();
        for(int i=skip * limit;i<result.size() && i<skip*limit+limit;i++) {
            List<Image> imageList = this.imageRepository.findByPid(result.get(i).getPid())
                    .orElse(new ArrayList<>());
            List<String> strList = new ArrayList<>();
            for(int j=0;j<imageList.size();j++) {
                strList.add(imageList.get(j).getImageUrl());
            }
            PostResponse postResponse = new PostResponse(result.get(i), strList);
            list.add(postResponse);
        }
        return list;
    }

    @Override
    public void createPost(String token, PostResponse postResponse) {
        int uid = jwtTokenUtil.getUserIdFromToken(token);
        Post post = new Post();
        post.setContents(postResponse.getContents());
        post.setUid(uid);

        this.postRepository.save(post);

        for(int i = 0; i< postResponse.getImageUrl().size(); i++) {
            Image image = new Image();
            image.setPid(post.getPid());
            image.setImageUrl(postResponse.getImageUrl().get(i));
            this.imageRepository.save(image);
        }

    }

    @Override
    public PostResponse updatePost(String token, int id, Post post) {
        Post p = this.postRepository.findById(id)
                .orElseThrow(() -> new ParameterNotFoundException("Not Found"));

        int uid = jwtTokenUtil.getUserIdFromToken(token);
        if(uid == p.getUid()) {
            // token에서 가져온 uid랑 일치할 경우
            p.setUpdated(LocalDateTime.now());
            p.setContents(post.getContents());
            this.postRepository.save(p);

            List<Image> images = this.imageRepository.findByPid(p.getPid())
                    .orElse(new ArrayList<>());

            List<String> imageUrl = new ArrayList<>();

            for (int i = 0; i < images.size(); i++) {
                imageUrl.add(images.get(i).getImageUrl());
            }

            PostResponse postResponse = new PostResponse(p, imageUrl);
            return postResponse;
        }
        else {
            // 403 error
            throw new ForbiddenException("Forbidden");
        }

    }

    @Override
    public void deletePost(String token, int id) {
        int uid = jwtTokenUtil.getUserIdFromToken(token);
        Post post = this.postRepository.findById(id)
                .orElseThrow(() -> new ParameterNotFoundException("Not Found"));
        if (uid == post.getUid()) {
            this.imageRepository.deleteAllByPid(id);
            this.postRepository.deleteById(id);
        } else {
            throw new ForbiddenException("Forbidden");
        }
    }

    @Override
    public PostResponse getPost(int id) {
        if(this.postRepository.findById(id).isPresent()) {
            Post result = this.postRepository.findById(id).get();
            List<Image> imageList = this.imageRepository.findByPid(id)
                    .orElse(new ArrayList<>());
            List<String> strList = new ArrayList<>();
            for(int i=0;i<imageList.size();i++) {
                strList.add(imageList.get(i).getImageUrl());
            }
            PostResponse postResponse = new PostResponse(result, strList);
            return postResponse;
        }
        else {
            throw new ParameterNotFoundException("Not Found");
        }
    }
}
