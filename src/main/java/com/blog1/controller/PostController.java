package com.blog1.controller;

import com.blog1.paylod.PostDto;
import com.blog1.sevice.PostService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
     @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody  PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }
         PostDto dto = postService.createPost(postDto);
         return new ResponseEntity<>(dto , HttpStatus.CREATED);


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("post is deleted!!",HttpStatus.OK);
    }
    //htttp://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title&sortBy=desc
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPost(
         @RequestParam(name="pageNo" , defaultValue = "0",required = false) int pageNo,
         @RequestParam(name="pageSize", defaultValue = "3",required = false) int pageSize,
         @RequestParam(name = "sortBy", defaultValue = "id",required = false) String sortBy,
         @RequestParam(name="sortDir",defaultValue = "desc",required = false) String sortDir

    ){
        List<PostDto> postDtos= postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @PutMapping
      public ResponseEntity<PostDto>updatePost(@RequestParam("postId") long postId, @RequestBody PostDto postDto){
        PostDto dto = postService.updatePost(postId, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
      }
}
