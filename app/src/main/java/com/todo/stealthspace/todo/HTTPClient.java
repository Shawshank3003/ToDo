package com.todo.stealthspace.todo;
import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.entity.StringEntity;

public class HTTPClient
{
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
    {
        client.get(url, params, responseHandler);
    }

    public static void get(String url, FileAsyncHttpResponseHandler responseHandler)
    {
        client.get(url, responseHandler);
    }

    public static void post(Context context, String url, StringEntity entity, String contentType, AsyncHttpResponseHandler responseHandler)
    {
        client.post(context, url, entity, contentType, responseHandler);
    }
}
