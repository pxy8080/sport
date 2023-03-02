package com.goldze.mvvmhabit.ui.login;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.goldze.mvvmhabit.data.DemoRepository;
import com.goldze.mvvmhabit.entity.DemoEntity;
import com.goldze.mvvmhabit.entity.Result;
import com.goldze.mvvmhabit.entity.User;
import com.goldze.mvvmhabit.ui.main.DemoActivity;
import com.goldze.mvvmhabit.ui.network.NetWorkItemViewModel;
import com.goldze.mvvmhabit.ui.network.NetWorkViewModel;
import com.goldze.mvvmhabit.ui.tab_bar.activity.TabBarActivity;
import com.goldze.mvvmhabit.ui.vp_frg.ViewPagerGroupFragment;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.MaterialDialogUtils;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * Created by goldze on 2017/7/17.
 */

public class LoginViewModel extends BaseViewModel<DemoRepository> {
    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> password = new ObservableField<>("");
    //用户名清除按钮的显示隐藏绑定
    public ObservableInt clearBtnVisibility = new ObservableInt();
    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //密码开关观察者
        public SingleLiveEvent<Boolean> pSwitchEvent = new SingleLiveEvent<>();
    }

    public LoginViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
        //从本地取得数据绑定到View层
        userName.set(model.getUserName());
        password.set(model.getPassword());
    }

    //清除用户名的点击事件, 逻辑从View层转换到ViewModel层
    public BindingCommand clearUserNameOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            userName.set("");
        }
    });
    //密码显示开关  (你可以尝试着狂按这个按钮,会发现它有防多次点击的功能)
    public BindingCommand passwordShowSwitchOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //让观察者的数据改变,逻辑从ViewModel层转到View层，在View层的监听则会被调用
            uc.pSwitchEvent.setValue(uc.pSwitchEvent.getValue() == null || !uc.pSwitchEvent.getValue());
        }
    });

    public BindingCommand registerOnClickCommand=new BindingCommand(new BindingAction() {
        @Override
        public void call() {
//            startActivity();
        }
    });
    //用户名输入框焦点改变的回调事件
    public BindingCommand<Boolean> onFocusChangeCommand = new BindingCommand<>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean hasFocus) {
            if (hasFocus) {
                clearBtnVisibility.set(View.VISIBLE);
            } else {
                clearBtnVisibility.set(View.INVISIBLE);
            }
        }
    });
    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            login();
        }
    });

    /**
     * 网络模拟一个登陆操作
     **/
    private void login() {
        if (TextUtils.isEmpty(userName.get())) {
            ToastUtils.showShort("请输入账号！");
            return;
        }
        if (TextUtils.isEmpty(password.get())) {
            ToastUtils.showShort("请输入密码！");
            return;
        }


        model.login(userName.get(), password.get())
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                }).subscribe(new DisposableObserver<BaseResponse<User>>() {
                    @Override
                    public void onNext(BaseResponse<User> resultBaseResponse) {
                        Log.i("TAG", "onNext: 获取到的结果" + resultBaseResponse);
                        User user = resultBaseResponse.getResult();
                        if (user == null) {
                            ToastUtils.showShort("账号密码错误");
                            userName.set("");
                            password.set("");
                        } else {
                            //保存账号密码
                            model.saveUserName(userName.get());
                            model.savePassword(password.get());
//                            //进入DemoActivity页面
//                            startActivity(DemoActivity.class);
                            startActivity(TabBarActivity.class);
                            //关闭页面
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissDialog();
                        showDialog("网络错误");
                    }

                    @Override
                    public void onComplete() {
                        dismissDialog();
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
