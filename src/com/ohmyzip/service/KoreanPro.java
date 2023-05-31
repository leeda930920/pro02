package com.ohmyzip.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KoreanPro {
	public HttpServletResponse encode(HttpServletRequest request, HttpServletResponse response, String fileName, String filePath){
		try{                                        
		    request.setCharacterEncoding("UTF-8");
		    fileName = new String(fileName.getBytes("UTF-8"), "UTF-8");
		    String fileNameOrg = fileName;
		    File file = new File(filePath, fileName);
		                
		    if(file.isFile()){
		        int bytes = (int)file.length();
		        String header = request.getHeader("User-Agent");
	
		        if (header.contains("MSIE") || header.contains("Trident")) {
		            fileNameOrg = URLEncoder.encode(fileNameOrg,"UTF-8").replaceAll("\\+", "%20");
		            response.setHeader("Content-Disposition", "attachment;filename=" + fileNameOrg + ";");
		        } else {
		            fileNameOrg = new String(fileNameOrg.getBytes("UTF-8"), "ISO-8859-1");
		           response.setHeader("Content-Disposition", "attachment; filename=\"" + fileNameOrg + "\"");
		        }
		         
		        response.setContentType( "application/download; UTF-8" );
		        response.setContentLength(bytes);
		        response.setHeader("Content-Type", "application/octet-stream");                
		        response.setHeader("Content-Transfer-Encoding", "binary;");
		        response.setHeader("Pragma", "no-cache;");
		        response.setHeader("Expires", "-1;");
		        
		        BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
		        BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
		        
		        byte[] readByte = new byte[4096];
		        try{
		            while((bytes = fin.read(readByte)) > 0){
		                outs.write(readByte, 0, bytes);
		                outs.flush();
		            }
		        }
		        catch(Exception ex){
		            ex.printStackTrace();
		        }
		        finally{
		            outs.close();
		            fin.close();
		        }
		    }
		}catch(Exception ex){
		    ex.printStackTrace();
		}
		return response;
	}
}
