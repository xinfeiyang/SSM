package com.security.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.security.bean.User;
import com.security.dao.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper mapper;
	
	public List<User> getUsers(){
		return mapper.getUsers();
	}
	
	public List<User> getUsersByConditon(String condition){
		return mapper.getUserByConditon(condition);
	}
	
	public User getUserById(Integer id){
		return mapper.getUserWithDeptById(id);
	}

	public void updateUser(User user) {
		mapper.updateUser(user);
	}
}
