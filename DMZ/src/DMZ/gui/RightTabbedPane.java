package DMZ.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;


public class RightTabbedPane extends JTabbedPane{

	JPanel firstPanel;
	JPanel secondPanel;
	JPanel thirdPanel;
	JButton tab1,tab2;
	String type;
	
	RightTabbedPane(RightPanel right,JPanel firstPanel,JPanel secondPanel,String type){
		setBackground(Color.WHITE);
		this.firstPanel = firstPanel;
		this.secondPanel = secondPanel;
		this.type = type;
		this.setTabPlacement(JTabbedPane.TOP);
		entry(right);
		this.setUI(new BasicTabbedPaneUI(){
			 @Override
			 protected void installDefaults() {
			  super.installDefaults();
			  highlight = Color.black;
			  lightHighlight = Color.black;
			  darkShadow = Color.black;
			  shadow = Color.black;
			  focus = Color.black;
			 }
			}); 
	}
	RightTabbedPane(RightPanel right,JPanel firstPanel,JPanel secondPanel,JPanel thirdPanel,String type){
		setBackground(Color.WHITE);
		this.firstPanel = firstPanel;
		this.secondPanel = secondPanel;
		this.thirdPanel= thirdPanel;
		this.type = type;
		this.setTabPlacement(JTabbedPane.TOP);
		entry(right);
		this.setUI(new BasicTabbedPaneUI(){
			 @Override
			 protected void installDefaults() {
			  super.installDefaults();
			  highlight = Color.black;
			  lightHighlight = Color.black;
			  darkShadow = Color.black;
			  shadow = Color.black;
			  focus = Color.black;
			 }
			}); 
	}
	
	private void entry(RightPanel right){
		right.removeAll();
		firstPanel.setVisible(true);
		secondPanel.setVisible(true);

		switch(type){
		case "Algorithm":
			this.addTab("Edit",firstPanel);
			this.setMnemonicAt(0, KeyEvent.VK_1);
			this.addTab("Result", secondPanel);
			this.setMnemonicAt(1, KeyEvent.VK_2);
			break;
		case "filter":
			this.addTab("Edit",firstPanel);
			this.setMnemonicAt(0, KeyEvent.VK_1);
			this.addTab("Result", secondPanel);
			this.setMnemonicAt(1, KeyEvent.VK_2);
			break;
		case "train":
			
			this.addTab("훈련",firstPanel);
			this.setMnemonicAt(0, KeyEvent.VK_1);
			this.addTab("설정", secondPanel);
			this.setMnemonicAt(1, KeyEvent.VK_2);
			this.addTab("결과",  thirdPanel);
			this.setMnemonicAt(2, KeyEvent.VK_3);
			break;
			
		}
		this.setVisible(true);
		right.setLayout(new BorderLayout());
		right.add(this);
	
		right.validate();
		right.repaint();
	}
	


	
}
