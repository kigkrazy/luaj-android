package com.reizx.luaj.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.reizx.luaj.IAndromedaInf;
import com.reizx.luaj.R;
import com.reizx.luaj.constant.Constants;
import com.reizx.luaj.util.LogUtil;

import org.qiyi.video.svg.Andromeda;

/**
 * Created by kigkrazy on 18-5-10.
 */

public class ForegroundService extends Service {
    public final static String TAG = "ForegroundService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d(TAG, "--------->onStartCommand: ");
        //启动前台服务
        setNotification(Constants.FORGROUND_SERVICE_TITILE,
                Constants.FORGROUND_SERVICE_CONTENT_TEXT,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                Constants.FORGROUND_SERVICE_ID);
        //notificationManager.notify(1, notification);
        registerAndromeda();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public void setNotification(String title, String description, int smallIcon, int bigIcon, int forgroundServiceId) {
        // 在API11之后构建Notification的方式
        //NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext()); //获取一个Notification构造器
        builder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), bigIcon)) // 设置下拉列表中的图标(大图标)
                .setContentTitle(title) // 设置下拉列表里的标题
                .setSmallIcon(smallIcon) // 设置状态栏内的小图标
                .setContentText(description) // 设置描述
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间

        //设置广播通知
        builder.setContentIntent(PendingIntent.getBroadcast(this, 1011, new Intent(this, NotificationClickReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT));
        Notification notification = builder.build(); // 获取构建好的Notification
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
        startForeground(forgroundServiceId, notification);
        //notificationManager.notify(1, notification);
    }

    public void registerAndromeda() {
        Andromeda.registerRemoteService(IAndromedaInf.class, new IAndromedaInf.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public void remoteCall() throws RemoteException {
                LogUtil.d("remoteCall success...");
            }
        });
    }

    /**
     * 前台服务广播接收器
     */
    public static class NotificationClickReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtil.d(TAG, "NotificationClickReceiver --------->onReceive: stop service");
            context.stopService(new Intent(context, ForegroundService.class));
        }
    }
}
