package io.xujiaji.sample.contract;

import android.app.Activity;

import java.io.File;

import io.xujiaji.xmvp.contracts.Contract;

/**
 * Created by jiana on 16-11-21.
 */

public interface CodeContract {
    interface Presenter extends Contract.BasePresenter{
        void readCodeByFile(Activity activity, File file);
    }

    interface View extends Contract.BaseView{
        void showLoadCode();
        void showCode(String code);
    }
}
