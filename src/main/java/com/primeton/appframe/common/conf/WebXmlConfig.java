package com.primeton.appframe.common.conf;

import java.util.EventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.primeton.appframe.common.filter.XssFilter;
import com.primeton.appframe.common.interceptor.LoginRequestInterceptor;
import com.primeton.appframe.common.interceptor.MaliciousRequestInterceptor;


/**
 * 
 * ClassName: WebXmlConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午12:10:34 <br/>
 * web访问配置
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */
@Configuration
public class WebXmlConfig extends WebMvcConfigurationSupport {

	/**
	 * 静态资源访问
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
	}

	/**
	 * 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginRequestInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/login")
				.excludePathPatterns("/admin/logout");
		registry.addInterceptor(new MaliciousRequestInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

	/**
	 * 过滤器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new XssFilter());
		// filter只能配置"/*","/**"无法识别
		registration.addUrlPatterns("/admin/*");
		return registration;
	}
	
	@Autowired
	private Environment env;

}
