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

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * author: xujiaji
 * created on: 2018/9/4 10:57
 * description: 定义View相关周期 <br /> Define View related Cycle
 */
public interface XViewCycle {

    /**
     * 在 super {@link android.app.Activity#onCreate(Bundle)}之前被调用              <br />  will be called before super class {@link android.app.Activity#onCreate(Bundle)} called
     */
    void onBeforeCreateCircle();

    /**
     * 在 super {@link android.app.Activity#onCreate(Bundle)}之前被调用，并且有Bundle <br />  will be called before super class {@link android.app.Activity#onCreate(Bundle)} called
     * @param savedInstanceState 该参数不可能为null                                  <br /> this parameter cannot be null
     */
    void onBundleHandle(@NonNull Bundle savedInstanceState);

    /**
     * 获取布局的id                                <br /> get layout id
     * 在 {@link #onBeforeCreateCircle }之后被调用 <br /> will be called after {@link #onBeforeCreateCircle } called
     * @return xml布局id                         <br /> xml layout id
     */
    int layoutId();

    /**
     *  在这里面进行初始化                    <br /> initialize here
     *  在 {@link #layoutId()} 之后被调用   <br /> will be called after {@link #layoutId()} called
     */
    void onInitCircle();

    /**
     * 这里面写监听事件                       <br /> write listens event here
     * 在 {@link #onInitCircle()} 之后被调用 <br /> will be called after {@link #onInitCircle()} called
     */
    void onListenerCircle();

}
