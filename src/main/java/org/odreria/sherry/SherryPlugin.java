package org.odreria.sherry;

import org.gradle.api.Project;
import org.gradle.api.Plugin;

public class SherryPlugin implements Plugin<Project>
{
    @Override
    public void apply(Project project)
    {
        project.getTasks().create("hello")
    }

}
