package io.xujiaji.sample.contract;

import android.app.Activity;

import java.io.File;

import io.xujiaji.xmvp.contracts.Contract;

/**
 * Created by jiana on 16-11-21.
 */

public interface CodeContract {
    interface Presenter extends Contract.Presenter{
        void readCodeByFile(Activity activity, File file);
    }

    interface View extends Contract.View{
        void showLoadCode();
        void showCode(String code);
    }
}
