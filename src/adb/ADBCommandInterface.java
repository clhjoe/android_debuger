package adb;

import object.AtmelInstance;
import object.Singletone;

public interface ADBCommandInterface {
	
	
	
   
    public String getDeviceList();
	public String [] getPowerConfig();
	public String [] getMultiTouch();
	public String [] getPalm();
	public boolean setAddr(int lbyte,int hbyte, int rlen);
	public boolean WriteValue( int lbyte,int hbyte,int val);
	String readValue(int lbyte,int hbyte,int rlen);
	//String verifyPath(String str);
	public void Initial();
	public String getBatteryInfo();
	public String getSuspendInfo();
	public String getWakelockInfo();
	public String getWakeunlockInfo();
	public void addLock(String str);
	public void addunLock(String str);
	public void earlysuspend() ;
	public void lateresume();
	public boolean writeto(String target, String text);
	public String getDrag(String targetP);
	public String getPinmux ();
    public String getGPIO();
}
