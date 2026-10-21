package dev.dmitriirussu.clean.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchitectureTest {

    private static final JavaClasses CLASSES = new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages("dev.dmitriirussu.clean.architecture");

    @Test
    void coreHasNoSpring() {
        noClasses().that().resideInAnyPackage("..application..", "..model..")
                .should().dependOnClassesThat().resideInAPackage("org.springframework..")
                .check(CLASSES);
    }

    @Test
    void coreHasNoJpa() {
        noClasses().that().resideInAnyPackage("..application..", "..model..")
                .should().dependOnClassesThat().resideInAnyPackage("jakarta.persistence..", "org.hibernate..")
                .check(CLASSES);
    }

    @Test
    void modelDoesNotDependOnOuterLayers() {
        noClasses().that().resideInAPackage("..model..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..application..", "..infrastructure..", "..presentation..", "..bootstrap..")
                .check(CLASSES);
    }

    @Test
    void applicationDoesNotDependOnOuterLayers() {
        noClasses().that().resideInAPackage("..application..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..infrastructure..", "..presentation..", "..bootstrap..")
                .check(CLASSES);
    }

    @Test
    void presentationDoesNotDependOnInfrastructure() {
        noClasses().that().resideInAPackage("..presentation..")
                .should().dependOnClassesThat().resideInAPackage("..infrastructure..")
                .check(CLASSES);
    }

    @Test
    void infrastructureDoesNotDependOnPresentation() {
        noClasses().that().resideInAPackage("..infrastructure..")
                .should().dependOnClassesThat().resideInAPackage("..presentation..")
                .check(CLASSES);
    }
}