package com.qflbai.lib.ui.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @Description:
 * @Author: bwx
 * @CreateDate: 2019/5/16 0016--16:56
 * @Version: 1.0
 */
public abstract class AbsDelegationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private AdapterDelegatesManager adapterDelegatesManager;
    public AbsDelegationAdapter(AdapterDelegatesManager adapterDelegatesManager){
        this.adapterDelegatesManager = adapterDelegatesManager;
    }

    public AbsDelegationAdapter addDelegate(AdapterDelegate adapterDelegate,String tag){
        adapterDelegate.tag = tag;
        adapterDelegatesManager.addDelegate(adapterDelegate,tag);
        return this;
    }

    public AbsDelegationAdapter setFallbackDelegate(AdapterDelegate adapterDelegate){
        adapterDelegatesManager.setFallbackDelegate(adapterDelegate);
        return this;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return adapterDelegatesManager.onCreateViewHolder(viewGroup,i);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        adapterDelegatesManager.onBindViewHolder(holder,i,getItem(i));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        onBindViewHolder(holder,position);
        adapterDelegatesManager.onBindViewHolder(holder, position, payloads,getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        return adapterDelegatesManager.getItemViewType(getItem(position),position);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder){
        adapterDelegatesManager.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder holder) {
        return adapterDelegatesManager.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        adapterDelegatesManager.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        adapterDelegatesManager.onViewDetachedFromWindow(holder);
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        adapterDelegatesManager.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        adapterDelegatesManager.onDetachedFromRecyclerView(recyclerView);
    }

    public abstract Object getItem(int position);
}
