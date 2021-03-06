package com.skjline.annotation.rest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 6/3/17.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface RequestParams {
}
