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

@RunWith(SpringJUnit4ClassRunner.class)	//테스트시 필요한 클래스를 지정 -> springjunit4classrunner가  그 대상
// 테스트 관련 어노테이션 : 스프링이 실행되면서 어떤 설정 정보를 읽어들여야 하는지를 명시,
// classes 속성으로 @Configuration이 적용된 클래스를 지정 해 줄 수도 있다.
@ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class SampleTests {
	
	@Setter(onMethod_ = { @Autowired })	//autowired 자신이 특정 객체에 의존적이므로 자신에게 해당 타입의 빈을 주입해라.
	private Restaurant restaurant;	//SampleTests에 restaurant 타입의 빈을 주입
	
	@Test
	public void tesExist() {
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("------------------------");
		log.info(restaurant.getChef());
	}
}
