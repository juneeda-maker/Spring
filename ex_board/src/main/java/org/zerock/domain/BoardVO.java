package org.zerock.domain;

import java.util.Date;

import lombok.Data;


@Data
public class BoardVO {
	//각 계층간 데이터 교환을 위한 자바 객체.

	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;

}
