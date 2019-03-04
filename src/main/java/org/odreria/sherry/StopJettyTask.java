package org.odreria.sherry;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class StopJettyTask extends DefaultTask
{
    @TaskAction
    public void stop()
    throws Exception
    {
        getProject().getLogger().info("STOPING JETTY SERVER...........");
        
        ServerFactory.getInstance(0).stop();

    }
}
