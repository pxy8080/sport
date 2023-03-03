package com.goldze.mvvmhabit.app;

import android.annotation.SuppressLint;
import android.app.Application;

import com.goldze.mvvmhabit.data.DemoRepository;
import com.goldze.mvvmhabit.ui.login.LoginViewModel;
import com.goldze.mvvmhabit.ui.network.NetWorkViewModel;
import com.goldze.mvvmhabit.ui.register.RegisterViewModel;
import com.goldze.mvvmhabit.ui.tab_bar.fragment.TabBar3FragmentViewModel;
import com.goldze.mvvmhabit.ui.tab_bar.fragment.TabBar4FragmentViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by goldze on 2019/3/26.
 */
public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile AppViewModelFactory INSTANCE;
    private final Application mApplication;
    private final DemoRepository mRepository;

    public static AppViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (AppViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppViewModelFactory(application, Injection.provideDemoRepository());
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private AppViewModelFactory(Application application, DemoRepository repository) {
        this.mApplication = application;
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NetWorkViewModel.class)) {
            return (T) new NetWorkViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(TabBar3FragmentViewModel.class)) {
            return (T) new TabBar3FragmentViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(TabBar4FragmentViewModel.class)) {
            return (T) new TabBar4FragmentViewModel(mApplication, mRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
