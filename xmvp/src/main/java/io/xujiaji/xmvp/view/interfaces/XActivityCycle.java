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

import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * author: xujiaji
 * created on: 2018/9/11 15:05
 * description: 定义Activity View相关周期 <br /> Define Activity View related Cycle
 */
public interface XActivityCycle extends XViewCycle {
    /**
     * 处理上个页面传递过来的数据 <br /> Handle the data passed from the previous page
     */
    void onIntentHandle(@NonNull Intent intent);
}
