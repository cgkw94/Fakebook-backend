package com.dxc.fakebookbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dxc.fakebookbackend.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, String>{
	User findByUserName(String userName);
}
