package com.goldze.mvvmhabit.ui.tab_bar.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.goldze.mvvmhabit.data.DemoRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;

public class TabBar4FragmentViewModel extends BaseViewModel<DemoRepository> {
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> sex = new ObservableField<>("");
    public ObservableField<String> age = new ObservableField<>("");
    public ObservableField<String> height = new ObservableField<>("");
    public ObservableField<String> weight = new ObservableField<>("");

    public TabBar4FragmentViewModel(@NonNull Application application, DemoRepository repository) {
        super(application, repository);
        name.set(model.getUserName());
        sex.set(model.getSex());
        age.set(model.getAge());
        height.set(model.getHeight());
        weight.set(model.getWeight());
    }
}
