package com.qflbai.lib.net.interceptor;

import com.qflbai.lib.utils.log.LogUtil;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author WenXian Bai
 * @Date: 2018/3/14.
 * @Description: Log 拦截器
 */

public class LogInterceptor implements HttpLoggingInterceptor.Logger {
    private String tag = "NetLog";
    @Override
    public void log(String message) {
        LogUtil.d("NetLog", message);
        // 响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")||message.startsWith("--> END GET")) {
            LogUtil.i(tag, "--------------------------------------------------------------------------------------------------------");
            LogUtil.i(tag, "--------------------------------------------------------------------------------------------------------");
        }
    }
}
