package com.talent.front.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Value("${default.security.anpath}")
	String[] auths;

	// @Bean
	// public AccessTokenVerifyInterceptor tokenVerifyInterceptor() {
	// return new AccessTokenVerifyInterceptor();
	// }

	@Autowired
	AccessTokenVerifyInterceptor accessTokenVerifyInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(accessTokenVerifyInterceptor).addPathPatterns("/test");
		// registry.addInterceptor(accessTokenVerifyInterceptor).addPathPatterns("/user/**");
		registry.addInterceptor(accessTokenVerifyInterceptor).addPathPatterns("/**").excludePathPatterns("/login",
				"/deny", "/index", "/relogin", "/logout", "/upload", "/download", "/uploadFile")
				.excludePathPatterns(auths);
		super.addInterceptors(registry);
	}

}
