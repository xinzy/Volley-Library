package com.android.volley.http;

import java.util.HashMap;
import java.util.Map;

public class RequestParams {
	
	private Map<String, String> bodyParams;
	private Map<String, String> headers;
	
	public RequestParams() {
	}
	
	public RequestParams addParam(String key, String value) {
		if (bodyParams == null) {
			bodyParams = new HashMap<String, String>();
		}
		bodyParams.put(key, value);

		return this;
	}

	public RequestParams addParams(Map<String, String> param) {
		if (param != null) {
			if (bodyParams == null) {
				bodyParams = new HashMap<String, String>();
			}
			bodyParams.putAll(param);
		}

		return this;
	}
	
	public RequestParams addHeader(String key, String value) {
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		headers.put(key, value);

		return this;
	}

	public RequestParams addHeaders(Map<String, String> header) {
		if (header != null) {
			if (bodyParams == null) {
				bodyParams = new HashMap<String, String>();
			}
			bodyParams.putAll(header);
		}

		return this;
	}
	
	public Map<String, String> getParams() {
		return bodyParams;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
}
