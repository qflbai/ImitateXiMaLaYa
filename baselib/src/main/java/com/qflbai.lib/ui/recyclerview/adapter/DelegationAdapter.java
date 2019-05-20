package com.qflbai.lib.ui.recyclerview.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: bwx
 * @CreateDate: 2019/5/16 0016--17:14
 * @Version: 1.0
 */
public class DelegationAdapter extends AbsDelegationAdapter {
    private ArrayList dataItems = new ArrayList();
    private ArrayList headerItems = new ArrayList();
    private ArrayList footerItems = new ArrayList();
    private boolean hasConsistItemType;

    public DelegationAdapter(AdapterDelegatesManager adapterDelegatesManager) {
        super(adapterDelegatesManager);
    }

    public DelegationAdapter(boolean hasConsistItemType, AdapterDelegatesManager adapterDelegatesManager) {
        super(adapterDelegatesManager);
        this.hasConsistItemType = hasConsistItemType;
    }

    public int getDataCount() {
        return dataItems.size();
    }

    public int getHeaderCount() {
        return headerItems.size();
    }

    public int getFooterItems() {
        return footerItems.size();
    }

    public void setHeaderItem(Object headerItem) {
        if ((headerItem == null)) {
            return;
        }
        this.headerItems.clear();
        this.headerItems.add(headerItem);
        notifyDataSetChanged();
    }

    public void setHeaderItems(List headerItems) {
        if ((headerItems == null)) {
            return;
        }
        this.headerItems.clear();
        this.headerItems.addAll(headerItems);
        notifyDataSetChanged();
    }

    public void addHeaderItem(Object headerItem) {
        addHeaderItem(getHeaderCount(), headerItem);
    }

    public void addHeaderItem(int position, Object headerItem) {
        if (headerItem == null) {
            return;
        }
        this.headerItems.add(position, headerItem);
        notifyItemRangeInserted(position, 1);
    }

    public void addHeaderItems(List headerItem) {
        if (headerItem == null) {
            return;
        }
        addHeaderItems(getHeaderCount(), headerItem);
    }

    public void addHeaderItems(int position, List headerItem) {
        if (headerItem == null) {
            return;
        }
        this.headerItems.addAll(position, headerItem);
        notifyItemRangeInserted(position, headerItem.size());
    }

    public void setFooterItem(Object footerItems) {
        if ((footerItems == null)) {
            return;
        }
        this.footerItems.clear();
        this.footerItems.add(footerItems);
        notifyDataSetChanged();
    }

    public void setFooterItems(List footerItems) {
        if ((footerItems == null)) {
            return;
        }
        this.footerItems.clear();
        this.footerItems.addAll(footerItems);
        notifyDataSetChanged();
    }

    public void addFooterItem(Object footerItems) {
        addHeaderItem(getFooterItems(), footerItems);
    }

    public void addFooterItem(int position, Object footerItems) {
        if (footerItems == null) {
            return;
        }
        this.footerItems.add(position, footerItems);
        notifyItemRangeInserted(getHeaderCount() + getDataCount() + position, 1);
    }

    public void addFooterItems(List footerItems) {
        if (footerItems == null) {
            return;
        }
        addFooterItems(getFooterItems(), footerItems);
    }

    public void addFooterItems(int position, List footerItems) {
        if (footerItems == null) {
            return;
        }
        this.footerItems.addAll(position, footerItems);
        notifyItemRangeInserted(getHeaderCount() + getDataCount() + position, getFooterItems());
    }

    public void setDataItems(List dataItems) {
        if (dataItems == null) {
            return;
        }
        this.dataItems.clear();
        this.dataItems.addAll(dataItems);
        notifyDataSetChanged();
    }

    public void addDataItem(Object dataItem) {
        addDataItem(getDataCount(), dataItem);
    }

    public void addDataItem(int position, Object dataItem) {
        if (dataItem == null) {
            return;
        }
        dataItems.add(position, dataItem);
        notifyItemRangeInserted(getHeaderCount() + position, 1);
    }

    public void addDataItems(List dataItems) {
        addDataItems(getDataCount(), dataItems);
    }

    public void addDataItems(int position, List dataItems) {
        if (dataItems == null) {
            return;
        }
        this.dataItems.addAll(position, dataItems);
        notifyItemRangeInserted(getHeaderCount() + position, dataItems.size());
    }

    public void moveDataItem(int fromPosition, int toPosition) {
        if (fromPosition > getDataCount() - 1 || toPosition > getDataCount() - 1) {
            return;
        }

        dataItems.add(toPosition, dataItems.remove(fromPosition));
        notifyItemMoved(fromPosition, toPosition);
    }

    public void swapDataItem(int fromPosition, int toPosition) {
        if (fromPosition > getDataCount() - 1 || toPosition > getDataCount() - 1) {
            return;
        }

        dataItems.set(toPosition, dataItems.get(fromPosition));
        notifyItemMoved(fromPosition, toPosition);
    }

    public void removeDataItem(Object dataItem, String tag) {
        if (dataItem == null) {
            return;
        }

        ArrayList indexes = new ArrayList<Integer>();
        for (int i = 0; i < dataItems.size(); i++) {
            Object o = dataItems.get(i);
            if (o instanceof ItemData && dataItem == ((ItemData) o).getData() && tag.equals(((ItemData) o).getTag())) {
                indexes.add(i);
            } else if (dataItem == o) {
                indexes.add(i);
            }
        }

        for (int i = 0; i < indexes.size(); i++) {
            removeDataItemAt(i);
        }
    }

    public void removeDataItemAt(int position) {
        int itemCount = 1;
        dataItems.remove(position);
        notifyItemRangeRemoved(getHeaderCount() + position, itemCount);
    }

    public void updateDataItem(Object dataItem, String tag) {
        if (dataItem == null) {
            return;
        }

        for (int i = 0; i < dataItems.size(); i++) {
            Object item = dataItems.get(i);
            if (item instanceof ItemData && dataItem == ((ItemData) item).getData() && tag.equals(((ItemData) item).getTag())) {
                notifyItemChanged(getHeaderCount() + i);
            } else if (dataItem == item) {
                notifyItemChanged(getHeaderCount() + i);
            }
        }

    }

    @Override
    public Object getItem(int position) {
        if (position < getHeaderCount()) {
            return headerItems.get(position);
        }

        int offsetPosition = position - getHeaderCount();
        if (offsetPosition < getDataCount()) {
            return dataItems.get(offsetPosition);
        }

        offsetPosition -= getDataCount();
        return footerItems.get(offsetPosition);
    }

    @Override
    public int getItemCount() {
        return getHeaderCount() + getDataCount() + getFooterItems();
    }

    public void clearAllData() {
        clearData();
        clearHeader();
        clearFooter();
    }

    public void clearData() {
        dataItems.clear();
    }

    public void clearHeader() {
        headerItems.clear();
    }

    public void clearFooter() {
        footerItems.clear();
    }
}
