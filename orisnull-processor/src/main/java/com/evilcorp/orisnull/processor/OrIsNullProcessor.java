package com.evilcorp.orisnull.processor;

import com.evilcorp.orisnull.generator.template.TemplateSearchServiceGenerator;
import com.evilcorp.orisnull.model.AnnotatedOrIsNullClass;
import com.evilcorp.orisnull.model.AnnotatedOrIsNullSearchMethod;
import com.evilcorp.orisnull.model.OrIsNullClass;
import com.evilcorp.orisnull.model.OrIsNullSearchMethod;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("com.evilcorp.orisnull.annotation.OrIsNullRepository")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class OrIsNullProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement orIsNullAnnotation : annotations) {
            Set<? extends Element> annotatedInterfaces
                    = roundEnv.getElementsAnnotatedWith(orIsNullAnnotation);
            for (Element orIsNullRepository : annotatedInterfaces) {
                OrIsNullClass orIsNullInterface = new AnnotatedOrIsNullClass(orIsNullRepository);

                final List<OrIsNullSearchMethod> methods = orIsNullRepository
                        .getEnclosedElements()
                        .stream()
                        .filter(e -> e.getKind() == ElementKind.METHOD)
                        .map(queryMethodCandidate -> new AnnotatedOrIsNullSearchMethod(
                                queryMethodCandidate, processingEnv.getTypeUtils()
                        ))
                        .collect(Collectors.toList());

                final var searchGenerator = new TemplateSearchServiceGenerator(
                        orIsNullInterface,
                        methods
                );
                searchGenerator.toFiler(processingEnv.getFiler());
            }
        }
        return true;
    }
}
