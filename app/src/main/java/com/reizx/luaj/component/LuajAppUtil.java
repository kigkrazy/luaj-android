package com.reizx.luaj.component;

import com.blankj.utilcode.util.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class LuajAppUtil {


    /**
     * 获取当前APP信息
     *
     * @param
     * @return
     */
    public static AppUtils.AppInfo getAppInfo() {
        return AppUtils.getAppInfo();
    }

    /**
     * 获取APP信息
     *
     * @param packageName
     * @return
     */
    public static AppUtils.AppInfo getAppInfo(String packageName) {
        return AppUtils.getAppInfo(packageName);
    }


    /**
     * 获取APP信息
     *
     * @param packageName
     * @return
     */
    public static List<String> getAppInfo1(String packageName) {
        List<String> result = new ArrayList<>();
        result.add("xxx");
        result.add("yyy");
        result.add("zzz");
        return result;
    }

    /**
     * 获取APP信息
     *
     * @param packageName
     * @return
     */
    public static String[] getAppInfo2(String packageName) {
        return new String[]{"xxx", "222222"};
    }

    /**
     * 获取APP信息
     *
     * @param packageName
     * @return
     */
    public static int[] getAppInfo3(String packageName) {
        return new int[]{1, 2};
    }

    /**
     * 获取所有APP列表信息
     *
     * @return
     */
    public static List<AppUtils.AppInfo> getAppInfos() {
        return AppUtils.getAppsInfo();
    }
}
