package com.smartisan.lvluotv.mvp.model.imp;

import android.arch.lifecycle.Lifecycle;

import com.smartisan.lvluotv.App;
import com.smartisan.lvluotv.BuildConfig;
import com.smartisan.lvluotv.finals.Constant;
import com.smartisan.lvluotv.mvp.model.interfaces.LoadTasksCallBack;
import com.smartisan.lvluotv.mvp.model.interfaces.NetTask;
import com.smartisan.netlibrary.entity.request.NewsInfo;
import com.smartisan.netlibrary.net.service.NewsService;
import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsInfoTask implements NetTask {

    private Disposable mDisposable;

    @Override
    public void execute(LifecycleProvider lifecycleProvider,String type, final LoadTasksCallBack callBack) {

        App.getNetTaskComponent().getRetrofitManager().getRetrofit(Constant.BASEURL).create(NewsService.class)
                .getNewsInfo(type, BuildConfig.NewKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lifecycleProvider.<Long>bindUntilEvent(Lifecycle.Event.ON_DESTROY))
                .subscribe(new Observer<NewsInfo>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        mDisposable= disposable;
                        callBack.OnStart();
                    }

                    @Override
                    public void onNext(NewsInfo newsInfo) {
                        callBack.OnSuccess(newsInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed();
                    }

                    @Override
                    public void onComplete() {
                        callBack.onFinish();
                        mDisposable.dispose();
                    }
                });
    }
}
