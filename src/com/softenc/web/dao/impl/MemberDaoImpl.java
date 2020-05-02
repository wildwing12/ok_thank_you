package com.softenc.web.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softenc.web.dao.MemberDao;
import com.softenc.web.model.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<Object> selectList() {
		return sqlSession.selectList("member.selectList");
	}
	
	@Override
	public Object selectOne(String rowId) {
		return sqlSession.selectOne("member.selectOne", rowId);
	}
	
	@Override
	public Integer insert(Member member) {
		return sqlSession.insert("member.insert", member);
	}
	
	@Override
	public Integer update(Member member) {
		return sqlSession.update("member.update", member);
	}
	
	@Override
	public Integer delete(Member member) {
		return sqlSession.delete("member.delete", member);
	}
	
}
