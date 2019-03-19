package com.qflbai.imitate.ximalaya.home.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import com.qflbai.imitate.ximalaya.R;
import com.qflbai.imitate.ximalaya.home.ui.fragment.PageFragment;
import com.qflbai.imitate.ximalaya.home.viewmodel.HomeViewModel;
import com.qflbai.lib.base.activity.AbsActivity;
import com.qflbai.lib.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbsActivity<HomeViewModel> {

    TabLayout tabLayout;
    ViewPager pager;
    List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(new PageFragment(), "line_layout"));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                PageModel pageModel = pageModels.get(position);
                return pageModel.baseFragment;
            }

            @Override
            public int getCount() {
                return pageModels.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return pageModels.get(position).titleRes;
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private class PageModel {
        BaseFragment baseFragment;
        String titleRes;

        PageModel(BaseFragment baseFragment, String titleRes) {
            this.baseFragment = baseFragment;
            this.titleRes = titleRes;
        }


    }
}
