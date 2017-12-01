package com.security.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 在使用时,为了解决延迟加载出现的错误,应该在实体类上添加@JsonIgnoreProperties, 
 * 作用是json序列化时忽略bean中的一些属性序列化和反序列化时抛出的异常.
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class User implements Serializable{


	private static final long serialVersionUID = -5664848109847758589L;
	private Integer id;
	private String name;
	private String gender;
	private Integer age;
	
	private Date birth;
	
	private Department department;

	public User() {

	}
	

	public User(String name, String gender, Integer age, Department department) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", birth=" + birth
				+ ", department=" + department + "]";
	}

}
