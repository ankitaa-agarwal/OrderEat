package com.ordereat.OrderEat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ordereat.OrderEat.SpringSecurity.CustomerUserDetailsServiceImpl;
import com.ordereat.OrderEat.SpringSecurity.RestaurantUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomerUserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests().antMatchers("/cus/login/**").hasRole("CUSTOMER")
			.antMatchers("/oe/order/**").hasAnyRole("CUSTOMER","STAFF")
			.antMatchers("/roe/auth/registerStaff/**").hasRole("ADMIN")
			.antMatchers("/roe/auth/login/**").hasAnyRole("ADMIN","STAFF","MANAGER")
			.anyRequest().authenticated()
			.and()
			.httpBasic();
	}
	@Override 
	  public void configure(WebSecurity web) throws Exception{
		  web.ignoring().antMatchers("/cus/register/**","/roe/register/**","/oe/getOrders/**");
	  }
	 
	 
}