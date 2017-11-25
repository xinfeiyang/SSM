package com.security.dao;

import java.util.List;

import com.security.bean.Department;

public interface DepartmentMapper {

	public Department getDepartmentById(Integer id);
	
	public Department getDepartmentWithUsersById(Integer id);
	
	public List<Department> getDepartments();
	
}
