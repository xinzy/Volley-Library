package com.android.volley.http;

import java.util.HashMap;
import java.util.Map;

public class RequestParams {
	
	private Map<String, String> bodyParams;
	private Map<String, String> headers;
	
	public RequestParams() {
	}
	
	public void addParams(String key, String value) {
		if (bodyParams == null) {
			bodyParams = new HashMap<String, String>();
		}
		bodyParams.put(key, value);
	}
	
	public void addHeaders(String key, String value) {
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		
		headers.put(key, value);
	}
	
	public Map<String, String> getParams() {
		return bodyParams;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
}
