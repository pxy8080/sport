package com.goldze.mvvmhabit.ui.tab_bar.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;

import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.ui.network.NetWorkItemViewModel;
import com.goldze.mvvmhabit.ui.network.detail.DetailFragment;
import com.goldze.mvvmhabit.ui.video.VideoActivity;

import java.util.HashMap;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class TabBar3FragmentItemViewModel extends ItemViewModel<TabBar3FragmentViewModel> {
    public ObservableField<Bitmap> bitmap = new ObservableField<>();
    public ObservableField<String> url = new ObservableField<>();
    public Drawable drawableImg;

    public TabBar3FragmentItemViewModel(@NonNull TabBar3FragmentViewModel viewModel, String url) {
        super(viewModel);
        this.url.set(url);
        //ImageView的占位图片，可以解决RecyclerView中图片错误问题
        drawableImg = ContextCompat.getDrawable(viewModel.getApplication(), R.mipmap.ic_launcher);
    }

    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //这里可以通过一个标识,做出判断，已达到跳入不同界面的逻辑
            //跳转到详情界面,传入条目的实体对象
            Bundle mBundle = new Bundle();
            mBundle.putString("VideoUrl", url.get());
            viewModel.startActivity(VideoActivity.class, mBundle);
        }
    });
}
