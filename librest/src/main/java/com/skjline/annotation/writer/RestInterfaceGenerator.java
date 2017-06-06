package com.skjline.annotation.writer;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.TypeElement;

/**
 * Created on 6/4/17.
 */

public interface RestInterfaceGenerator {
    void write(Messager messager, Filer filer, TypeElement origin);
}
