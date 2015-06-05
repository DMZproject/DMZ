package DMZ.gui;

import java.awt.Image;

import javax.swing.ImageIcon;

import DMZ.inputdata.InputData;

public class HistogramLabel extends OnlyInputLabel {

	boolean selected = false;
	int colorType=2;
	String type = "histogram";
	ImageIcon histogramImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/histogramIcon.PNG"));
	
	HistogramLabel() {
		histogramImageIcon = new ImageIcon(histogramImageIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		setIcon(histogramImageIcon);
	}

	HistogramLabel(String name) {
		super(name);
	}

	public int getColorType(){
		return colorType;
	}
	public String getType() {
		return type;
	}
	public void run() {
		rightPanel.addHistogramPanel(this);
	}

	public String getExpainText() {
		return expainText;
	}

	@Override
	public void setExpainText(String expainText) {
		this.expainText = expainText;
		
	}
}
