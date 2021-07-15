package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@ToString
@Getter
// @AllArgsConstructor	 // 인스턴스 변수로 선언된 모든 것을 파라미터로 받는 생성자를 작성하게 된다.
@RequiredArgsConstructor	// 여러개의 인스턴스 변수 들 중에서 특정한 벼누에 대해서만 생성자를 작성하고 싶다면 , @NonNull이나 final이 붙은 인스턴스 변수에대한 생성자를 만들어 냄
public class SampleHotel {
	@NonNull
	private Chef chef;	//인스턴스변수

	//기존과 다르게 생성자를 선언하고 Chef를 주입하도록 작성함. @Autowired도 없음
//	public SampleHotel(Chef chef) {
//		this.chef = chef;
//	}
	
	
	
	
	
}
