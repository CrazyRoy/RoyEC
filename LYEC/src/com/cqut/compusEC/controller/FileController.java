package com.cqut.compusEC.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.compusEC.service.FileService;

/**
 * 文件Controller
 * @author LV
 *
 */
@Controller
@RequestMapping("/fileController")
public class FileController {

	@Resource
	private FileService fileService; // 用于文件上传的Service
	
	/**
	 * 将上传的文件存入指定位置
	 * @param request  请求
	 * @return         存储成功返回1，失败返回0
	 */
	@RequestMapping("/uploadFile")  
    @ResponseBody
	public JSONObject uploadFile(HttpServletRequest request){
		
		Map<String, Object> result = fileService.saveFile(request);
		
		return JSONObject.fromObject(result);
	}
	
	/**
	 * 删除上传的文件
	 * @param request  请求
	 * @return         存储成功返回1，失败返回0
	 */
	@RequestMapping("/deleteFile")  
    @ResponseBody
	public JSONObject deleteFile(HttpServletRequest request){
		Map<String, Object> result = fileService.deleteFile(request);
		
		return JSONObject.fromObject(result);
	}
	
	/**
	 * 删除上传的文件
	 * @param request  请求
	 * @return         存储成功返回1，失败返回0
	 */
	@RequestMapping("/deleteFileByFilePath")  
    @ResponseBody
	public JSONObject deleteFileByFilePath(String fileName, String filePath, HttpServletRequest request){
		Map<String, Object> result = fileService.deleteFileByFilePath(fileName, filePath, request);
		
		return JSONObject.fromObject(result);
	}
	
	/**
	 * 下载上传的文件
	 * @param request  请求
	 * @return         存储成功返回1，失败返回0
	 */
	@RequestMapping("/downloadFile")  
    @ResponseBody
	public JSONObject downloadFile(String fileName, String filePath, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = fileService.downloadFile(fileName, filePath, request, response);
		
		return JSONObject.fromObject(result);
	}
}
