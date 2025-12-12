package com.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exam.Services.Implement.CustomUserDetailService;
import com.exam.filter.JWTAuthFilter;

@Configuration
public class SecurityConfig {
	@Autowired
	 JWTAuthFilter jwtAuthFilter;
	 UserDetailsService userDetailsService() {
	    	return new CustomUserDetailService();
	    }
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	    	return new BCryptPasswordEncoder();
	    }
	  
	    @Bean
	    AuthenticationManager authenticationManager(UserDetailsService service,PasswordEncoder encoder){
	    	DaoAuthenticationProvider provider=new DaoAuthenticationProvider(service);
	    	provider.setPasswordEncoder(encoder);
	    	return new ProviderManager(provider);
	    }
	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity security) {
		return security
				//.httpBasic(Customizer.withDefaults())
				.csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth->auth.requestMatchers("/authenticate").permitAll()
	            .anyRequest().authenticated())
	            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}
	
}   
