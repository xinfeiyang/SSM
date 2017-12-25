package com.security.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
public class UploadService {

	/**
	 * 返回文件的最终保存路径;
	 * @param file
	 * @param uploadPath
	 * @param realUploadPath
	 * @return
	 */
	@SuppressWarnings("unused")
	public String uploadImage(CommonsMultipartFile file,String uploadPath,String realUploadPath){
		InputStream is = null;
		OutputStream os = null;
		try {
			is = file.getInputStream();
			String des = realUploadPath+"/"+file.getOriginalFilename();//文件最终的路径
			//创建文件输出流
			os = new FileOutputStream(des);
			//读取文件输入流
			byte[] b = new byte[1024];
			int len = 0;
			while((len = is.read(b))>0){//输入流读取
				os.write(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(is!=null){
				try {
					is.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			if(os!=null){
				try {
					os.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
		return uploadPath+"/"+file.getOriginalFilename();
	}
}
