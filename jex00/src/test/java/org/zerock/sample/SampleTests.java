package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.config.RootConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { RootConfig.class })
@Log4j
public class SampleTests {
	
	@Setter(onMethod_ = { @Autowired }) //@Autowired는 해당 인스턴스 변수가 스프링으로 부터 자동으로 주입해달라는 표시.
	private Restaurant restaurant;
	
	@Test //테스트 대상을 표시하는 어노테이션.
	public void testExist() {
		
		assertNotNull(restaurant); //레스토랑 변수가 null이 아니어야만 테스트가 성공한다는것을 의미.
		
		log.info(restaurant);
		log.info("-------------");
		log.info(restaurant.getChef());
	}
}