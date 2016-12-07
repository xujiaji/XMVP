package io.xujiaji.sample.contract;

import android.app.Activity;

import java.util.List;

import io.xujiaji.sample.model.entity.FileEntity;
import io.xujiaji.xmvp.contracts.Contract;

/**
 * Created by jiana on 16-11-19.
 */

public interface HomeContract {
    interface Presenter extends Contract.BasePresenter{
        void loadData(Activity activity);
    }

    interface View extends Contract.BaseView{
        void loadStart();
        void loadEnd(List<FileEntity> fileEntities);
    }
}
