package ejb_Handler;

import java.util.*;
import javax.naming.*;

import _EJB.Database_bean;
import _EJB.Database_beanRemote;

//import com.logic.*;
import javax.ejb.*;
 
public class DatabaseHandler {
	public Database_beanRemote GetBean() {       
	    Database_beanRemote bean = null;
		try{   
			System.out.println("Hello From Java!");
		    Properties props = new Properties();
	        props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
	        InitialContext context = new InitialContext(props);
 
	        String appName = "";        	 
	        String moduleName = "AddressBook_EJB";
	        String distinctName = "";        	 
	        String beanName = Database_bean.class.getSimpleName();        	 
	        String interfaceName = Database_beanRemote.class.getName();
	        String name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + beanName + "!" + interfaceName;
	        System.out.println(name);
	        
	         bean = (Database_beanRemote)context.lookup(name);
	        
		}catch(Exception e){
			e.printStackTrace();
		}
		return bean;
	}
}