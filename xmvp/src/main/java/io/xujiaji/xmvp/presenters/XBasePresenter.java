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

package io.xujiaji.xmvp.presenters;

import io.xujiaji.xmvp.contracts.XContract;

/**
 * MVP：Presenter基类
 */

public class XBasePresenter<T extends XContract.View, E extends XContract.Model> {
    protected T view;
    protected E model;

    public void init(Object view, Object model) {
        this.view = (T) view;
        this.model = (E) model;
    }

    /**
     * 当onCreate或onCreateView方法执行完毕将会调用
     */
    public void start() {}

    /**
     * 当onDestroy或onDestroyView方法执行完毕将会调用
     */
    public void end() {
        view = null;
        model = null;
    }

    /**
     * view是否还存在
     */
    public boolean viewIsExist()
    {
        return view != null;
    }
}
