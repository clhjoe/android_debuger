package mainform;

import object.Singletone;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.events.SelectionAdapter;
import resource.Strings;

public class MainForm {

	public Shell shell;
	private Text text_adblocation;
	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());

	public static Combo combo;
	private Button btnSelect;
	private Button btnDetect;
	Singletone singletone;
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainForm window = new MainForm();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		commonInitialization();
		createContents();
		initialActions();
		findDevice();
		
		shell.open();
		shell.layout();

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
		shell.setSize(768, 544);
		shell.setText("SWT Application");

		text_adblocation = new Text(shell, SWT.BORDER);
		text_adblocation.setBounds(112, 18, 256, 22);
		String loc=MainformActor.checkAdbPath(singletone,
				PropertyLoader.getConfig("adbpath"));
		if(!loc.equals("")){
			text_adblocation.setText(loc);
		}else{
			text_adblocation.setText(Strings.chooseADB);
		}
	
		Label lblAdbLocation = new Label(shell, SWT.NONE);
		lblAdbLocation.setBounds(10, 24, 96, 16);
		lblAdbLocation.setText("ADB Location: ");

		btnSelect = new Button(shell, SWT.NONE);
		btnSelect.setBounds(379, 14, 78, 26);
		btnSelect.setText("Select");

		Label lblDevice = new Label(shell, SWT.NONE);
		lblDevice.setBounds(471, 16, 44, 16);
		lblDevice.setText("Device :");
	
		combo = new Combo(shell, SWT.NULL);
		combo.setBounds(521, 10, 164, 30);
		String[] items = {Strings.selectDevice };
		combo.setItems(items);
		combo.select(0);

		/* Add tabfolder */
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 57, 746, 446);
		formToolkit.adapt(tabFolder);
		formToolkit.paintBordersFor(tabFolder);
		// add atmel
		TabItem atmel = new TabItem(tabFolder, SWT.NONE);
		atmel.setText("ATMEL");
		atmel.setToolTipText("ATMEL");
		atmel.setControl(TabControler.getAtmelTabControl(tabFolder, singletone));
		
		// add pwm
		TabItem pwm = new TabItem(tabFolder, SWT.NONE);
		pwm.setText("Power Management");
		pwm.setToolTipText("Power Management");
		pwm.setControl(TabControler.getPwmTabControl(tabFolder,singletone));
		
		
        TabItem pinmux = new TabItem(tabFolder, SWT.NONE);
        pinmux.setText("Pinmux");
        pinmux.setToolTipText("Pinmux");
        pinmux.setControl(TabControler.getPinmuxTabControl(tabFolder,singletone));
        
        TabItem gpio = new TabItem(tabFolder, SWT.NONE);
        gpio.setText("GPIO");
        gpio.setToolTipText("GPIO");
        gpio.setControl(TabControler.getGPIOTabControl(tabFolder,singletone));

		btnDetect = new Button(shell, SWT.NONE);
		btnDetect.setBounds(691, 14, 65, 26);
		//formToolkit.adapt(btnDetect, true, true);
		btnDetect.setText("Detect");

		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(463, 16, 2, 22);
		formToolkit.adapt(label, true, true);

	}

	private void initialActions() {
		
		btnSelect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_adblocation.setText(MainformActor.adbSelector(singletone,
						shell));
				findDevice();
			}
		}); 
		combo.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				String item = combo.getItem(combo.getSelectionIndex());
				if (item != null)
					singletone.target = combo.getItem(combo.getSelectionIndex());
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		btnDetect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				findDevice();
			}
		});
	}
	private void findDevice(){
		String item[] = MainformActor.refreshDeviceList(singletone,
				text_adblocation.getText());
		if (item == null) {
			text_adblocation.setText(Strings.chooseADB);
			combo.setText(Strings.selectDevice);
			btnDetect.setEnabled(false);
			//item=new String[]{strings.selectDevice};
			
		}else{
			btnDetect.setEnabled(true);
			combo.setItems(item);
			combo.select(0);
		}
	}
	private void commonInitialization() {
		singletone =new Singletone("", "");
		//atmelInstance = ;
		PropertyLoader.loadProperties();
		
	}
}
