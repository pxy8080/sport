package com.goldze.mvvmhabit.ui.tab_bar.fragment;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.goldze.mvvmhabit.BR;
import com.goldze.mvvmhabit.R;
import com.goldze.mvvmhabit.data.DemoRepository;
import com.goldze.mvvmhabit.ui.network.NetWorkItemViewModel;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class TabBar3FragmentViewModel extends BaseViewModel<DemoRepository> {
    //给RecyclerView添加ObservableList
    public ObservableList<TabBar3FragmentItemViewModel> observableList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public ItemBinding<TabBar3FragmentItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_video);

    public TabBar3FragmentViewModel(@NonNull Application application, DemoRepository model) {
        super(application, model);
    }

    public void requestVideo() {
        model.getVideo().compose(RxUtils.schedulersTransformer())   //线程调度
                .compose(RxUtils.exceptionTransformer())    // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(this)    //请求与ViewModel周期同步
                .doOnSubscribe((Consumer<Disposable>) disposable -> {
                    showDialog("正在请求...");
                }).subscribe(new DisposableObserver<BaseResponse<List<String>>>() {
                    @Override
                    public void onNext(BaseResponse<List<String>> listBaseResponse) {
                        for (String url : listBaseResponse.getResult()) {
                            TabBar3FragmentItemViewModel tabBar3FragmentItemViewModel = new TabBar3FragmentItemViewModel(TabBar3FragmentViewModel.this, url);
                            observableList.add(tabBar3FragmentItemViewModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("网络错误");
                        dismissDialog();
                    }

                    @Override
                    public void onComplete() {
                        dismissDialog();
                    }
                });

    }
}
