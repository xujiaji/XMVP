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

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.xujiaji.xmvp.presenters.XBasePresenter;
import io.xujiaji.xmvp.utils.GenericHelper;
import io.xujiaji.xmvp.view.interfaces.XActivityCycle;

/**
 * 项目中Activity的基类 <br />
 * base Activity class
 */
public abstract class XBaseActivity<T extends XBasePresenter> extends AppCompatActivity implements XActivityCycle<T> {
    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        onBeforeCreateCircle();
        try{
            presenter = GenericHelper.newPresenter(this);
            if (presenter != null) {
                presenter.start();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        onPresenterCircle(presenter);
        super.onCreate(savedInstanceState);

        beforeSetContentView();
        if (savedInstanceState != null) {
            // 页面已被启用，但因内存不足页面被系统销毁过
            // page start when the page was destroyed by the system due to insufficient memory
            onBundleHandle(savedInstanceState);
        } else {
            // 第一次进入页面获取上个页面传递过来的数据
            // handle intent when first enter the page
            Intent intent = getIntent();
            if (intent != null) {
                onIntentHandle(intent);
            }
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

    // 非standard的启动模式，第二次之后不会进入onCreate周期，转而是onNewIntent
    // Non-standard startup mode
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent != null) {
            onIntentHandle(intent);
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
    public void onPresenterCircle(T presenter) {

    }

    /**
     * 处理上个页面传递过来的值 <br />
     * Handle passed the previous page intent when first enter the page
     */
    @Override
    public void onIntentHandle(@NonNull Intent intent) {}

    @Override
    public void onBundleHandle(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public int layoutId() { return 0; }

    @Override
    public void onInitCircle() { }


    @Override
    public void onListenerCircle() {

    }

    /**
     * 需要在SetContentView之前做的操作
     * @deprecated 将弃用该方法，需尽快改为使用 {@link #onBeforeCreateCircle} <br />
     * use {@link #onBeforeCreateCircle()}, now
     */
    protected void beforeSetContentView() {
    }

    /**
     * 在这里面进行初始化
     * @deprecated 将弃用该方法，需尽快改为使用 {@link #onInitCircle()} <br />
     * use {@link #onInitCircle()}, now
     */
    protected void onInit() {}

    /**
     * 这里面写监听事件
     * @deprecated 将弃用该方法，需尽快改为使用 {@link #onListenerCircle()} <br />
     * use {@link #onListenerCircle()}, now
     */
    protected void onListener() {}

    /**
     * 获取布局的id
     * @deprecated 将弃用该方法，需尽快改为使用 {@link #layoutId()} <br />
     * use {@link #layoutId()}, now
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
