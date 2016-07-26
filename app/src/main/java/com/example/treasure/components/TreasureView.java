package com.example.treasure.components;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.treasure.R;
import com.example.treasure.treasure.Treasure;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 宝藏卡片视图
 * Created by 93432 on 2016/7/19.
 */
public class TreasureView extends RelativeLayout{
    public TreasureView(Context context) {
        super(context);
        init();
    }

    public TreasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TreasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //用来显示宝藏title
    @Bind(R.id.tv_treasureTitle)
    TextView tvTitle;
    //用来显示宝藏位置描述
    @Bind(R.id.tv_treasureLocation)
    TextView tvLocation;
    //用来显示宝藏距离
    @Bind(R.id.tv_distance)
    TextView tvDistance;

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_treasure, this, true);
        ButterKnife.bind(this);
    }

    /**
     * 将宝藏数据，绑定到当前视图上（卡片）
     * @param treasure
     */
    public void binTreasure(@NonNull Treasure treasure){
        tvTitle.setText(treasure.getTitle());
        tvLocation.setText(treasure.getLocation());
        tvDistance.setText("0.00KM");
    }
}
