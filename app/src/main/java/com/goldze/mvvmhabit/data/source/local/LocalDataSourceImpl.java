package com.goldze.mvvmhabit.data.source.local;

import android.content.Context;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.goldze.mvvmhabit.app.AppApplication;
import com.goldze.mvvmhabit.data.source.LocalDataSource;
import com.release.alert.Alert;

import me.goldze.mvvmhabit.utils.MaterialDialogUtils;
import me.goldze.mvvmhabit.utils.SPUtils;

/**
 * 本地数据源，可配合Room框架使用
 * Created by goldze on 2019/3/26.
 */
public class LocalDataSourceImpl implements LocalDataSource {
    private volatile static LocalDataSourceImpl INSTANCE = null;

    public static LocalDataSourceImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (LocalDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSourceImpl();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private LocalDataSourceImpl() {
        //数据库Helper构建
    }

    @Override
    public void saveUserName(String userName) {
        SPUtils.getInstance().put("UserName", userName);
    }

    @Override
    public void savePassword(String password) {
        SPUtils.getInstance().put("password", password);
    }

    @Override
    public void saveSex(String sex) {
        SPUtils.getInstance().put("sex", sex);
    }

    @Override
    public void saveAge(String age) {
        SPUtils.getInstance().put("age", age);
    }

    @Override
    public void saveHeight(String height) {
        SPUtils.getInstance().put("height", height);
    }

    @Override
    public void saveWeight(String weight) {
        SPUtils.getInstance().put("weight", weight);
    }

    @Override
    public String getUserName() {
        return SPUtils.getInstance().getString("UserName");
    }

    @Override
    public String getPassword() {
        return SPUtils.getInstance().getString("password");
    }

    @Override
    public String getSex() {
        return SPUtils.getInstance().getString("sex");
    }

    @Override
    public String getAge() {
        return SPUtils.getInstance().getString("age");
    }

    @Override
    public String getHeight() {
        return SPUtils.getInstance().getString("height");
    }

    @Override
    public String getWeight() {
        return SPUtils.getInstance().getString("weight");
    }

    @Override
    public String chooseSex(Context context) {
        new Alert(context)
                .builder(Alert.Type.BOTTOM)
                .addItem("男")
                .addItem("女")
                .setOnItemClickListener((view, position) -> {
                    Log.i("", "onItemClick: 选择" + position);
                })
                .show();
        MaterialDialogUtils.showBasicDialog(AppApplication.getInstance(), "hello").show();
        return "null";
    }
}
