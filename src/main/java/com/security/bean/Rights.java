package com.security.bean;

/**
 * 权限
 */
public class Rights {

	private Integer id;
	private String url;
	private String name;
	private Integer rightPos;// 权限位;
	private long rightCode;// 权限码;

	public Rights() {
		
	}
	

	public Rights(String url, String name, Integer rightPos, long rightCode) {
		this.url = url;
		this.name = name;
		this.rightPos = rightPos;
		this.rightCode = rightCode;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRightPos() {
		return rightPos;
	}

	public void setRightPos(Integer rightPos) {
		this.rightPos = rightPos;
	}

	public long getRightCode() {
		return rightCode;
	}

	public void setRightCode(long rightCode) {
		this.rightCode = rightCode;
	}

	@Override
	public String toString() {
		return "Rights [id=" + id + ", url=" + url + ", name=" + name + ", rightPos=" + rightPos + ", rightCode="
				+ rightCode + "]";
	}

}
