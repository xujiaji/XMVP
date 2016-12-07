package io.xujiaji.sample.model.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.File;

/**
 * Created by jiana on 16-11-21.
 *
 */

public class FileEntity extends AbstractExpandableItem<FileEntity> implements MultiItemEntity {
    private File file;
    private int level;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getItemType() {
        return level;
    }
}
