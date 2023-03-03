package com.goldze.mvvmhabit.data.source.http.service;

import com.goldze.mvvmhabit.entity.DemoEntity;
import com.goldze.mvvmhabit.entity.Result;
import com.goldze.mvvmhabit.entity.User;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by goldze on 2017/6/15.
 */

public interface DemoApiService {
    @GET("action/apiv2/banner?catalog=1")
    Observable<BaseResponse<DemoEntity>> demoGet();

    @GET("user/test")
    Observable<BaseResponse<String>> test();

    @FormUrlEncoded
    @POST("action/apiv2/banner")
    Observable<BaseResponse<DemoEntity>> demoPost(@Field("catalog") String catalog);

    @GET("user/login")
    Observable<BaseResponse<User>> login(@Query("name") String name, @Query("password") String password);

    @POST("user/registerUser")
    Observable<BaseResponse<Integer>> registerUser(@Body User user);

    @POST("video/getVideo")
    Observable<BaseResponse<List<String>>> getVideo();
}
