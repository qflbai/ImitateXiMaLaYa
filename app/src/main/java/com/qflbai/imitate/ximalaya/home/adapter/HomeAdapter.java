package com.qflbai.imitate.ximalaya.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;

/**
 * @author: qflbai
 * @CreateDate: 2019/3/14 11:30
 * @Version: 1.0
 * @description:
 */
public class HomeAdapter extends VirtualLayoutAdapter {
    public HomeAdapter(@NonNull VirtualLayoutManager layoutManager) {
        super(layoutManager);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{

        public HomeViewHolder(View itemView) {
            super(itemView);
        }
    }
}
