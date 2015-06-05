package DMZ.gui;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import weka.gui.explorer.ClassifierPanel;
import weka.gui.explorer.ClustererPanel;

public class ClusterLabel extends BothLabel{
	
	boolean selected = false;
	String algorithm;
	int colorType=4;
	String type="cluster";
	
	ClustererPanel cp =new ClustererPanel();
	ClusterLabel(){

	}

	ClusterLabel(String name) {
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

	public void run() {
		rightPanel.addClusterPanel(this);

	}
	public void setAlgorithm(String al){
		this.algorithm=al;
	}
	public String getAlgorithm(){
		return algorithm;
	}
	public String getType() {
		return type;
	}
	public ClustererPanel getCp(){
		return  cp;
	}
	
	public void setCp(ClustererPanel cp)
	{
		this.cp = cp;
	}
	public String getExpainText() {
		return expainText;
	}

	@Override
	public void setExpainText(String expainText) {
		this.expainText = expainText;
		
	}
}
