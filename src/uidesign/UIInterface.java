package uidesign;

import org.eclipse.swt.widgets.Composite;

public interface UIInterface {
	
	public void disableBtn();
	public void enableBtn();
	public void createContents(Composite composite);
	public void run(Composite composite);
	public void initialActions();
	public void enablePartial();
    
}
