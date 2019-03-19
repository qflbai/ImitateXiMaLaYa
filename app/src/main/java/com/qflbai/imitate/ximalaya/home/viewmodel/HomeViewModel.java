package com.qflbai.imitate.ximalaya.home.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;
import com.qflbai.imitate.ximalaya.home.repository.CommonRepository;
import com.qflbai.lib.base.viewmodel.BaseViewModel;

/**
 * @author: qflbai
 * @CreateDate: 2019/3/13 14:37
 * @Version: 1.0
 * @description:
 */
public class HomeViewModel extends BaseViewModel<CommonRepository> {
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }
}
