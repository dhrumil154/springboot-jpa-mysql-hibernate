
package com.kjit.Diekraft.config.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CORSFilter implements Filter {
	private String mode = "DENY";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {


        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Accept-Encoding, Accept-Language, Host, Referer, Connection, User-Agent, authorization, sw-useragent, sw-version");



		/*HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;

		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
		res.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept, Accept-Encoding, Accept-Language, Host, Referer, Connection, User-Agent, authorization, sw-useragent, sw-version");
*/
		// Just REPLY OK if request method is OPTIONS for CORS (pre-flight)
		if (req.getMethod().equals("OPTIONS")) {
			res.setStatus(HttpServletResponse.SC_OK);
			return;
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedHeader("*");
		config.addAllowedMethod("POST,GET,PUT,DELETE");
		config.addAllowedOrigin("*");
		config.addExposedHeader("content-type");
		if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
			source.registerCorsConfiguration("/api/**", config);
		}
		return new CorsFilter(source);
	}

}
