package com.dxc.fakebookbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.fakebookbackend.entity.Post;
import com.dxc.fakebookbackend.service.PostService;


@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	@GetMapping("/user/{userName}/posts")
	public Page<Post> getAllPostByUserName(@PathVariable String userName, Pageable pageable) {
		return postService.getAllPostByUserName(userName, pageable);
	}
	
	@PostMapping("/user/{userName}/posts")
	public Post createPost(@PathVariable String userName, @RequestBody Post post) {
		return postService.createPost(userName, post);
	}
	
	@GetMapping("/allpost") 
	public Page<Post> getAllPost(Pageable pageable) {
		return postService.getAllPost(pageable);
	}
	
	@PutMapping("/posts/{postId}")
	public Post updatePost(@PathVariable (value = "postId") Long postId, @RequestBody Post post) {
		
		return postService.updatePost(postId, post);
	}
	
	@PutMapping("/posts/{postId}/updateViewCount") 
	public Post updateViewCount(@PathVariable Long postId) {
		return postService.updateViewCount(postId);
	}
	
	
}
