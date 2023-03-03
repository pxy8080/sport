package com.goldze.mvvmhabit.ui.tab_bar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.goldze.mvvmhabit.BR;
import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.app.AppViewModelFactory;
import com.goldze.mvvmhabit.databinding.FragmentTabBar3Binding;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * Created by goldze on 2018/7/18.
 */
public class TabBar3Fragment extends BaseFragment<FragmentTabBar3Binding, TabBar3FragmentViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_tab_bar_3;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public TabBar3FragmentViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(TabBar3FragmentViewModel.class);
    }

    @Override
    public void initData() {
        viewModel.requestVideo();
        super.initData();
    }
}
