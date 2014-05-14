package uidesign.pwm;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.HashMap;
import object.Singletone;



import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.forms.widgets.FormToolkit;

import org.eclipse.swt.widgets.Canvas;

import resource.Strings;
import uidesign.UIInterface;


public class PwmUI implements UIInterface{

	private Text txt_suspendstate;
	private static Table table_wakelock;
	private Table table_unwakelock;
	private Text txt_addlock;
	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());
	private Display display;
	private HashMap<Integer, String> batteryMapping = new HashMap<Integer, String>();
	private Image image;
	private Canvas canvas;
	private static Singletone singletone;
	public static Button btnRefresh;
	private batteryInfo bi;
	protected Shell shell;
	private Text txt_power;
	private Text txt_status;
	private Text txt_health;
	private Text txt_present;
	private Text txt_level;
	private Text txt_scale;
	private Text txt_voltage;
	private Text txt_temperature;
	private Text txt_technology;
	private static Button btnAddLock;
	private static Button btnDelete;
	private Button btnHibernate;
	private Label lblIcon;
	private static Button btnEarlysuspend;
	private static Button btnLateresume;

	public PwmUI(Singletone singletone) {
		PwmUI.singletone = singletone;
	}

	public void run(Composite composite) {
		display = Display.getDefault();
		initialize();
		createContents(composite);
		initialActions();

	}

	private void initialize() {
		batteryMapping.put(0, "Status-battery-000-icon.jpg");
		batteryMapping.put(1, "Status-battery-025-icon.jpg");
		batteryMapping.put(2, "Status-battery-050-icon.jpg");
		batteryMapping.put(3, "Status-battery-075-icon.jpg");
		batteryMapping.put(4, "Status-battery-100-icon.jpg");
		batteryMapping.put(5, "Status-battery-charging-000-icon.jpg");
		batteryMapping.put(6, "Status-battery-charging-025-icon.jpg");
		batteryMapping.put(7, "Status-battery-charging-050-icon.jpg");
		batteryMapping.put(8, "Status-battery-charging-075-icon.jpg");
		batteryMapping.put(9, "Status-battery-charging-100-icon.jpg");
	}

	/**
	 * Create contents of the window.
	 */
	public void createContents(Composite composite) {
		Group grpPowerConfiguration = new Group(composite, SWT.NONE);
		grpPowerConfiguration.setText("Power Management");
		grpPowerConfiguration.setBounds(10, 10, 302, 162);

		Label lblCurrentState = new Label(grpPowerConfiguration, SWT.NONE);
		lblCurrentState.setBounds(10, 50, 104, 18);
		lblCurrentState.setText("Current state:");

		txt_suspendstate = new Text(grpPowerConfiguration, SWT.BORDER);
		txt_suspendstate.setBounds(158, 41, 142, 27);

		btnHibernate = new Button(grpPowerConfiguration, SWT.NONE);
		btnHibernate.setBounds(23, 88, 91, 29);
		btnHibernate.setText("Hibernate");
		btnHibernate.setEnabled(false);
		
		btnEarlysuspend = new Button(grpPowerConfiguration, SWT.NONE);
		btnEarlysuspend.setBounds(196, 88, 104, 29);
		btnEarlysuspend.setText("Earlysuspend");
		btnEarlysuspend.setEnabled(false);
		
		btnLateresume = new Button(grpPowerConfiguration, SWT.NONE);
		btnLateresume.setBounds(196, 123, 104, 29);
		btnLateresume.setText("Lateresume");
		btnLateresume.setEnabled(false);
		
		Group grpWakelock = new Group(composite, SWT.NONE);
		grpWakelock.setText("Wakelock");
		grpWakelock.setBounds(318, 10, 328, 406);

		List list = new List(grpWakelock, SWT.BORDER);
		list.setBounds(142, 52, 1, 2);

		table_wakelock = new Table(grpWakelock, SWT.BORDER | SWT.FULL_SELECTION);
		table_wakelock.setBounds(10, 20, 211, 168);
		table_wakelock.setHeaderVisible(true);
		table_wakelock.setLinesVisible(true);

		TableColumn type = new TableColumn(table_wakelock, SWT.LEFT);
		type.setText("Wakelock list");
		type.setWidth(100);

		btnAddLock = new Button(grpWakelock, SWT.NONE);
		btnAddLock.setBounds(227, 332, 91, 29);
		btnAddLock.setText("Add lock");
		btnAddLock.setEnabled(false);
		
		txt_addlock = new Text(grpWakelock, SWT.BORDER);
		txt_addlock.setBounds(227, 299, 91, 27);

		btnDelete = new Button(grpWakelock, SWT.NONE);
		btnDelete.setBounds(227, 367, 91, 29);
		btnDelete.setText("Delete");
		btnDelete.setEnabled(false);
		
		table_unwakelock = new Table(grpWakelock, SWT.BORDER | SWT.FULL_SELECTION);
		table_unwakelock.setLinesVisible(true);
		table_unwakelock.setHeaderVisible(true);
		table_unwakelock.setBounds(10, 194, 211, 202);
		formToolkit.adapt(table_unwakelock);
		formToolkit.paintBordersFor(table_unwakelock);
		
		TableColumn tblclmnUnwakelockList = new TableColumn(table_unwakelock, SWT.LEFT);
		tblclmnUnwakelockList.setWidth(100);
		tblclmnUnwakelockList.setText("unWakelock list");

		Group grpBatteryInformation = new Group(composite, SWT.NONE);
		grpBatteryInformation.setText("Battery Information");
		grpBatteryInformation.setBounds(10, 178, 302, 238);
	
		
		Label lblAcPowered = new Label(grpBatteryInformation, SWT.NONE);
		lblAcPowered.setBounds(10, 47, 68, 18);
		
		lblAcPowered.setText("Powered");
		
		Label lblStatus = new Label(grpBatteryInformation, SWT.NONE);
		lblStatus.setBounds(10, 80, 54, 18);
	
		lblStatus.setText("Status");
		
		Label lblHealth = new Label(grpBatteryInformation, SWT.NONE);
		lblHealth.setBounds(10, 113, 54, 18);
		lblHealth.setText("Health");
		
		Label lblPresent = new Label(grpBatteryInformation, SWT.NONE);
		lblPresent.setBounds(10, 146, 61, 18);
	
		lblPresent.setText("Present");
		
		Label lblLevel = new Label(grpBatteryInformation, SWT.NONE);
		lblLevel.setBounds(10, 179, 54, 18);
	
		lblLevel.setText("Level");
		
		Label lblScale = new Label(grpBatteryInformation, SWT.NONE);
		lblScale.setBounds(152, 212, 36, 18);
	
		lblScale.setText("Scale");
		
		Label lblVoltage = new Label(grpBatteryInformation, SWT.NONE);
		lblVoltage.setBounds(10, 212, 61, 18);

		lblVoltage.setText("Voltage");
		
		Label lblTemperature = new Label(grpBatteryInformation, SWT.NONE);
		lblTemperature.setBounds(152, 144, 90, 18);
		
		lblTemperature.setText("Temperature");
		
		Label lblTechnology = new Label(grpBatteryInformation, SWT.NONE);
		lblTechnology.setBounds(152, 177, 90, 18);
		lblTechnology.setText("Technology");
		
		txt_power = new Text(grpBatteryInformation, SWT.BORDER);
		txt_power.setBounds(78, 36, 75, 27);
		
		
		txt_status = new Text(grpBatteryInformation, SWT.BORDER);
		txt_status.setBounds(78, 69, 54, 27);

		
		txt_health = new Text(grpBatteryInformation, SWT.BORDER);
		txt_health.setBounds(78, 102, 54, 27);
		
		
		txt_present = new Text(grpBatteryInformation, SWT.BORDER);
		txt_present.setBounds(78, 135, 53, 27);
		
		
		txt_level = new Text(grpBatteryInformation, SWT.BORDER);
		txt_level.setBounds(78, 172, 53, 27);
	
		
		txt_scale = new Text(grpBatteryInformation, SWT.BORDER);
		txt_scale.setBounds(244, 203, 48, 27);
		
		
		txt_voltage = new Text(grpBatteryInformation, SWT.BORDER);
		txt_voltage.setBounds(78, 203, 54, 27);
		
		
		txt_temperature = new Text(grpBatteryInformation, SWT.BORDER);
		txt_temperature.setBounds(244, 135, 48, 27);
	
		
		txt_technology = new Text(grpBatteryInformation, SWT.BORDER);
		txt_technology.setBounds(244, 168, 48, 27);
		
		//canvas= new Canvas(grpBatteryInformation, SWT.NONE);
		//canvas.setBounds(206, 36, 86, 64);
		lblIcon = new Label(grpBatteryInformation, SWT.NONE);
		lblIcon.setBounds(206, 36, 86, 64);
	
		


		btnRefresh = new Button(composite, SWT.NONE);
		btnRefresh.setBounds(652, 29, 74, 29);
		//formToolkit.adapt(btnRefresh, true, true);
		btnRefresh.setText("Refresh");
		btnRefresh.setEnabled(false);

	}

	private void setBattory(int level, boolean isCharging) {
		level = (int) (((float) level / 100) * 4);
		if (isCharging)
			level += 5;
		//image = new Image(display, A.class.getResource(tupian.jpg)"pic/"+ batteryMapping.get(level));
		//lblIcon.setBackgroundImage(image);
	///	lblIcon.setSize(image.getImageData().width, image.getImageData().height);
		
		//canvas.setBackgroundImage(image);
		//canvas.setBackgroundMode(SWT.INHERIT_FORCE);
		//canvas.setSize(image.getImageData().width, image.getImageData().height);
	
	}

	public void initialActions() {
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refresh();
			}
		});
		btnAddLock.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String str=txt_addlock.getText();
				if(!str.equals("")&&str!=null){
					singletone.adb.addLock(str);
					updateWakelockList();
				}
				txt_addlock.setText("");
			}
		});
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem []item=table_wakelock.getSelection();
				for(int i=0;i<table_wakelock.getSelectionCount();i++){
					singletone.adb.addunLock(item[i].getText());
				}
				item=table_unwakelock.getSelection();
				for(int i=0;i<table_unwakelock.getSelectionCount();i++){
					singletone.adb.addLock(item[i].getText());
				}
				updateWakelockList();
			}
		});
		btnEarlysuspend.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				singletone.adb.earlysuspend();
				updatePwm();
			}
		});
		btnLateresume.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				singletone.adb.lateresume();
				updatePwm();
			}
			
		});
		
	}

	

	private void refresh() {
		
		String path=singletone.adbpath;
		if (path == null || path.equals("")
				|| singletone.target.equals(Strings.selectDevice)
				|| singletone.target.equals(""))
			return;
		updateBattery();
		updatePwm();
		updateWakelockList();
		
		
	}
	private void updateBattery(){
		String res[];
		res = singletone.adb.getBatteryInfo().split("\n");
		//if(res==null||res.length<10)return;
		bi = new batteryInfo(res);
		
		setBattory(bi.level, bi.isAC || bi.isUSB);
		if(bi.isAC || bi.isUSB){
			if(bi.isAC)
				txt_power.setText("AC");
			else 
				txt_power.setText("USB");
		}else{
			txt_power.setText("N/A");
		}
		txt_status.setText(Integer.toString(bi.status));
		txt_health.setText(Integer.toString(bi.health));
		txt_present.setText(Boolean.toString(bi.present));
		txt_level.setText(Integer.toString(bi.level));
		txt_scale.setText(Integer.toString(bi.scale));
		txt_voltage.setText(Integer.toString(bi.voltage));
		txt_temperature.setText(Integer.toString(bi.temperature));
		txt_technology.setText(bi.technology);
	}
	private void updateWakelockList(){
		String res[]= singletone.adb.getWakelockInfo().split(" ");
		TableItem item;
		table_wakelock.removeAll();
		if(res!=null)
		for(String s:res){
			if(s!=" "){
				item = new TableItem(table_wakelock, SWT.NONE);
				item.setText(new String[] { s });
			}
		}
		res= singletone.adb.getWakeunlockInfo().split(" ");
		table_unwakelock.removeAll();
		if(res!=null)
		for(String s:res){
			if(s!=" "){
				item = new TableItem(table_unwakelock, SWT.NONE);
				item.setText(new String[] { s });
			}
		}
	}
	private void updatePwm(){
		String []res = singletone.adb.getSuspendInfo().split("\n");
		if(res==null)return;
		txt_suspendstate.setText(res[0]);
	}
	public void enableBtn(){
		btnRefresh.setEnabled(true);
		btnAddLock.setEnabled(true);
		btnDelete.setEnabled(true);
		btnEarlysuspend.setEnabled(true);
		btnLateresume.setEnabled(true);
	}

	@Override
	public void disableBtn() {
		btnRefresh.setEnabled(false);
		btnAddLock.setEnabled(false);
		btnDelete.setEnabled(false);
		btnEarlysuspend.setEnabled(false);
		btnLateresume.setEnabled(false);
		
	}

	@Override
	public void enablePartial() {
		// TODO Auto-generated method stub
		
	}

	
}

