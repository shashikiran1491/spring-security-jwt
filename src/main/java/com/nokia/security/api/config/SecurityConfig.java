package com.nokia.security.api.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nokia.security.api.jwtFilter.JwtFilter;
import com.nokia.security.api.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtFilter jwtFilter;
	
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);	
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	 @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	 
	 @Override
	protected void configure(HttpSecurity http) throws Exception {
		 
		 
		 http.authorizeRequests().antMatchers("/h2-console")
         .permitAll();
		 http.headers().frameOptions().disable();
        
		 
		 http.csrf().disable().authorizeRequests().antMatchers("/authenticate")
         .permitAll()
         .and().exceptionHandling().and().sessionManagement()
        //Adding Session creation Policy 
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		 //Registering the filter
		 http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		 
		 
	}

	 
}
