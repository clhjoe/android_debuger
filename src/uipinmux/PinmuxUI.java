
package uipinmux;

import object.Singletone;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import uidesign.UIInterface;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

public class PinmuxUI implements UIInterface {

    protected Shell shell;
    private Table table_gpio;
    private Singletone singletone;
    private Display display;
    private Button btnRefresh;

    /**
     * Launch the application.
     * 
     * @param args public static void main(String[] args) { try { GPIOUI window
     *            = new GPIOUI(); window.open(); } catch (Exception e) {
     *            e.printStackTrace(); } }
     */

    public PinmuxUI(Singletone singletone) {
        this.singletone = singletone;
    }

    public void run(Composite composite) {
        display = Display.getDefault();
        createContents(composite);
        initialActions();

    }

    /**
     * Create contents of the window.
     */
    @Override
    public void createContents(Composite composite) {
        // shell = new Shell();
        // shell.setSize(572, 433);
        // shell.setText("SWT Application");

        table_gpio = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
        table_gpio.setBounds(10, 10, 630, 390);
        table_gpio.setHeaderVisible(true);
        table_gpio.setLinesVisible(true);
        //table_gpio. 
        
        TableColumn name = new TableColumn(table_gpio, SWT.LEFT);
        name.setText("Name");
        name.setWidth(180);
        
        
        TableColumn group = new TableColumn(table_gpio, SWT.LEFT);
        group.setText("Group");
        group.setWidth(140);
        
        TableColumn function1 = new TableColumn(table_gpio, SWT.LEFT);
        function1.setText("Ping");
        function1.setWidth(95);
        
        TableColumn function2 = new TableColumn(table_gpio, SWT.LEFT);
        function2.setText("PUPD");
        function2.setWidth(95);
        
        TableColumn function3 = new TableColumn(table_gpio, SWT.LEFT);
        function3.setText("TRI");
        function3.setWidth(95);
        
        
        btnRefresh = new Button(composite, SWT.NONE);
        btnRefresh.setBounds(647, 10, 91, 29);
        btnRefresh.setText("Refresh");

    }

    @Override
    public void disableBtn() {
        btnRefresh.setEnabled(false);

    }

    @Override
    public void enableBtn() {
        btnRefresh.setEnabled(true);

    }

    @Override
    public void initialActions() {
        btnRefresh.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                refresh();
            }
        });

    }

    protected void refresh() {
        String res[]= singletone.adb.getPinmux().split("\n");
        
        TableItem item;
        table_gpio.removeAll();
        if(res==null)return;
        String items[];    
        for(String s:res){
            if(s!=" "){
                s=s.substring(2, s.length()-2).replace(" ", "");
                items=s.split(",");
                item = new TableItem(table_gpio, SWT.NONE);
                item.setText(new String[] { items[0].replace("TEGRA_PINGROUP_", ""),items[1].replace("TEGRA_", ""),items[2].replace("TEGRA_PIN_", ""),items[3].replace("TEGRA_PUPD_", ""),items[4].replace("TEGRA_TRI_", "") });
            }
        }
        
    }

    @Override
    public void enablePartial() {
        // TODO Auto-generated method stub

    }

}
