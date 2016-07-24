package com.loveplusplus.update.sample.ui.iView;

/**
 * Created by Kevin on 7/21/16.
 */
public interface IMainView extends IBaseView {
    void showProgressBar();

    void hideProgressBar();

    void showUpdateDialog(String content,String downloadUrl);

    void showNotification(String content,String downloadUrl);

}
