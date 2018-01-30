package io.xujiaji.sample.general;

/**
 * 通用请求监听
 * Created by jiaji on 2018/1/30.
 */

public interface RequestListener<T>
{
    void requestSuccess(T t);

    void requestErr(String errStr);
}
