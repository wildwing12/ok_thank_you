package com.ok_thank.you.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {

	public FileUtils() {}
	
	public FileUtils(HttpServletRequest request, HttpServletResponse response, String filename, String oriname,
			String realFilename) {
		try {// 파일 이름이 깨지면 사용하세요.
			String browser = request.getHeader("User-Agent");//파일 인코딩
			if(browser.contains("MSIE")|| browser.contains("Trident")||browser.contains("Chrome")) {
				filename = URLEncoder.encode(filename,"UTF-8").replaceAll("\\+","%20");
				oriname =  URLEncoder.encode(oriname,"UTF-8").replaceAll("\\+","%20");
			} else {
				filename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
				oriname = new String(oriname.getBytes("UTF-8"),"ISO-8859-1");
			}
		} catch (UnsupportedEncodingException ex) {
			log.error("에러 발생 :UnsupportedEncodingException => {}", ex.getMessage());
		}
		realFilename = "C:\\temp\\"+filename;
		log.info(">>>>>>>>>>>>>>>>>>>>>>"+realFilename);
		log.info("리일 파일 네임=>{}", realFilename);
		File file1 = new  File(realFilename);
		if(!file1.exists()) {
			return ;
		}

		// 파일명 지정        
		response.setContentType("application/octer-stream");
		//response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Disposition", "attachment; filename=" + oriname );
		try {
			OutputStream os = response.getOutputStream();
			FileInputStream fis = new FileInputStream(realFilename);

			int ncount = 0;
			byte[] bytes = new byte[1024*10];

			while ((ncount = fis.read(bytes)) != -1 ) {
				os.write(bytes, 0, ncount);
			}
			fis.close();
			os.close();
		} catch (Exception e) {
			System.out.println("FileNotFoundException : " + e);
			log.info("에러내용 => {}", e.getMessage());
		}
	}
}
