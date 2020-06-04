package com.ok_thank.you.dto;

import lombok.Data;

@Data
public class TestsVO {

	// 보드 번호 
    private Integer idx;

    // 이름 
    private String name;

    // 직책 
    private String position;

    // 나이 
    private String age;

    // 사무실 
    private String office;

    // 취업일 
    private String startDate;

    // 월급 
    private Integer salary;

    // 비고 
    private String etc;

    // 등록일시 
    private String regDt;

    // 사용여부 
    private String useYn;

    // 수정일시 
    private String modDt;
}
