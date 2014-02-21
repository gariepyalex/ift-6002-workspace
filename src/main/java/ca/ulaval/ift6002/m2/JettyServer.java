package ca.ulaval.ift6002.m2;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class JettyServer {

    private static final int HTTP_PORT = 8080;
    private final Server server;

    public JettyServer() {
        server = new Server(HTTP_PORT);
        ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");

        ServletHolder jerseyServlet = setupJerseyServlet();
        servletContextHandler.addServlet(jerseyServlet, "/*");
    }

    private ServletHolder setupJerseyServlet() {
        ServletHolder jerseyServletHolder = new ServletHolder(ServletContainer.class);

        jerseyServletHolder.setInitParameter("com.sun.jersey.config.property.resourceConfigClass",
                "com.sun.jersey.api.core.PackagesResourceConfig");
        jerseyServletHolder.setInitParameter("com.sun.jersey.config.property.packages", "ca.ulaval.ift6002.m2");

        return jerseyServletHolder;
    }

    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
