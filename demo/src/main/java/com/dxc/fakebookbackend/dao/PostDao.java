package com.dxc.fakebookbackend.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.fakebookbackend.entity.Post;


public interface PostDao extends JpaRepository<Post, Long> {	
	
	public Optional<Post> findById(Long id);
	Page<Post> findByUserUserName(String userName, Pageable pageable);
	Optional<Post> findByIdAndUserUserName(Long id, String userName);
}
