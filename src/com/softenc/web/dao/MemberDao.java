package com.softenc.web.dao;

import java.util.List;

import com.softenc.web.model.Member;

public interface MemberDao {
	
	public abstract List<Object> selectList();
	
	public abstract Object selectOne(String rowId);
	
	public abstract Integer insert(Member member);
	
	public abstract Integer update(Member member);
	
	public abstract Integer delete(Member member);	
	
}
