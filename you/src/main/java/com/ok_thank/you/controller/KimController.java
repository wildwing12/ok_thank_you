package com.ok_thank.you.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ok_thank.you.dto.Diary;
import com.ok_thank.you.dto.Pager;
import com.ok_thank.you.service.DiaryService;

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

		  HSSFWorkbook objWorkBook = new HSSFWorkbook();
		  HSSFSheet objSheet = null;// 시트생성
		  HSSFRow objRow = null;// 행 생성
		  HSSFCell objCell = null;// 셀 생성

		  HSSFFont font = objWorkBook.createFont();
		  font.setFontHeightInPoints((short) 14);
		  // 글자 굵게 하기
		  font.setBoldweight((short) font.BOLDWEIGHT_BOLD);
		  // 폰트 설정
		  font.setFontName("맑은고딕");

		  // 제목 스타일에 폰트 적용, 정렬
		  HSSFCellStyle styleHd = objWorkBook.createCellStyle();// 제목 스타일
		  // 폰트 설정
		  styleHd.setFont(font);
		  // 가운데 정렬
		  styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		  styleHd.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		  objSheet = objWorkBook.createSheet("첫번째 시트"); // 워크시트 생성

		  //디비에서 받아오는 값
		  List<Diary> rowList = diaryService.list();

		  // 행으로 제작을 하네
		  // 1행
		  objRow = objSheet.createRow(0);
		  objRow.setHeight((short) 0x150);

		  objCell = objRow.createCell(0);
		  objCell.setCellValue("아이디");
		  objCell.setCellStyle(styleHd);

		  objCell = objRow.createCell(1);
		  objCell.setCellValue("이름");
		  objCell.setCellStyle(styleHd);

		  objCell = objRow.createCell(2);
		  objCell.setCellValue("나이");
		  objCell.setCellStyle(styleHd);

		  objCell = objRow.createCell(3);
		  objCell.setCellValue("이메일");
		  objCell.setCellStyle(styleHd);

		  int index = 1;
		  for (Diary diary : rowList) {
		    objRow = objSheet.createRow(index);
		    objRow.setHeight((short) 0x150);

		    objCell = objRow.createCell(0);
		    objCell.setCellValue(diary.getIdx());
		    objCell.setCellStyle(styleHd);

		    objCell = objRow.createCell(1);
		    objCell.setCellValue(diary.getContent());
		    objCell.setCellStyle(styleHd);

		    objCell = objRow.createCell(2);
		    objCell.setCellValue(diary.getWriter());
		    objCell.setCellStyle(styleHd);

		    objCell = objRow.createCell(3);
		    objCell.setCellValue(diary.getRegDt());
		    objCell.setCellStyle(styleHd);
		    
		    objCell = objRow.createCell(4);
		    objCell.setCellValue(diary.getUdtDt());
		    objCell.setCellStyle(styleHd);
		    index++;
		  }

		  for (int i = 0; i < rowList.size(); i++) {
		    objSheet.autoSizeColumn(i);
		  }
		  
		  response.setContentType("Application/Msexcel");
		  response.setHeader("Content-Disposition", "ATTachment; Filename="
		      + URLEncoder.encode("너는 쥬드러스뽕따이", "UTF-8") + ".xls");

		  OutputStream fileOut = response.getOutputStream();
		  objWorkBook.write(fileOut);
		  fileOut.close();

		  response.getOutputStream().flush();
		  response.getOutputStream().close();
		}
}
