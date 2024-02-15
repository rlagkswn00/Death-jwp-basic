package next;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

public class WebServerLauncher {
    public static void main(String[] args) throws ServletException, LifecycleException {
        String webappDirLocation = "webapp/";
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8080);
        Connector connector = tomcat.getConnector();
        connector.setURIEncoding("UTF-8");
        tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}
