package object;

import java.util.HashMap;

public class ObjectElement {
	//private String name;
	private String type ;
	private int lbyte;
	private int hbyte;
	private int size;
	private String instance ;
	private String ReportID;
	
	public ObjectElement(String type,int hbyte,int lbyte,int size,String instance,String ReportID){
		
		this.type=type;
		this.lbyte=lbyte;
		this.hbyte=hbyte;
		this.size=size;
		this.instance=instance;
		this.ReportID=ReportID;
	}
	
	public void setType(String type){this.type=type;}
	public void setLbyte(int lbyte){this.lbyte=lbyte;}
	public void setHbyte(int hbyte){this.hbyte=hbyte;}
	public void setSize(int size){this.size=size;}
	public void setInstance(String instance){this.instance=instance;}
	public void setReportID(String reportID){this.ReportID=reportID;}
	
	public String getType(){return this.type;}
	public int getLbyte(){return this.lbyte;}
	public int getHbyte(){return this.hbyte;}
	public int getSize(){return this.size;}
	public String getInstance(){return this.instance;}
	public String getReportID(){return this.ReportID;}
	
	
}
