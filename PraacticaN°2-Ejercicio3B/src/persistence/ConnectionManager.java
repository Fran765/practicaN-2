package persistence;

import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectionManager {

	public static Properties getProperties() throws RuntimeException {

		Properties prop = new Properties();
		try {
			ResourceBundle infoDataBase = ResourceBundle.getBundle("conn");
			prop.setProperty("connection", infoDataBase.getString("connection"));
			prop.setProperty("username", infoDataBase.getString("username"));
			prop.setProperty("password", infoDataBase.getString("password"));
			
		} catch (Exception e1) {
			throw new RuntimeException("Ocurrio un error al leer la configuraci�n desde el archivo");
		}
		return prop;

	}


}
