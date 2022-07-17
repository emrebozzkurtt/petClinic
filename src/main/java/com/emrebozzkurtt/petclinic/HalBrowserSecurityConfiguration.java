package com.emrebozzkurtt.petclinic;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(value = -1)
@Configuration
public class HalBrowserSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		//super.configure(httpSecurity);
		httpSecurity.antMatcher("/hal/**").authorizeRequests().anyRequest().permitAll();
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();		
	}
}