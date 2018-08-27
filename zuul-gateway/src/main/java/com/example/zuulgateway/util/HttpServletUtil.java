package com.example.zuulgateway.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HttpServletUtil {

	public static Map<String, String> obtenerCabecerasPeticion(HttpServletRequest request) {
		Enumeration headerNames = request.getHeaderNames();
		return construirMap(request, headerNames);
	}

	public static Map<String, String> obtenerAtributosPeticion(HttpServletRequest request) {
		Enumeration attributeNames = request.getAttributeNames();
		return construirMap(request, attributeNames);
	}
	
	public static Map<String, String> obtenerParametroPeticion(HttpServletRequest request) {
		Enumeration attributeNames = request.getParameterNames();
		return construirMap(request, attributeNames);
	}
	
	private static Map<String, String> construirMap(HttpServletRequest request, Enumeration headerNames) {
		Map<String, String> map = new HashMap<String, String>();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return map;
	}
	
}
