package com.goldze.mvvmhabit.data.source;

import com.goldze.mvvmhabit.entity.DemoEntity;
import com.goldze.mvvmhabit.entity.Result;
import com.goldze.mvvmhabit.entity.User;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * Created by goldze on 2019/3/26.
 */
public interface HttpDataSource {
    //模拟登录
    Observable<BaseResponse<User>> login(String name, String password);
    Observable<BaseResponse<String>> test();

    //模拟上拉加载
    Observable<DemoEntity> loadMore();

    Observable<BaseResponse<DemoEntity>> demoGet();

    Observable<BaseResponse<DemoEntity>> demoPost(String catalog);

    Observable<BaseResponse<Integer>> register(User user);

    Observable<BaseResponse<List<String>>> getVideo();
}
