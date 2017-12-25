package com.security.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 缩略图生成服务类
 */
@Service
public class ThumbnailService {

	private static final int HEIGHT = 100;
	private static final int WIDTH = 100;

	/**
	 * 返回缩略图的路径;
	 * @param file
	 * @param uploadPath
	 * @param realUploadPath
	 * @return
	 */
	public String thumbnail(CommonsMultipartFile file, String uploadPath, String realUploadPath) {
		try {
			String des = realUploadPath + "/" + "thumb_" + file.getOriginalFilename();
			//Thumbnails.of(file.getInputStream()).size(HEIGHT, WIDTH).toFile(des);
			Thumbnails.of(file.getInputStream()).size(HEIGHT, WIDTH).toFile(des);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return uploadPath + "/thumb_" + file.getOriginalFilename();
	}
}
