package com.ok_thank.you.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ok_thank.you.dto.Diary;
import com.ok_thank.you.dto.Pager;
import com.ok_thank.you.dto.TestFileTEst;
import com.ok_thank.you.service.DiaryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class KimController {

	@Autowired
	DiaryService diaryService;
	
	@RequestMapping("/todo/kim")
	public ModelAndView diaryKim(@RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "") String search, ModelAndView mav) {
		int rowCnt = diaryService.rowCnt(search);
		Pager pager = new Pager(rowCnt, curPage);
		int pageScale = Pager.PAGE_SCALE;//화면 출력될 로우 갯수
		int pageBegin= pager.getPageBegin()-1;//시작로우
		List<Diary> list =diaryService.List(pageScale,pageBegin,search);
		mav.addObject("list",list);
		mav.addObject("pager", pager);
		mav.setViewName("diaryKim");
		return mav;
	}
	
	@RequestMapping("/todo/del/{idx}")
	public ModelAndView  del(@PathVariable int idx) {
		diaryService.del(idx);
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("/todo/kim"));
		return mav;
	}
	
	@PostMapping("/todo/insert")
	public ModelAndView insert(@RequestParam String content, ModelAndView mav) {
		diaryService.insert(content);
		mav.setView(new RedirectView("/todo/kim"));
		return mav;
	} 
	@GetMapping("/exceljjud")
	  public void ExcelPoi(  HttpServletResponse response, Model model) throws Exception {
		diaryService.ExcelPoi(response,model);
		}
	
//	파일 업로드
	@PostMapping("/uploadFileTest")
	public ModelAndView uploadTest(ModelAndView mav, TestFileTEst test) {
		String fileName = null;
		String orginalFileName =null;
		String ext=null;
		MultipartFile uploadFile = test.getUploadFile();
		try {
			if(!uploadFile.isEmpty()) {
				orginalFileName = uploadFile.getOriginalFilename();
				ext = FilenameUtils.getExtension(orginalFileName);//확장자 구하기
				
				UUID uuid = UUID.randomUUID();//UUID구하기
				fileName = uuid+"."+ext;
				uploadFile.transferTo(new File("C:\\temp\\"+fileName));
			}
			test.setFileName(fileName);
			test.setExt(ext);
			test.setOriname(orginalFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		diaryService.uploadTest(test);
		mav.setView(new RedirectView("/todo/kim"));
		return mav;
		
	}
	
	@GetMapping("/testview")
	public ModelAndView view(ModelAndView mav) {
		List<TestFileTEst> list = diaryService.downLoad();
		mav.addObject("list",list);
		mav.setViewName("testUploadView");
		return mav;
	}
	//파일 다운로드
	@GetMapping("/fileDownload")
	public void testView(HttpServletRequest request, HttpServletResponse response) {
		String  filename = request.getParameter("filename");//변환된 파일 이름
		String oriname = request.getParameter("oriname");//원래 파일 이름
		String realFilename = "";
		//System.out.println(oriname);//위에 파일 이름 찍어보기
		log.info("오리지널 이름=>{}", oriname);
		try {// 파일 이름이 깨진면 사용하세요.
			String browser = request.getHeader("User-Agent");//파일 인코딩
			if(browser.contains("MSIE")|| browser.contains("Trident")||browser.contains("Chrome")) {
				filename = URLEncoder.encode(filename,"UTF-8").replaceAll("\\+","%20");
				oriname =  URLEncoder.encode(oriname,"UTF-8").replaceAll("\\+","%20");
			} else {
				filename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
				oriname = new String(oriname.getBytes("UTF-8"),"ISO-8859-1");
			}
		} catch (UnsupportedEncodingException ex) {
			
			System.out.println("에러 발생 :UnsupportedEncodingException ");
		}
		realFilename = "C:\\temp\\"+filename;
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+realFilename);
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
            log.info("에러내용 => {}", e);
        }
	}
	
}
