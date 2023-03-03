package com.goldze.mvvmhabit.data;

import android.content.Context;
import android.util.Log;

import com.goldze.mvvmhabit.app.AppApplication;
import com.goldze.mvvmhabit.data.source.HttpDataSource;
import com.goldze.mvvmhabit.data.source.LocalDataSource;
import com.goldze.mvvmhabit.entity.DemoEntity;
import com.goldze.mvvmhabit.entity.Result;
import com.goldze.mvvmhabit.entity.User;
import com.release.alert.Alert;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.base.BaseModel;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repositor）
 * Created by goldze on 2019/3/26.
 */
public class DemoRepository extends BaseModel implements HttpDataSource, LocalDataSource {
    private volatile static DemoRepository INSTANCE = null;
    private final HttpDataSource mHttpDataSource;

    private final LocalDataSource mLocalDataSource;

    private DemoRepository(@NonNull HttpDataSource httpDataSource,
                           @NonNull LocalDataSource localDataSource) {
        this.mHttpDataSource = httpDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public static DemoRepository getInstance(HttpDataSource httpDataSource,
                                             LocalDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (DemoRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DemoRepository(httpDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public Observable<BaseResponse<String>> test() {
        return mHttpDataSource.test();
    }

    @Override
    public Observable<BaseResponse<User>> login(String name, String password) {
        return mHttpDataSource.login(name, password);
    }


    @Override
    public Observable<DemoEntity> loadMore() {
        return mHttpDataSource.loadMore();
    }

    @Override
    public Observable<BaseResponse<DemoEntity>> demoGet() {
        return mHttpDataSource.demoGet();
    }

    @Override
    public Observable<BaseResponse<DemoEntity>> demoPost(String catalog) {
        return mHttpDataSource.demoPost(catalog);
    }

    @Override
    public void saveUserName(String userName) {
        mLocalDataSource.saveUserName(userName);
    }

    @Override
    public void savePassword(String password) {
        mLocalDataSource.savePassword(password);
    }

    @Override
    public void saveSex(String sex) {
        mLocalDataSource.saveSex(sex);
    }

    @Override
    public void saveAge(String age) {
        mLocalDataSource.saveAge(age);
    }

    @Override
    public void saveHeight(String height) {
        mLocalDataSource.saveHeight(height);
    }

    @Override
    public void saveWeight(String weight) {
        mLocalDataSource.saveWeight(weight);
    }

    @Override
    public String getUserName() {
        return mLocalDataSource.getUserName();
    }

    @Override
    public String getPassword() {
        return mLocalDataSource.getPassword();
    }

    @Override
    public String getSex() {
        return mLocalDataSource.getSex();
    }

    @Override
    public String getAge() {
        return mLocalDataSource.getAge();
    }

    @Override
    public String getHeight() {
        return mLocalDataSource.getHeight();
    }

    @Override
    public String getWeight() {
        return mLocalDataSource.getWeight();
    }

    @Override
    public String chooseSex(Context context) {
        return mLocalDataSource.chooseSex(context);
    }

    public Observable<BaseResponse<Integer>> register(User user) {
        return mHttpDataSource.register(user);
    }

    @Override
    public Observable<BaseResponse<List<String>>> getVideo() {
        return mHttpDataSource.getVideo();
    }
}
