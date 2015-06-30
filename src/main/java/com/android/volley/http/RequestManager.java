package com.android.volley.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue.RequestFinishedListener;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RequestManager {

	public static final String VOLLEY_TAG = "Volley";
	
	private static RequestManager instance;
	private RequestQueue mRequestQueue;
	
	private RequestManager(){}
	
	public static RequestManager getInstance() {
		if (instance == null) {
			synchronized (RequestManager.class) {
				if (instance == null) {
					instance = new RequestManager();
				}
			}
		}
		return instance;
	}
	
	public void init(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
	}
	
	public <T> Request<T> addRequest(Request<T> request) {
		return mRequestQueue.add(request);
	}
	
	/**
	 * 发送同步请求
	 * 目前只能针对StringRequest
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T addSyncRequest(Request<T> request) {
		if (request instanceof StringRequest) {
			RequestFuture<String> future = RequestFuture.newFuture();
			StringRequest stringRequest = new StringRequest(request.getMethod(), request.getUrl(), future);
			mRequestQueue.add(stringRequest);
			try {
				return (T) future.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public <T> void addRequestFinishedListener(RequestFinishedListener<T> listener) {
		mRequestQueue.addRequestFinishedListener(listener);
	}
	
	public <T> void removeRequestFinishedListener(RequestFinishedListener<T> listener) {
		mRequestQueue.removeRequestFinishedListener(listener);
	}
	
	public void start() {
		mRequestQueue.start();
	}
	
	public void stop() {
		mRequestQueue.stop();
	}
	
	public void cancel(final Object tag) {
		mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
			@Override
			public boolean apply(Request<?> request) {
				if (tag == null) {
					return request.getTag() == null;
				}
				
				return tag.equals(request.getTag());
			}
		});
	}
	
	public void cancelAll(Object tag) {
		mRequestQueue.cancelAll(tag);
	}
	
}
