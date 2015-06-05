package DMZ.gui;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import weka.gui.explorer.AssociationsPanel;
import weka.gui.explorer.ClassifierPanel;
import DMZ.inputdata.InputData;

public class ClassifyLabel extends BothLabel{
	
	boolean selected = false;
	String algorithm;
	int colorType=4;
	String type = "classify";
	
	
	ClassifierPanel fp=new ClassifierPanel();
	ClassifyLabel(){

	}

	ClassifyLabel(String name) {
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
		rightPanel.addClassifyPanel(this);

	}
	public void setAlgorithm(String al){
		this.algorithm=al;
	}
	public String getAlgorithm(){
		return algorithm;
	}

	public ClassifierPanel getFp(){
		return  fp;
	}
	
	public void setFp(ClassifierPanel fp)
	{
		this.fp = fp;
	}

	public String getExpainText() {
		return expainText;
	}

	@Override
	public void setExpainText(String expainText) {
		this.expainText = expainText;
		
	}
}
