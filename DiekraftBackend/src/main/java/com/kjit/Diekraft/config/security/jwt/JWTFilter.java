package com.kjit.Diekraft.config.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class JWTFilter extends GenericFilterBean {

	private TokenProvider tokenProvider;

	public JWTFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String jwt = resolveToken(httpServletRequest);
		if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
			Authentication authentication = this.tokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);

	}

	private String resolveToken(HttpServletRequest request) {
		String barerToken = request.getHeader(JWTConfigurer.AUTHORIZATION_HEADER);
		if (StringUtils.hasText(barerToken) && barerToken.startsWith("Bearer ")) {
			return barerToken.substring(7, barerToken.length());
		}
		return null;
	}

}
