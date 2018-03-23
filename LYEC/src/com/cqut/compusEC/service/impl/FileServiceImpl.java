package com.cqut.compusEC.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cqut.compusEC.service.FileService;
import com.cqut.compusEC.util.UploadFile;
import com.cqut.compusEC.util.Variables;

@Service
public class FileServiceImpl implements FileService{
    public Map<String, Object> saveFile(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        
        // 转换为文件类型的request
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获取对应file对象
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Iterator<String> fileIterator = multipartRequest.getFileNames();

        // 获取项目的相对路径（http://localhost:8080/file）
        String realPath = request.getSession().getServletContext().getRealPath("");
        String projectName = request.getContextPath();
        int fileEndIndex = realPath.length() - projectName.length();
        String saveFilePath = realPath.substring(0, fileEndIndex-1);
        Variables.save_directory = saveFilePath + "\\ROOT\\" + Variables.save_path;
//	        String requestURL = request.getRequestURL().toString();
//	        String prePath = requestURL.substring(0, requestURL.indexOf(Variables.ctx));

        int fileCount = 0;
        while (fileIterator.hasNext()) {
        	if(fileCount>=5){
        		break;
        	}
        	Map<String, Object> resultFileMap = new HashMap<>();
            String fileKey = fileIterator.next();
            // 获取对应文件
            MultipartFile multipartFile = fileMap.get(fileKey);

            if (multipartFile.getSize() != 0L) {

                validateImage(multipartFile);

                // 调用saveImage方法保存
                UploadFile file;
				try {
					file = saveFile(multipartFile);
					file.setPrePath(realPath);
					
					String fileName = file.getFileName();
					String filePath = Variables.save_path + "/" + fileName;
					
					resultFileMap.put("filePath", filePath);
					resultFileMap.put("fileName", fileName);
					resultList.add(resultFileMap);
					fileCount++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
//					resultMap.put("resultCode", "9999");
//					resultMap.put("resultMessage", "上传文件失败");
					break;
				}
            }
        }
        if(resultList.size()>0){
        	resultMap.put("resultCode", "0000");
			resultMap.put("resultMessage", "上传文件成功");
			resultMap.put("resultFile", resultList);
        }else{
        	resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "上传文件失败");
        }

