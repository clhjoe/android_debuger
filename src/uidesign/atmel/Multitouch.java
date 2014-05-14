package uidesign.atmel;

import mainform.MainForm;
import object.AtmelInstance;
import object.ObjectElement;
import object.Singletone;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import adb.ADBCommandInterface;


public class Multitouch {

	protected Shell shell;
	private Text txt_xorigin;
	private Text txt_xsize;
	private Text txt_blen;
	private Text txt_yorigion;
	private Text txt_ysize;
	private Text txt_tchthr;
	private Text txt_tchdi;
	private Text txt_movhysti;
	private Text txt_mrgtimeout;
	private Text txt_movhystn;
	private Text txt_Filterlimit;
	private Text txt_Adaptthr;
	private Text txt_numtouch;
	private Text txt_mrgthr;
	private Text txt_mrghyst;
	private Text txt_amphyst;
	private Text txt_xedgedit;
	private Text txt_correctiongradient;
	private Text txt_jumlimit;
	private Text text_19;
	private Text txt_xpitch;
	private Text txt_tchhyst;
	private Text txt_ypitch;
	private Button chkCtrl_Scanen;
	private Button chkCtrl_Dispress;
	private Button chkCtrl_Disrel;
	private Button chkCtrl_Dismov;
	private Button chkCtrl_Disvect;
	private Button chkCtrl_Disamp;
	private Button chkCtrl_Rpten;
	private Button chkCtrl_Enable;
	private Button chkGroup_8;
	private Button chkGroup_7;
	private Button chkGroup_6;
	private Button chkGroup_5;
	private Button chkGroup_1;
	private Button chkGroup_2;
	private Button chkGroup_3;
	private Button chkGroup_4;
	private Button chkOrient_InvertX;
	private Button chkOrient_InvertY;
	private Button chkOrient_Switch;
	private Button chkMovfilter_Disable;
	private Button chkMovfilter_Span;
	private Button chkMovfilter_Dislock;
	private Button btnRefresh;
	private Button btnWrite;
	private Group YEDGECTRL;
	private Button chkMovfilter_ySpan;
	private Button chkMovfilter_ydislock;
	private Label label;
	private Text txt_yedgedit;
	private Label label_1;
	private Text txt_ycorrectiongradient;
	private String []resS;
	private int []res;
	private AtmelInstance atmel;
	private Singletone singletone;
	public Multitouch(Singletone singletone){
		//this.shell=shell;
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
	 * Launch the application.
	 * 
	 * @param args
	 */
	/*public static void main(String[] args) {
		Atmel atmel;
		try {
			Multitouch window = new Multitouch(atmel);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//*/
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell=new Shell();
		shell.setSize(805, 620);
		shell.setText("SWT Application");
		
		Group grpCtrl = new Group(shell, SWT.NONE);
		grpCtrl.setText("CTRL");
		grpCtrl.setBounds(10, 10, 378, 115);
		
		chkCtrl_Scanen = new Button(grpCtrl, SWT.CHECK);
		chkCtrl_Scanen.setBounds(10, 30, 83, 24);
		chkCtrl_Scanen.setText("SCANEN");
		
		chkCtrl_Dispress = new Button(grpCtrl, SWT.CHECK);
		chkCtrl_Dispress.setBounds(99, 30, 90, 24);
		chkCtrl_Dispress.setText("DISPRESS");
		
		chkCtrl_Disrel = new Button(grpCtrl, SWT.CHECK);
		chkCtrl_Disrel.setBounds(195, 30, 73, 24);
		chkCtrl_Disrel.setText("DISREL");
		
		chkCtrl_Dismov = new Button(grpCtrl, SWT.CHECK);
		chkCtrl_Dismov.setBounds(274, 30, 96, 24);
		chkCtrl_Dismov.setText("DISMOVE");
		
		chkCtrl_Disvect = new Button(grpCtrl, SWT.CHECK);
		chkCtrl_Disvect.setBounds(10, 70, 83, 24);
		chkCtrl_Disvect.setText("DISVECT");
		
		chkCtrl_Disamp = new Button(grpCtrl, SWT.CHECK);
		chkCtrl_Disamp.setBounds(99, 70, 83, 24);
		chkCtrl_Disamp.setText("DISAMP");
		
		chkCtrl_Rpten = new Button(grpCtrl, SWT.CHECK);
		chkCtrl_Rpten.setBounds(195, 70, 73, 24);
		chkCtrl_Rpten.setText("RPTEN");
		
		chkCtrl_Enable = new Button(grpCtrl, SWT.CHECK);
		chkCtrl_Enable.setBounds(274, 70, 86, 24);
		chkCtrl_Enable.setText("ENABLE");
		
		Group grpAkscfg = new Group(shell, SWT.NONE);
		grpAkscfg.setText("AKSCFG");
		grpAkscfg.setBounds(394, 10, 399, 115);
		
		chkGroup_8 = new Button(grpAkscfg, SWT.CHECK);
		chkGroup_8.setBounds(10, 30, 90, 24);
		chkGroup_8.setText("GROUP8");
		
		chkGroup_7 = new Button(grpAkscfg, SWT.CHECK);
		chkGroup_7.setText("GROUP7");
		chkGroup_7.setBounds(106, 30, 90, 24);
		
		chkGroup_6 = new Button(grpAkscfg, SWT.CHECK);
		chkGroup_6.setText("GROUP6");
		chkGroup_6.setBounds(202, 30, 90, 24);
		
		chkGroup_5 = new Button(grpAkscfg, SWT.CHECK);
		chkGroup_5.setText("GROUP5");
		chkGroup_5.setBounds(299, 30, 90, 24);
		
		chkGroup_1 = new Button(grpAkscfg, SWT.CHECK);
		chkGroup_1.setText("GROUP1");
		chkGroup_1.setBounds(299, 70, 90, 24);
		
		chkGroup_2 = new Button(grpAkscfg, SWT.CHECK);
		chkGroup_2.setText("GROUP2");
		chkGroup_2.setBounds(202, 70, 90, 24);
		
		chkGroup_3 = new Button(grpAkscfg, SWT.CHECK);
		chkGroup_3.setText("GROUP3");
		chkGroup_3.setBounds(106, 70, 90, 24);
		
		chkGroup_4 = new Button(grpAkscfg, SWT.CHECK);
		chkGroup_4.setText("GROUP4");
		chkGroup_4.setBounds(10, 70, 90, 24);
		
		Label lblXorigion = new Label(shell, SWT.NONE);
		lblXorigion.setBounds(33, 140, 70, 18);
		lblXorigion.setText("XORIGIN");
		
		Label lblXsize = new Label(shell, SWT.NONE);
		lblXsize.setBounds(33, 176, 70, 18);
		lblXsize.setText("XSIZE");
		
		Label lblBlen = new Label(shell, SWT.NONE);
		lblBlen.setBounds(33, 209, 70, 18);
		lblBlen.setText("BLEN");
		
		txt_xorigin = new Text(shell, SWT.BORDER);
		txt_xorigin.setBounds(109, 131, 75, 27);
		
		txt_xsize = new Text(shell, SWT.BORDER);
		txt_xsize.setBounds(109, 164, 75, 27);
		
		txt_blen = new Text(shell, SWT.BORDER);
		txt_blen.setBounds(109, 200, 75, 27);
		
		Label lblYorigin = new Label(shell, SWT.NONE);
		lblYorigin.setBounds(222, 140, 70, 18);
		lblYorigin.setText("YORIGIN");
		
		Label lblYsize = new Label(shell, SWT.NONE);
		lblYsize.setBounds(222, 176, 70, 18);
		lblYsize.setText("YSIZE");
		
		Label lblTchthr = new Label(shell, SWT.NONE);
		lblTchthr.setBounds(222, 209, 70, 18);
		lblTchthr.setText("TCHTHR");
		
		txt_yorigion = new Text(shell, SWT.BORDER);
		txt_yorigion.setBounds(298, 131, 75, 27);
		
		txt_ysize = new Text(shell, SWT.BORDER);
		txt_ysize.setBounds(298, 164, 75, 27);
		
		txt_tchthr = new Text(shell, SWT.BORDER);
		txt_tchthr.setBounds(298, 200, 75, 27);
		
		Group grpOrient = new Group(shell, SWT.NONE);
		grpOrient.setText("ORIENT");
		grpOrient.setBounds(10, 245, 378, 70);
		
		chkOrient_InvertX = new Button(grpOrient, SWT.CHECK);
		chkOrient_InvertX.setBounds(10, 32, 85, 24);
		chkOrient_InvertX.setText("INVERTX");
		
		chkOrient_InvertY = new Button(grpOrient, SWT.CHECK);
		chkOrient_InvertY.setBounds(132, 32, 115, 24);
		chkOrient_InvertY.setText("INVERTY");
		
		chkOrient_Switch = new Button(grpOrient, SWT.CHECK);
		chkOrient_Switch.setBounds(253, 32, 115, 24);
		chkOrient_Switch.setText("SWITCH");
		
		Label lblTchDi = new Label(shell, SWT.NONE);
		lblTchDi.setBounds(28, 335, 70, 18);
		lblTchDi.setText("TCHDI");
		
		Label lblMov = new Label(shell, SWT.NONE);
		lblMov.setBounds(28, 373, 75, 18);
		lblMov.setText("MOVHYSTI");
		
		txt_tchdi = new Text(shell, SWT.BORDER);
		txt_tchdi.setBounds(109, 326, 75, 27);
		
		txt_movhysti = new Text(shell, SWT.BORDER);
		txt_movhysti.setBounds(109, 364, 75, 27);
		
		Label lblMrgtimeout = new Label(shell, SWT.NONE);
		lblMrgtimeout.setBounds(198, 335, 94, 18);
		lblMrgtimeout.setText("MRGTIMEOUT");
		
		txt_mrgtimeout = new Text(shell, SWT.BORDER);
		txt_mrgtimeout.setBounds(298, 326, 75, 27);
		
		Label lblMovhystn = new Label(shell, SWT.NONE);
		lblMovhystn.setBounds(198, 373, 83, 18);
		lblMovhystn.setText("MOVHYSTN");
		
		txt_movhystn = new Text(shell, SWT.BORDER);
		txt_movhystn.setBounds(298, 364, 75, 27);
		
		Group grpMovfiler = new Group(shell, SWT.NONE);
		grpMovfiler.setText("MOVFILTER");
		grpMovfiler.setBounds(10, 414, 378, 76);
		
		chkMovfilter_Disable = new Button(grpMovfiler, SWT.CHECK);
		chkMovfilter_Disable.setBounds(10, 42, 90, 24);
		chkMovfilter_Disable.setText("DISABLE");
		
		Label lblFilterlimit = new Label(grpMovfiler, SWT.NONE);
		lblFilterlimit.setBounds(101, 45, 80, 18);
		lblFilterlimit.setText("FILTERLIMIT");
		
		txt_Filterlimit = new Text(grpMovfiler, SWT.BORDER);
		txt_Filterlimit.setBounds(186, 41, 28, 27);
		
		Label lblApadtthr = new Label(grpMovfiler, SWT.NONE);
		lblApadtthr.setBounds(225, 45, 80, 18);
		lblApadtthr.setText("ADAPTTHR");
		
		txt_Adaptthr = new Text(grpMovfiler, SWT.BORDER);
		txt_Adaptthr.setBounds(306, 41, 28, 27);
		
		Label lblNumtouch = new Label(shell, SWT.NONE);
		lblNumtouch.setBounds(10, 516, 83, 18);
		lblNumtouch.setText("NUMTOUCH");
		
		Label lblMrgthr = new Label(shell, SWT.NONE);
		lblMrgthr.setBounds(33, 556, 70, 18);
		lblMrgthr.setText("MRGTHR");
		
		Label lblMrghyst = new Label(shell, SWT.NONE);
		lblMrghyst.setBounds(198, 516, 70, 18);
		lblMrghyst.setText("MRGHYST");
		
		Label lblAmphyst = new Label(shell, SWT.NONE);
		lblAmphyst.setBounds(198, 556, 70, 18);
		lblAmphyst.setText("AMPHYST");
		
		txt_numtouch = new Text(shell, SWT.BORDER);
		txt_numtouch.setBounds(109, 507, 75, 27);
		
		txt_mrgthr = new Text(shell, SWT.BORDER);
		txt_mrgthr.setBounds(109, 547, 75, 27);
		
		txt_mrghyst = new Text(shell, SWT.BORDER);
		txt_mrghyst.setBounds(298, 507, 75, 27);
		
		txt_amphyst = new Text(shell, SWT.BORDER);
		txt_amphyst.setBounds(298, 547, 75, 27);
		
		Group grpXedge = new Group(shell, SWT.NONE);
		grpXedge.setText("XEDGECTRL");
		grpXedge.setBounds(394, 131, 399, 96);
		
		chkMovfilter_Span = new Button(grpXedge, SWT.CHECK);
		chkMovfilter_Span.setBounds(10, 28, 70, 24);
		chkMovfilter_Span.setText("SPAN");
		
		chkMovfilter_Dislock = new Button(grpXedge, SWT.CHECK);
		chkMovfilter_Dislock.setBounds(110, 28, 90, 24);
		chkMovfilter_Dislock.setText("DISLOCK");
		
		Label lblXedgedist = new Label(grpXedge, SWT.NONE);
		lblXedgedist.setBounds(218, 31, 82, 18);
		lblXedgedist.setText("XEDGEDIST");
		
		txt_xedgedit = new Text(grpXedge, SWT.BORDER);
		txt_xedgedit.setBounds(305, 22, 75, 27);
		
		Label lblCorrection = new Label(grpXedge, SWT.NONE);
		lblCorrection.setBounds(134, 68, 165, 18);
		lblCorrection.setText("CORRECTIONGRADIENT");
		
		txt_correctiongradient = new Text(grpXedge, SWT.BORDER);
		txt_correctiongradient.setBounds(305, 59, 75, 27);
		
		Label lblJumplimit = new Label(shell, SWT.NONE);
		lblJumplimit.setBounds(428, 357, 83, 18);
		lblJumplimit.setText("JUMPLIMIT");
		
		Label lblXpitch = new Label(shell, SWT.NONE);
		lblXpitch.setBounds(428, 398, 70, 18);
		lblXpitch.setText("XPITCH");
		
		Label lblTchhycht = new Label(shell, SWT.NONE);
		lblTchhycht.setBounds(617, 357, 70, 18);
		lblTchhycht.setText("TCHHYST");
		
		Label lblYpitch = new Label(shell, SWT.NONE);
		lblYpitch.setBounds(617, 398, 70, 18);
		lblYpitch.setText("YPITCH");
		
		txt_jumlimit = new Text(shell, SWT.BORDER);
		txt_jumlimit.setBounds(517, 348, 75, 27);
		
		text_19 = new Text(shell, SWT.BORDER);
		text_19.setBounds(508, 297, 39, -1);
		
		txt_xpitch = new Text(shell, SWT.BORDER);
		txt_xpitch.setBounds(517, 389, 75, 27);
		
		txt_tchhyst = new Text(shell, SWT.BORDER);
		txt_tchhyst.setBounds(693, 348, 75, 27);
		
		txt_ypitch = new Text(shell, SWT.BORDER);
		txt_ypitch.setBounds(693, 389, 75, 27);
		
		btnRefresh = new Button(shell, SWT.NONE);
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refresh();
			}
		});
		btnRefresh.setBounds(677, 507, 91, 29);
		btnRefresh.setText("Refresh");
		
		btnWrite = new Button(shell, SWT.NONE);
		btnWrite.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				write();
			}
		});
		btnWrite.setBounds(677, 545, 91, 29);
		btnWrite.setText("Write");
		
		YEDGECTRL = new Group(shell, SWT.NONE);
		YEDGECTRL.setText("YEDGECTRL");
		YEDGECTRL.setBounds(394, 233, 399, 96);
		
		chkMovfilter_ySpan = new Button(YEDGECTRL, SWT.CHECK);
		chkMovfilter_ySpan.setText("SPAN");
		chkMovfilter_ySpan.setBounds(10, 28, 70, 24);
		
		chkMovfilter_ydislock = new Button(YEDGECTRL, SWT.CHECK);
		chkMovfilter_ydislock.setText("DISLOCK");
		chkMovfilter_ydislock.setBounds(110, 28, 90, 24);
		
		label = new Label(YEDGECTRL, SWT.NONE);
		label.setText("YEDGEDIST");
		label.setBounds(218, 31, 82, 18);
		
		txt_yedgedit = new Text(YEDGECTRL, SWT.BORDER);
		txt_yedgedit.setBounds(305, 22, 75, 27);
		
		label_1 = new Label(YEDGECTRL, SWT.NONE);
		label_1.setText("CORRECTIONGRADIENT");
		label_1.setBounds(134, 68, 165, 18);
		
		txt_ycorrectiongradient = new Text(YEDGECTRL, SWT.BORDER);
		txt_ycorrectiongradient.setBounds(305, 59, 75, 27);

	}
	private void write(){
		ObjectElement oe = atmel.ObjectElemens.get(atmel.getNameMapping(9));
		if(oe==null)return;
		ADBCommandInterface adb=singletone.adb;
		int v = 0;
		//System.out.println("lbyte: "+oe.getLbyte()+" hbyte "+oe.getHbyte());
		if(chkCtrl_Scanen.getSelection())v|=0x80;
		if(chkCtrl_Dispress.getSelection())v|=0x40;
		if(chkCtrl_Disrel.getSelection())v|=0x20;
		if(chkCtrl_Dismov.getSelection())v|=0x10;
		if(chkCtrl_Disvect.getSelection())v|=0x08;
		if(chkCtrl_Disamp.getSelection())v|=0x04;
		if(chkCtrl_Rpten.getSelection())v|=0x02;
		if(chkCtrl_Enable.getSelection())v|=0x01;
		
		if(v!=res[0]){res[0]=v;adb.WriteValue( oe.getLbyte(), oe.getHbyte(), v);}
		
		v=Integer.parseInt(txt_xorigin.getText());if(v!=res[1]){res[1]=v;adb.WriteValue( oe.getLbyte()+1, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_yorigion.getText());if(v!=res[2]){res[2]=v;adb.WriteValue( oe.getLbyte()+2, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_xsize.getText());if(v!=res[3]){res[3]=v;adb.WriteValue( oe.getLbyte()+3, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_ysize.getText());if(v!=res[4]){res[4]=v;adb.WriteValue( oe.getLbyte()+4, oe.getHbyte(), v);}
		
		v = 0;
		if(chkGroup_1.getSelection())v|=0x01;
		if(chkGroup_2.getSelection())v|=0x02;
		if(chkGroup_3.getSelection())v|=0x04;
		if(chkGroup_4.getSelection())v|=0x08;
		if(chkGroup_5.getSelection())v|=0x10;
		if(chkGroup_6.getSelection())v|=0x20;
		if(chkGroup_7.getSelection())v|=0x40;
		if(chkGroup_8.getSelection())v|=0x80;
		
		if(v!=res[5]){res[5]=v;adb.WriteValue( oe.getLbyte()+5, oe.getHbyte(), v);}
		
		v=Integer.parseInt(txt_blen.getText());if(v!=res[6]>>4){res[6]=v>>4;adb.WriteValue( oe.getLbyte()+6, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_tchthr.getText());if(v!=res[7]){res[7]=v;adb.WriteValue( oe.getLbyte()+7, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_tchdi.getText());if(v!=res[8]){res[8]=v;adb.WriteValue( oe.getLbyte()+8, oe.getHbyte(), v);}
		
		v = 0;
		if(chkOrient_InvertX.getSelection())v|=0x04;
		if(chkOrient_InvertY.getSelection())v|=0x02;
		if(chkOrient_Switch.getSelection())v|=0x01;
		if(v!=res[9]){res[9]=v;adb.WriteValue( oe.getLbyte()+9, oe.getHbyte(), v);}
		
		v=Integer.parseInt(txt_mrgtimeout.getText());if(v!=res[10]){res[10]=v;adb.WriteValue( oe.getLbyte()+10, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_movhysti.getText());if(v!=res[11]){res[11]=v;adb.WriteValue( oe.getLbyte()+11, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_movhystn.getText());if(v!=res[12]){res[12]=v;adb.WriteValue( oe.getLbyte()+12, oe.getHbyte(), v);}
		
		v = 0;
		int v2;
		if(chkMovfilter_Disable.getSelection()){v|=0x80;}
		v2=Integer.parseInt(txt_Filterlimit.getText());v +=(v2&0x07)<<4;
		v2=Integer.parseInt(txt_Adaptthr.getText());v +=(v2&0x0f);
		if(v!=res[13]){res[13]=v;adb.WriteValue( oe.getLbyte()+13, oe.getHbyte(), v);}
	
		
		v=Integer.parseInt(txt_numtouch.getText());if(v!=res[14]){res[14]=v;adb.WriteValue( oe.getLbyte()+14, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_mrghyst.getText());if(v!=res[15]){res[15]=v;adb.WriteValue( oe.getLbyte()+15, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_mrgthr.getText());if(v!=res[16]){res[16]=v;adb.WriteValue( oe.getLbyte()+16, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_amphyst.getText());if(v!=res[17]){res[17]=v;adb.WriteValue( oe.getLbyte()+17, oe.getHbyte(), v);}


		v = 0;
		if(chkMovfilter_Span.getSelection())v|=0x80;
		if(chkMovfilter_Dislock.getSelection())v|=0x40;
		v2=Integer.parseInt(txt_correctiongradient.getText());
		v += v2 &0x3f;
		if(v !=res[26]){res[26]=v; adb.WriteValue( oe.getLbyte()+26, oe.getHbyte(), v);}


		v = 0;
		if(chkMovfilter_ySpan.getSelection())v|=0x80;
		if(chkMovfilter_ydislock.getSelection())v|=0x40;
		v2=Integer.parseInt(txt_ycorrectiongradient.getText());
		v += v2 & 0x3f;
		if(v != res[28]){res[28]=v; adb.WriteValue( oe.getLbyte()+28, oe.getHbyte(), v);}

		v=Integer.parseInt(txt_xedgedit.getText());if(v!=res[27]){res[27]=v;adb.WriteValue( oe.getLbyte()+27, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_yedgedit.getText());if(v!=res[29]){res[29]=v;adb.WriteValue( oe.getLbyte()+29, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_jumlimit.getText());if(v!=res[30]){res[30]=v;adb.WriteValue( oe.getLbyte()+30, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_tchhyst.getText());if(v!=res[31]){res[31]=v;adb.WriteValue( oe.getLbyte()+31, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_xpitch.getText());if(v!=res[32]){res[32]=v;adb.WriteValue( oe.getLbyte()+32, oe.getHbyte(), v);}
		v=Integer.parseInt(txt_ypitch.getText());if(v!=res[33]){res[33]=v;adb.WriteValue( oe.getLbyte()+33, oe.getHbyte(), v);}
		refresh();
	

	}
	private void refresh(){
		ADBCommandInterface adb=singletone.adb;
		resS=adb.getMultiTouch();
		//System.out.println(resS);
		if(resS==null)return;
		res=new int [resS.length];
		for(int i=0;i<resS.length;i++)
			res[i]=Integer.parseInt(resS[i]);
		//set ctrl group
		int val=res[0];
		if((val&1)==1) chkCtrl_Enable.setSelection(true);else chkCtrl_Enable.setSelection(false);
		if(((val>>=1)&1)==1) chkCtrl_Rpten.setSelection(true);else chkCtrl_Rpten.setSelection(false);
		if(((val>>=1)&1)==1) chkCtrl_Disamp.setSelection(true);else chkCtrl_Disamp.setSelection(false);
		if(((val>>=1)&1)==1) chkCtrl_Disvect.setSelection(true);else chkCtrl_Disvect.setSelection(false);
		if(((val>>=1)&1)==1) chkCtrl_Dismov.setSelection(true);else chkCtrl_Dismov.setSelection(false);
		if(((val>>=1)&1)==1) chkCtrl_Disrel.setSelection(true);else chkCtrl_Disrel.setSelection(false);
		if(((val>>=1)&1)==1) chkCtrl_Dispress.setSelection(true);else chkCtrl_Dispress.setSelection(false);
		if(((val>>=1)&1)==1) chkCtrl_Scanen.setSelection(true);else chkCtrl_Scanen.setSelection(false);
		txt_xorigin.setText(resS[1]);
		txt_yorigion.setText(resS[2]);
		txt_xsize.setText(resS[3]);
		txt_ysize.setText(resS[4]);
		txt_blen.setText(Integer.toString(res[6]>>4));
		txt_tchthr.setText(resS[7]);
		val=res[5];
		if((val&0x1)==1) chkGroup_1.setSelection(true);else chkGroup_1.setSelection(false);
		if((val&0x2)==2) chkGroup_2.setSelection(true);else chkGroup_2.setSelection(false);
		if((val&0x4)>0) chkGroup_3.setSelection(true);else chkGroup_3.setSelection(false);
		if((val&0x8)>0) chkGroup_4.setSelection(true);else chkGroup_4.setSelection(false);
		if((val&0x10)>0) chkGroup_5.setSelection(true);else chkGroup_5.setSelection(false);
		if((val&0x20)>0) chkGroup_6.setSelection(true);else chkGroup_6.setSelection(false);
		if((val&0x40)>0) chkGroup_7.setSelection(true);else chkGroup_7.setSelection(false);
		if((val&0x80)>0) chkGroup_8.setSelection(true);else chkGroup_8.setSelection(false);
		txt_tchdi.setText(resS[8]);
		
		val=res[9];
		if(((val)&1)==1) chkOrient_Switch.setSelection(true);else chkOrient_Switch.setSelection(false);
		if(((val>>=1)&1)==1) chkOrient_InvertY.setSelection(true);else chkOrient_InvertY.setSelection(false);
		if(((val>>=1)&1)==1) chkOrient_InvertX.setSelection(true);else chkOrient_InvertX.setSelection(false);
		txt_mrgtimeout.setText(resS[10]);
		txt_movhysti.setText(resS[11]);
		txt_movhystn.setText(resS[12]);
		
		val=res[13];
	//	System.out.println("~~~"+res[13]+" "+Integer.toString((val)&0x80));
		if(((val)&0x80)>0) {chkMovfilter_Disable.setSelection(true);System.out.println("select"+res[13]);}else chkMovfilter_Disable.setSelection(false);
		txt_Filterlimit.setText(Integer.toString((val>>4)&0x7));
		txt_Adaptthr.setText(Integer.toString(val&0xf));
		txt_numtouch.setText(resS[14]);
		txt_mrghyst.setText(resS[15]);
		txt_mrgthr.setText(resS[16]);
		txt_amphyst.setText(resS[17]);
		val=res[26];
		if(((val)&0x80)>0) chkMovfilter_Span.setSelection(true);else chkMovfilter_Span.setSelection(false);
		if(((val)&0x40)>0) chkMovfilter_Dislock.setSelection(true);else chkMovfilter_Dislock.setSelection(false);
		txt_correctiongradient.setText(Integer.toString(val&0x3f));
		val=res[28];
		if(((val)&0x80)>0) chkMovfilter_ySpan.setSelection(true);else chkMovfilter_ySpan.setSelection(false);
		if(((val)&0x40)>0) chkMovfilter_ydislock.setSelection(true);else chkMovfilter_ydislock.setSelection(false);
		txt_ycorrectiongradient.setText(Integer.toString(val&0x3f));
		txt_xedgedit.setText(resS[27]);
		txt_yedgedit.setText(resS[29]);
		txt_jumlimit.setText(resS[30]);
		txt_tchhyst.setText(resS[31]);
		txt_xpitch.setText(resS[32]);
		txt_ypitch.setText(resS[33]);
	}
}
