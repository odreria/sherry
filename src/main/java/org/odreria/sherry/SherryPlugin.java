/*
 * Copyright (c) 2019 Diego Cortés. All rights reserved.
 *
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package org.odreria.sherry;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class SherryPlugin implements Plugin<Project>
{
    @Override
    public void apply(Project project)
    {
        project.getExtensions().create("sherry", SherryExtension.class);
        project.getTasks().create("run", RunJettyTask.class);
        project.getTasks().create("stop", StopJettyTask.class);
        
        project.afterEvaluate( pro -> {
            int port = pro.getExtensions().getByType(SherryExtension.class).getPort();
            RunJettyTask runTask = (RunJettyTask) pro.getTasks().getByName("run");
            if (port != 0)
            {
                runTask.setPort(port);
            }
            
        });
    }
}
