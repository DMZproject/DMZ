package DMZ.gui;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import DMZ.gui.*;
import DMZ.inputdata.InputData;
public class AnalyzeLabel extends OnlyInputLabel {
	
	boolean selected = false;
	int colorType=5;
	String type = "analyze";
	ParentLabel fromLabel;
	
	ImageIcon analyzeIcon =  new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/subAnalysisIcon.png"));
	AnalyzeLabel(){
		
	}
	
	AnalyzeLabel(String name){
		this.name = name;
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
		setIcon(new ImageIcon(analyzeIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH)));
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		rightPanel.addAnalyzePanel(this);
		
	}

	@Override
	public int getColorType() {
		// TODO Auto-generated method stub
		return colorType;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	public ParentLabel getFromLabel(){
		return fromLabel;
	}
	public void setFromLabel(ParentLabel fromLabel){
		this.fromLabel = fromLabel;
	}


	public String getExpainText() {
		return expainText;
	}

	@Override
	public void setExpainText(String expainText) {
		this.expainText = expainText;
		
	}
	

}
