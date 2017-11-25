package com.security.bean;

import java.util.List;

public class Department {

	private Integer id;
	private String deptname;
	private List<User> users;

	public Department() {

	}

	public Department(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", deptname=" + deptname + "]";
	}

}
