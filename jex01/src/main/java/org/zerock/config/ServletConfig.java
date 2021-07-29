package org.zerock.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


//servlet-context.xml을 대신하는 별도의 클래스
@EnableWebMvc

/*
 * exceptino 처리 exception 패키지는 servlet-context.xml에서 인식x 떄문에 아래와같이
 * componet-scan을 이용해 해당 패키지의 내용을 조사하도록 시킴
 */
@ComponentScan(basePackages = {"org.zerock.controller", "org.zerock.exception"})
// WebMvcConfigurer는 스프링 MVC와 관련된 설정을 메서드로 오버라이드하는 형태를 이용할때 사용.
public class ServletConfig implements WebMvcConfigurer{
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		registry.viewResolver(bean);
		
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	
	@Bean(name ="multipartResolver")
	public CommonsMultipartResolver getResolver() throws IOException{
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		
		// 10MB
		resolver.setMaxUploadSize(1024 * 1024 * 10);
		
		// 2MB
		resolver.setMaxUploadSizePerFile(1024 * 1024 * 2);
		
		// 1MB
		resolver.setMaxInMemorySize(1024 * 1024);
		
		// temp upload
		resolver.setUploadTempDir(new FileSystemResource("C:\\upload\\tmp"));
		
		resolver.setDefaultEncoding("UTF-8");
		
		return resolver;
	}
	

}
