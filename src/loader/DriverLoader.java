package loader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import config.OracleConfig;

/**
 * Application Lifecycle Listener implementation class DriverLoader
 *
 */
public class DriverLoader implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DriverLoader() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	try{
    		Class.forName(OracleConfig.DRIVER);
    		System.out.println("jdbc driver loading");
    	}catch(ClassNotFoundException e){
    		e.printStackTrace();
    	}
    }
	
}
