package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	//mapper를 작성후 mybatis가 동작 할때 mapper를 인식 할 수있도록 root-context.xml에 추가설정 필요mybatis-spring
	@Select("SELECT sysdate FROM dual")
	public String getTime();
	
	//메서드의선언은 인터페이스에 존재하고 SQL에대한 처리는 XML에을 이용하는 방식.
	public String getTime2();
}
