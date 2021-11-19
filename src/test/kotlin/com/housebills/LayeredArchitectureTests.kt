package com.housebills

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import javax.persistence.Entity

@AnalyzeClasses(packages = ["com.housebills"])
class LayeredArchitectureTests {

    @ArchTest
    val `No one should access Controllers` =
        noClasses().should().accessClassesThat().areAnnotatedWith(Controller::class.java)

    @ArchTest
    val `Controllers shouldn't access Entiries` =
        noClasses().that().areAnnotatedWith(Controller::class.java)
            .should().accessClassesThat().areAnnotatedWith(Entity::class.java)

    @ArchTest
    val `Only Services should access Repositories` =
        noClasses().that().areNotAnnotatedWith(Service::class.java)
            .should().accessClassesThat().areAnnotatedWith(Repository::class.java)
}