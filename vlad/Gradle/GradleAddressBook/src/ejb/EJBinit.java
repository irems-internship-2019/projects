package ejb;

import java.util.Properties;

import javax.naming.InitialContext;

import database.Services;
import database.ServicesRemote;

public class EJBinit {

	public ServicesRemote beanInit() {
		ServicesRemote remote = null;

		try {
			Properties props = new Properties();
			props.put("java.naming.factory.url.pkgs", "org.jboss.ejb.client.naming");
			InitialContext context = new InitialContext(props);
			String appName = "";
			String moduleName = "AddressEJB";
			String distinctName = "";
			String beanName = Services.class.getSimpleName();
			String interfaceName = ServicesRemote.class.getName();
			String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!"
					+ interfaceName;
			remote = (ServicesRemote) context.lookup(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return remote;
	}
}
