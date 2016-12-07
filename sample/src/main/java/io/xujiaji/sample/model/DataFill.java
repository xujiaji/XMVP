package io.xujiaji.sample.model;

import android.app.Activity;

import java.io.File;
import java.util.List;

import io.xujiaji.sample.model.entity.FileEntity;
import io.xujiaji.sample.util.FileHelper;

/**
 * Created by jiana on 16-11-19.
 * 数据填充
 */

public class DataFill {

    public static void scanFile(final Activity activity, final FileHelper.Listener<List<FileEntity>> listener) {
        new Thread() {
            @Override
            public void run() {
                final FileHelper fileHelper = new FileHelper();
                fileHelper.unzip(activity, listener);
            }
        }.start();
    }

    public static void readCode(final Activity activity, final File file, final FileHelper.Listener<String> listener) {
        new Thread() {
            @Override
            public void run() {
                FileHelper fileHelper = new FileHelper();
                fileHelper.readText(activity, file, listener);
            }
        }.start();
    }

}
