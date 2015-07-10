/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.volley.toolbox;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.http.RequestCallback;

/**
 * A request for retrieving a T type response body at a given URL that also optionally sends along a JSON body in the request specified.
 *
 * @param <T>
 *            JSON type of response expected
 */
public abstract class JsonRequest<T> extends Request<T> {
	/** Default charset for JSON request. */
	protected static final String PROTOCOL_CHARSET = "utf-8";

	/** Content type for request. */
	private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

	/**
	 * Deprecated constructor for a JsonRequest which defaults to GET unless {@link #getPostBody()} or {@link #getPostParams()} is overridden (which
	 * defaults to POST).
	 */
	public JsonRequest(String url, RequestCallback<T> callback) {
		this(Method.DEPRECATED_GET_OR_POST, url, callback);
	}

	public JsonRequest(int method, String url, RequestCallback<T> callback) {
		super(method, url, callback);
	}

	@Override
	abstract protected Response<T> parseNetworkResponse(NetworkResponse response);

	@Override
	public String getBodyContentType() {
		return PROTOCOL_CONTENT_TYPE;
	}
}
