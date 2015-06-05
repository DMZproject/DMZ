package DMZ.gui;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;

import weka.core.Attribute;
import weka.core.Instances;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.VisualizePanel;
import DMZ.gui.OnlyInputLabel;
import DMZ.inputdata.InputData;

public class ScatterPlotLabel extends OnlyInputLabel{



	boolean selected = false;
	int colorType=2;
	String type="scatterPlot";
	ImageIcon scatterPlotImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/scatterPlotIcon.PNG"));
	
	ScatterPlotLabel(){
		scatterPlotImageIcon = new ImageIcon(scatterPlotImageIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		setIcon(scatterPlotImageIcon);
	}

	ScatterPlotLabel(String name) {
		super(name);
	}

	
	public int getColorType(){
		return colorType;
	}
	public String getType() {
		return type;
	}

	public void run() {
		rightPanel.addScatterPlot(this);
		
	}
	public String getExpainText() {
		return expainText;
	}

	@Override
	public void setExpainText(String expainText) {
		this.expainText = expainText;
		
	}
}
