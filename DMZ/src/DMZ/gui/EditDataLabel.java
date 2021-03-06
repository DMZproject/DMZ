package DMZ.gui;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import DMZ.inputdata.InputData;

public class EditDataLabel extends BothLabel {

	boolean selected = false;
	int colorType=1;
	String type = "edit";
	ImageIcon editImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/editImageIcon.PNG"));
	EditDataLabel() {
		editImageIcon = new ImageIcon(editImageIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		setIcon(editImageIcon);
	}

	EditDataLabel(String name) {
		super(name);
	}


	public int getColorType(){
		return colorType;
	}
	public String getType() {
		return type;
	}
	public void run() {
		inputdata.edit();
	}
	public String getExpainText() {
		return expainText;
	}

	@Override
	public void setExpainText(String expainText) {
		this.expainText = expainText;
		
	}

}