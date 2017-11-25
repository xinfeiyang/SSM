package com.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.bean.Department;
import com.security.dao.DepartmentMapper;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentMapper mapper;

	public List<Department> getDepartments(){
		return mapper.getDepartments();
	}
}
