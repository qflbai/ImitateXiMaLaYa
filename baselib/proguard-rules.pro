# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

########################################################公共############################################
# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5

# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames

# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses

# 指定不去忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers

# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify

# 这句话能够使我们的项目混淆后产生映射文件
# 包含有类名->混淆后类名的映射关系
-verbose
#-printmapping proguardMapping.txt

# 指定混淆是采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*

# 保留Annotation不混淆
-keepattributes *Annotation*

# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable

# 不混淆泛型
-keepattributes Signature

# 保持native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#枚举不混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留Parcelable序列化的类不能被混淆
-keep class * implements android.os.Parcelable{
    public static final android.os.Parcelable$Creator *;
}

# 保留Parcelable序列化的类不能被混淆
-keep class * implements  java.io.Serializable{
    public static final android.os.Parcelable$Creator *;
}

# 保留support下的所有类及其内部类
-keep class android.support.** {*;}

# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

#########################################sdk中不混淆的##############################################
#sdk验证不混淆
-keep class com.suntech.decode.authorization.SDKManager{
 public <methods>;
}
#suntechscanmanage类中的公共方法不混淆
-keep class com.suntech.decode.scan.SuntechScanManage{
   public <methods>;
}
#扫码结果回调不混淆
-keep class com.suntech.decode.scan.listener.OnScanListener{*;}
#扫码结果不混淆
-keep class com.suntech.decode.scan.result.ScanResult{*;}
# 预览view
-keep class com.suntech.decode.camera.view.AutoFitTextureView

-keep class com.suntech.decode.authorization.model.**{*;}
-keep class com.suntech.decode.scan.model.**{*;}
-keep class com.suntech.decode.scan.result.GoodsDetails{*;}
-keep class com.suntech.decode.code.model.**{*;}
-keep class com.suntech.decode.camera.model.**{*;}
#######################用于量子微查
-keep class com.suntech.decode.camera.configuration.**{*;}
-keep class com.qflbai.lib.utils.SystemUtil{*;}

-keep class com.suntech.decode.configuration.**{*;}
-keep class com.suntech.decode.decode.constant.DecodeConstant{*;}
-keep class  com.suntech.decode.decode.info.ScreenInfo{*;}
-keep class com.suntech.decode.decode.ScanManager{*;}
-keep class com.suntech.decode.ui.view.ErrorDialog{*;}
-keep class com.suntech.decode.utils.FileUtil{*;}

#####################################lib#########################################
-keep public class * extends com.qflbai.lib.base.viewmodel.BaseViewModel.**
-keep public class * extends com.qflbai.lib.base.repository.**
-keep class com.qflbai.lib.net.body.**{*;}
-keep class com.qflbai.lib.net.download.modle.**{*;}
#-keep class com.qflbai.lib.net.rxjava.**{*;}
#####################################第三方库#########################################

# Retrofit
# Retain generic type information for use by reflection by converters and adapters.
# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontnote retrofit2.Platform
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
-dontwarn retrofit2.Platform$Java8
-keepattributes Exceptions

# okhttp
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

#rxjava
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# fastjson
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.**{*; }
