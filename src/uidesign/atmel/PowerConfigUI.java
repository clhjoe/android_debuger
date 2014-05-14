package uidesign.atmel;
import object.AtmelInstance;
import object.ObjectElement;
import object.Singletone;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import adb.ADBCommandInterface;


public class PowerConfigUI {

	protected Shell shell;
	private Button btnRefresh;
	private Button btnWrite;
	private String []res;
	private AtmelInstance atmel;
	private Singletone singletone;
	private Text txt_idleacqint;
	private Text txt_actvacqint;
	private Text txt_actv2idleto;
	
	public PowerConfigUI(Singletone singletone){
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
	/*public static void main(String[] args) {
		try {
			PowerConfigUI window = new PowerConfigUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell=new Shell();
		shell.setSize(323, 168);
		shell.setText("SWT Application");
		
		
		
		btnRefresh = new Button(shell, SWT.NONE);
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refresh();
			}
		});
		btnRefresh.setBounds(220, 55, 91, 29);
		btnRefresh.setText("Refresh");
		
		btnWrite = new Button(shell, SWT.NONE);
		btnWrite.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				write();
			}
		});
		btnWrite.setBounds(220, 98, 91, 29);
		btnWrite.setText("Write");
		
		Label lblIdleacqint = new Label(shell, SWT.NONE);
		lblIdleacqint.setBounds(10, 19, 91, 18);
		lblIdleacqint.setText("IDLEACQINT");
		
		Label lblActvacqint = new Label(shell, SWT.NONE);
		lblActvacqint.setBounds(10, 64, 91, 18);
		lblActvacqint.setText("ACTVACQINT");
		
		Label lblIdle = new Label(shell, SWT.NONE);
		lblIdle.setBounds(10, 109, 91, 18);
		lblIdle.setText("ACTV2IDLETO");
		
		txt_idleacqint = new Text(shell, SWT.BORDER);
		txt_idleacqint.setBounds(107, 10, 75, 27);
		
		txt_actvacqint = new Text(shell, SWT.BORDER);
		txt_actvacqint.setBounds(107, 55, 75, 27);
		
		txt_actv2idleto = new Text(shell, SWT.BORDER);
		txt_actv2idleto.setBounds(107, 100, 75, 27);

	}
	private void write(){
		ADBCommandInterface adb=singletone.adb;
		ObjectElement oe = atmel.ObjectElemens.get(atmel.getNameMapping(7));
		if(oe==null)return;
		int v=0;
		
		v=Integer.parseInt(txt_idleacqint.getText());if(v!=Integer.parseInt(res[0])){adb.WriteValue( oe.getLbyte(), oe.getHbyte(),v);}
		v=Integer.parseInt(txt_actvacqint.getText());if(v!=Integer.parseInt(res[1])){adb.WriteValue( oe.getLbyte()+1, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_actv2idleto.getText());if(v!=Integer.parseInt(res[2])){adb.WriteValue( oe.getLbyte()+2, oe.getHbyte(), v);}
		
		refresh();
	}
	private void refresh(){
		ADBCommandInterface adb=singletone.adb;
		res=adb.getPowerConfig();
		if(res==null)return;
		if(res.length==3){
			txt_idleacqint.setText(res[0]);
			txt_actvacqint.setText(res[1]);
			txt_actv2idleto.setText(res[2]);
		}
	}
}
