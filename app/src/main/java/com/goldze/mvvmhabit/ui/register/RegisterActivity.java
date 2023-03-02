package com.goldze.mvvmhabit.ui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.databinding.ActivityLoginBinding;
import com.goldze.mvvmhabit.ui.login.LoginViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

import com.goldze.mvvmhabit.BR;
public class RegisterActivity extends BaseActivity<ActivityLoginBinding, RegisterViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_register;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}