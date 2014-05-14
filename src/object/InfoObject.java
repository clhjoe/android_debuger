package object;

import java.util.HashMap;

public class InfoObject {
	
	private String []infoMsg={};
	
//	private HashMap<String,ObjectElement>oe=new HashMap<String,ObjectElement>();
	
	public void setInfoMsg(String []infoMsg){
		this.infoMsg=infoMsg;
	}
	public String []getInfoMsg(){
		return this.infoMsg;
	}
	
}
