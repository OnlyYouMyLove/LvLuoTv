package com.smartisan.lvluotv.mvp.contract;

import com.smartisan.lvluotv.mvp.presenter.NewsInfoPresenter;
import com.smartisan.netlibrary.entity.request.NewsInfo;
import com.trello.rxlifecycle2.LifecycleProvider;

public interface NewsInfoContract {
    interface Presenter{
        void getNewsInfo(LifecycleProvider lifecycleProvider, String type);
    }

    interface View {
        void setNewsInfo(NewsInfo newsInfo);
        void showLoading();
        void hideLoading();
        void showError();
        void setPresenter(NewsInfoPresenter presenter);
    }
}

