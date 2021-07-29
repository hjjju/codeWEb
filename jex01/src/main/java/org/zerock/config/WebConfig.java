package org.zerock.config;

import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new  Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]	{ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String [] {"/"}; //스프링 MVC의 기본 경로
	}

	
	//404에러처리를 위한 web.xml에서 설정한 thorwExceptionIfNoHandlerFound설정
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registratoin) {
		registratoin.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}
	
}
