package mainform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
	static Properties props;
	private static String configName="config.properties";
	public static void loadProperties() {
		props = new Properties();
		try {
			props.load(new FileInputStream(configName));
			if (!props.containsKey("adbpath")) {
				setConfig("adbpath", "");
			}

		} catch (FileNotFoundException e) {
			try {
				new File(configName).createNewFile();
				setConfig("adbpath", "");
			} catch (IOException e1) {
				// e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getConfig(String key) {
		return props.getProperty(key);
	}
	
	public static void setConfig(String key,String val){
		props.setProperty(key, val);
		try {
			props.store(new FileOutputStream(configName), null);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}

}
