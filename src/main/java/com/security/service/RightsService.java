package com.security.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.security.bean.Rights;
import com.security.dao.RightsMapper;

@Service
public class RightsService {

	@Autowired
	private RightsMapper mapper;
	
	/**
	 * 新增权限;
	 */
	public void addRight(String url,String name){
		int pos=0;
		long code=1L;
		Map<String, Object> map = mapper.getMaxRightPosAndRightCode();
		if(map!=null){
			pos=(int) map.get("max(rightPos)");
			code=(long) map.get("max(rightCode)");
			//权限位是否达到最大值
			if(code>=(1L<<60)){
				pos+=1;
				code=1L;
			}else{
				code=(code<<1);
			}
		}
		mapper.addRight(new Rights(url, name, pos,code));
	}
}
