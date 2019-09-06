/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package GradleEJBAddressBook

import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import spock.lang.Specification

/**
 * A simple unit test for the 'GradleEJBAddressBook.greeting' plugin.
 */
public class GradleEJBAddressBookPluginTest extends Specification {
    def "plugin registers task"() {
        given:
        def project = ProjectBuilder.builder().build()

        when:
        project.plugins.apply("GradleEJBAddressBook.greeting")

        then:
        project.tasks.findByName("greeting") != null
    }
}