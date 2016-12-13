package io.xujiaji.sample.presenter;

import android.app.Activity;

import java.io.File;

import io.xujiaji.sample.contract.CodeContract;
import io.xujiaji.sample.model.CodeModel;
import io.xujiaji.sample.util.FileHelper;
import io.xujiaji.xmvp.presenters.XBasePresenter;

/**
 * Created by jiana on 16-11-21.
 */

public class CodePresenter extends XBasePresenter<CodeContract.View, CodeModel> implements CodeContract.Presenter {

    @Override
    public void readCodeByFile(Activity activity, File file) {
        view.showLoadCode();
        model.readCode(activity, file, new FileHelper.Listener<String>() {
            @Override
            public void success(String s) {
                view.showCode(s);
            }
        });
    }
}
