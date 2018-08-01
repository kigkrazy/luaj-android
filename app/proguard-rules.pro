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

## normal
# 指定压缩级别
-optimizationpasses 5
# 不跳过非公共的库的类成员
-dontskipnonpubliclibraryclassmembers
# 混淆时采用的算法(google推荐，一般不改变)
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
# 把混淆类中的方法名也混淆了
-useuniqueclassmembernames
# 优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification
# 不做预检验,preverify是proguard的四个步骤之一,Android不需要preverify,去掉这一步可以加快混淆速度
-dontpreverify
# 将文件来源重命名为“SourceFile”字符串
-renamesourcefileattribute SourceFile
# 保留行号
-keepattributes SourceFile,LineNumberTable
-keep class * implements android.os.Parcelable {public static final android.os.Parcelable$Creator *;}
# 注解
-keepattributes *Annotation*
# 异常
-keepattributes Exceptions
# 泛型
-keepattributes Signature
# 反射
-keepattributes EnclosingMethod
## normal

#--------------------------------------------------------------------------------------------
## Mine proguard rules(需要根据项目更改)
# Keep the bean_data in project(保留项目中需要gson转换的数据基类)
-keep class com.skyzone.netdemomvp.data.** { *; }


#--------------------------------------------------------------------------------------------
# 第三方库

## qmui [version 1.1.5]
-keep class com.qmuiteam.qmui.** { *; }
-dontwarn com.qmuiteam.qmui.widget.**
## qmui

## retrofit2[version 2.1.0]
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
## retrofit2

## rx[version_rx_java 1.1.8,version_rx_android 1.2.1]
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {long producerIndex;long consumerIndex;}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {rx.internal.util.atomic.LinkedQueueNode producerNode;}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {rx.internal.util.atomic.LinkedQueueNode consumerNode;}
## rx

## glide[version 3.7.0]
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {  **[] $VALUES;  public *;}
## glide

## okhttp3[version_logging-interceptor 3.3.1]
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

-keep class okio.** { *; }
-keep interface okio.** { *; }
-dontwarn okio.**
## okhttp3

## butterknife[version 8.2.1]
-keep class butterknife.*
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }
-keep class **$$ViewBinder { *; }
## butterknife

## gson[version 2.8.0]
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
## gson

## javax
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-dontwarn javax.annotation.concurrent.GuardedBy
## javax

## xposed
-dontwarn de.robv.**
# 修改为自己的包名
-keep class com.reizx.xcore.XposedEntry # 修改为自己的目标类名
-keepnames class com.reizx.xcore.XposedEntry # 修改为自己的目标类名
-keep class de.robv.android.xposed.**{*;}
-keepnames class de.robv.android.xposed.**
-keep class de.robv.android.xposed.IXposedHookLoadPackage
-keepnames class de.robv.android.xposed.IXposedHookLoadPackage
#保持 IXposedHookLoadPackage 的实现类
-keep class * implements de.robv.android.xposed.IXposedHookLoadPackage {
  public void *(de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam);
}
#保持 IXposedHookInitPackageResources 的实现类
-keep class * implements de.robv.android.xposed.IXposedHookInitPackageResources {
  public void *(de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam);
}
## xposed

## logback
-keep class ch.qos.** { *; }
-dontwarn ch.qos.**
-keep class org.slf4j.** { *; }
-dontwarn org.slf4j.**
-keepattributes *Annotation*
## logback

## logger
-keep class com.orhanobut.logger.**
-keep class com.orhanobut.logger.** { *; }
## logger