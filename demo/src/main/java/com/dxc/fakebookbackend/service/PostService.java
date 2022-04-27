package com.dxc.fakebookbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dxc.fakebookbackend.dao.PostDao;
import com.dxc.fakebookbackend.dao.UserDao;
import com.dxc.fakebookbackend.entity.Post;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserDao userDao;
	
	public Page<Post> getAllPostByUserName(String userName, Pageable pageable) {
		
		return postDao.findByUserUserName(userName, pageable);
	}

}
