package io.xujiaji.sample.general;

import io.xujiaji.xmvp.presenters.XBasePresenter;

/**
 * Created by jiaji on 2018/1/30.
 */

public abstract class RequestListenerProxy<T> implements RequestListener<T>
{
    private XBasePresenter p;

    public RequestListenerProxy(XBasePresenter p)
    {
        this.p = p;
    }

    @Override
    public void requestSuccess(T t)
    {
        if (p.viewIsExist())
        {
            success(t);
        }
    }

    @Override
    public void requestErr(String errStr)
    {
        if (p.viewIsExist())
        {
            err(errStr);
        }
    }

    public abstract void success(T t);

    public void err(String err)
    {

    }
}
