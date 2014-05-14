package adb;

import object.Singletone;

public class ADBFactory {
	private String osType = System.getProperty("os.name");
	private Singletone singletone;
	public ADBFactory(Singletone singletone){
		this.singletone=singletone;
	}
	public Object getCommand() throws ClassNotFoundException{
		if(osType.equals("Linux"))
			return new LinuxCommander(singletone);
		else
			return new WindowsCommander(singletone);
	}
}
