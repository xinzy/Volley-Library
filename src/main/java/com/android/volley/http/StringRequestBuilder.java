package com.android.volley.http;

import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;

public class StringRequestBuilder extends RequestBuilder<String> {
	
	public static StringRequestBuilder getInstance(String url) {
		return getInstance(Method.GET, url);
	}

	public static StringRequestBuilder getInstance(int method, String url) {
		return getInstance(method, url, null);
	}
	
	public static StringRequestBuilder getInstance(int method, String url, RequestCallback<String> callback) {
		StringRequestBuilder builder = new StringRequestBuilder();
		builder.setMethod(method).setUrl(url).setRequestCallback(callback);
		return builder;
	}
	
	@Override
	public StringRequest getRequest() throws IllegalArgumentException {
		if (url == null) {
			throw new IllegalArgumentException("The url must be not null");
		}
		
		return new StringRequest(method, url, requestCallback);
	}
}
