package org.odreria.sherry

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.nio.file.Files

class ProvisionTask extends DefaultTask
{
    @TaskAction
    void copyWar()
    {
        def srcWarFile = new File(project.projectDir.canonicalPath + "/build/libs").listFiles().find {
            it.name.endsWith('.war')
        }
        def destWarFile = new File(project.projectDir.canonicalPath + "/build/tmp/", "deploy")

        project.delete {
            delete destWarFile
        }
        
        project.copy {
            from srcWarFile
            into destWarFile
        }
    }
}
