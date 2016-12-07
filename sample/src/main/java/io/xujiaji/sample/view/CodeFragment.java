package io.xujiaji.sample.view;

import android.app.ProgressDialog;
import android.os.Bundle;

import java.io.File;

import butterknife.BindView;
import io.xujiaji.sample.R;
import io.xujiaji.sample.contract.CodeContract;
import io.xujiaji.sample.presenter.CodePresenter;
import io.xujiaji.xmvp.view.base.BaseFragment;
import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;

/**
 * Created by jiana on 16-11-21.
 */

public class CodeFragment extends BaseFragment<CodePresenter> implements CodeContract.View {
    public static final String KEY = "file";
    private ProgressDialog dialog;

    public static CodeFragment newInstance(File file) {
        CodeFragment cf = new CodeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, file);
        cf.setArguments(bundle);
        return cf;
    }

    @BindView(R.id.codeView)
    CodeView codeView;

    @Override
    protected void onInit() {
        super.onInit();
        codeView.setTheme(CodeViewTheme.ANDROIDSTUDIO).fillColor();;
        File file = (File) getArguments().getSerializable(KEY);
        presenter.readCodeByFile(getActivity(), file);
    }

    @Override
    public void showLoadCode() {
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("正在加载数据...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void showCode(String code) {
        codeView.showCode(code);
        codeView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }, 1000);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_code;
    }

}
