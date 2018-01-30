package io.xujiaji.sample.contract;

import android.app.Activity;

import java.io.File;

import io.xujiaji.sample.general.RequestListener;
import io.xujiaji.sample.util.FileHelper;
import io.xujiaji.xmvp.contracts.XContract;

/**
 * Created by jiana on 16-11-21.
 */

public interface CodeContract {
    interface Presenter extends XContract.Presenter{
        void readCodeByFile(Activity activity, File file);
    }

    interface View extends XContract.View{
        void showLoadCode();
        void showCode(String code);
    }

    interface Model extends XContract.Model {

        void readCode(final Activity activity, final File file, final RequestListener<String> listener);
    }
}
