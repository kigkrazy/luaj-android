package com.reizx.luaj.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by kigkrazy on 18-5-12.
 */

@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
