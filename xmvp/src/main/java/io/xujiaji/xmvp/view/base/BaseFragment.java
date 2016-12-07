/*
 * Copyright 2016 XuJiaji
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.xujiaji.xmvp.view.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.xujiaji.xmvp.presenters.BasePresenter;
import io.xujiaji.xmvp.utils.GenericHelper;

/**
 * 项目中Fragment的基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected T presenter;

    private View rootView;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try{
            presenter = GenericHelper.initPresenter(this);
        }catch (Exception e) {
            e.printStackTrace();
        }
        rootView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        onInit();
        onListener();
        if (presenter != null) {
            presenter.start();
        }
        return rootView;
    }



    /**
     * 添加监听
     */
    protected void onListener(){

    }

    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected void onInit(){}

    public View getRootView() {
        return this.rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.end();
        unbinder.unbind();
    }
}
