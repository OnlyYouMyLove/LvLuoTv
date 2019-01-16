package com.smartisan.lvluotv.component;

import com.smartisan.lvluotv.module.NetTaskModule;
import com.smartisan.lvluotv.mvp.model.interfaces.NetTask;
import com.smartisan.netlibrary.net.manager.RetrofitManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NetTaskModule.class)
public interface NetTaskComponent {
    NetTask getNetTask();
    RetrofitManager getRetrofitManager();
}
