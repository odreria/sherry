/*
 * Copyright (c) 2019 Diego Cortés. All rights reserved.
 *
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package org.odreria.sherry;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;

class SherryPlugin implements Plugin<Project>
{
    void apply(Project project)
    {
        project.extensions.create("sherry", SherryExtension)
        project.tasks.create("run", RunJettyTask)
        project.tasks.create("provision", ProvisionTask)

        project.afterEvaluate { pro ->
            int port = pro.getExtensions().getByType(SherryExtension).getPort()
            RunJettyTask runTask = (RunJettyTask) pro.getTasks().getByName("run")
            if (port != 0)
            {
                runTask.port(port)
            }
        }

        project.tasks.getByName("provision") {
            dependsOn 'build'
        } 
        
        project.tasks.getByName("run") {
            dependsOn 'provision'
        }

        /*
        project.getTasks().withType(RunJettyTask.class).all( task -> {
            
           // task.dependsOn(paths)
        });
        */
    }
}
