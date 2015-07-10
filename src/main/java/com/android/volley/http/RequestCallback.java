package com.android.volley.http;

import com.android.volley.Request;
import com.android.volley.VolleyError;

public abstract class RequestCallback<T> {
	
	/**
	 * 请求开始
	 * @param request
	 */
	public void onStart(Request<T> request){}
	
	/**
	 * 关闭请求
	 * @param request
	 */
	public void onCancelled(Request<T> request){}
	
	/**
	 * 使用缓存
	 * @param request
	 */
	public void onUsedCache(Request<T> request){}
	
	/**
	 * 下载时使用的。目前暂时不需要
	 * @param request
	 * @param total
	 * @param current
	 */
	public void onLoading(Request<T> request, long total, long current) {}
	
	public abstract void onSuccess(T result);
	
	public abstract void onError(VolleyError error);
}
