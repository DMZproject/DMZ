package DMZ.gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import weka.core.Instances;
import weka.gui.explorer.AssociationsPanel;
import DMZ.gui.OnlyInputLabel;
import DMZ.inputdata.InputData;

public class AssociationLabel extends BothLabel {


	boolean selected = false;
	String algorithm;
	int colorType=4;
	String type="association";
	AssociationsPanel sp =new AssociationsPanel();
	

	 
	AssociationLabel(){

	}

	AssociationLabel(String name) {
		this.name = name;
		String IconName = "DMZ/ImageIcon/algorithmIcon/"+name+"Icon.png";
		ImageIcon algorithmIcon = new ImageIcon(ClassLoader.getSystemResource(IconName));
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
		setIcon(new ImageIcon(algorithmIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH)));
	}

	
	public int getColorType(){
		return colorType;
	}
	public String getType() {
		return type;
	}
	public void run() {
		rightPanel.addAssociationPanel(this);
		
	}
	public void setAlgorithm(String al){
		this.algorithm=al;
	}
	public String getAlgorithm(){
		return algorithm;
	}
	
	public AssociationsPanel getSp(){
		return sp;
	}
	
	public void setSp(AssociationsPanel sp)
	{
		this.sp = sp;
	}
	public String getExpainText() {
		return expainText;
	}

	@Override
	public void setExpainText(String expainText) {
		this.expainText = expainText;
		
	}
	
}
