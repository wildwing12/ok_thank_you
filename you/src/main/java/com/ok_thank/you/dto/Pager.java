package com.ok_thank.you.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Pager {

	private Integer pageNum;
	private Integer pageRowCnt;
	private Integer pageDisplayCnt;
	private Integer startRowNum;
	private Integer totalRow;
	private Integer totalPageCount;
	private Integer startPageNum;
	private Integer endPageNum;
	private Integer endRowNum;
	
	public Map<String, Object> pageReturn(String pn, int tot , int display, int offset){
		
		this.pageNum = 0;
		this.pageRowCnt = 10;
		this.pageDisplayCnt = display;
		this.endRowNum = offset;
		
		setPageNum(Integer.valueOf(pn)); 
		
		this.startRowNum = getPageNum() * getPageRowCnt();
		this.totalRow = tot; //맵 밸류 2
		this.totalPageCount = (int)Math.ceil(getTotalRow() / (double) getPageRowCnt());
		this.startPageNum = 1+((getPageNum() / getPageDisplayCnt()) * getPageDisplayCnt());
		this.endPageNum = getStartPageNum() + getPageDisplayCnt() -1;

		if(getTotalPageCount() < getEndPageNum()){
			setEndPageNum(getTotalPageCount());
		}
		
		Map<String, Object> page = new HashMap<String, Object>();
		
		page.put("startRowNum", getStartRowNum());
		page.put("startPageNum", getStartPageNum());
		page.put("endPageNum", getEndPageNum());
		page.put("totalPageNum", getTotalPageCount());
		page.put("endRowNum", getEndRowNum());
		
		return page;
	}
}
