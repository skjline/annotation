package com.skjline.annotation.writer;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * Created on 6/4/17.
 */

public class RequestGenerator implements RestInterfaceGenerator {

    @Override
    public void write(Messager messager, Filer filer, TypeElement origin) {

        String qName = origin.getQualifiedName().toString();
        final String quaulifyPackage = qName.substring(0, qName.lastIndexOf("."));

        // todo: work this out with javapoet
        // example writing file content
        StringBuilder builder = new StringBuilder()
                .append("package " + quaulifyPackage + ";\n\n")
                .append("public class Generated" + origin.getSimpleName()).append(" {\n\n") // open class
                .append("\tpublic String getMessage() {\n") // open method
                .append("\t\treturn \"").append(origin.getQualifiedName()).append("\";\n") // end return
                .append("\t}\n") // close method
                .append("}\n"); // close class

        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, "Printing: \n" + builder.toString());

        try {

            // todo: follow javapoet model for writing to a file when above task completes
            // writing as a file out to a package and filename (last qualified + .java)
            JavaFileObject source = filer.createSourceFile(quaulifyPackage + ".Generated" + origin.getSimpleName());

            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

