package com.reizx.luaj.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.reizx.luaj.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kigkrazy on 18-5-12.
 */

@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
