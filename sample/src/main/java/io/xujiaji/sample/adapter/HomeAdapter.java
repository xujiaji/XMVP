package io.xujiaji.sample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.xujiaji.sample.App;
import io.xujiaji.sample.R;
import io.xujiaji.sample.model.entity.FileEntity;

/**
 * Created by jiana on 16-11-21.
 */

public class HomeAdapter extends BaseMultiItemQuickAdapter<FileEntity, BaseViewHolder> {
    private List<Integer> bgRes;

    public HomeAdapter(List<FileEntity> data) {
        super(data);
        initBgRes();
        for (int i = 0; i < 6; i++) {
            addItemType(i, R.layout.item_layer);
        }
    }

    private void initBgRes() {
        bgRes = new ArrayList<>();
        bgRes.add(R.drawable.item_bg_0);
        bgRes.add(R.drawable.item_bg_1);
        bgRes.add(R.drawable.item_bg_2);
        bgRes.add(R.drawable.item_bg_3);
        bgRes.add(R.drawable.item_bg_4);
        bgRes.add(R.drawable.item_bg_1);
        Collections.shuffle(bgRes);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final FileEntity multiItemEntity) {
        TextView name = baseViewHolder.getView(R.id.name);
        baseViewHolder.addOnClickListener(R.id.name);
        name.setText(multiItemEntity.getFile().getName());
        final int level = multiItemEntity.getLevel();
        if (level < bgRes.size()) {
            name.setBackgroundResource(bgRes.get(level));
        }
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) name.getLayoutParams();
        params.leftMargin = (level + 1) * App.getInstanse().getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        name.setLayoutParams(params);
//        Log.e("convert",  multiItemEntity.getLevel() + "; file = " + multiItemEntity.getFile());
//        expandAll(baseViewHolder.getAdapterPosition(), true);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = baseViewHolder.getAdapterPosition();
                if (multiItemEntity.isExpanded()) {
                    collapse(pos, false);
                } else {
                    expand(pos, false);
                }
                File file = multiItemEntity.getFile();
                if (listener != null && file.isFile()) {
                    listener.open(file);
                }
            }
        });
    }


    private OpenListener listener;
    public void setOpenLister(OpenListener listener) {
        this.listener = listener;
    }
    public interface OpenListener {
        void open(File file);
    }
}
