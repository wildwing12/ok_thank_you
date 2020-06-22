package com.ok_thank.you.service;

import java.util.List;

import com.ok_thank.you.dto.Diary;

public interface DiaryService {

	public List<Diary> List();

	public void del(int idx);

	public void insert(String content);

}
