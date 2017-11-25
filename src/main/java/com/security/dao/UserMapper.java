package com.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.security.bean.User;

public interface UserMapper {

	public void addUser(User user);
	
	public void updateUser(User user);
	
	public User getUserWithDeptById(Integer id);
	
	public List<User> getUsers();
	
	public List<User> getUserByDeptId(@Param("deptId")Integer deptId);
	
	public List<User> getUserByConditon(@Param("condition")String condition);
	
	
	
}
