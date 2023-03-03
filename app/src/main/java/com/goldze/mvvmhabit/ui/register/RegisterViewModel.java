package com.goldze.mvvmhabit.ui.register;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.goldze.mvvmhabit.data.DemoRepository;
import com.goldze.mvvmhabit.entity.User;
import com.goldze.mvvmhabit.ui.login.LoginViewModel;
import com.goldze.mvvmhabit.ui.tab_bar.activity.TabBarActivity;
import com.release.alert.Alert;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class RegisterViewModel extends BaseViewModel<DemoRepository> {
    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> password = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> passwordagain = new ObservableField<>("");
    //性别的绑定
    public ObservableField<String> sex = new ObservableField<>("");
    //年龄的绑定
    public ObservableField<String> age = new ObservableField<>();
    //身高绑定
    public ObservableField<String> height = new ObservableField<>();
    //体重绑定
    public ObservableField<String> weight = new ObservableField<>();
    public UIChangeObservable uc = new RegisterViewModel.UIChangeObservable();

    public RegisterViewModel(@NonNull Application application, DemoRepository model) {
        super(application, model);
    }

    public class UIChangeObservable {
        //选择性别观察者
        public SingleLiveEvent<Boolean> sex = new SingleLiveEvent<>();
    }

    //点击选择性别，UI变化
    public BindingCommand chooseSexOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.sex.setValue(true);
        }
    });
    public BindingCommand navigationBackCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            onBackPressed();
        }
    });

    public BindingCommand registerOnClickCommand = new BindingCommand(() -> {
        if (userName.get() == null || password.get() == null || sex.get() == null || age.get() == null || height.get() == null || weight.get() == null) {
            ToastUtils.showShort("输入有空,请检查");
            return;
        }
        User user = new User(userName.get(), password.get(), sex.get().equals("男") ? 0 : 1, Integer.parseInt(age.get()),
                Double.parseDouble(height.get()), Integer.parseInt(weight.get()));

        model.register(user)
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe((Consumer<Disposable>) disposable -> showDialog("注册中")).subscribe(new DisposableObserver<BaseResponse<Integer>>() {
                    @Override
                    public void onNext(BaseResponse<Integer> userBaseResponse) {
                        if (userBaseResponse.getResult() == 1) {
                            ToastUtils.showShort("注册成功");
                            model.saveUserName(userName.get());
                            model.savePassword(password.get());
                            model.saveSex(user.getSex() == 0 ? "男" : "女");
                            model.saveAge(String.valueOf(user.getAge()));
                            model.saveHeight(String.valueOf(user.getHeight()));
                            model.saveWeight(String.valueOf(user.getWeight()));
                            startActivity(TabBarActivity.class);
                            finish();
                        } else {
                            ToastUtils.showShort("该用户名注册过了");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("网络错误,请检查");
                        dismissDialog();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        dismissDialog();
                    }
                });
    });
}
