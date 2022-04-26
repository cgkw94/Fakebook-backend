package com.dxc.fakebookbackend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dxc.fakebookbackend.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String>{
	
}
