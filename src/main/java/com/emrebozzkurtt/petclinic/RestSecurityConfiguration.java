package com.emrebozzkurtt.petclinic;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(value = 1)
@Configuration
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		//super.configure(httpSecurity);
		httpSecurity.antMatcher("/rest/**");
		httpSecurity.authorizeRequests().antMatchers("/rest/**").access("hasRole('ROLE_EDITOR')");
		httpSecurity.csrf().disable();
		httpSecurity.httpBasic();
		
	}
}
