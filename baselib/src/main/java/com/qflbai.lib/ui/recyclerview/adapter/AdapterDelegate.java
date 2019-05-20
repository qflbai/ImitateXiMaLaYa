package com.qflbai.lib.ui.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @Description:
 * @Author: bwx
 * @CreateDate: 2019/5/16 0016--17:30
 * @Version: 1.0
 */
public abstract class AdapterDelegate<T, VH extends RecyclerView.ViewHolder> {
    public  String tag = "";

    public AdapterDelegate(){}

    public AdapterDelegate(String tag){
        this.tag = tag;
    }

    protected abstract boolean isForViewType(T item,int position);

    protected abstract VH onCreateViewHolder(ViewGroup parent);

    protected abstract void onBindViewHolder(VH holder,int position,T item);

    protected  void onBindViewHolder(VH holder, int position, List payloads,T item){}

    protected void onViewRecycled(VH holder){}

    protected boolean onFailedToRecycleView(VH holder){
        return false;
    }

    protected void onViewAttachedToWindow( VH holder) {
    }

    protected void onViewDetachedFromWindow( VH holder) {
    }

    protected void onAttachedToRecyclerView(RecyclerView recyclerView){}

    protected void onDetachedFromRecyclerView(RecyclerView recyclerView){}

    protected int getItemViewType(){
        return 0;
    }

}
