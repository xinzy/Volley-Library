# Volley-Library
基于Google Volley二次封装

Application 启动时，在onCreate()里面添加Volley初始化代码  
RequestManager.getInstance().init(this);

发送一个请求  

```
RequestCallback callback = new RequestCallback(){
	@Override
	public void onSuccess(String res) {
		//请求成功
	}

	@Override
	public void onError(VolleyError err) {
	//请求失败
	}
};
Object TAG = "VOLLEY_TAG";
Map<String, String> params = new HashMap<String, String>();
params.put("key", "value");
StringRequest request = (StringRequest) StringRequestBuilder.getInstance(Method.POST, url, callback).setTag(TAG).setParams(params).build();
RequestManager.getInstance().addRequest(request);
```

取消一个请求  

```
RequestManager.getInstance().cance(TAG);
```
