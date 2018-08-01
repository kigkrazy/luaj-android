package com.reizx.luaj.model;

import com.reizx.luaj.model.retrofit.api.IpApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 将所有的APP请求在此处统一管理
 */
public class DataManager {
    IpApi ipApi;

    public DataManager(Retrofit.Builder builder, OkHttpClient client) {
        createAllApi(builder, client);//生成所有API对象
    }

    public void createAllApi(Retrofit.Builder builder, OkHttpClient client) {
        // todo 此处添加所有需要的API
        ipApi = createRetrofit(builder, client, IpApi.HOST).create(IpApi.class);//ip情况请求接口
    }

    /**
     * 创建Retrofit
     *
     * @param builder
     * @param client
     * @param url
     * @return
     */
    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public IpApi getIpApi() {
        return ipApi;
    }
}
