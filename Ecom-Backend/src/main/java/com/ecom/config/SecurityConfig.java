package com.ecom.config;

//import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ecom.security.JwtAuthenticationEntryPoint;
import com.ecom.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig{
	public static String[] PUBLIC_URL= {"/user/create", "/auth/login", "/product/view", "/categories/getAll", "/product/category/**"};
	@Autowired
	private JwtAuthenticationFilter filter;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
//	@Bean
//	public UserDetailsService getUserDetailsService(){
//		return  new CustomUserDetailsService();
//	}
	@Autowired
	private CustomUserDetailsService getUserDetailsService;
	@Bean
 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
    	.csrf(csrf->csrf.disable())
    	.authorizeHttpRequests(auth->auth.
    			requestMatchers(PUBLIC_URL).
    			permitAll().
    			anyRequest().
    			authenticated())
//    	.httpBasic();
    	.exceptionHandling(exp->exp.authenticationEntryPoint(entryPoint))
    	.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//   
    	http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
 		return http.build();
 	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		  
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
	}
	@Bean
    public PasswordEncoder passwordEncoder() {

         return new BCryptPasswordEncoder();   
    } 
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
//	@Bean
//	public FilterRegistrationBean cosFilter() {
//		UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
//		CorsConfiguration configuration= new CorsConfiguration();
//		configuration.setAllowCredentials(true);
//		configuration.addAllowedOriginPattern("*");
//		configuration.addAllowedHeader("Authorization");
//		configuration.addAllowedHeader("Context-Type");
//		configuration.addAllowedHeader("Accept");
//		configuration.addAllowedMethod("POST");
//		configuration.addAllowedMethod("GET");
//		configuration.addAllowedMethod("PUT");
//		configuration.addAllowedMethod("DELETE");
//		configuration.addAllowedMethod("OPTIONS");
//		configuration.setMaxAge(3600L);
//		source.registerCorsConfiguration("/**", configuration);
//		FilterRegistrationBean bean= new FilterRegistrationBean(new CorsFilter(source));
//		bean.setOrder(-110);
//		return bean;
//		
//	}
//	
	
}
