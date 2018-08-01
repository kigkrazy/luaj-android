package com.reizx.luaj.constant;

import android.annotation.SuppressLint;
import android.os.Environment;

import com.reizx.luaj.app.App;

import java.io.File;

/**
 * 常量类
 * Created by kigkrazy on 18-5-11.
 */

public class Constants {
    //todo 此处可以存放一些常量
    //================= PATH ====================
    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";//获取data文件夹
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";//网络缓存文件夹
    public static final String LOG_DIR = PATH_DATA + "/log";//log文件夹
    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + App.getInstance().getPackageName();//sd卡下以包名为名称的文件夹


    //===========================前台测试服务===========================
    public final static String FORGROUND_SERVICE_ACTION = "com.reizx.asf.service.ForegroundService";
    public final static String FORGROUND_SERVICE_TITILE = "前台服务";
    public final static String FORGROUND_SERVICE_CONTENT_TEXT = "正在运行...";
    public final static int FORGROUND_SERVICE_ID = 5;
}
