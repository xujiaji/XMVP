package io.xujiaji.sample.presenter;

import android.app.Activity;

import java.util.List;

import io.xujiaji.sample.contract.HomeContract;
import io.xujiaji.sample.model.DataFill;
import io.xujiaji.sample.model.entity.FileEntity;
import io.xujiaji.sample.util.FileHelper;
import io.xujiaji.xmvp.presenters.BasePresenter;

/**
 * Created by jiana on 16-11-19.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    public HomePresenter(HomeContract.View view) {
        super(view);
    }

    @Override
    public void loadData(Activity activity) {
        view.loadStart();
        DataFill.scanFile(activity, new FileHelper.Listener<List<FileEntity>>() {
            @Override
            public void success(List<FileEntity> fileEntities) {
                view.loadEnd(fileEntities);
            }
        });
    }
}
