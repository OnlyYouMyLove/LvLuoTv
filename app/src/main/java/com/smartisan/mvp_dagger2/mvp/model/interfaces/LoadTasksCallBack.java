package com.smartisan.mvp_dagger2.mvp.model.interfaces;


import com.smartisan.netlibrary.entity.NewsInfo;

public interface LoadTasksCallBack {
    void OnSuccess(NewsInfo newsInfo);
    void OnStart();
    void onFailed();
    void onFinish();
}
