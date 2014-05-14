package object;

import java.util.HashMap;
import java.util.TreeMap;


public class AtmelInstance {
	public InfoObject io=new InfoObject();
	public TreeMap<String, ObjectElement>ObjectElemens=new TreeMap<String, ObjectElement>();
	public HashMap<Integer,String>ObjectMapping=new HashMap<Integer,String>();
	public AtmelInstance(){
		ObjectMapping.put(5,"T5 Message Processor");
		ObjectMapping.put(6,"T6 Command Processor");
		ObjectMapping.put(7,"T7 Power Config");
		ObjectMapping.put(8,"T8 Acquisition config");
		ObjectMapping.put(9,"T9 Multiple Touch");
		ObjectMapping.put(15,"T15 Key Array");
		ObjectMapping.put(18,"T18 Communications Config");
		ObjectMapping.put(22,"T22 Noise suppression");
		ObjectMapping.put(24,"T24 One-touch Gesture");
		ObjectMapping.put(25,"T25 Self Test");
		ObjectMapping.put(27,"T27 Two-touch Gesture");
		ObjectMapping.put(28,"T28 CTE Config");
		ObjectMapping.put(38,"T38 User Data");
		ObjectMapping.put(40,"T40 Grip Suppression");
		ObjectMapping.put(41,"T41 Palm Suppression");
		ObjectMapping.put(43,"T43 Digitizer HID Config");
		ObjectMapping.put(44,"T44 Message Count");
	}
	public String getNameMapping(int i){
		return this.ObjectMapping.get(i);
	}
	public ObjectElement getObject(int i){
		if(!ObjectMapping.containsKey(i))return null;
		if(!ObjectElemens.containsKey(ObjectMapping.get(i)))return null;
		return ObjectElemens.get(ObjectMapping.get(i));
	}
	
}
