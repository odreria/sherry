/*
 * Copyright (c) 2019 Diego Cortés. All rights reserved.
 *
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package org.odreria.sherry;

import org.eclipse.jetty.server.Server;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

public class RunJettyTask extends DefaultTask
{
    private int port = 8080;

    @Input
    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    @TaskAction
    void start() throws Exception
    {
       getProject().getLogger().quiet("-------------------------------------------");
       Server jettyServer = ServerFactory.getInstance(getPort());
       jettyServer.start();
       getProject().getLogger().quiet("Sherry is starting Jetty " + Server.getVersion() + " port: " + getPort());
       getProject().getLogger().quiet("Status: " + jettyServer.getServer().getState());
       ServerFactory.getInstance(0).join();
       getProject().getLogger().quiet("-------------------------------------------");
    }
}
