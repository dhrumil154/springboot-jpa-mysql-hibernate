package com.kjit.Diekraft.config.security;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.kjit.Diekraft.config.security.jwt.JWTConfigurer;
import com.kjit.Diekraft.config.security.jwt.TokenProvider;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	 
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	    private final UserDetailsService userDetailsService;

	    private final TokenProvider tokenProvider;
	     
	    public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsService userDetailsService,TokenProvider tokenProvider  /*SecurityProblemSupport problemSupport*/) {
	        this.authenticationManagerBuilder = authenticationManagerBuilder;
	        this.userDetailsService = userDetailsService;
	        this.tokenProvider = tokenProvider;
	    }
	    
	    @PostConstruct
	    public void init() {
	        try {
	            authenticationManagerBuilder
	                .userDetailsService(userDetailsService)
	                .passwordEncoder(passwordEncoder());
	        } catch (Exception e) {
	            throw new BeanInitializationException("Security configuration failed", e);
	        }
	    }

	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	    @Bean
	    public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("POST,GET,PUT,DELETE,OPTIONS");
	        config.addAllowedOrigin("*");
	        config.addExposedHeader("");
	        config.setAllowCredentials(true);
	        System.out.println("......................SecurityConfiguration.corsFilter()........"+config.getAllowedHeaders());
	        System.out.println("......................SecurityConfiguration.corsFilter()........"+config.getAllowedMethods());
	        System.out.println("......................SecurityConfiguration.corsFilter()........"+config.getAllowedOrigins());
	        System.out.println("SecurityConfiguration.corsFilter()............"+config.getAllowCredentials());
	        
	        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
	            source.registerCorsConfiguration("*/api/**", config);
	         //   sorce.registerCorsConfiguration("/v2/api-docs", config);
	        }
	        return new CorsFilter(source);



	    }

	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring()
	            .antMatchers(HttpMethod.OPTIONS, "/**")
	            .antMatchers("/app/**/*.{js,html}")
	            .antMatchers("/i18n/**")
	            .antMatchers("/content/**")
	            .antMatchers("/swagger-ui/index.html")
	            .antMatchers("/test/**")
	            .antMatchers("/h2-console/**");
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
	            .exceptionHandling()
	           /* .authenticationEntryPoint(problemSupport)
	            .accessDeniedHandler(problemSupport)*/
	        .and()
	            .csrf()
	            .disable()
	            .headers()
	            .frameOptions()
	            .disable()
	        .and()
	            .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	            .authorizeRequests()
	            .antMatchers("/api/authenticate").permitAll()
	            .antMatchers("/api/user/create").permitAll()
	            .antMatchers("/api/git/config").permitAll()
	            .antMatchers("/api/**/*").permitAll()
	            /*.antMatchers("/v2/api-docs/**").permitAll()
	            .antMatchers("/swagger-resources/configuration/ui").permitAll()*/
	        .and()
	            .apply(securityConfigurerAdapter());

	    }

	    private JWTConfigurer securityConfigurerAdapter() {
	        return new JWTConfigurer(tokenProvider);
	    }
	
}
