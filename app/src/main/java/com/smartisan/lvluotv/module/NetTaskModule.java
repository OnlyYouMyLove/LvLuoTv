package com.smartisan.lvluotv.module;

import com.smartisan.lvluotv.mvp.model.imp.NewsInfoTask;
import com.smartisan.lvluotv.mvp.model.interfaces.NetTask;
import com.smartisan.netlibrary.net.manager.RetrofitManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetTaskModule {
    @Singleton
    @Provides
    public NetTask provideNewsInfoTask(){
        return new NewsInfoTask();
    }

    @Singleton
    @Provides
    public RetrofitManager provideRetrofitManager(){
        return new RetrofitManager();
    }
}
