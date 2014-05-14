package adb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

import object.AtmelInstance;
import object.InfoObject;
import object.ObjectElement;
import object.Singletone;

import mainform.MainForm;

public class LinuxCommander extends ADBCommnad implements ADBCommandInterface{
	private Runtime rt = Runtime.getRuntime();
	private Process proc;
	private Singletone singletone;
	
	public LinuxCommander(Singletone singletone) {
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
				singletone.target, "shell", "dumpsys", "battery" };
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
	public boolean writeto(String targetP, String text) {
		String adbpath = singletone.adbpath;
		String target = singletone.target;
		
		try {
			String[] list = new String[] { adbpath, "-s", target, "shell",
					"echo " + text + " >" + targetP };
			proc = rt.exec(list);
			proc.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return true;
		
	}

	@Override
	public String getDrag(String targetP) {
		String[] list = new String[] { singletone.adbpath, "-s",
				singletone.target, "shell", "cat", targetP };
		return executeCMD(list,true);
	}

    @Override
    public String getPinmux() {
        String[] list = new String[] { singletone.adbpath, "-s",
                singletone.target, "shell", "cat", "/sys/kernel/debug/tegra_pinmux" };
        return executeCMD(list,true);
    }

    @Override
    public String getGPIO() {
        String[] list = new String[] { singletone.adbpath, "-s",
                singletone.target, "shell", "cat", "/sys/kernel/debug/gpio" };
        return executeCMD(list,true);
    }
	
	
}
