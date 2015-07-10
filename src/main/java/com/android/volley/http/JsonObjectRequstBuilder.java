package com.android.volley.http;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by Xinzy on 2015/7/10.
 */
public class JsonObjectRequstBuilder extends RequestBuilder<JSONObject> {

    public static JsonObjectRequstBuilder getInstance(String url) {
        return getInstance(url, Request.Method.GET);
    }

    public static JsonObjectRequstBuilder getInstance(String url, int method) {
        return getInstance(url, method, null);
    }

    public static JsonObjectRequstBuilder getInstance(String url, int method, RequestCallback<JSONObject> callback) {
        JsonObjectRequstBuilder builder = new JsonObjectRequstBuilder();
        builder.setMethod(method).setUrl(url).setRequestCallback(callback);
        return builder;
    }

    @Override
    public JsonObjectRequest getRequest() throws IllegalArgumentException {
        if (url == null) {
            throw new IllegalArgumentException("The url must be not null");
        }

        return new JsonObjectRequest(method, url, requestCallback);
    }
}
