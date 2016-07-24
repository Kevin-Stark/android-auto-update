package com.loveplusplus.update.sample.ui;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loveplusplus.update.sample.R;
import com.loveplusplus.update.sample.autoupdate.AppUtils;
import com.loveplusplus.update.sample.presenter.MainPresenter;
import com.loveplusplus.update.sample.ui.iView.IMainView;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    private MainPresenter presenter;
    private ProgressDialog dialog;
    @Bind(R.id.button1)
    Button btn1;
    @Bind(R.id.button2)
    Button btn2;
    @Bind(R.id.textView1)
    TextView textView;
    @OnClick(R.id.button1)
    public void DialogBtn(View view) {
        presenter.checkForDialog();
    }

    @OnClick(R.id.button2)
    public void NotificationBtn(View view) {
        presenter.checkForNotification();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        presenter = new MainPresenter(this, this);
        presenter.init();
    }

    @Override
    public void showProgressBar() {
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage(getString(R.string.android_auto_update_dialog_checking));
        dialog.show();
    }

    @Override
    public void hideProgressBar() {
        dialog.dismiss();
    }

    @Override
    public void showUpdateDialog(final String content, final String downloadUrl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.android_auto_update_dialog_title);
        builder.setMessage(Html.fromHtml(content))
                .setPositiveButton(R.string.android_auto_update_dialog_btn_download, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        presenter.goToDoalogDownload(downloadUrl);
                    }
                })
                .setNegativeButton(R.string.android_auto_update_dialog_btn_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog dialog = builder.create();
        //点击对话框外面,对话框不消失
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void showNotification(final String content, final String downloadUrl) {
        PendingIntent intent = presenter.goToNotifDownload(downloadUrl);
        int smallIcon = getApplicationInfo().icon;
        Notification notify = new NotificationCompat.Builder(MainActivity.this)
                .setTicker(getString(R.string.android_auto_update_notify_ticker))
                .setContentTitle(getString(R.string.android_auto_update_notify_content))
                .setContentText(content)
                .setSmallIcon(smallIcon)
                .setContentIntent(intent).build();

        notify.flags = android.app.Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notify);
    }


    @Override
    public void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView.setText("当前版本信息: versionName = " + AppUtils.getVersionName(this) + " versionCode = " + AppUtils.getVersionCode(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.release();
    }

}
