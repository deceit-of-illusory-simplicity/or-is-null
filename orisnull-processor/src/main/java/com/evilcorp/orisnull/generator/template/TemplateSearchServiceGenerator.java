package com.evilcorp.orisnull.generator.template;

import com.evilcorp.orisnull.model.OrIsNullClass;
import com.evilcorp.orisnull.model.OrIsNullSearchMethod;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TemplateSearchServiceGenerator {

    private final OrIsNullClass orIsNullSearchInterface;
    private final List<OrIsNullSearchMethod> methods;
    private final Template temp;

    public TemplateSearchServiceGenerator(
            OrIsNullClass orIsNullSearchInterface
            , List<OrIsNullSearchMethod> methods
    ) {
        this.orIsNullSearchInterface = orIsNullSearchInterface;
        this.methods = methods;
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        try {
            cfg.setClassForTemplateLoading(this.getClass(), "/");
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            temp = cfg.getTemplate("service.ftpl");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void toFiler(Filer filer) {
            Map<String, Object> root = new HashMap<>();
            root.put("packageName", orIsNullSearchInterface.packageName());
            root.put("klass", new FreeKlass(orIsNullSearchInterface));
            root.put("methods", methods.stream().map(FreeMethod::new)
                    .collect(Collectors.toList()));
        try {
            final JavaFileObject sourceFile = filer.createSourceFile(orIsNullSearchInterface.name() + "Impl");
            Writer out = new OutputStreamWriter(sourceFile.openOutputStream());
            temp.process(root, out);
            out.close();
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
