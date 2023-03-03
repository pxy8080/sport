package com.goldze.mvvmhabit.ui.video;

import android.app.Application;

import androidx.annotation.NonNull;

import com.goldze.mvvmhabit.data.DemoRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;

public class VideoModelView extends BaseViewModel<DemoRepository> {
    public VideoModelView(@NonNull Application application) {
        super(application);

    }

}
