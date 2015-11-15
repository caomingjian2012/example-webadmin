package com.palmtech.ad.web;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.palmtech.ad.utils.ApkUtils;
import com.palmtech.ad.utils.ImageUtils;

/**
 * 文件
 * @author Administrator
 *
 */
@Controller
public class UploadController {
	
	
	public static final String ALL_POINT = ".";
	public static final String ALL_JPG_IMAGE_SUFFIX_NAME = "jpg";
	public static final String ALL_PNG_IMAGE_SUFFIX_NAME = "png";
	public static final String ALL_APP_FILE_SUFFIX_NAME = "apk";
	public static final String ALL_IMAGE_UPLOAD_PATH = "images";
	public static final String ALL_IMAGE_UPLOAD_TMP_PATH = "tmp";
	public static final String ALL_ICON_FILE_PATH = "icon";
	public static final String ALL_APP_UPLOAD_APK_FILE_PATH = "files";
	
	public static final int ICON_SCALING_IMAGE_WIDTH = 72;

	public static final int ICON_SCALING_IMAGE_HEIGTH = 72;
	
	public static String DISKPATH ="/data/www/palmcms/";//外网服务器默认，内网请注入
	
	public static String URLPATH="http://image.palmtech.com.cn/palmcms/";//外网服务器默认，内网请注入
	
	@RequestMapping("/upload/icon")
	public void uploadIcon(@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
	
		String basePath = URLPATH;
		
		String path = DISKPATH;
		
		UUID uuid = UUID.randomUUID();
		
		String fileName = uuid.toString() + ALL_POINT + ALL_PNG_IMAGE_SUFFIX_NAME;
		
		String tmpFile = path + ALL_ICON_FILE_PATH + File.separatorChar + fileName;
		
		FileCopyUtils.copy(file.getBytes(),new File(tmpFile));
		
		String convertPath = path + ALL_ICON_FILE_PATH + File.separatorChar + fileName;
		
		
		ImageUtils.resizeByBalance(tmpFile, convertPath, ICON_SCALING_IMAGE_WIDTH,ICON_SCALING_IMAGE_HEIGTH);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("url", basePath + ALL_ICON_FILE_PATH + "/" + fileName);
		
		
		String result = JSON.toJSONString(map);

		response.setContentType("text/json");
		response.getWriter().write(result);
	}
	@RequestMapping("/upload/apk")
	public void uploadApk(@RequestParam("apkFile") MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		
		
		String basePath = URLPATH;
		
		String path = DISKPATH;
		
		UUID uuid = UUID.randomUUID();
		
		String fileName = uuid.toString() +  ALL_POINT +  ALL_APP_FILE_SUFFIX_NAME;
		
		
		
		String tmpFile = path +  ALL_APP_UPLOAD_APK_FILE_PATH + File.separatorChar + fileName;
		
		FileCopyUtils.copy(file.getBytes(),new File(tmpFile));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uploadFileName", file.getOriginalFilename());
		map.put("name", fileName);
		map.put("size", file.getSize());
		map.put("url", basePath + ALL_APP_UPLOAD_APK_FILE_PATH + "/" + fileName);
		
		Map<String,Object> apkMap = ApkUtils.unZip(tmpFile);
		map.put("versionName", apkMap.get("versionName"));
		map.put("packageName", apkMap.get("packageName"));
		map.put("versionCode", apkMap.get("versionCode"));
		
		String result = JSON.toJSONString(map);

		response.setContentType("text/json");
		//response.setContentType("text/html");
		response.getWriter().write(result);
	}
	
	@RequestMapping("/upload/pic")
	public void uploadpic(@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
	
		String basePath = URLPATH;
		
		String path = DISKPATH;
		
		UUID uuid = UUID.randomUUID();
		
		String fileName = uuid.toString() +  ALL_POINT +  ALL_PNG_IMAGE_SUFFIX_NAME;
		
		String tmpFile = path +  ALL_ICON_FILE_PATH + File.separatorChar + fileName;
		
		FileCopyUtils.copy(file.getBytes(),new File(tmpFile));
		
		String convertPath = path +  ALL_ICON_FILE_PATH + File.separatorChar + fileName;
		
		
		ImageUtils.resizeByBalance(tmpFile, convertPath, 480,480);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("url", basePath + ALL_ICON_FILE_PATH + "/" + fileName);
		
		
		String result = JSON.toJSONString(map);

		response.setContentType("text/json");
		response.getWriter().write(result);
	}
	
	
	

	
	public  void setDISKPATH(String dISKPATH) {
		DISKPATH = dISKPATH;
	}
	
	public  void setURLPATH(String uRLPATH) {
		URLPATH = uRLPATH;
	}
	
	
}
