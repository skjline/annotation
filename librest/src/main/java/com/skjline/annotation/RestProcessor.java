package com.skjline.annotation;

import com.skjline.annotation.rest.RequestParams;
import com.skjline.annotation.writer.RequestGenerator;
import com.skjline.annotation.writer.RestInterfaceGenerator;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes({
        "com.skjline.annotation.rest.RequestParams",
        "com.skjline.annotation.rest.ResponseData"})
public class RestProcessor extends AbstractProcessor {
    private static final String REQUEST_PARAMS = "com.skjline.annotation.rest.RequestParams";
    private static final String RESPONSE_DATA = "com.skjline.annotation.rest.ResponseData";

    private Messager messager;
    private Filer filer;

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment environment) {
        super.init(environment);

        messager = environment.getMessager();
        filer = environment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set,
                           RoundEnvironment elements) {

        //get all elements annotated with AwesomeLogger
        List<TypeElement> types = new LinkedList<>();
        types.addAll(ElementFilter.typesIn(elements.getElementsAnnotatedWith(RequestParams.class)));

        for (TypeElement type : types) {
            if (!isValidClass(type)) {
                continue;
            }

            RestInterfaceGenerator generator = new RequestGenerator();
            generator.write(messager, filer, type);
        }

        return true;
    }

    private boolean isValidClass(TypeElement type) {
        if (type.getKind() != ElementKind.CLASS ||
                type.getModifiers().contains(Modifier.PRIVATE) ||
                type.getModifiers().contains(Modifier.ABSTRACT)) {
            messager.printMessage(Diagnostic.Kind.ERROR,
                    type.getSimpleName() + " doesn't meet the auto gen requirement");
            return false;
        }

        return true;
    }

}