class batteryInfo {
	boolean isAC=false;
	boolean isUSB=false;
	int status;
	int health;
	boolean present=false;
	int level;
	int scale;
	int voltage;
	int temperature;
	String technology="";

	public batteryInfo(String[] res) {
		if(res==null||res.length<10)return;
		String[] tmp = res[1].split(":");
		this.isAC = Boolean.parseBoolean(tmp[1].replace(" ", ""));
		tmp = res[2].split(":");
		this.isUSB = Boolean.parseBoolean(tmp[1].replace(" ", ""));
		tmp = res[3].split(":");
		this.status = Integer.parseInt(tmp[1].replace(" ", ""));
		tmp = res[4].split(":");
		this.health = Integer.parseInt(tmp[1].replace(" ", ""));
		tmp = res[5].split(":");
		this.present = Boolean.parseBoolean(tmp[1].replace(" ", ""));
		tmp = res[6].split(":");
		this.level = Integer.parseInt(tmp[1].replace(" ", ""));
		tmp = res[7].split(":");
		this.scale = Integer.parseInt(tmp[1].replace(" ", ""));
		tmp = res[8].split(":");
		this.voltage = Integer.parseInt(tmp[1].replace(" ", ""));
		tmp = res[9].split(":");
		this.temperature = Integer.parseInt(tmp[1].replace(" ", ""));
		tmp = res[10].split(":");
		this.technology = tmp[1].replace(" ", "");
	}

	public batteryInfo(boolean isAC, boolean isUSB, int status, int health,
			boolean present, int level, int scale, int voltage,
			int temperature, String technology) {
		this.isAC = isAC;
		this.isUSB = isUSB;
		this.status = status;
		this.health = health;
		this.present = present;
		this.level = level;
		this.scale = scale;
		this.voltage = voltage;
		this.temperature = temperature;
		this.technology = technology;

	}
}
