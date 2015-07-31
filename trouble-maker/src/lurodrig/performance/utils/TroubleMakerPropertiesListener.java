package lurodrig.performance.utils;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class
 * TroubleMakerPropertiesListener
 *
 */
@WebListener
public class TroubleMakerPropertiesListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public TroubleMakerPropertiesListener() {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			System.out.println("HELLO TROUBLEMAKER!!!");
			ServletContext servletContext = servletContextEvent
					.getServletContext();
			String filePath = servletContext
					.getInitParameter(Constants.TROUBLE_MAKER_PROPERTIES_PATH);
			Properties properties = Commons.getProperties(filePath);
			servletContext.setAttribute(Constants.TROUBLE_MAKER_PROPERTIES,
					properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("GOOD BYE TROUBLEMAKER!!!");
	}

}
