package com.security.dao;

import java.util.Map;

import com.security.bean.Rights;

public interface RightsMapper {

	public void addRight(Rights rights);
	
	/**
	 * 获取最大权限位和最大权限码
	 */
	public Map<String,Object> getMaxRightPosAndRightCode();
}
