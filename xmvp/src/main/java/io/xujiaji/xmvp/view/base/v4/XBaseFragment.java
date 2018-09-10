package io.xujiaji.xmvp.view.base.v4;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.xujiaji.xmvp.presenters.XBasePresenter;
import io.xujiaji.xmvp.utils.GenericHelper;
import io.xujiaji.xmvp.view.interfaces.XFragViewCycle;

/**
 *
 * 继承于v4包中的Fragment
 */

public abstract class XBaseFragment<T extends XBasePresenter> extends Fragment implements XFragViewCycle{

    protected T presenter;

    private View rootView;

    /**
     * Fragment是否可见状态
     */
    private boolean isFragmentVisible;

    /**
     * View已经初始化完成
     */
    private boolean isPrepared;

    /**
     * 是否第一次加载
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
     *  如果是与ViewPager一起使用，调用的是setUserVisibleHint
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
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
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
