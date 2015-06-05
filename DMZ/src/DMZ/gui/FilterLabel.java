package DMZ.gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import weka.gui.explorer.PreprocessPanel;

public class FilterLabel extends BothLabel{

	boolean selected = false;
	String algorithm;
	int colorType=3;
	String type = "filter";
	String expainText;
	PreprocessPanel pp =new PreprocessPanel();
	FilterLabel(){

	}

	FilterLabel(String name) {
		super(name);	
		String IconName = "DMZ/ImageIcon/filterIcon/"+name+"ImageIcon.png";
		ImageIcon filterIcon = new ImageIcon(ClassLoader.getSystemResource(IconName));
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
		setIcon(new ImageIcon(filterIcon.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH)));
		
	}
	
	public PreprocessPanel getPp(){
		return pp;
	}

	
	public int getColorType(){
		return colorType;
	}
	public String getType() {
		return type;
	}
	public void run() {
		rightPanel.addPreprocessPanel(this);

	}
	public void setAlgorithm(String al){
		this.algorithm=al;
	}
	public String getAlgorithm(){
		return algorithm;
	}
	public String getExpainText() {
		return expainText;
	}

	@Override
	public void setExpainText(String expainText) {
		this.expainText = expainText;
		
	}
}
