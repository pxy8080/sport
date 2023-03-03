package com.goldze.mvvmhabit.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.app.AppViewModelFactory;
import com.goldze.mvvmhabit.databinding.ActivityRegisterBinding;
import com.release.alert.Alert;

import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.base.BaseActivity;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_register;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public RegisterViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(RegisterViewModel.class);
    }

    @Override
    public void initViewObservable() {
        viewModel.uc.sex.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean sex) {
                if (sex) {
                    new Alert(RegisterActivity.this)
                            .builder(Alert.Type.BOTTOM)
                            .addItem("男")
                            .addItem("女")
                            .setOnItemClickListener(new Alert.OnAlertItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    if (position==0)
                                        binding.sex.setText("男");
                                    else if (position==1)
                                        binding.sex.setText("女");
                                }
                            })
                            .show();
                }
            }
        });
    }
}
