package com.android.volley.http;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

/**
 * Created by Xinzy on 2015/7/10.
 */
public class JsonArrayRequestBuilder extends RequestBuilder<JSONArray> {

    public static JsonArrayRequestBuilder getInstance(String url) {
        return getInstance(url, Request.Method.GET);
    }

    public static JsonArrayRequestBuilder getInstance(String url, int method) {
        return getInstance(url, method, null);
    }

    public static JsonArrayRequestBuilder getInstance(String url, int method, RequestCallback<JSONArray> callback) {
        JsonArrayRequestBuilder builder = new JsonArrayRequestBuilder();
        builder.setMethod(method).setUrl(url).setRequestCallback(callback);
        return builder;
    }

    @Override
    public JsonArrayRequest getRequest() throws IllegalArgumentException {
        if (url == null) {
            throw new IllegalArgumentException("The url must be not null");
        }
        return new JsonArrayRequest(method, url, requestCallback);
    }
}
