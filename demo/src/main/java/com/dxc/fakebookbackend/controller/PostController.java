package com.dxc.fakebookbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dxc.fakebookbackend.entity.FileInfo;
import com.dxc.fakebookbackend.entity.Post;
import com.dxc.fakebookbackend.reponse.ResponseMessage;
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
	
	@GetMapping("/posts/{postId}")
	public Post getPost(@PathVariable Long postId) {
		System.out.println(postId);
		return postService.getPost(postId);
	}
	
	@PutMapping("/posts/{postId}")
	public Post updatePost(@PathVariable (value = "postId") Long postId, @RequestBody Post post) {
		
		return postService.updatePost(postId, post);
	}
	
	@DeleteMapping("/user/{userName}/posts/{postId}") 
	public void deletePost(@PathVariable (value = "userName") String userName, @PathVariable (value = "postId") Long postId) {
		
		postService.deletePost(userName, postId);
	}
	
	@GetMapping("/posts/{postId}/updateViewCount") 
	public Post updateViewCount(@PathVariable Long postId) {
		return postService.updateViewCount(postId);
	}

	@PostMapping("/user/{userName}/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("content") String content, @RequestParam("hyperlink") String hyperlink,  @PathVariable String userName ) {
		return postService.uploadFile(file, content, hyperlink, userName);
	}
	
	@GetMapping("/files")
	  public ResponseEntity<List<FileInfo>> getListFiles() {
	    return postService.getListFiles();
	  }
	
	@GetMapping("/files/{userName}/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String userName, @PathVariable String filename) {
		return postService.getFile(filename, userName);
	}
}
