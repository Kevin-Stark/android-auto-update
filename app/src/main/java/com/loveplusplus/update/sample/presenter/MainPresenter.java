package com.loveplusplus.update.sample.presenter;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.loveplusplus.update.sample.autoupdate.AppUpdateConfig;
import com.loveplusplus.update.sample.autoupdate.AppUtils;
import com.loveplusplus.update.sample.autoupdate.DownloadService;
import com.loveplusplus.update.sample.http.UpdateClient;
import com.loveplusplus.update.sample.model.entity.TestAppUpdate;
import com.loveplusplus.update.sample.ui.iView.IMainView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Kevin on 7/21/16.
 */
public class MainPresenter extends BasePresenter<IMainView> {


    public MainPresenter(Context context, IMainView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }


    public void checkForDialog() {
        checkUpdate(AppUpdateConfig.TYPE_DIALOG);
    }


    public void checkForNotification() {
        checkUpdate(AppUpdateConfig.TYPE_NOTIFICATION);
    }

    private void checkUpdate(final int updateTpye){
        if (context != null) {
            if(updateTpye == AppUpdateConfig.TYPE_DIALOG){
                iView.showProgressBar();
            }
            String version = AppUtils.getVersionName(context);
            Log.d(AppUpdateConfig.UPDATETAG, "当前版本" + version);
            subscription = UpdateClient.getUpdateRetrofitInstance().checkTestAPPUpdate()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TestAppUpdate>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            if(updateTpye == AppUpdateConfig.TYPE_DIALOG){
                                iView.hideProgressBar();
                            }
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(TestAppUpdate appUpdate) {
                            int versionCode = AppUtils.getVersionCode(context);
                            Log.d(AppUpdateConfig.UPDATETAG, "检查版本更新");
                            //更新版本
                            if (appUpdate.getVersionCode() > versionCode) {
                                Log.d(AppUpdateConfig.UPDATETAG, "发现新版本");
                                //显示升级对话框
                                if(updateTpye == AppUpdateConfig.TYPE_DIALOG){
                                    iView.hideProgressBar();
                                    iView.showUpdateDialog(appUpdate.getUpdateMessage(),appUpdate.getUrl());
                                }else{
                                    iView.showNotification(appUpdate.getUpdateMessage(),appUpdate.getUrl());
                                }

                            } else {
                                if(updateTpye == AppUpdateConfig.TYPE_DIALOG){
                                    iView.hideProgressBar();
                                }
                            }
                        }
                    });
        } else {
            Log.d(AppUpdateConfig.UPDATETAG, "自动检查更新失败");
        }
    }


    //开始执行Dialog APP下载更新任务
    public void goToDoalogDownload(String downloadUrl) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(AppUpdateConfig.APK_DOWNLOAD_URL, downloadUrl);
        context.startService(intent);
    }

    //开始执行Notification APP下载更新任务
    public PendingIntent goToNotifDownload(String downloadUrl) {
        Intent myIntent = new Intent(context, DownloadService.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        myIntent.putExtra(AppUpdateConfig.APK_DOWNLOAD_URL, downloadUrl);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
   }
}
