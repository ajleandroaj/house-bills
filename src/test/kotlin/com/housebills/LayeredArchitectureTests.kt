package com.housebills

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import org.springframework.stereotype.Controller

@AnalyzeClasses(packages = ["com.housebills"])
class LayeredArchitectureTests {

    @ArchTest
    val `Domain shouldn't depend on Application` =
        noClasses().that().resideInAPackage("..domain..")
            .should().dependOnClassesThat().resideInAPackage("..application..")

    @ArchTest
    val `No one should access Controllers` =
        noClasses().should().accessClassesThat().areAnnotatedWith(Controller::class.java)

    @ArchTest
    val `Layers Level 1` =
        layeredArchitecture()
            .layer("Application").definedBy("..application..")
            .layer("Domain").definedBy("..domain..")

            .whereLayer("Domain").mayOnlyBeAccessedByLayers("Application")

    @ArchTest
    val `Layers Level 2` =
        layeredArchitecture()
            .layer("Facade").definedBy("..application.facade..")
            .layer("QueryRepository").definedBy("..domain.irepository.query..")
            .layer("CommandRepository").definedBy("..domain.irepository.command..")
            .layer("DomainService").definedBy("..domain.service..")

            .whereLayer("QueryRepository").mayOnlyBeAccessedByLayers("Facade")
            .whereLayer("CommandRepository").mayOnlyBeAccessedByLayers("DomainService")
            .whereLayer("DomainService").mayOnlyBeAccessedByLayers("DomainService", "Facade")

}