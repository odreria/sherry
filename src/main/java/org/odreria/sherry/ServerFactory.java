package org.odreria.sherry;

import org.eclipse.jetty.server.Server;

public class ServerFactory
{
    private static Server server;
    
    private ServerFactory()
    {
    }

    public static Server getInstance(int port)
    throws Exception
    {
        if (server == null)
        {
            server = new Server(port);
        }
        return server;
    }
}
