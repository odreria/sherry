/*
 * Copyright (c) 2019 Diego Cortés. All rights reserved.
 *
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>.
 */

package org.odreria.sherry;

import java.util.concurrent.Executors;
import org.eclipse.jetty.deploy.DeploymentManager;
import org.eclipse.jetty.deploy.bindings.DebugListenerBinding;
import org.eclipse.jetty.deploy.PropertiesConfigurationManager;
import org.eclipse.jetty.deploy.providers.WebAppProvider;
import org.eclipse.jetty.server.DebugListener;
import org.eclipse.jetty.server.handler.ContextHandlerCollection
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

class RunJettyTask extends DefaultTask
{
    @Input
    int port = 8080;

    @TaskAction
    void start()
    {
        def webappFolder = project.projectDir.canonicalPath + "/build/tmp/deploy";
        
        project.logger.quiet "-------------------------------------------";
        Server jettyServer = ServerFactory.getInstance(port);

        ContextHandlerCollection jettyContexts = new ContextHandlerCollection();
        DeploymentManager deployer = new DeploymentManager();
        DebugListener debug = new DebugListener(System.err, true, true, true);
        jettyServer.addBean(debug);
        deployer.addLifeCycleBinding(new DebugListenerBinding(debug));
        deployer.setContexts(jettyContexts);
        deployer.setContextAttribute(
            "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
            ".*/[^/]*servlet-api-[^/]*\\.jar\$|.*/javax.servlet.jsp.jstl-.*\\.jar\$|.*/org.apache.taglibs.taglibs-standard-impl-.*\\.jar\$");
       
        WebAppProvider webAppProvider = new WebAppProvider();
        webAppProvider.setMonitoredDirName(webappFolder);
        webAppProvider.setDefaultsDescriptor(project.projectDir.canonicalPath + "/build/tmp/etc/webdefault.xml");
        webAppProvider.setScanInterval(1);
        webAppProvider.setExtractWars(true);
        webAppProvider.setConfigurationManager(new PropertiesConfigurationManager());
        deployer.addAppProvider(webAppProvider);
        jettyServer.addBean(deployer);
       
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        def warFile = new File(webappFolder + "/consumerSherry.war");
       
        project.logger.quiet "File War:" + warFile.absolutePath
        
        webapp.setWar(warFile.absolutePath);
       
        jettyServer.setHandler(webapp);
        jettyServer.start();
       
        project.logger.quiet("Groovy: Sherry is starting Jetty " + Server.getVersion() + " port: " + port);
        project.logger.quiet("Status: " + jettyServer.getServer().getState());

        project.logger.quiet "-------------------------------------------";
    }
}
