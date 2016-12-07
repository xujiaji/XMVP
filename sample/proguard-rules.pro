# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/jiana/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:


-optimizationpasses 7
-dontusemixedcaseclassnames   # 是否使用大小写混合
-dontpreverify           # 混淆时是否做预校验
-verbose                # 混淆时是否记录日志
#忽略警告
-ignorewarning
#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
 #优化  不优化输入的类文件
-dontoptimize
 # 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

#如果项目中用到了webview的复杂操作 ，最好加入
-keepclassmembers class * extends android.webkit.WebViewClient {
     public void *(android.webkit.WebView,java.lang.String,android.graphics.Bitmap);
     public boolean *(android.webkit.WebView,java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebChromeClient {
     public void *(android.webkit.WebView,java.lang.String);
}

#本地方法
-keepclasseswithmembernames class * {
    native <methods>;
}

#枚举不混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class assets.** {*; }

#View的构造函数
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

#注解框架butterknife
# https://github.com/JakeWharton/butterknife
# http://jakewharton.github.io/butterknife/
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}



#序列化值
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

#序列化值
-keep class * implements java.ioSerializable {
    public static final java.ioSerializable *;
}

# 保护注解
-keepattributes *Annotation*
-keep class * extends java.lang.annotation.Annotation {*;}

# 泛型与反射
-keepattributes Signature
-keepattributes EnclosingMethod

-dontwarn io.xujiaji.hnbc.model.entity.**
-keep class io.xujiaji.hnbc.model.entity.** { *; }

#BottomSheet
-dontwarn com.flipboard.bottomsheet.commons.**
-keep class com.flipboard.bottomsheet.commons.** { *; }
-dontwarn com.flipboard.bottomsheet.**
-keep class com.flipboard.bottomsheet.** { *; }

#Glide 图片框架  https://github.com/bumptech/glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
-keep class com.loopj.** { *; }
#LeakCanary内存检测
-keep class org.eclipse.mat.** { *; }
-keep class com.squareup.leakcanary.** { *; }

# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }


#-keep public class * extends android.app.Fragment
#-keep public class * extends android.app.Activity
#-keep public class * extends android.app.Application
#-keep public class * extends android.app.Service
#-keep public class * extends android.content.BroadcastReceiver
#-keep public class * extends android.content.ContentProvider
#-keep public class * extends android.app.backup.BackupAgentHelper
#-keep public class * extends android.preference.Preference
#如果有引用v4包可以添加下面这行
-keep public class * extends android.support.**
#如果引用了v4或者v7包
-dontwarn android.support.**


-keep class com.jpardogo.android.googleprogressbar.** { *; }
-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}

-ignorewarnings

-keepattributes Signature,*Annotation*

# keep BmobSDK
-dontwarn cn.bmob.v3.**
-keep class cn.bmob.v3.** {*;}

# 确保JavaBean不被混淆-否则gson将无法将数据解析成具体对象
-keep class * extends cn.bmob.v3.BmobObject {
    *;
}
-keep class com.example.bmobexample.bean.BankCard{*;}
-keep class com.example.bmobexample.bean.GameScore{*;}
-keep class com.example.bmobexample.bean.MyUser{*;}
-keep class com.example.bmobexample.bean.Person{*;}
-keep class com.example.bmobexample.file.Movie{*;}
-keep class com.example.bmobexample.file.Song{*;}
-keep class com.example.bmobexample.relation.Post{*;}
-keep class com.example.bmobexample.relation.Comment{*;}

# gson
-dontwarn com.google.**
-keep class com.google.gson.** {*;}

# keep BmobPush
-dontwarn  cn.bmob.push.**
-keep class cn.bmob.push.** {*;}

# keep okhttp3、okio
-dontwarn okhttp3.**
-keep class okhttp3.** { *;}
-keep interface okhttp3.** { *; }
-dontwarn okio.**

# keep rx
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

# 如果你需要兼容6.0系统，请不要混淆org.apache.http.legacy.jar
-dontwarn android.net.compatibility.**
-dontwarn android.net.http.**
-dontwarn com.android.internal.http.multipart.**
-dontwarn org.apache.commons.**
-dontwarn org.apache.http.**
-keep class android.net.compatibility.**{*;}
-keep class android.net.http.**{*;}
-keep class com.android.internal.http.multipart.**{*;}
-keep class org.apache.commons.**{*;}
-keep class org.apache.http.**{*;}

-keep class * implements java.io.Serializable {*;}
-keepclassmembers class * implements java.io.Serializable

#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-verbose
-printmapping mapping.txt

#不混淆资源类
-keep class **.R$* {
    *;
}