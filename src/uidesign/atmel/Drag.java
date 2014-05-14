package uidesign.atmel;

import object.AtmelInstance;
import object.ObjectElement;
import object.Singletone;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import adb.ADBCommandInterface;

public class Drag {

	protected Shell shell;
	private Text txt_delta;
	private Text txt_velosity;
	private Button btnRefresh;
	private Button btnWrite;
	private AtmelInstance atmel;
	private Singletone singletone;
	private String[] res;
	private String DELTA_FILE =  "/data/data/com.android.launcher2/delta.txt";
	private String VELOCITY_FILE="/data/data/com.android.launcher2/velocity.txt";
	public Drag(Singletone singletone){
		this.atmel=singletone.atmel;
		this.singletone=singletone;
		
		}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		refresh();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(316, 125);
		shell.setText("Drag/Flick");
		
		Label lblDelta = new Label(shell, SWT.NONE);
		lblDelta.setBounds(10, 19, 70, 18);
		lblDelta.setText("Delta");
		
		Label lblViosity = new Label(shell, SWT.NONE);
		lblViosity.setBounds(10, 54, 70, 18);
		lblViosity.setText("Velosity");
		
		txt_delta = new Text(shell, SWT.BORDER);
		txt_delta.setBounds(72, 10, 75, 27);
		
		txt_velosity = new Text(shell, SWT.BORDER);
		txt_velosity.setBounds(72, 45, 75, 27);
		
		btnRefresh = new Button(shell, SWT.NONE);
		btnRefresh.setBounds(202, 8, 91, 29);
		btnRefresh.setText("Refresh");
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refresh();
			}
		});
		
		btnWrite = new Button(shell, SWT.NONE);
		btnWrite.setBounds(202, 43, 91, 29);
		btnWrite.setText("Write");
		btnWrite.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				write();
			}
		});
		
	}

	protected void refresh() {
		ADBCommandInterface adb=singletone.adb;
		String res=adb.getDrag(DELTA_FILE);
		if(res!=null){
			txt_delta.setText(res);
		}
		res=adb.getDrag(VELOCITY_FILE);
		if(res!=null){
			txt_delta.setText(res);
		}
	}

	protected void write() {
		ADBCommandInterface adb=singletone.adb;
		adb.writeto(DELTA_FILE,txt_delta.getText());
		adb.writeto(VELOCITY_FILE,txt_velosity.getText());
		refresh();
		
	}
}
