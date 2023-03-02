package com.goldze.mvvmhabit.ui.register;

import android.app.Application;

import androidx.annotation.NonNull;

import com.goldze.mvvmhabit.data.DemoRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;

public class RegisterViewModel extends BaseViewModel<DemoRepository> {

    public RegisterViewModel(@NonNull Application application, DemoRepository model) {
        super(application, model);
    }
}
