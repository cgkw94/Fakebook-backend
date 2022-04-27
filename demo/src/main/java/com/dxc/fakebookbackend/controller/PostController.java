package com.dxc.fakebookbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.dxc.fakebookbackend.entity.Post;
import com.dxc.fakebookbackend.service.PostService;
import com.dxc.fakebookbackend.service.UserService;



@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/post/{userName}/posts")
	public Page<Post> getAllPostByUserName(@PathVariable (value = "UserUserName") String userName, Pageable pageable) {
		return postService.getAllPostByUserName(userName, pageable);
	}
	
	
	
}
