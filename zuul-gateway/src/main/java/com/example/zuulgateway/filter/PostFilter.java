package com.example.zuulgateway.filter;

import javax.servlet.http.HttpServletRequest;

import com.example.zuulgateway.util.HttpServletUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PostFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		System.out.println("Using Post Filter");
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		System.out.println("Cabeceras de petición");
		System.out.println(HttpServletUtil.obtenerCabecerasPeticion(request));
		System.out.println("-----------------------------------------------");
		System.out.println("Atributos de petición");
		System.out.println(HttpServletUtil.obtenerAtributosPeticion(request));
		System.out.println("-----------------------------------------------");
		System.out.println("Parametros de petición");
		System.out.println(HttpServletUtil.obtenerParametroPeticion(request));
		System.out.println("-----------------------------------------------");
		System.out.println("Metodo y URL de petición");
		System.out.println(String.format("%s petición a %s", request.getMethod(), request.getRequestURL().toString()));
		System.out.println("-----------------------------------------------");
		
		//rechazarPeticion(400, "ERROR");
		
		return null;
	}
	

	private void rechazarPeticion(int codigoEstado, String mensaje) {
	    RequestContext ctx = RequestContext.getCurrentContext();
	    ctx.setResponseStatusCode(codigoEstado);
	    if (ctx.getResponseBody() == null) {
	        ctx.setResponseBody(mensaje);
	        ctx.setSendZuulResponse(false);
	    }
	}

}