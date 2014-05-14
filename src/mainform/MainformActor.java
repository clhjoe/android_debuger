package mainform;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

import adb.LinuxCommander;


import object.AtmelInstance;
import object.Singletone;
import resource.Strings;
import uidesign.atmel.AtmelUI;
import uidesign.pwm.PwmUI;

public class MainformActor {
	public static String checkAdbPath(Singletone singletone,String path) {
		String adbPath;
		if (new File(path + File.separator + "adb").exists()) {
			adbPath = path + File.separator + "adb";

		} else if (new File(path + File.separator + "adb.exe").exists()) {
			adbPath = path + File.separator + "adb.exe";
			
		} else {
			adbPath = "";
			path="";
			
		}
		singletone.adbpath=adbPath;
		PropertyLoader.setConfig("adbpath", path);
		return path;
	}
	public static String adbSelector(Singletone singletone,Shell shell){
		DirectoryDialog dialog = new DirectoryDialog(shell);
		dialog.setFilterPath("./");
		
		return checkAdbPath(singletone,dialog.open());
		
	}
	public static String[] refreshDeviceList(Singletone singletone,String path) {
		AtmelUI.btnUpdate.setEnabled(false);
		PwmUI.btnRefresh.setEnabled(false);
		checkAdbPath(singletone,path);
		if(singletone.adbpath==null||singletone.adbpath.equals("")){
			return null;
		}
		String res = null;
		res = singletone.adb.getDeviceList();
		String[] dList;
		ArrayList<String> items = new ArrayList<String>();
		
		if(res!=null)
		for (String s : res.split("\n")) {
			if (!s.startsWith("List of")) {
				dList = s.split("	");
				if (dList.length > 1 && dList[1].equals("device")
						&& !dList[0].equals("")) {
					items.add(dList[0]);
				}
			}
		}
		String[] item;
		if (items.size() > 0) {
			//singletone.UIs.get("atmel").enableBtn();
			singletone.UIs.get("atmel").enablePartial();
			singletone.UIs.get("pwm").enableBtn();
			item = new String[items.size()];
			items.toArray(item);
		} else {
			item = new String[1];
			item[0] = Strings.selectDevice;
		}
		singletone.target=item[0];
		return item;
		
	
	}
}
