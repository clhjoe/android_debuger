package uidesign.atmel;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Event;

import mainform.MainForm;

import object.AtmelInstance;
import object.InfoObject;
import object.ObjectElement;
import object.Singletone;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;

import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowLayout;

import adb.LinuxCommander;

import uidesign.UIInterface;


public class AtmelUI implements UIInterface{

	protected static Shell shell;
	private static Text text_family;
	private static Text text_variant;
	private static Text text_version;
	private static Text text_build;
	private static Text text_matrix;
	private static Text text_element;
	private static Table table;
	private static Text txt_enginnerMode;
	private static Text txt_hbyte;
	private static Text txt_lbyte;
	private static Text text_offset;
	private static Text text_value;
	public static Button btnUpdate;
	private static Button btnQueue, btnMultitouch, btnDragflic, btnPalm;
	private static Button btnPowerConfig;
	private static AtmelInstance atmel;
	private Singletone singletone;
	private Group infoGroup;
	private Group enginnerMode;
	private Button btnWrite;
	private Button btnRead;
	
	public AtmelUI(AtmelInstance atmel, Singletone singletone) {
		this.atmel = atmel;
		this.singletone=singletone;
		
	}
	public void run(Composite composite){
		createContents(composite);
		initialActions();
		disableBtn();
	}
	/*
	 * public static void main(String[] args) { try { AtmelUI window = new
	 * AtmelUI();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }//
	 */
	/**
	 * Create contents of the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createContents(Composite composite) {
		// tabfolder.setLayout(new FillLayout(SWT.VERTICAL));
		int infoGroupWid = 727, infoGroupHei = 201, enginnerModeY = infoGroupHei + 20;
		infoGroup = new Group(composite, SWT.NONE);
		infoGroup.setText("Information");
		infoGroup.setBounds(10, 20, infoGroupWid, infoGroupHei);

		enginnerMode = new Group(composite, SWT.NONE);
		enginnerMode.setText("Enginner Mode");
		enginnerMode.setBounds(10, enginnerModeY, 256, 139);

		txt_enginnerMode = new Text(enginnerMode, SWT.BORDER);
		txt_enginnerMode.setEditable(false);
		txt_enginnerMode.setBounds(83, 23, 163, 22);

		txt_hbyte = new Text(enginnerMode, SWT.BORDER);
		txt_hbyte.setEditable(false);
		txt_hbyte.setBounds(83, 52, 37, 22);

		txt_lbyte = new Text(enginnerMode, SWT.BORDER);
		txt_lbyte.setEditable(false);
		txt_lbyte.setBounds(83, 80, 37, 22);

		Label lblHighByte = new Label(enginnerMode, SWT.NONE);
		lblHighByte.setBounds(7, 58, 70, 16);
		lblHighByte.setText("High byte");

		Label lblLowByte = new Label(enginnerMode, SWT.NONE);
		lblLowByte.setBounds(7, 86, 70, 16);
		lblLowByte.setText("Low byte");

		btnWrite = new Button(enginnerMode, SWT.NONE);
		btnWrite.setBounds(140, 48, 50, 26);
		btnWrite.setText("Write");

		btnRead = new Button(enginnerMode, SWT.NONE);
		btnRead.setBounds(196, 48, 50, 26);
		btnRead.setText("Read");

		Label lblOffset = new Label(enginnerMode, SWT.NONE);
		lblOffset.setBounds(140, 86, 50, 16);
		lblOffset.setText("Offset");

		text_offset = new Text(enginnerMode, SWT.BORDER);
		text_offset.setEditable(false);
		text_offset.setBounds(196, 80, 50, 22);

		Label lblValue = new Label(enginnerMode, SWT.NONE);
		lblValue.setBounds(147, 109, 43, 16);
		lblValue.setText("Value");

		text_value = new Text(enginnerMode, SWT.BORDER);
		text_value.setEditable(false);
		text_value.setBounds(196, 103, 50, 22);

		Button btnHex = new Button(enginnerMode, SWT.CHECK);
		btnHex.setBounds(7, 108, 50, 16);
		btnHex.setText("Hex");

		Label lblFamily = new Label(infoGroup, SWT.PUSH);
		lblFamily.setBounds(10, 29, 60, 16);
		lblFamily.setText("Family");

		Label lblVariant = new Label(infoGroup, SWT.PUSH);
		lblVariant.setBounds(10, 52, 60, 16);
		lblVariant.setText("Variant");

		Label lblVersion = new Label(infoGroup, SWT.PUSH);
		lblVersion.setBounds(10, 74, 60, 16);
		lblVersion.setText("Version");

		Label lblBuild = new Label(infoGroup, SWT.PUSH);
		lblBuild.setBounds(10, 96, 60, 16);
		lblBuild.setText("Build");

		Label lblMatrix = new Label(infoGroup, SWT.PUSH);
		lblMatrix.setBounds(10, 118, 60, 16);
		lblMatrix.setText("Matrix");

		Label lblElements = new Label(infoGroup, SWT.PUSH);
		lblElements.setBounds(10, 140, 62, 16);
		lblElements.setText("Elements");

		table = new Table(infoGroup, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(165, 23, 550, 168);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn type = new TableColumn(table, SWT.LEFT);
		type.setText("TYPE");
		type.setWidth(213);

		TableColumn hbyte = new TableColumn(table, SWT.LEFT);
		hbyte.setText("HByte");
		hbyte.setWidth(60);

		TableColumn lbyte = new TableColumn(table, SWT.LEFT);
		lbyte.setText("LByte");
		lbyte.setWidth(60);

		TableColumn size = new TableColumn(table, SWT.LEFT);
		size.setText("Size");
		size.setWidth(49);

		TableColumn instance = new TableColumn(table, SWT.LEFT);
		instance.setText("Instance");
		instance.setWidth(69);

		TableColumn report = new TableColumn(table, SWT.LEFT);
		report.setText("Reports");
		report.setWidth(60);
		
		btnUpdate = new Button(infoGroup, SWT.PUSH);
		btnUpdate.setBounds(71, 162, 78, 26);
		btnUpdate.setText("Update");

		text_family = new Text(infoGroup, SWT.BORDER);
		text_family.setEditable(false);
		text_family.setBounds(80, 23, 73, 22);

		text_variant = new Text(infoGroup, SWT.BORDER);
		text_variant.setEditable(false);
		text_variant.setBounds(80, 46, 73, 22);

		text_version = new Text(infoGroup, SWT.BORDER);
		text_version.setEditable(false);
		text_version.setBounds(80, 68, 73, 22);

		text_build = new Text(infoGroup, SWT.BORDER);
		text_build.setEditable(false);
		text_build.setBounds(80, 90, 73, 22);

		text_matrix = new Text(infoGroup, SWT.BORDER);
		text_matrix.setEditable(false);
		text_matrix.setBounds(80, 112, 73, 22);

		text_element = new Text(infoGroup, SWT.BORDER);
		text_element.setEditable(false);
		text_element.setBounds(80, 134, 73, 22);

		btnQueue = new Button(composite, SWT.NONE);
		btnQueue.setBounds(293, enginnerModeY + 10, 78, 26);
		btnQueue.setText("Queue");

		btnPowerConfig = new Button(composite, SWT.NONE);
		btnPowerConfig.setBounds(402, enginnerModeY + 10, 100, 26);
		btnPowerConfig.setText("Power config");
		

		btnMultitouch = new Button(composite, SWT.NONE);
		btnMultitouch.setBounds(533, enginnerModeY + 10, 90, 26);
		btnMultitouch.setText("Multi-touch");
		

		btnDragflic = new Button(composite, SWT.NONE);
		btnDragflic.setBounds(293, enginnerModeY + 60, 80, 26);
		btnDragflic.setText("Drag/Flick");

		btnPalm = new Button(composite, SWT.NONE);
		btnPalm.setBounds(402, enginnerModeY + 60, 78, 26);
		btnPalm.setText("Palm");
		
	}

	@Override
	public void disableBtn() {
		btnWrite.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnRead.setEnabled(false);
		btnQueue.setEnabled(false);
		btnPowerConfig.setEnabled(false);
		btnMultitouch.setEnabled(false);
		btnPalm.setEnabled(false);
	}
	
	public void enableBtn() {
		btnWrite.setEnabled(true);
		btnUpdate.setEnabled(true);
		btnRead.setEnabled(true);
		btnQueue.setEnabled(true);
		btnPowerConfig.setEnabled(true);
		btnMultitouch.setEnabled(true);
		btnPalm.setEnabled(true);
		
	}
	public void enablePartial() {
		btnUpdate.setEnabled(true);
		
	}
	@Override
	public void initialActions() {
		table.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				tableHandler(event);
			}
		});
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onClickbtn_update(singletone);
			}
		});
		btnPowerConfig.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PowerConfigUI pc = new PowerConfigUI(singletone);
				pc.open();
			}
		});
		btnMultitouch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				Multitouch window = new Multitouch(singletone);
				window.open();
			}
		});
		btnPalm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PalmUI palm = new PalmUI(singletone);
				palm.open();

			}
		});
		btnQueue.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Queue queue = new Queue(singletone);
				queue.open();

			}
		});
		btnDragflic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Drag drag = new Drag(singletone);
				drag.open();

			}
		});
	}
	
	protected static void onClickbtn_update(Singletone singletone) {
		singletone.adb.Initial();
		String res[] = atmel.io.getInfoMsg();
		if (res.length >= 6) {
			text_family.setText(res[0]);
			text_variant.setText(res[1]);
			text_version.setText(res[2]);
			text_build.setText(res[3]);
			text_matrix.setText(res[4] + "x" + res[5]);
			text_element.setText(res[6]);
		}

		TableItem item;
		table.removeAll();
		String tag = "";
		ObjectElement oe;
		if (atmel.ObjectElemens != null) {
			for (String s : atmel.ObjectElemens.keySet()) {
				oe = atmel.ObjectElemens.get(s);
				item = new TableItem(table, SWT.NONE);
				item.setText(new String[] { oe.getType(),
						Integer.toString(oe.getHbyte()),
						Integer.toString(oe.getLbyte()),
						Integer.toString(oe.getSize()), oe.getInstance(),
						oe.getReportID() });
			}
			
			// enable button
			btnQueue.setEnabled(true);
			btnPowerConfig.setEnabled(true);
			btnMultitouch.setEnabled(true);
			btnDragflic.setEnabled(true);
			btnPalm.setEnabled(true);
		}

	}
	protected static void tableHandler(org.eclipse.swt.widgets.Event event) {
		Point point = new Point(event.x, event.y);
		final TableItem item = table.getItem(point);
		if (item == null)
			return;

		ObjectElement oe = atmel.ObjectElemens.get(item.getText());
		txt_enginnerMode.setText(oe.getType());
		txt_hbyte.setText(Integer.toString(oe.getHbyte()));
		txt_lbyte.setText(Integer.toString(oe.getLbyte()));
		//System.out.println(item.getText());

	}
	
	
}
