package com.landvibe.dstagram.post;

import com.landvibe.dstagram.image.ImageRepository;
import com.landvibe.dstagram.model.Image;
import com.landvibe.dstagram.model.Post;
import com.landvibe.dstagram.model.PostDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ImageRepository imageRepository;

    public PostServiceImpl(PostRepository postRepository, ImageRepository imageRepository) {
        this.postRepository = postRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<PostDTO> getPosts() {
        List<PostDTO> list = new ArrayList<>();
        List<Post> result = this.postRepository.findAll();
        for(int i=0;i<result.size();i++) {
            List<Image> imageList = this.imageRepository.findByPid(result.get(i).getPid());
            List<String> strList = new ArrayList<>();
            System.out.println("imagelist size: " + imageList.size());
            for(int j=0;j<imageList.size();j++) {
                strList.add(imageList.get(j).getImageUrl());
                System.out.println("imagelist" + j + " " + imageList.get(j).getImageUrl());
            }
            PostDTO postDTO = new PostDTO();
            postDTO.setImageUrl(strList);
            postDTO.setContents(result.get(i).getContents());
            postDTO.setCreated(result.get(i).getCreated());
            postDTO.setPid(result.get(i).getPid());
            postDTO.setUpdated(result.get(i).getUpdated());
            list.add(postDTO);
        }
        return list;
    }

    @Override
    public void createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setContents(postDTO.getContents());
        if (this.postRepository.findById(post.getPid()).isPresent()) {
            throw new RuntimeException("This post already exists: " + post.getPid());
        } else {
            this.postRepository.save(post);

            for(int i = 0; i< postDTO.getImageUrl().size(); i++) {
                Image image = new Image();
                image.setPid(post.getPid());
                image.setImageUrl(postDTO.getImageUrl().get(i));
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
            this.imageRepository.deleteByPid(id);
            this.postRepository.deleteById(id);
        } else {
            throw new RuntimeException("Not found post: " + id);
        }
    }

    @Override
    public PostDTO getPost(int id) {
        if(this.postRepository.findById(id).isPresent()) {
            PostDTO postDTO = new PostDTO();
            Post result = this.postRepository.findById(id).get();
            List<Image> imageList = this.imageRepository.findByPid(id);
            List<String> strList = new ArrayList<>();
            for(int i=0;i<imageList.size();i++) {
                strList.add(imageList.get(i).getImageUrl());
            }
            postDTO.setImageUrl(strList);
            postDTO.setContents(result.getContents());
            postDTO.setCreated(result.getCreated());
            postDTO.setPid(result.getPid());
            postDTO.setUpdated(result.getUpdated());

            return postDTO;
        }
        else {
            throw new RuntimeException("Not found post: " + id);
        }
    }
}
