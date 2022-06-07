package com.icici.moneytransfer.data.api;


import android.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private OkHttpClient.Builder getBuilder(final ArrayList<Pair<String, String>> header) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        Interceptor interceptor = chain -> {

            Headers.Builder headerBuilder = new Headers.Builder();
            if (header != null && header.size() > 0) {
                for (int i = 0; i < header.size(); i++) {
                    headerBuilder.add(header.get(i).first, header.get(i).second);
                }
            }
            Request request = chain.request().newBuilder()
                    .headers(headerBuilder.build())
                    .build();

            return chain.proceed(request);
        };
        builder.addInterceptor(interceptor);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);  // <-- this is the impo

        return builder;
    }

    public Retrofit getRetrofitClient(ArrayList<Pair<String, String>> headerList, String url) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        if (url != null)
            retrofitBuilder.baseUrl(url);
        retrofitBuilder.client(getBuilder(headerList).build());
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());

        return retrofitBuilder.build();
    }
}
