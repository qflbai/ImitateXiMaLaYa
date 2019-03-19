package com.qflbai.imitate.ximalaya.home.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;
import com.qflbai.imitate.ximalaya.home.repository.CommonRepository;
import com.qflbai.lib.base.repository.BaseRepository;
import com.qflbai.lib.base.viewmodel.BaseViewModel;

/**
 * @author: qflbai
 * @CreateDate: 2019/3/14 16:40
 * @Version: 1.0
 * @description:
 */
public class PageFragmentViewmodel extends BaseViewModel<CommonRepository> {
    public PageFragmentViewmodel(@NonNull Application application) {
        super(application);
    }
}
