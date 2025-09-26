package ru.mirea.popov.mireaproject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IpInfoApi {
    @GET("json")
    Call<IpInfo> getIpInfo();
}

