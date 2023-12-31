package com.blog1.sevice;

import com.blog1.paylod.PostDto;

import java.util.List;

public interface PostService {


    public PostDto createPost(PostDto postDto);

    void deletePost(long id);

    List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);


    PostDto updatePost(long postId, PostDto postDto);
}
