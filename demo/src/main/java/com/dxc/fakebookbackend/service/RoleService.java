package com.dxc.fakebookbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.fakebookbackend.dao.RoleDao;
import com.dxc.fakebookbackend.entity.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	
	public Role createNewRole(Role role) {
		return roleDao.save(role);
	}
}
