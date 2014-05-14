package mainform;


import object.AtmelInstance;
import object.ObjectElement;
import object.Singletone;

import org.eclipse.swt.SWT;

import org.eclipse.swt.layout.GridData;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.TabFolder;


import uidesign.*;

public class TabControler {

	/**
	 * Gets the control for tab one
	 * 
	 * @param tabFolder
	 *            the parent tab folder
	 * @return Control
	 */
	public static Control getAtmelTabControl(TabFolder tabFolder,Singletone singletone) {
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite
				.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		singletone.UIs.get("atmel").run(composite);
		
		return composite;
	}

	public static Control getPwmTabControl(TabFolder tabFolder,Singletone singletone) {
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite
				.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		singletone.UIs.get("pwm").run(composite);
		return composite;
	}
	public static Control getPinmuxTabControl(TabFolder tabFolder,Singletone singletone) {
        Composite composite = new Composite(tabFolder, SWT.NONE);
        composite
                .setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
        singletone.UIs.get("pinmux").run(composite);
        return composite;
    }

    public static Control getGPIOTabControl(TabFolder tabFolder,Singletone singletone) {
        Composite composite = new Composite(tabFolder, SWT.NONE);
        composite
                .setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
        singletone.UIs.get("gpio").run(composite);
        return composite;
    }
	

}
