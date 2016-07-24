package com.loveplusplus.update.sample.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class UpdateClient {
    public static String HOST = "https://raw.githubusercontent.com";
    private static UpdateRetrofit updateRetrofit;
    protected static final Object monitor = new Object();
    private static Retrofit retrofit;

    private UpdateClient(){

    }

    static {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public static UpdateRetrofit getUpdateRetrofitInstance() {
        synchronized (monitor) {
            if (updateRetrofit == null) {
                updateRetrofit = retrofit.create(UpdateRetrofit.class);
            }
            return updateRetrofit;
        }
    }
}
