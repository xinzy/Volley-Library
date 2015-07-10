package com.android.volley.http;

import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Request.Priority;
import com.android.volley.RetryPolicy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class RequestBuilder<T> {
	
	public static final int DEFAULT_TIMEOUT = 30 * 1000;//默认Socket超时时间
	public static final int DEFAULT_RETRIES = 1;		//默认重试次数，默认连接失败不重试

	String url;
	int method = Method.GET;
	RequestCallback<T> requestCallback;
	
	private int timeout;
	private int retryTimes;
	private float backoffMultiplier = 1.0f;
	private Map<String, String> params;
	private Map<String, String> headers;
	private boolean shouldCache = false;
	private Object tag;
	
	private Priority priority = Priority.NORMAL;
	
	private RequestParams requestParams;
	private String paramsEncoding;
	
	public abstract Request<T> getRequest() throws IllegalArgumentException;
	
	public Request<T> build() {
		Request<T> request = getRequest();
		if (timeout == 0) {
			timeout = DEFAULT_TIMEOUT;
		}
		if (retryTimes == 0) {
			retryTimes = DEFAULT_RETRIES;
		}
		if (backoffMultiplier == 0.0) {
			backoffMultiplier = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
		}
		RetryPolicy retryPolicy = new DefaultRetryPolicy(timeout, retryTimes, backoffMultiplier);

		if (tag == null) {
			tag = RequestManager.VOLLEY_TAG;
		}
		if (priority == null) {
			priority = Priority.NORMAL;
		}
		if (TextUtils.isEmpty(paramsEncoding)) {
			paramsEncoding = Request.DEFAULT_PARAMS_ENCODING;
		}
		if (requestParams != null) {
			setParams(requestParams.getParams());
			setHeaders(requestParams.getHeaders());
		}
		if (headers == null) {
			headers = Collections.emptyMap();
		}
		
		request.setParams(params).setShouldCache(shouldCache).setRetryPolicy(retryPolicy).setTag(tag)
				.setPriority(priority).setHeader(headers).setParamsEncoding(paramsEncoding)
				.setRequestCallback(requestCallback);

		return request;
	}
	
	//**设置参数***************************************************************
	
	public RequestBuilder<T> setPriority(Priority priority) {
		this.priority = priority;
		return this;
	}
	
	public RequestBuilder<T> setBackoffMultiplier(float backoffMultiplier) {
		this.backoffMultiplier = backoffMultiplier;
		return this;
	}

	public RequestBuilder<T> setUrl(String url) {
		this.url = url;
		return this;
	}

	public RequestBuilder<T> setMethod(int method) {
		this.method = method;
		return this;
	}

	public RequestBuilder<T> setTimeout(int timeout) {
		this.timeout = timeout;
		return this;
	}

	public RequestBuilder<T> setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
		return this;
	}

	public RequestBuilder<T> setRequestCallback(RequestCallback<T> callback) {
		this.requestCallback = callback;
		return this;
	}
	
	public RequestBuilder<T> setHeaders(String key, String val) {
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		headers.put(key, val);
		return this;
	}

	public RequestBuilder<T> setHeaders(Map<String, String> headers) {
		if (headers != null && headers.size() > 0){
			if (this.headers == null) {
				this.headers = headers;
			} else {
				this.headers.putAll(headers);
			}
		}
		return this;
	}
	
	public RequestBuilder<T> setParams(String key, String val) {
		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put(key, val);
		return this;
	}
	
	public RequestBuilder<T> setParams(Map<String, String> params) {
		if (params != null && params.size() > 0){
			if (this.params == null) {
				this.params = params;
			} else {
				this.params.putAll(params);
			}
		}
		return this;
	}

	public RequestBuilder<T> setShouldCache(boolean shouldCache) {
		this.shouldCache = shouldCache;
		return this;
	}
	
	public RequestBuilder<T> setEncoding(String encoding) {
		this.paramsEncoding = encoding;
		return this;
	}
	
	public RequestBuilder<T> setTag(Object tag) {
		this.tag = tag;
		return this;
	}
	
	public RequestBuilder<T> setRequestParams(RequestParams params) {
		this.requestParams = params;
		return this;
	}
}
