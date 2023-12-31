package com.blog1.sevice.impl;

import com.blog1.entity.Post;
import com.blog1.exception.ResourceNotFoundException;
import com.blog1.paylod.PostDto;
import com.blog1.repository.PostRepository;
import com.blog1.sevice.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;


    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post savePost = postRepo.save(post);

        PostDto dto = new PostDto();
        dto.setId(savePost.getId());
        dto.setTitle(savePost.getTitle());
        dto.setContent(savePost.getContent());
        dto.setDescription(savePost.getDescription());
        return dto;
    }

    @Override
    public void deletePost(long id) {

        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id" + id)
        );
        postRepo.deleteById(id);
    }

    @Override
    public List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Post> pagePost = postRepo.findAll(pageable);
        List<Post> post = pagePost.getContent();
        List<PostDto> dtos = post.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {

        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found:" + postId)
        );
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post savedPost = postRepo.save(post);

        PostDto dto = mapToDto(savedPost);
        return dto;
    }

    PostDto mapToDto(Post post){
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
        return dto;
    }

}
