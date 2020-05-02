package com.softenc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softenc.web.dao.MemberDao;
import com.softenc.web.model.Member;
import com.softenc.web.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao memberDao;
	
	@Override
	public List<Object> selectList() {
		return memberDao.selectList();
	}
	
	@Override
	public Object selectOne(String rowId) {
		return memberDao.selectOne(rowId);
	}
	
	@Override
	public Integer insert(Member member) {
		return memberDao.insert(member);
	}
	
	@Override
	public Integer update(Member member) {
		return memberDao.update(member);
	}
	
	@Override
	public Integer delete(Member member) {
		return memberDao.delete(member);
	}

}
