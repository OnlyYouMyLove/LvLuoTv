package com.smartisan.lvluotv.component;

import com.smartisan.lvluotv.scope.ActivityScoped;
import com.smartisan.lvluotv.module.NewsInfoModule;
import com.smartisan.lvluotv.mvp.view.MainActivity;

import dagger.Component;

    @ActivityScoped
    @Component(modules = NewsInfoModule.class,dependencies =NetTaskComponent.class)
    public interface MainActivityComponent {
        void inject(MainActivity mainActivity);
    }
