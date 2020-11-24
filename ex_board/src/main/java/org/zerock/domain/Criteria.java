package org.zerock.domain;


import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Criteria {

	private int pageNum;
	private int amount;
	
	private String type; 
	private String keyword;
	
	public Criteria() {
		this(1,10);
		//this() <- 같은 클래스의 다른 생성자 호출할떄 사용 .
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null? new String[] {}: type.split("");
	}
	
	public String getListLink() {
		
		
		//UriCOmponentsBuilder 여러개의 파라미터를 연결해서 하나의 URL을 제공.
		//fromPath(String path) 주어진 경로와 시작하는 빌더를 생성한다.
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		
		return builder.toUriString();
				
	}

}
