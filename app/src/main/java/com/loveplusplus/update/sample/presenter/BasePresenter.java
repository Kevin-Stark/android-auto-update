package com.loveplusplus.update.sample.presenter;

import android.content.Context;
import com.loveplusplus.update.sample.ui.iView.IBaseView;

import rx.Subscription;

/**
 * 基础presenter
 * Created by Kevin on 2016/7/20.
 */

public abstract class BasePresenter<T extends IBaseView> {

    protected Subscription subscription;
    protected Context context;
    protected T iView;

    public BasePresenter(Context context, T iView) {
        this.context = context;
        this.iView = iView;
    }

    public void init() {
        iView.initView();
    }

    public abstract void release();
}
