package com.reizx.luaj.di.module;

import android.app.Activity;

import com.reizx.luaj.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kigkrazy on 18-5-12.
 */

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity= activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return activity;
    }
}
