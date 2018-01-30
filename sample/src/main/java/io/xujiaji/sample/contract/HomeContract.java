package io.xujiaji.sample.contract;

import android.app.Activity;

import java.util.List;

import io.xujiaji.sample.general.RequestListener;
import io.xujiaji.sample.model.entity.FileEntity;
import io.xujiaji.sample.util.FileHelper;
import io.xujiaji.xmvp.contracts.XContract;

/**
 * Created by jiana on 16-11-19.
 */

public interface HomeContract {
    interface Presenter extends XContract.Presenter{
        void loadData(Activity activity);
    }

    interface View extends XContract.View{
        void loadStart();
        void loadEnd(List<FileEntity> fileEntities);
    }

    interface Model extends XContract.Model {
        void scanFile(final Activity activity, final RequestListener<List<FileEntity>> listener);
    }
}
