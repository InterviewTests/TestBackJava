package br.com.adslima.prefilters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 
 * @author andrews.silva
 *
 */
@Component
public class SimpleLoggingPreFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		/*
		 * Adding authorization header to zuul request header as zuul omits
		 * sensitive headers
		 */
		if (request.getHeader("Authorization") != null) {
			ctx.addZuulRequestHeader("Authorization", request.getHeader("Authorization"));
		}
		return null;
	}

}
