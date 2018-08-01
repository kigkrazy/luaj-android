package com.reizx.luaj.model.retrofit.api;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface IpApi {
    String HOST = "http://2018.ip138.com/";

    /**
     * 获取IP信息
     * @return
     */
    @GET("/ic.asp")
    Flowable<ResponseBody> getCurrentIp();

}
