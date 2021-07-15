package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component		//스프링에게 해당 클래스가 스프링에서 관리해야 하는 대상임을 표시
@Data
public class Restaurant {
	
	@Setter(onMethod_=@Autowired)	//생성자는 세터등을 사용하여 의존성 주입을 하려고 할때 해당 빈을 찾아서 주입 해주는 annotation이다.
	private Chef chef;
}
