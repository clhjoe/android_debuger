package adb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import object.AtmelInstance;
import object.Singletone;

public class WindowsCommander  extends ADBCommnad implements ADBCommandInterface{

	
	private Runtime rt = Runtime.getRuntime();
	private Process proc;
	private Singletone singletone;

	public WindowsCommander(Singletone singletone){
		super(singletone);
		this.singletone=singletone;
	}

	public String getDeviceList() {
		String list[] = new String[] { singletone.adbpath, "devices" };
		return executeCMD(list,true);
	}

	public boolean setAddr(int lbyte, int hbyte, int rlen) {
		String adbpath = singletone.adbpath;
		String target = singletone.target;
		String list[] = null;
		try {
			list = new String[] { adbpath, "-s", target, "shell",
					"echo " + lbyte + " >" + "/sys/Touch/lbyte" };
			proc = rt.exec(list);
			proc.waitFor();
			list = new String[] { adbpath, "-s", target, "shell",
					"echo " + hbyte + " >" + "/sys/Touch/hbyte" };
			proc = rt.exec(list);
			proc.waitFor();
			list = new String[] { adbpath, "-s", target, "shell",
					"echo " + rlen + " > /sys/Touch/rlen" };
			proc = rt.exec(list);
			proc.waitFor();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean WriteValue(int lbyte, int hbyte, int val) {
		String adbpath = singletone.adbpath;
		String target = singletone.target;
		String list[] = null;
		try {
			list = new String[] { adbpath, "-s", target, "shell",
					"echo " + lbyte + " >" + "/sys/Touch/lbyte" };
			proc = rt.exec(list);
			proc.waitFor();

			list = new String[] { adbpath, "-s", target, "shell",
					"echo " + hbyte + " >" + "/sys/Touch/hbyte" };
			proc = rt.exec(list);
			proc.waitFor();

			list = new String[] { adbpath, "-s", target, "shell",
					"echo " + val + " > /sys/Touch/val" };

			proc = rt.exec(list);
			proc.waitFor();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return true;
	}

	public String readValue(int lbyte, int hbyte, int rlen) {
		setAddr(lbyte, hbyte, rlen);
		String[] list = new String[] { singletone.adbpath, "-s",
				singletone.target, "shell", "cat", "/sys/Touch/val" };
		return executeCMD(list,true);
	}

	
	public String getBatteryInfo() {
		String[] list = new String[] { singletone.adbpath, "-s",
				singletone.target, "shell", "\"dumpsys", "battery\"" };
		return executeCMD(list,true);
	}

	public String getSuspendInfo() {
		String[] list = new String[] { singletone.adbpath, "-s",
				singletone.target, "shell", "cat", "/sys/power/state" };
		return executeCMD(list,true);
	}

	public String getWakelockInfo() {
		String[] list = new String[] { singletone.adbpath, "-s",
				singletone.target, "shell", "cat", "/sys/power/wake_lock" };
		return executeCMD(list,true);
	}

	public String getWakeunlockInfo() {
		String[] list = new String[] { singletone.adbpath, "-s",
				singletone.target, "shell", "cat", "/sys/power/wake_unlock" };
		return executeCMD(list,true);
	}

	public void addLock(String str) {
		String[] list = new String[] { singletone.adbpath, "-s",
				singletone.target, "shell",
				"echo " + str + " >" + "/sys/power/wake_lock" };
		executeCMD(list,false);

	}

	public void addunLock(String str) {
		String[] list = new String[] { singletone.adbpath, "-s",
				singletone.target, "shell",
				"echo " + str + " >" + "/sys/power/wake_unlock" };
		executeCMD(list,false);

	}

	public void earlysuspend() {
		String[] list = new String[] { singletone.adbpath, "-s",
				singletone.target, "shell",
				"echo " + "mem" + " >" + "/sys/power/state" };
		executeCMD(list,false);

	}

	public void lateresume() {
		String[] list = new String[] { singletone.adbpath, "-s",
				singletone.target, "shell",
				"echo " + "on" + " >" + "/sys/power/state" };
		executeCMD(list,false);
		
	}

	@Override
	public boolean writeto(String target, String text) {
		return true;
		
	}

	@Override
	public String getDrag(String targetP) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public String[] getPinmux() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getGPIO() {
        // TODO Auto-generated method stub
        return null;
    }
	
	
}