package uidesign.atmel;
import mainform.MainForm;
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


public class PalmUI {

	protected Shell shell;
	private Button btnEnable;
	private Button chkMovfilter_Disable;
	private Button btnRefresh;
	private Button btnWrite;
	private String []resS;
	private String []res;
	private AtmelInstance atmel;
	private Text txt_largeobjthr;
	private Text txt_distancethr;
	private Text txt_supextto;
	private Singletone singletone;
	public PalmUI(Singletone singletone){
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
			PalmUI window = new PalmUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//*/
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
		shell.setSize(323, 222);
		shell.setText("Palm");
				
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
		lblIdleacqint.setBounds(10, 51, 91, 18);
		lblIdleacqint.setText("LARGEOBJTHR");
		
		Label lblActvacqint = new Label(shell, SWT.NONE);
		lblActvacqint.setBounds(10, 96, 91, 18);
		lblActvacqint.setText("DISTANCETHR");
		
		Label lblIdle = new Label(shell, SWT.NONE);
		lblIdle.setBounds(10, 141, 91, 18);
		lblIdle.setText("SUPEXTTO");
		
		txt_largeobjthr = new Text(shell, SWT.BORDER);
		txt_largeobjthr.setBounds(107, 42, 75, 27);
		
		txt_distancethr = new Text(shell, SWT.BORDER);
		txt_distancethr.setBounds(107, 87, 75, 27);
		
		txt_supextto = new Text(shell, SWT.BORDER);
		txt_supextto.setBounds(107, 132, 75, 27);
		
		btnEnable = new Button(shell, SWT.CHECK);
		btnEnable.setBounds(10, 12, 115, 24);
		btnEnable.setText("Enable");

	}
	private void write(){
		ADBCommandInterface adb=singletone.adb;
		ObjectElement oe = atmel.ObjectElemens.get(atmel.getNameMapping(41));
		if(oe==null)return ;
		int v=0;
		v=btnEnable.getSelection()?1:0;if(v!=Integer.parseInt(res[0])){adb.WriteValue( oe.getLbyte(), oe.getHbyte(),v);}
		v=Integer.parseInt(txt_largeobjthr.getText());if(v!=Integer.parseInt(res[3])){adb.WriteValue( oe.getLbyte()+3, oe.getHbyte(),v);}
		v=Integer.parseInt(txt_distancethr.getText());if(v!=Integer.parseInt(res[4])){adb.WriteValue( oe.getLbyte()+4, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_supextto.getText());if(v!=Integer.parseInt(res[5])){adb.WriteValue( oe.getLbyte()+5, oe.getHbyte(), v);}
		refresh();
	}
	private void refresh(){
		ADBCommandInterface adb=singletone.adb;
		res=adb.getPalm();
		if(res==null)return ;
		if(res.length==6){
			if(Integer.parseInt(res[0])>0)btnEnable.setSelection(true);else btnEnable.setSelection(false);
			txt_largeobjthr.setText(res[3]);
			txt_distancethr.setText(res[4]);
			txt_supextto.setText(res[5]);
		}
	}
}
