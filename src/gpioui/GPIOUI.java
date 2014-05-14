
package gpioui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import object.Singletone;
import uidesign.UIInterface;

public class GPIOUI implements UIInterface {

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

    public GPIOUI(Singletone singletone) {
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
        name.setText("#GPIO");
        name.setWidth(100);
        
        
        TableColumn group = new TableColumn(table_gpio, SWT.LEFT);
        group.setText("Name");
        group.setWidth(190);
        
        TableColumn function1 = new TableColumn(table_gpio, SWT.LEFT);
        function1.setText("In/Out");
        function1.setWidth(85);
        
        TableColumn function2 = new TableColumn(table_gpio, SWT.LEFT);
        function2.setText("Hi/Lo");
        function2.setWidth(75);
        
        TableColumn irq = new TableColumn(table_gpio, SWT.LEFT);
        irq.setText("IRQ");
        irq.setWidth(65);
        
        TableColumn edge = new TableColumn(table_gpio, SWT.LEFT);
        edge.setText("Edge");
        edge.setWidth(85);
        
        
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
        String res[]= singletone.adb.getGPIO().split("\n");
        
        TableItem item;
        table_gpio.removeAll();
        if(res==null)return;
        String items[];    
        for(String s:res){
            if(s!=""){
                s=s.replace("(", "").replace(")","");
                items=new String [6];
                
                String []ress=s.split(" ");
                item = new TableItem(table_gpio, SWT.NONE);
                
                int count=0;
                for(int i=0;i<ress.length;i++){
                    if(ress[i].equals(""))continue;
                    System.out.println(ress[count]);
                    items[count]=ress[i];
                    count++;
                }
                for(int i=count;i<6;i++){
                    items[i]="";
                }
                if(items[0].contains("GPIO"))item.setBackground(display.getSystemColor(SWT.COLOR_DARK_GRAY));
                item.setText(new String[] { items[0],items[1],items[2],items[3],items[4],items[5]});
            }
        }
        
    }

    @Override
    public void enablePartial() {
        // TODO Auto-generated method stub

    }

}
