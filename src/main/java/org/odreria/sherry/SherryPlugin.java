/*
 * Copyright (c) 2019 Diego Cortés. All rights reserved.
 *
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package org.odreria.sherry;

import org.gradle.api.Project;
import org.gradle.api.Plugin;

public class SherryPlugin implements Plugin<Project>
{
    @Override
    public void apply(Project project)
    {
        project.getTasks().create("server", RunJettyTask.class);
    }

}
