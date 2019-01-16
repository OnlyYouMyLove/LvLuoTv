package com.smartisan.lvluotv.mvp.presenter;

import com.smartisan.lvluotv.mvp.contract.NewsInfoContract;
import com.smartisan.lvluotv.mvp.model.interfaces.LoadTasksCallBack;
import com.smartisan.lvluotv.mvp.model.interfaces.NetTask;
import com.smartisan.netlibrary.entity.request.NewsInfo;
import com.trello.rxlifecycle2.LifecycleProvider;

import javax.inject.Inject;

public class NewsInfoPresenter implements NewsInfoContract.Presenter,LoadTasksCallBack {

    NetTask mNetTask;
    private NewsInfoContract.View mView;

    @Inject
    public NewsInfoPresenter(NewsInfoContract.View view,NetTask netTask) {
        mView = view;
        mNetTask = netTask;
    }

    @Inject
    void setPresenter(){
        mView.setPresenter(this);
    }

    @Override
    public void getNewsInfo(LifecycleProvider lifecycleProvider, String type) {
        mNetTask.execute(lifecycleProvider,type,this);
    }


    @Override
    public void OnSuccess(NewsInfo newsInfo) {
            mView.setNewsInfo(newsInfo);
    }

    @Override
    public void OnStart() {
        mView.showLoading();
    }

    @Override
    public void onFailed() {
        mView.showError();
        mView.hideLoading();
    }

    @Override
    public void onFinish() {
        mView.hideLoading();
    }
}
