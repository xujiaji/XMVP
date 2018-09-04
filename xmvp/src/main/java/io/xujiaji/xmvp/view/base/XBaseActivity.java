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

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.xujiaji.xmvp.presenters.XBasePresenter;
import io.xujiaji.xmvp.utils.GenericHelper;
import io.xujiaji.xmvp.view.interfaces.XViewCycle;

/**
 * 项目中Activity的基类
 */
public abstract class XBaseActivity<T extends XBasePresenter> extends AppCompatActivity implements XViewCycle {
    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        onBeforeCreateCircle();
        super.onCreate(savedInstanceState);
        try{
            presenter = GenericHelper.newPresenter(this);
            if (presenter != null) {
                presenter.start();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        beforeSetContentView();
        if (savedInstanceState != null) {
            onBundleHandle(savedInstanceState);
        }
        if (getContentId() == 0) {
            setContentView(layoutId());
        } else {
            setContentView(getContentId());
        }

        onInitCircle();
        onListenerCircle();

        onInit();
        onListener();

    }

    @Override
    public void onBeforeCreateCircle() {

    }

    @Override
    public void onBundleHandle(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public void onListenerCircle() {

    }

    /**
     * 需要在SetContentView之前做的操作
     * @deprecated 将弃用该方法，需尽快改为使用 {@link #onBeforeCreateCircle}
     */
    protected void beforeSetContentView() {
    }

    /**
     * 在这里面进行初始化
     * @deprecated 将弃用该方法，需尽快改为使用 {@link #onInitCircle()}
     */
    protected void onInit() {}

    /**
     * 这里面写监听事件
     * @deprecated 将弃用该方法，需尽快改为使用 {@link #onListenerCircle()}
     */
    protected void onListener() {}

    /**
     * 获取布局的id
     * @deprecated 将弃用该方法，需尽快改为使用 {@link #layoutId()}
     */
    protected int getContentId() { return 0; }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.end();
        }
    }
}
