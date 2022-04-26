package com.dxc.fakebookbackend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dxc.fakebookbackend.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {
	
}
