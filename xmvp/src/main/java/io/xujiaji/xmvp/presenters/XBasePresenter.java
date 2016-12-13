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
 * Created by jiana on 16-11-4.
 */

public class XBasePresenter<T extends XContract.View, E extends XContract.Model> {
    protected T view;
    protected E model;

    public void init(Object view, Object model) {
        this.view = (T) view;
        this.model = (E) model;
    }
//    public XBasePresenter(T view, E model) {
//        this.view = view;
//        this.model = model;
//    }

    public void start() {}

    public void end() {
        view = null;
        model = null;
    }
}
