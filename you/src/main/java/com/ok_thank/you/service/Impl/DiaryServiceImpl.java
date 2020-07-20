package com.ok_thank.you.service.Impl;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.ok_thank.you.dto.Diary;
import com.ok_thank.you.dto.DiarySearchReq;
import com.ok_thank.you.dto.TestFileTEst;
import com.ok_thank.you.mapper.DiaryMapper;
import com.ok_thank.you.service.DiaryService;
import com.ok_thank.you.util.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiaryServiceImpl implements DiaryService{

	@Autowired
	DiaryMapper mapper;

	@Override
	public List<Diary> list() {
		return mapper.list();
	}

	@Override
	public void del(int idx) {
		mapper.del(idx);
	}

	@Override
	public void insert(String content) {
		mapper.insert(content);
	}

	@Override
	public List<Map<String, Object>> aList(Map<String, Object> map) {
		return mapper.aList(map);
	}

	@Override
	public Integer insert(Diary model) {
		return mapper.insert(model);
	}


	//비동기(HYUNJOO)
	@Override
	public List<Diary> asyncList(DiarySearchReq searchParam) {
		List<Diary> list = null;
		try {
			Map<String,Object> map = new HashMap<>();
			String keyword = searchParam.getKeyword();
			log.info("keyword: "+keyword);
			map.put("keyword", "%"+keyword+"%");
			list = mapper.asyncList(map);
			log.info(list.toString());
		} catch (Exception e) {
			log.error("리스트 조회 실패! => {}", e.getMessage());
			throw new RuntimeException();
		}
		return list;
	}

	@Override
	public void asyncInsert(Diary diary) {
		String writer = "Lee";
		try {
			if(diary != null) {
				diary.setWriter(writer);
				mapper.asyncInsert(diary);
				log.info("작성자: "+writer);
			}
		} catch (Exception e) {
			log.error("다이어리 입력 실패! => {}", e.getMessage());
			throw new RuntimeException();
		}
	}

	@Override
	public void asyncDelete(Integer idx) {
		try {
			if(idx != null) {
				mapper.asyncDelete(idx);
				log.info("글번호: "+idx);
			}
		} catch (Exception e) {
			log.error("삭제 실패! => {}", e.getMessage());
			throw new RuntimeException();
		}
	}

	@Override
	public int rowCnt(String search) {
		int rowCnt = mapper.rowCnt("%"+search+"%");
		return rowCnt;
	}

	@Override
	public List<Diary> List(int pageScale, int pageBegin, String search) {
		Map<String,Object> map = new HashMap<>();
		map.put("pageScale", pageScale);
		map.put("pageBegin",pageBegin);
		map.put("search","%"+search+"%");
		return mapper.plist(map);
	}

	@Override
	public int getRowCnt(String writer) {
		int count = 0;
		try {
			if(StringUtils.isNotEmpty(writer)) {
				count = mapper.getRowCnt(writer);
				log.info("글 갯수: "+count);
			}
		} catch (Exception e) {
			count = -1;
			log.error("조회 실패! => {}", e.getMessage());
		}
		return count;
	}

	@Override
	public List<Diary> listLee(int pageScale, int begin) {
		List<Diary> list = null;
		try {
			Map<String,Object> map = new HashMap<>();
			map.put("pageScale",pageScale);
			map.put("begin", begin);
			list = mapper.listLee(map);
		} catch (Exception e) {
			log.error("paging 조회 실패! => {}", e.getMessage());
		}
		return list;
	}

	@Override
	public void ExcelPoi( HttpServletResponse response, Model model) throws Exception {
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
		List<Diary> rowList = list();

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
				+ URLEncoder.encode("너는_쥬드러스뽕따이", "UTF-8") + ".xls");

		OutputStream fileOut = response.getOutputStream();
		objWorkBook.write(fileOut);
		fileOut.close();

		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

	@Override
	public void uploadTest(TestFileTEst test) {
		String fileName = null;
		String orginalFileName =null;
		String ext=null;
		MultipartFile uploadFile = test.getUploadFile();
		try {
			if(test != null && !uploadFile.isEmpty()) {
				orginalFileName = uploadFile.getOriginalFilename();
				ext = FilenameUtils.getExtension(orginalFileName);//확장자 구하기

				UUID uuid = UUID.randomUUID();//UUID구하기
				fileName = uuid+"."+ext;
				uploadFile.transferTo(new File("C:\\temp\\"+fileName));
			}
			test.setFileName(fileName);
			test.setExt(ext);
			test.setOriname(orginalFileName);
			log.info("파일 => {}", test);
		} catch (Exception e) {
			log.error("업로드 실패! => {}",e.getMessage());
		}
		mapper.uploadTest(test);

	}

	@Override
	public List<TestFileTEst> downLoad() {
		return mapper.downLoad();
	}

	@Override
	public void fileDownload(HttpServletRequest request, HttpServletResponse response, String filename, String oriname,
			String realFilename) {
		new FileUtils(request, response, filename, oriname, realFilename);
		
	}

}
