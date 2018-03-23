package com.cqut.compusEC.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {
	public Map<String, Object> saveFile(HttpServletRequest request);

	public Map<String, Object> deleteFile(HttpServletRequest request);

	public Map<String, Object> deleteFileByFilePath(String fileName, String filePath, HttpServletRequest request);
	
	public Map<String, Object> downloadFile(String fileName, String filePath, HttpServletRequest request, HttpServletResponse response);
}
