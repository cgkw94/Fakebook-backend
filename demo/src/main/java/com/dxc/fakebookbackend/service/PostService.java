package com.dxc.fakebookbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dxc.fakebookbackend.dao.PostDao;
import com.dxc.fakebookbackend.dao.UserDao;
import com.dxc.fakebookbackend.entity.Post;
import com.dxc.fakebookbackend.entity.User;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserDao userDao;
	
	private User user;
	
	
	public Page<Post> getAllPostByUserName(String userName, Pageable pageable) {
		
		return postDao.findByUserUserName(userName, pageable);
	}

	public Page<Post> getAllPost(Pageable pageable) {
		return postDao.findAll(pageable);
	}
	
	public Post createPost(String userName, Post post) {
		
		user = userDao.findByUserName(userName);
		
		post.setUser(user);
		
		return postDao.save(post);
	}

	public Post updatePost(Long postId, Post post) {
		Post postToUpdate = postDao.getOne(postId);
		
		postToUpdate.setContent(post.getContent());
		postToUpdate.setHyperlink(post.getHyperlink());
	
		return postDao.save(postToUpdate);
	}
	
	public void updateViewCount(Long postId) {
		
	}
	

	
}
