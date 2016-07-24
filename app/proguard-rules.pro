##--- For:android默认 ---
-optimizationpasses 5  # 指定代码的压缩级别
-allowaccessmodification #优化时允许访问并修改有修饰符的类和类的成员
-dontusemixedcaseclassnames  # 是否使用大小写混合
-dontskipnonpubliclibraryclasses  # 是否混淆第三方jar
-dontpreverify  # 混淆时是否做预校验
-verbose    # 混淆时是否记录日志
-ignorewarnings  # 忽略警告，避免打包时某些警告出现
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*  # 混淆时所采用的算法


#不混淆行数,方便debug
-keepattributes SourceFile,LineNumberTable
#不混淆注解
-keepattributes *Annotation*

-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService


# Keep the support library
-keep class android.support.** { *; }
-keep interface android.support.** { *; }

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keep class android.support.v8.renderscript.** { *; }

-keepattributes Signature
-keep class **.R$* {*;}
-ignorewarnings

 -keepattributes InnerClasses

#删除log代码
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

-keep public class com.loveplusplus.update.sample..R$*{
    public static final int *;
}

-keep class com.loveplusplus.update.sample.BuildConfig { *; }
-keep public class * extends android.os.Binder
-dontwarn java.lang.invoke.*

################common###############  
#实体类不参与混淆  
-keep class com.loveplusplus.update.sample.model.data.** { *; } 
-keep class com.loveplusplus.update.sample.model.entity.** { *; }  
-keep class com.loveplusplus.update.sample.ui.iView.** { *; } 
#自定义控件不参与混淆  
-keep class om.loveplusplus.update.sample.view.** { *; } 


# GSON
-keepattributes EnclosingMethod
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# butterknife
-dontwarn butterknife.internal.**
-keep class butterknife.** { *; }
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
           @butterknife.* <fields>;
       }
-keepclasseswithmembernames class * {
           @butterknife.* <methods>;
       }

# okhttp
-keepattributes Signature
-keepattributes Annotation
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

# retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# RxJava:
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


# umeng
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}
-keep class com.umeng.** { *; }
-keep public class * extends com.umeng.**
-keep class com.alimana.**{*;}
-keep class com.u.upd.**{*;}
-keep class com.umeng.update.**{*;}

-keep class io.reactivex.**{*;}
-keep class com.squareup.leakcanary.**{*;}
-keep class com.android.support.**{*;}



