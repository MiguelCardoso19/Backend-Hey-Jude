package io.codeforall.hackaton;

import org.apache.logging.log4j.web.Log4jServletContextListener;
import org.apache.logging.log4j.web.Log4jWebSupport;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class Log4jWebConfig implements ServletContextListener {

    public static final String LOGGER_BASE_PATH = "WEB-INF/log4j2/log4j2";
    private Log4jServletContextListener listener = new Log4jServletContextListener();


    @Override
    public void contextInitialized(ServletContextEvent event) {
        String activeProfile = event.getServletContext().getInitParameter("spring.profiles.active");

        String loggerPath = LOGGER_BASE_PATH + (activeProfile.equals("prod") ? ".xml" : "-" + activeProfile + ".xml");

        event.getServletContext().setInitParameter(Log4jWebSupport.LOG4J_CONFIG_LOCATION, loggerPath);
        listener.contextInitialized(event);
    }


    @Override
    public void contextDestroyed(ServletContextEvent event) {
        listener.contextDestroyed(event);
    }
}
