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

package io.xujiaji.xmvp.view.interfaces;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * author: xujiaji
 * created on: 2018/9/4 10:57
 * description: 定义View相关周期
 */
public interface XFragViewCycle extends XViewCycle {

    /**
     * 处理{@link Fragment#getArguments()} 的值，如果有，才会调用
     * @param bundle
     */
    void onArgumentsHandle(@NonNull Bundle bundle);

    void onVisible();

    void onInvisible();

    void onLazyLoad();

    /**
     * 忽略{@link #isFirstLoad() }的值，强制刷新数据，<br>
     * 但仍要 {@link #isFragmentVisible()} && {@link #isPrepared()}
     */
    void setForceLoad(boolean forceLoad);

    boolean isForceLoad();

    boolean isPrepared();

    boolean isFirstLoad();

    boolean isFragmentVisible();

    boolean isInViewPager();

}
