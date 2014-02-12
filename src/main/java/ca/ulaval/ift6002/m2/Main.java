package ca.ulaval.ift6002.m2;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class Main {

    private static final int HTTP_PORT = 8080; // TODO: Should be in .properties

    public static void main(String[] args) {
        System.out.println("Applicated started!");

        Server server = setupServer();

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Server setupServer() {
        Server server = new Server(HTTP_PORT);
        ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");

        ServletHolder jerseyServlet = setupJerseyServlet();
        servletContextHandler.addServlet(jerseyServlet, "/*");

        return server;
    }

    private static ServletHolder setupJerseyServlet() {
        ServletHolder jerseyServletHolder = new ServletHolder(ServletContainer.class);

        jerseyServletHolder.setInitParameter("com.sun.jersey.config.property.resourceConfigClass",
                "com.sun.jersey.api.core.PackagesResourceConfig");
        jerseyServletHolder.setInitParameter("com.sun.jersey.config.property.packages", "ca.ulaval.ift6002.m2");

        return jerseyServletHolder;
    }

}
