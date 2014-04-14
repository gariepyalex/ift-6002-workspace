package ca.ulaval.ift6002.m2;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import ca.ulaval.ift6002.m2.application.rest.filters.EntityManagerContextFilter;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class JettyServer {

    private final Server server;

    public JettyServer(int port) {
        server = new Server(port);
        ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");
        servletContextHandler.addFilter(EntityManagerContextFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void join() {
        try {
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
