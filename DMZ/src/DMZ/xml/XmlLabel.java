package DMZ.xml;

import DMZ.gui.ParentLabel;
import DMZ.inputdata.InputData;

public class XmlLabel {
	private int x;
	private int y;
	private InputData inputData;
	private String type;
	private String name;
	private String path=null;
	private String from;
	private String to;
	private String fromIndex;
	private String toIndex;
	private String none;
	
	public void setXY(int x,int y){
		this.x=x;
		this.y=y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setPath(String pt ){
		this.path=pt;
	}

	public String getPath(){
		return this.path;
	}
	
	public void setName(String name)
	{
		this.name  = name;
	}
	public String getName(){
		return name;
	}
	
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return this.type;
	}
	public void setFrom(String str){
		this.from=str;
	}
	public String getFrom(){
		return this.from;
	}
	public void setTo(String str){
		this.to=str;
	}
	public String getTo(){
		return this.to;
	}
	public void setFromIndex(String str){
		this.fromIndex=str;
	}
	public String getFromIndex(){
		return this.fromIndex;
	}
	public void setToIndex(String str){
		this.toIndex=str;
	}
	public String getToIndex(){
		return this.toIndex;
	}
	public void setNone(String str){
		this.none=str;
	}
	public String getNone(){
		return this.none;
	}
	
	

}
