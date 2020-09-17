package org.zerock.sample;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component //해당클래스가 스프링에서 관리해야 하는 대상임을 표시
@Data

public class Restaurant {
	
	@Setter(onMethod_ = @Autowired) //컴파일시 setChef를 생성
	private Chef chef;
}
