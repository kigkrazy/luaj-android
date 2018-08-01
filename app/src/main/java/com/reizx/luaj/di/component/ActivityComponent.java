package com.reizx.luaj.di.component;

import android.app.Activity;

import com.reizx.luaj.di.module.ActivityModule;
import com.reizx.luaj.di.scope.ActivityScope;
import com.reizx.luaj.view.MainActivity;

import dagger.Component;

/**
 * Created by kigkrazy on 18-5-12.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
    
    void inject(MainActivity mainActivity);
}
