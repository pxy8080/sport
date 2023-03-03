package com.goldze.mvvmhabit.ui.video;

import android.os.Bundle;
import android.util.Log;

import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.databinding.ActivityVideoBinding;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.base.BaseActivity;

public class VideoActivity extends BaseActivity<ActivityVideoBinding, VideoModelView> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_video;
    }

    @Override
    public VideoModelView initViewModel() {
        Bundle bundle = getIntent().getExtras();
        String videourl = bundle.getString("VideoUrl");
        binding.jzVideo.setUp(videourl, "Video");
        return super.initViewModel();
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }
}