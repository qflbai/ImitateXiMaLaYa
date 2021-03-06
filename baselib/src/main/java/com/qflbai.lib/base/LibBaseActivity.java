package com.qflbai.lib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qflbai.lib.exception.crash.AppCrashHandler;
import com.qflbai.lib.manager.ActivityManage;
import com.qflbai.lib.utils.toast.ToastUtil;


/**
 * @author WenXian Bai
 * @Date: 2018/3/22.
 * @Description: 应用activity基类
 */

public class LibBaseActivity extends AppCompatActivity {
    public String tag = getClaseName();
    /**
     * LayoutInflater 对象
     */
    protected LayoutInflater mInflater;
    /**
     * Activity上下文对象(注意与Application Content区分)
     */
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mInflater = getLayoutInflater();
        // 把当前activity放入栈中
        ActivityManage.getActivityManager().addActivity(this);
        // 捕获异常
        AppCrashHandler.getInstance().init(LibBaseApplication.getAPPContext());

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消Toast显示
        ToastUtil.cancel();
        // 从栈中移除activity
        ActivityManage.getActivityManager().removeActivity(this);
    }

    /**
     * 获取布局
     *
     * @param layoutId
     * @return
     */
    protected View inflateView(int layoutId) {
        return mInflater.inflate(layoutId, null);
    }

    /**
     * 获取布局
     *
     * @param layoutId
     * @return
     */
    protected View inflateView(int layoutId, ViewGroup viewGroup) {
        return mInflater.inflate(layoutId, viewGroup);
    }

    /**
     * 销毁所有activity
     */
    protected void finishAllActivity() {
        ActivityManage.getActivityManager().finishAllActivity();
    }

    /**
     * 退出app
     */
    protected void appExit() {
        ActivityManage.getActivityManager().AppExit(LibBaseApplication.getAPPContext());
    }

    protected void quitLogin(){
    }

    private String getClaseName(){
        return getClass().getSimpleName();
    }
}
