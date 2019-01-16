package com.smartisan.lvluotv.mvp.model.interfaces;


import com.smartisan.netlibrary.entity.request.NewsInfo;

public interface LoadTasksCallBack {
    void OnSuccess(NewsInfo newsInfo);
    void OnStart();
    void onFailed();
    void onFinish();
}
