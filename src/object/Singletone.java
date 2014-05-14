package object;

import gpioui.GPIOUI;

import java.util.HashMap;

import adb.ADBCommandInterface;
import adb.ADBFactory;

import uidesign.UIInterface;
import uidesign.atmel.AtmelUI;
import uidesign.pwm.PwmUI;
import uipinmux.PinmuxUI;

public class Singletone {
	public String adbpath;
	public String target;
	public ADBCommandInterface adb;
	public HashMap <String,UIInterface>UIs=new HashMap <String,UIInterface>();
	public AtmelInstance atmel=new AtmelInstance();
	public Singletone(String adbpath,String target){
		this.adbpath=adbpath;
		this.target=target;
		try {
			adb=(ADBCommandInterface) new ADBFactory(this).getCommand();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		UIs.put("atmel", new AtmelUI(atmel,this) );
		UIs.put("pwm", new PwmUI(this) );
		UIs.put("pinmux", new PinmuxUI(this) );
		UIs.put("gpio", new GPIOUI(this) );
	}
}
