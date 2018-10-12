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
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.xujiaji.xmvp.presenters.XBasePresenter;
import io.xujiaji.xmvp.utils.GenericHelper;
import io.xujiaji.xmvp.view.interfaces.XFragViewCycle;

/**
 *
 * 项目中Fragment的基类 <br /> base Fragment class <br />
 * 部分代码参照(reference)：https://github.com/xmagicj/LazyFragment/blob/master/app/src/main/java/com/xmagicj/android/lazyfragment/BaseFragment.java
 */
public abstract class XBaseFragment<T extends XBasePresenter> extends Fragment implements XFragViewCycle<T> {

    protected T presenter;

    private View rootView;

    /**
     * Fragment是否可见状态 <br /> whether the Fragment is visible
     */
    private boolean isFragmentVisible;

    /**
     * Layout已经初始化完成 <br /> Layout has been initialized
     */
    private boolean isPrepared;

    /**
     * 是否第一次加载       <br /> whether first load
     */
    private boolean isFirstLoad = true;

    /**
     * <pre>
     * 忽略isFirstLoad的值，强制刷新数据，但仍要Visible & Prepared
     * 一般用于PagerAdapter需要刷新各个子Fragment的场景
     * 不要new 新的 PagerAdapter 而采取reset数据的方式
     * 所以要求Fragment重新走initData方法
     * 故使用 {@link #setForceLoad(boolean)}来让Fragment下次执行initData
     * </pre>
     *
     * force load
     * <pre>
     *     ignore isFirstLoad value, but still keep Visible & Prepared.
     *     use this when PagerAdapter need refresh multiple Fragment.
     *
     * </pre>
     */
    private boolean forceLoad = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            presenter = GenericHelper.newPresenter(this);
            if (presenter != null) {
                presenter.start();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        onPresenterCircle(presenter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        onBeforeCreateCircle();
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            onBundleHandle(savedInstanceState);
        }

        Bundle bundle = getArguments();
        if (bundle != null && bundle.size() > 0) {
            onArgumentsHandle(bundle);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() == 0) {
            rootView = inflater.inflate(layoutId(), container, false);
        } else {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }
        isFirstLoad = true;
        isPrepared = true;
        if (!isInViewPager()) {
            isFragmentVisible = true;
        }
        onInitCircle();
        onListenerCircle();
        onInit();
        onListener();
        lazyLoad();
        return rootView;
    }

    /***
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint <br /> If used with ViewPager, the call to setUserVisibleHint
     * @param isVisibleToUser 是否显示出来了
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged. 此时您因该让 {@link #isInViewPager()} 返回false <br />
     * If use by FragmentTransaction show or hide, the call to onHiddenChanged. now you need return false in {@link #isInViewPager()}
     *
     * @param hidden hidden True if the fragment is now hidden, false if it is not
     * visible.
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onInvisible();
        } else {
            onVisible();
        }
    }

    @Override
    public void onVisible() {
        isFragmentVisible = true;
        lazyLoad();
    }

    @Override
    public void onInvisible() {
        isFragmentVisible = false;
    }

    @Override
    public void onBeforeCreateCircle() { }

    @Override
    public void onPresenterCircle(T presenter) {

    }

    @Override
    public void onBundleHandle(@NonNull Bundle savedInstanceState) { }

    @Override
    public void onArgumentsHandle(@NonNull Bundle bundle) { }

    @Override
    public void onListenerCircle() { }

    @Override
    public void onLazyLoad() { }

    @Override
    public void onInitCircle() { }

    @Override
    public int layoutId() { return 0; }

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
    protected int getLayoutId() {
        return 0;
    }

    public View getRootView() {
        return this.rootView;
    }

    private void lazyLoad() {
        if (isPrepared() && isFragmentVisible()) {
            if (isForceLoad() || isFirstLoad()) {
                forceLoad = false;
                isFirstLoad = false;
                onLazyLoad();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isPrepared = false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (presenter != null) {
            presenter.end();
        }
    }

    @Override
    public void setForceLoad(boolean forceLoad) {
        this.forceLoad = forceLoad;
    }

    @Override
    public boolean isForceLoad() {
        return forceLoad;
    }

    @Override
    public boolean isPrepared() {
        return isPrepared;
    }

    @Override
    public boolean isFirstLoad() {
        return isFirstLoad;
    }

    @Override
    public boolean isFragmentVisible() {
        return isFragmentVisible;
    }

    @Override
    public boolean isInViewPager() {
        return true;
    }
}
