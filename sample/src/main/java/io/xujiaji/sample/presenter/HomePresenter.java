package io.xujiaji.sample.presenter;

import android.app.Activity;

import java.util.List;

import io.xujiaji.sample.contract.HomeContract;
import io.xujiaji.sample.general.RequestListenerProxy;
import io.xujiaji.sample.model.HomeModel;
import io.xujiaji.sample.model.entity.FileEntity;
import io.xujiaji.sample.util.FileHelper;
import io.xujiaji.xmvp.presenters.XBasePresenter;

/**
 * Created by jiana on 16-11-19.
 */

public class HomePresenter extends XBasePresenter<HomeContract.View, HomeModel> implements HomeContract.Presenter {

    @Override
    public void loadData(Activity activity) {
        view.loadStart();
        model.scanFile(activity, new RequestListenerProxy<List<FileEntity>>(this)
        {
            @Override
            public void success(List<FileEntity> fileEntities)
            {
                view.loadEnd(fileEntities);
            }

        });
    }
}
