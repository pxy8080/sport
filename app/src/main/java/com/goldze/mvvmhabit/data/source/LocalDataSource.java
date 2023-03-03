package com.goldze.mvvmhabit.data.source;

import android.content.Context;

/**
 * Created by goldze on 2019/3/26.
 */
public interface LocalDataSource {
    /**
     * 保存用户名
     */
    void saveUserName(String userName);

    /**
     * 保存用户密码
     */
    void savePassword(String password);

    void saveSex(String sex);

    void saveAge(String age);

    void saveHeight(String height);

    void saveWeight(String weight);

    /**
     * 获取用户名
     */
    String getUserName();

    /**
     * 获取用户密码
     */
    String getPassword();

    String getSex();

    String getAge();

    String getHeight();

    String getWeight();

    /**
     * 选择性别
     *
     * @return
     */
    String chooseSex(Context context);
}
