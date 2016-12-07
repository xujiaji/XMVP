package io.xujiaji.sample.presenter;

import android.app.Activity;

import java.io.File;

import io.xujiaji.sample.contract.CodeContract;
import io.xujiaji.sample.model.DataFill;
import io.xujiaji.sample.util.FileHelper;
import io.xujiaji.xmvp.presenters.BasePresenter;

/**
 * Created by jiana on 16-11-21.
 */

public class CodePresenter extends BasePresenter<CodeContract.View> implements CodeContract.Presenter {
    public CodePresenter(CodeContract.View view) {
        super(view);
    }

    @Override
    public void readCodeByFile(Activity activity, File file) {
        view.showLoadCode();
        DataFill.readCode(activity, file, new FileHelper.Listener<String>() {
            @Override
            public void success(String s) {
                view.showCode(s);
            }
        });
    }
}