        return resultMap;
    }

    private UploadFile saveFile(MultipartFile reqFile) throws IOException {
        String originalFilename = reqFile.getOriginalFilename();

        String contentType = reqFile.getContentType();
        String type = contentType.substring(contentType.indexOf("/") + 1);
//	        String fileName = DateUtil.getCurrentMillStr() + new Random().nextInt(100) + "." + type;
        String fileName = originalFilename;

        // 封装了一个简单的file对象，增加了几个属性
        UploadFile file = new UploadFile(Variables.save_directory, fileName);
        file.setContentType(contentType);

        // 通过org.apache.commons.io.FileUtils的writeByteArrayToFile对图片进行保存
        File saveFile = file.getFile();
        FileUtils.writeByteArrayToFile(saveFile, reqFile.getBytes());

        return file;
    }

    private void validateImage(MultipartFile image) {
    }
    
    public Map<String, Object> deleteFile(HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        // 转换为文件类型的request
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获取对应file对象
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Iterator<String> fileIterator = multipartRequest.getFileNames();

        // 获取项目的相对路径（http://localhost:8080/file）
        String realPath = request.getSession().getServletContext().getRealPath("");
        String projectName = request.getContextPath();
        int fileEndIndex = realPath.length() - projectName.length();
        String saveFilePath = realPath.substring(0, fileEndIndex-1);
        Variables.save_directory = saveFilePath + "\\" + Variables.save_path;
//        String realPath = request.getSession().getServletContext().getRealPath("");
//        Variables.save_directory = realPath + "\\" + Variables.save_path;
//	        String requestURL = request.getRequestURL().toString();
//	        String prePath = requestURL.substring(0, requestURL.indexOf(Variables.ctx));

        while (fileIterator.hasNext()) {
            String fileKey = fileIterator.next();

            // 获取对应文件
            MultipartFile multipartFile = fileMap.get(fileKey);

            if (multipartFile.getSize() != 0L) {

                validateImage(multipartFile);

                // 调用saveImage方法保存
				try {
					boolean result = deleteFile(multipartFile);
					if(result){
						resultMap.put("resultCode", "0000");
						resultMap.put("resultMessage", "删除文件成功");
					}else{
						resultMap.put("resultCode", "9999");
						resultMap.put("resultMessage", "删除文件失败");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "删除文件失败");
				}
            }
        }

        return resultMap;
    }
    
    private boolean deleteFile(MultipartFile reqFile) throws IOException {
        String originalFilename = reqFile.getOriginalFilename();

        String contentType = reqFile.getContentType();
        String type = contentType.substring(contentType.indexOf("/") + 1);
//        String fileName = DateUtil.getCurrentMillStr() + new Random().nextInt(100) + "." + type;
        String fileName = originalFilename;

        // 封装了一个简单的file对象，增加了几个属性
        UploadFile file = new UploadFile(Variables.save_directory, fileName);
        file.setContentType(contentType);

        // 通过org.apache.commons.io.FileUtils的writeByteArrayToFile对图片进行保存
        File deleteFile = file.getFile();
        boolean result = FileUtils.deleteQuietly(deleteFile);

        return result;
    }
    
    public Map<String, Object> deleteFileByFilePath(String fileName, String filePath, HttpServletRequest request) {

        // 获取项目的相对路径（http://localhost:8080/file）
    	String realPath = request.getSession().getServletContext().getRealPath("");
        String projectName = request.getContextPath();
        int fileEndIndex = realPath.length() - projectName.length();
        String saveFilePath = realPath.substring(0, fileEndIndex-1);
        Variables.save_directory = saveFilePath + "\\" + Variables.save_path;
//        String realPath = request.getSession().getServletContext().getRealPath("");
//        Variables.delete_directory = realPath + "\\" + Variables.save_path;
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        try {
			boolean result = deleteFile(fileName, filePath);
			if(result){
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "删除文件成功");
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "删除文件失败");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "删除文件失败");
		}

        return resultMap;
    }
    
    private boolean deleteFile(String fileName, String filePath) throws IOException {

        // 通过org.apache.commons.io.FileUtils的writeByteArrayToFile对图片进行保存
        File deleteFile = new File(Variables.delete_directory + "/" + fileName);
        boolean result = FileUtils.deleteQuietly(deleteFile);

        return result;
    }
    
    public Map<String, Object> downloadFile(String fileName, String filePath, HttpServletRequest request, HttpServletResponse response) {

//        response.setContentType("multipart/form-data");
//        response.setHeader("Content-Disposition", "fileName="+fileName);
        
        // 获取项目的相对路径（http://localhost:8080/file）
        String realPath = request.getSession().getServletContext().getRealPath("");
        Variables.download_directory = realPath + "\\" + Variables.save_path;
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        try {
        	String downloadFileName = URLEncoder.encode(fileName,"UTF-8");
        	response.reset();
            response.setContentType(request.getServletContext().getMimeType(downloadFileName));  
            response.setHeader("Content-Disposition", "attachment;filename="+downloadFileName); 
        	File file = new File(Variables.download_directory + "/" + fileName);
        	
        	InputStream in = new FileInputStream(file);
        	OutputStream out = response.getOutputStream();

        	int b;
        	while((b=in.read()) != -1){
        		out.write(b);
        	}
        	in.close();
        	out.close();
        	out.flush();
			resultMap.put("resultCode", "0000");
			resultMap.put("resultMessage", "下载文件成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "下载文件失败");
		}

        return resultMap;
    }
}