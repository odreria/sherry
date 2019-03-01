/*
 * Copyright (c) 2019 Diego Cortés. All rights reserved.
 *
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package org.odreria.sherry;

import java.io.IOException;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

public class RunJettyTask extends DefaultTask
{
    @Input
    private int port = 8080;

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    @TaskAction
    void start() throws IOException
    {
        System.out.println("STARTING JETTY SERVER...........");
        
        System.in.read();
        System.out.println("STOPING JETTY SERVER...........");
    }
}
