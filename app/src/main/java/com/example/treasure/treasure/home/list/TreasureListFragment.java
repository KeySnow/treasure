package com.example.treasure.treasure.home.list;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.treasure.R;
import com.example.treasure.treasure.TreasureRepo;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * 列表模式宝藏
 * Created by 93432 on 2016/7/23.
 */
public class TreasureListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TreasureListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = new RecyclerView(container.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.setBackgroundResource(R.drawable.screen_bg);
        return recyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new TreasureListAdapter();
        recyclerView.setAdapter(adapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.addItem(TreasureRepo.getInstance().getTreasure());
            }
        }, 50);
    }
}
