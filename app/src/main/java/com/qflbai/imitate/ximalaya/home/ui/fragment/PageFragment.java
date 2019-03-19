package com.qflbai.imitate.ximalaya.home.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.qflbai.imitate.ximalaya.R;
import com.qflbai.imitate.ximalaya.home.viewmodel.PageFragmentViewmodel;
import com.qflbai.lib.base.fragment.AbsFragment;


public class PageFragment extends AbsFragment<PageFragmentViewmodel> {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @LayoutRes
    int productLayoutRes;

    public static PageFragment newInstance(@LayoutRes int productLayoutRes) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("productLayoutRes", productLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);


    }

    @Override
    public int getContentResId() {
        return R.layout.fragment_page;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            productLayoutRes = args.getInt("productLayoutRes");
        }
    }

    @Override
    protected Object getStateEventKey() {
        return null;
    }
}
