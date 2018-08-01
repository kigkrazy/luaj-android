package com.reizx.luaj.di.component;

import com.reizx.luaj.di.module.ServiceModule;
import com.reizx.luaj.di.scope.ServiceScope;

import dagger.Component;

/**
 * Created by kigkrazy on 18-5-12.
 */

@ServiceScope
@Component(dependencies = AppComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
}
