package com.qflbai.lib.ui.recyclerview.adapter;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: bwx
 * @CreateDate: 2019/5/16 0016--17:05
 * @Version: 1.0
 */
public class AdapterDelegatesManager {
    private boolean hasConsistItemType;
    private SparseArray<String> dataTypeWithTags = new SparseArray<>();
    private SparseArrayCompat<AdapterDelegate<Object, RecyclerView.ViewHolder>> delegates = new SparseArrayCompat<>();
    private AdapterDelegate<Object, RecyclerView.ViewHolder> fallbackDelegate;
    public AdapterDelegatesManager() {
    }

    public AdapterDelegatesManager(boolean hasConsistItemType) {
        this.hasConsistItemType = hasConsistItemType;
    }


    public AdapterDelegatesManager addDelegate(AdapterDelegate adapterDelegate, String tag) {

        ParameterizedType genericSuperclass = (ParameterizedType) adapterDelegate.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class actualTypeArgument = (Class) actualTypeArguments[0];
        String typeWithTag = "";
        if (tag.isEmpty()) {
            typeWithTag = actualTypeArgument.getName();
        } else {
            typeWithTag = actualTypeArgument.getName() + ":" + tag;
        }
        int viewType = 0;
        if (hasConsistItemType) {
            viewType = adapterDelegate.getItemViewType();
        } else {
            viewType = delegates.size();
        }

        delegates.put(viewType, adapterDelegate);
        dataTypeWithTags.put(viewType, typeWithTag);
        return this;
    }

    /**
     * 获取viewHolder
     * @param parent
     * @param viewType
     * @return
     */
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate delegate = getDelegate(viewType);
        return delegate.onCreateViewHolder(parent);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, Object item) {
        int viewType = holder.getItemViewType();
        AdapterDelegate delegate = getDelegate(viewType);

        delegate.onBindViewHolder(holder, position, targetItem(item));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads, Object item) {
        int viewType = holder.getItemViewType();
        AdapterDelegate delegate = getDelegate(viewType);

        delegate.onBindViewHolder(holder, position, payloads, targetItem(item));
    }

    public int getItemViewType(Object item, int position) {
        Class clazz = targetItem(item).getClass();
        String tag = targetTag(item);

        String typeWithTag =typeWithTag(clazz,tag);
        ArrayList<Integer> indexList = indexesOfValue(dataTypeWithTags,typeWithTag);
        for(int i=0;i<indexList.size();i++){
            AdapterDelegate<Object, RecyclerView.ViewHolder> delegate = delegates.get(i);
            if(delegate.tag.equals(tag)&&delegate.isForViewType(targetItem(item),position)){
                if(hasConsistItemType){
                    return delegate.getItemViewType();
                }else {
                    return i;
                }
            }
        }

        if(fallbackDelegate!=null){
            return delegates.size();
        }

        throw new NullPointerException("No AdapterDelegate added that matches position = "+position+" item =" +targetItem(item)+" in data source.");
    }

    public void onViewRecycled(RecyclerView.ViewHolder holder){
        AdapterDelegate<Object, RecyclerView.ViewHolder> delegate = getDelegate(holder.getItemViewType());
        delegate.onViewRecycled(holder);
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder){
        AdapterDelegate<Object, RecyclerView.ViewHolder> delegate = getDelegate(holder.getItemViewType());
        delegate.onViewAttachedToWindow(holder);
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder){
        AdapterDelegate<Object, RecyclerView.ViewHolder> delegate = getDelegate(holder.getItemViewType());
        delegate.onViewDetachedFromWindow(holder);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        for(int i=0;i<delegates.size();i++){
            AdapterDelegate<Object, RecyclerView.ViewHolder> delegate = delegates.get(i);
            delegate.onAttachedToRecyclerView(recyclerView);
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView){
        for(int i=0;i<delegates.size();i++){
            AdapterDelegate<Object, RecyclerView.ViewHolder> delegate = delegates.get(i);
            delegate.onDetachedFromRecyclerView(recyclerView);
        }
    }

    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder){
        AdapterDelegate<Object, RecyclerView.ViewHolder> delegate = getDelegate(holder.getItemViewType());
        return delegate.onFailedToRecycleView(holder);
    }

    public AdapterDelegate<Object,RecyclerView.ViewHolder> getDelegate(int viewType){
        return delegates.get(viewType);
    }

    public Object targetItem(Object data) {
        if (data instanceof ItemData) {
            return ((ItemData) data).getData();
        } else {
            return data;
        }
    }

    public String targetTag(Object data) {
        if (data instanceof ItemData) {
            return ((ItemData) data).getTag();
        } else {
            return "";
        }
    }

    public String typeWithTag(Class clazz, String tag) {
        if (tag.isEmpty()) {
            return clazz.getName();
        } else {
            return clazz.getName() + ":" + tag;
        }
    }

    public ArrayList<Integer> indexesOfValue(SparseArray<String> sparseArray,String value){
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i= 0;i<sparseArray.size();i++){
            if(value==sparseArray.get(i)){
                indexes.add(i);
            }
        }
        return indexes;
    }

    public boolean isHasConsistItemType() {
        return hasConsistItemType;
    }

    public void setHasConsistItemType(boolean hasConsistItemType) {
        this.hasConsistItemType = hasConsistItemType;
    }

    public SparseArray<String> getDataTypeWithTags() {
        return dataTypeWithTags;
    }

    public void setDataTypeWithTags(SparseArray<String> dataTypeWithTags) {
        this.dataTypeWithTags = dataTypeWithTags;
    }

    public SparseArrayCompat<AdapterDelegate<Object, RecyclerView.ViewHolder>> getDelegates() {
        return delegates;
    }

    public void setDelegates(SparseArrayCompat<AdapterDelegate<Object, RecyclerView.ViewHolder>> delegates) {
        this.delegates = delegates;
    }

    public AdapterDelegate<Object, RecyclerView.ViewHolder> getFallbackDelegate() {
        return fallbackDelegate;
    }

    public void setFallbackDelegate(AdapterDelegate<Object, RecyclerView.ViewHolder> fallbackDelegate) {
        this.fallbackDelegate = fallbackDelegate;
    }
}
