package uidesign.atmel;

import object.AtmelInstance;
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

public class Queue {

	protected Shell shell;
	private Text txt_maxqueue;
	private Text txt_queue;
	private Button btnRefresh;
	private Button btnWrite;
	private Object singletone;
	private AtmelInstance atmel;

	public Queue(Singletone singletone){
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
		shell.setSize(322, 120);
		shell.setText("Queue");
		
		Label lblMaxQueue = new Label(shell, SWT.NONE);
		lblMaxQueue.setBounds(10, 20, 79, 18);
		lblMaxQueue.setText("Max queue");
		
		Label lblQueue = new Label(shell, SWT.NONE);
		lblQueue.setBounds(10, 55, 70, 18);
		lblQueue.setText("Queue");
		
		txt_maxqueue = new Text(shell, SWT.BORDER);
		txt_maxqueue.setBounds(95, 10, 75, 27);
		
		txt_queue = new Text(shell, SWT.BORDER);
		txt_queue.setBounds(95, 46, 75, 27);
		
		btnRefresh = new Button(shell, SWT.NONE);
		btnRefresh.setBounds(212, 10, 91, 29);
		btnRefresh.setText("Refresh");
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refresh();
			}
		});
		
		btnWrite = new Button(shell, SWT.NONE);
		btnWrite.setBounds(212, 46, 91, 29);
		btnWrite.setText("Write");
		btnWrite.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				write();
			}
		});

	}
	protected void refresh() {
	
	}

	protected void write() {
	}

}
