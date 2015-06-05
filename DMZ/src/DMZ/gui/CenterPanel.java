﻿package DMZ.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import DMZ.inputdata.InputData;
public class CenterPanel extends JPanel{
	
	UndoRedoStack undoRedoStack = new UndoRedoStack();

	boolean check_LeftPanel_drag=false;
	public void setCheckDrag(boolean check){
		this.check_LeftPanel_drag=check;
	}
	boolean checkDragg = false;
	Point sp;
	CenterPanel thisPanel = this;
	RightPanel rightPanel;
	LeftPanel leftPanel;
	ParentLabel fromLabel = null;
	ParentLabel toLabel = null;
	ParentLabel tempLabel = null;

	JPopupMenu popUpMenu = new JPopupMenu();
	JMenuItem deleteMenu = new JMenuItem("삭 제");
	
	int editLabelCount=0;
	int filterLabelCount=0;
	
	Color inputColor = new Color(103,153,255);
	Color visualizeColor = new Color(242,150,97);
	Color preprocessColor = new Color(209,178,255);
	Color dataminingColor = new Color(92,209,229);
	Color analyzeColor = new Color(206,242,121);
	
	JRoundedCornerBorder line1 = new JRoundedCornerBorder(inputColor);
	JRoundedCornerBorder line2 = new JRoundedCornerBorder(visualizeColor);
	JRoundedCornerBorder line3 = new JRoundedCornerBorder(preprocessColor);
	JRoundedCornerBorder line4 = new JRoundedCornerBorder(dataminingColor);
	JRoundedCornerBorder line5 = new JRoundedCornerBorder(analyzeColor);
	/*
	LineBorder line1 = new LineBorder(inputColor, 3, true);
	LineBorder line2 = new LineBorder(visualizeColor, 3, true);
	LineBorder line3 = new LineBorder(preprocessColor, 3, true);
	LineBorder line4 = new LineBorder(dataminingColor, 3, true);
	LineBorder line5 = new LineBorder(analyzeColor, 3, true);
	*/
	
	
	ParentLabel copyLabel;
	JTextField explainText;
	
	
	
	 public ParentLabel getCopyLabel(){
		 return this.copyLabel;
		 
	 }
	

	ArrayList<ParentLabel> fromList = new ArrayList<ParentLabel>();
	ArrayList<ParentLabel> toList = new ArrayList<ParentLabel>();
	ArrayList<ParentLabel> noneList = new ArrayList<ParentLabel>();
	ArrayList<ParentLabel> xmlList=new ArrayList<ParentLabel>();
	int maxCount = 0;
	int noneCount = 0;
	
	// 화살표와 선 그리
	int ToX = 0, ToY = 0, FromX = 0, FromY = 0;
	DrawArrow da = new DrawArrow();

	public CenterPanel() {
	

	}
	
	//--------------------------------------------
	
	
	public void init(){
		
		ParentLabel fromLabel = null;
		ParentLabel toLabel = null;
		ParentLabel tempLabel = null;
		ParentLabel xmlList = null;

	}
	
	public void removeCenterPanel(){
		this.removeAll();
		rightPanel.removeAll();

		fromList = null;
		toList =null;
		noneList = null;
		xmlList =null;
		

		fromList = new ArrayList<ParentLabel>();
		toList = new ArrayList<ParentLabel>();
		noneList = new ArrayList<ParentLabel>();
		xmlList=new ArrayList<ParentLabel>();
		
		
		maxCount = 0;
		noneCount = 0;
		
		repaint();
	}

	public void getLocation(int q) {
		// fromList의 x,y좌표
		int[] X = { 85, 40, 0, 40 };
		int[] Y = { 45, 0, 45, 97 };
		int[] iX = { 0, 80 };
		int[] iY = { 45, 45 };
		int index;
		int x = 0, y = 0, min = 999999999, sum = 0, toX = 0, toY = 0, fromX = 0, fromY = 0;
		// 반복문 두개를 이용하여 fromlist-tolist값을 계산하여 가장 짧은거리를 min값에 넣어준다.

		// 1번 타입은, 좌우에서만 화살표 생
		if (fromList.get(q).getLabelType() == 1) {
			for (int a = 0; a < 2; a++) {
				for (int b = 0; b < 4; b++) {
					// x,y의 거리 계산
					x = ((toList.get(q).getX() + X[b]) - (fromList.get(q)
							.getX() + iX[a]));
					y = ((toList.get(q).getY() + Y[b]) - (fromList.get(q)
							.getY() + iY[a]));

					// 음수라면 양수로 바꿔
					if (x < 0)
						x = -x;
					if (y < 0)
						y = -y;
					sum = x + y;

					// 거리가 가장 짧을때,
					if(b==0 || b==2){
						if((sum-8)<min){
							min=sum;
							toX=X[b]; toY=Y[b];
							fromX=iX[a]; fromY=iY[a];
						}
					}
		
					else{
						
					if (sum < min) {
						min = sum;
						toX = X[b];
						toY = Y[b];
						fromX = iX[a];
						fromY = iY[a];
					}
				}
				}
			}
			FromX = fromX;
			FromY = fromY;
			ToX = toX;
			ToY = toY;
		} else {
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					// x,y의 거리 계산
					x = ((toList.get(q).getX() + X[b]) - (fromList.get(q)
							.getX() + X[a]));
					y = ((toList.get(q).getY() + Y[b]) - (fromList.get(q)
							.getY() + Y[a]));

					// 음수라면 양수로 바꿔
					if (x < 0)
						x = -x;
					if (y < 0)
						y = -y;
					sum = x + y;

					// 거리가 가장 짧을때,
					
					if(b==0 || b==2){
						if((sum-8)<min){
							min=sum;
							toX=X[b]; toY=Y[b];
							fromX=X[a]; fromY=Y[a];
						}
					}
		
					else{
						
					if (sum < min) {
						min = sum;
						toX = X[b];
						toY = Y[b];
						fromX = X[a];
						fromY = Y[a];
					}
				}
				}
			}
			FromX = fromX;
			FromY = fromY;
			ToX = toX;
			ToY = toY;
		}
	}

	public void setRightPanel(RightPanel rightPanel) {
		this.rightPanel = rightPanel;
	}
	public void setLeftPanel(LeftPanel leftPanel){
		this.leftPanel=leftPanel;
	}

	public void addLabel(ParentLabel la) {
		

		la.addMouseListener(new LabelClickListener());
		la.addMouseMotionListener(new LabelClickListener());
		la.addKeyListener(new LabelKeyListener());
		//add(la);
		
		undoRedoStack.push(add(la));
		undoRedoStack.stackRedoClear();
		//System.out.println(undoRedoStack.size());
		
		noneList.add(la);
		xmlList.add(la);
		noneCount++;
		repaint();

	}
	public void addLabel2(ParentLabel la){
		la.addMouseListener(new LabelClickListener());
		la.addMouseMotionListener(new LabelClickListener());
		la.addKeyListener(new LabelKeyListener());
		
		add(la);
		noneList.add(la);
		xmlList.add(la);
		noneCount++;
		repaint();	
	}

	class LabelKeyListener implements KeyListener {
		ParentLabel la;
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			boolean checkFrom = false;
			boolean checkTo = false;
			boolean checknone = false;
			int indexList = 0;
			int countFrom =0;
			int countTo=0;

			la = (ParentLabel) e.getSource();

			for (int i = 0; i < maxCount; i++) {
				if (fromList.get(i) == la) {
					checkFrom = true;
				}
				if (toList.get(i) == la) {
					checkTo = true;
					indexList = i;
				}
			}

			if (checkFrom == false)
				for (int i = 0; i < noneCount; i++) {
					if (noneList.get(i) == la) {
						checknone = true;
						indexList = i;
					}
				}

			if (la.selected && e.getKeyCode() == 127 && checkFrom == false) {
				if (maxCount != 0 && checknone != true && checkTo==true) {
					undoRedoStack.pushRemove(fromList.get(indexList),toList.get(indexList));
					undoRedoStack.stackRedoClear();
					toList.remove(indexList);
					for(int i =0;i<maxCount;i++){
						if(fromList.get(i)==fromList.get(indexList))
							countFrom++;
					}
					for(int i=0;i<maxCount-1;i++){
						if(toList.get(i)==fromList.get(indexList))
							countTo++;
					}
					if(countFrom==1 && countTo==0 ){
						noneList.add(fromList.get(indexList));
						noneCount++;
					}
					fromList.remove(indexList);
					maxCount--;
					for(int i=0;i<xmlList.size();i++){
						if(la.getXmlId()==xmlList.get(i).getXmlId())
							xmlList.remove(i);
					}
					remove(la);
					
					
					
				} else if ((checknone == true || noneCount == 0)) {

					if (noneCount != 0) {
						undoRedoStack.pushRemove(noneList.get(indexList));
						undoRedoStack.stackRedoClear();
						noneList.remove(indexList);
						noneCount--;
					}
					for(int i=0;i<xmlList.size();i++){
						if(la.getXmlId()==xmlList.get(i).getXmlId())
							xmlList.remove(i);
					}
					remove(la);
					
					
				}
				else if(!checkFrom&&!checkTo&&!checknone){
					for(int i=0;i<xmlList.size();i++){
						if(la.getXmlId()==xmlList.get(i).getXmlId())
							xmlList.remove(i);
					}
					remove(la);
				}
				la.selected = false;
				la.setBorder(null);
				fromLabel = null;

				repaint();
			}
		
				

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}
	public void returnRemoveLabelForUndo(ParentLabel fromLabel,ParentLabel toLabel){
		int CountTo=0;
		for(int i = 0;i<maxCount;i++){
			if(toList.get(i)==toLabel)
				CountTo++;
		}
		if(CountTo==0){
			noneList.remove(toLabel);
			noneCount--;
		}
		fromList.add(fromLabel);
		toList.add(toLabel);
		InputData input = new InputData();
		input.setInstances(fromLabel.getInputData().getInstances());
		
		toLabel.setInputData(input);
		maxCount++;
	}
	public void removeLabelsForUndo(ParentLabel fromLabel,ParentLabel toLabel){
		boolean checkFrom = false;
		boolean checkTo = false;
		int CountTo=0;
		int indexList = 0;
		for (int i = 0; i < maxCount; i++) {
			if (toList.get(i) == toLabel) {
				CountTo++;
				checkTo = true;
				indexList = i;
				if(fromList.get(i)==fromLabel)
					checkFrom=true;
			}
		}
		removeLabelsToFrom(indexList,toLabel,CountTo==1);

		
	}
	private void removeLabelsToFrom(int indexList,ParentLabel la,boolean checkTo){
		toList.remove(indexList);
		fromList.remove(indexList);
		maxCount--;
		if(checkTo){
			la.setInputData(null);
			noneList.add(la);
			noneCount++;
		}
		
	}

	class LabelClickListener implements MouseMotionListener, MouseListener {
		ParentLabel la;
		boolean checkFrom = false;
		boolean checknone = false;
		boolean checkTo = false;
		int indexList = 0;
		int removeIndex = 0;
		int countFrom=0;
		int countTo=0;
		
		Point p;
		Point lp;
		@Override
		public void mouseClicked(MouseEvent e) {
			la = (ParentLabel) e.getSource();
			copyLabel = la;
			System.out.println(la.getName());
			if (e.getClickCount() == 2) { // 더블 클릭이면
				System.out.println(noneList.size());
				if (la.getInputData() == null) {
					
				}

				else{
					la.run();
					la.selected = false;
					la.setBorder(null);
/*					try {
						if(fromLabel == null
									&& fromLabel != la
									&& la.getInputData() != null
									&& !(la.getClass() == Class
											.forName("DMZ.gui.InputDataLabel") && la
											.getInputData().getInstances() == null))
						fromLabel=la;
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}*/
				}
			} else if (e.getButton() == 3) // 우클릭 이면
			{

				popUpMenu.add(deleteMenu);
				popUpMenu.show(la, e.getX(), e.getY());
				deleteMenu.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						for (int i = 0; i < maxCount; i++) {
							if (fromList.get(i) == la) {
								checkFrom = true;
							}
							if (toList.get(i) == la) {
								checkTo=true;
								indexList = i;
							}
						}
						if (checkFrom == false) {
							for (int i = 0; i < noneCount; i++) {
								if (noneList.get(i) == la) {
									checknone = true;
									indexList = i;
								}
							}
						}
						if (la.selected && checkFrom == false ) {
							if (maxCount != 0 && checknone == false && checkTo==true) {
								undoRedoStack.pushRemove(fromList.get(indexList),toList.get(indexList));
								undoRedoStack.stackRedoClear();
								toList.remove(indexList);
								for(int i =0;i<maxCount;i++){
									if(fromList.get(i)==fromList.get(indexList))
										countFrom++;
								}
								for(int i=0;i<maxCount-1;i++){
									if(toList.get(i)==fromList.get(indexList))
										countTo++;
								}
								if(countFrom==1 && countTo==0){
									noneList.add(fromList.get(indexList));
									noneCount++;
								}
								fromList.remove(indexList);								
								maxCount--;
								remove(la);
							} else if (checknone == true || noneCount == 0) {

								if (noneCount != 0) {
									undoRedoStack.pushRemove(noneList.get(indexList));
									undoRedoStack.stackRedoClear();
									noneCount--;
									noneList.remove(indexList);
								}
								remove(la);
							}
							else if(!checkFrom&&!checkTo&&!checknone){
								remove(la);
							}
							la.selected = false;
							fromLabel = null;
							repaint();
						}
						checkFrom = false;
						checknone = false;
						indexList = 0;
						countFrom=0;
						countTo=0;
						
					}

				});

			}

			else // 더블 클릭이 아니고 그냥 클릭일때
			{
			
				explainText.setText(la.getExpainText());
				if (!la.selected) { // 선택 안된 라벨이면
					
					if (tempLabel == null) {
						tempLabel = la;
					} else if (tempLabel != null && fromLabel == null) {
						tempLabel.selected = false;
						tempLabel.setBorder(null);
						tempLabel = la;
					}

					if(la.getColorType()==1){
					la.setBorder(line1); // 보더 넣어주고
					la.selected = true; // 선택된 상태
					}
					else if(la.getColorType()==2){
						la.setBorder(line2); // 보더 넣어주고
						la.selected = true; // 선택된 상태
					}
					else if(la.getColorType()==3){
						la.setBorder(line3); // 보더 넣어주고
						la.selected = true; // 선택된 상태
					}
					else if(la.getColorType()==4){
						la.setBorder(line4); // 보더 넣어주고
						la.selected = true; // 선택된 상태
					}
					else{
						la.setBorder(line5); // 보더 넣어주고
						la.selected = true; // 선택된 상태
						
					}
				
				
					la.requestFocus();

					try {
						if (fromLabel == null
								&& fromLabel != la
								&& la.getInputData() != null
								&& !(la.getClass() == Class
										.forName("DMZ.gui.InputDataLabel") && la
										.getInputData().getInstances() == null)) {
							
							fromLabel = la;
							
						} else if (fromLabel != null
								&& toLabel == null
								&& fromLabel.getInputData().getInstances() != null
								&& la.getInputData() == null && fromLabel != la ) {
						
							toLabel = la;
							
							if ((fromLabel.getLabelType() < toLabel.getLabelType())|| (fromLabel.getLabelType() == 2 && toLabel.getLabelType() == 2) ) {
							
								
								fromLabel.setBorder(null);
								toLabel.setBorder(null);
						
								fromLabel.selected = false;
								toLabel.selected = false;
								toList.add(toLabel);
								fromList.add(fromLabel);
								
								for (int i = 0; i < noneCount; i++) {
									if (noneList.get(i) == fromLabel) {
										checkFrom = true;
										removeIndex = i;
									}
								}
								if (checkFrom) {
									noneList.remove(removeIndex);
									noneCount--;
									removeIndex = 0;
									checkFrom = false;
								}
								for (int i = 0; i < noneCount; i++) {
									if (noneList.get(i) == toLabel) {
										removeIndex = i;
									}
								}
								noneList.remove(removeIndex);
								noneCount--;
								removeIndex = 0;

								maxCount++;
								
						
								InputData input = new InputData();
								input.setInstances(fromLabel.getInputData().getInstances());
								
								Date data=new Date();
								
								
								if(toLabel.getType()=="edit"){
									toLabel.setLabelFilePath("src/DMZ/tmpdata/"+((Long)data.getTime()).toString()+toLabel.getType()+editLabelCount+".arff");
									editLabelCount++;
								}
								else if(toLabel.getType()=="filter"){
									toLabel.setLabelFilePath("src/DMZ/tmpdata/"+((Long)data.getTime()).toString()+toLabel.getType()+filterLabelCount+".arff");
									filterLabelCount++;
								}
								else
									toLabel.setLabelFilePath(fromLabel.getLabelFilePath());
								
								
								if(toLabel.getType()=="analyze")
								{
									((AnalyzeLabel)toLabel).setFromLabel(fromLabel);
								}
								
								toLabel.setInputData(input);
								
								undoRedoStack.pushSetLine(fromLabel, toLabel);
								undoRedoStack.stackRedoClear();
							
								
								
							
								repaint();

							} else {
								fromLabel.selected = false;
								toLabel.selected = false;
								fromLabel.setBorder(null);
								toLabel.setBorder(null);

							}
							fromLabel = null;
							toLabel = null;
							tempLabel = null;

						} else {
							if (fromLabel != null) {
								fromLabel.selected = false;
								fromLabel.setBorder(null);
								fromLabel = la;
								fromLabel.selected = true;
								if(fromLabel.getColorType()==1){
								fromLabel.setBorder(line1);
								}
								else if(fromLabel.getColorType()==2){
									fromLabel.setBorder(line2); // 보더 넣어주고
								
								}
								else if(fromLabel.getColorType()==3){
									fromLabel.setBorder(line3); // 보더 넣어주고
								
								}
								else if(fromLabel.getColorType()==4){
									fromLabel.setBorder(line4); // 보더 넣어주고
								
								}
								else{
									fromLabel.setBorder(line5); // 보더 넣어주고
								
								}
								
								}

							toLabel = null;

						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else { 
					la.setBorder(null); // 보더 없애주고
					la.selected = false; // 선택 해제
					fromLabel = null;

				}

						}

		}

		public void mouseEntered(MouseEvent e) {
			explainText.setText(((ParentLabel)e.getSource()).getExpainText());
		}

		public void mouseExited(MouseEvent e) {
			explainText.setText(null);
		}

		public void mousePressed(MouseEvent e) {
			sp = e.getPoint();
			la =(ParentLabel)(e.getSource());
			lp = la.getLocation();
		}

		public void mouseReleased(MouseEvent e) {
			/*
			Point ep=e.getPoint();
			la=(ParentLabel)(e.getSource());
			Point p=la.getLocation();
			*/
			ParentLabel releasedP;
			releasedP = (ParentLabel)(e.getSource());
			if(checkDragg){
				System.out.println(releasedP.getX());
				System.out.println(releasedP.getY());
				System.out.println(lp);
				undoRedoStack.pushMove(la,lp.x,lp.y,releasedP.getX(),releasedP.getY());
				undoRedoStack.stackRedoClear();
				System.out.println(undoRedoStack.size());
				checkDragg =false;
			}
			

		}

		public void mouseDragged(MouseEvent e) {
			Point ep = e.getPoint();
			la = (ParentLabel) (e.getSource());
			p = la.getLocation();

			if (!la.selected) {
				checkDragg=true;
				la.setLocation(ep.x + p.x - sp.x, ep.y + p.y - sp.y);
				
			}

			repaint();
		}

		public void mouseMoved(MouseEvent e) {

		}

	}
	public UndoRedoStack getUndoRedoStack(){
		return undoRedoStack;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		Graphics2D g2D = (Graphics2D) g;
		g.fillRect(0, 0, 2000, 2000);
		//System.out.println("undoStack:"+undoRedoStack.size());
		for (int i = 0; i < maxCount; i++) {
			if (fromList.get(i) != null && toList.get(i) != null) {

				BasicStroke stroke = new BasicStroke(5.0f);
				((Graphics2D) g).setStroke(stroke);

				if(toList.get(i).getColorType()==1){
				g2D.setColor(inputColor);
				}
				else if(toList.get(i).getColorType()==2){
					g2D.setColor(visualizeColor);
				}
				else if(toList.get(i).getColorType()==3){
					g2D.setColor(preprocessColor);
				}
				else if(toList.get(i).getColorType()==4){
					g2D.setColor(dataminingColor);
				}
				else if(toList.get(i).getColorType()==5){
					g2D.setColor(analyzeColor);
					
				}
				getLocation(i);
				g2D.setStroke(new BasicStroke(3));
				da.drawArrow(g2D, fromList.get(i).getX() + FromX,fromList.get(i).getY() + FromY, toList.get(i).getX()+ ToX, toList.get(i).getY() + ToY);
				//da.drawArrow(g2D, fromList.get(i).getX(),fromList.get(i).getY(), toList.get(i).getX(), toList.get(i).getY());
			
				
			
			}
		
			   
		}


		if (this.getComponentCount() == 0) {
			rightPanel.removeAll();
			rightPanel.repaint();
		}

	}
	public ArrayList getFromList(){
		return fromList;
	}
	public ArrayList getToList(){
		return toList;
	}
	public ArrayList getNoneList(){
		return noneList;
	}
	public ArrayList<ParentLabel> getXmlList(){
		return this.xmlList;
	}
	public void setXmlList(ArrayList<ParentLabel> list){
		this.xmlList=list;
	}
	
	public void setMaxCount(String s){
		switch(s){
		case "+":
			maxCount++;
			break;
		case "-":
			maxCount--;
			break;
		}
		
	}
	public void setNoneCount(String s){
		switch(s){
		case "+":
			noneCount++;
			break;
		case "-":
			noneCount--;
			break;
		}
		
	}
	public void setFromList(ArrayList<ParentLabel> list){
		this.fromList=list;
	}
	public void setToList(ArrayList<ParentLabel> list){
		this.toList=list;
	}
	public void setNoneList(ArrayList<ParentLabel> list){
		this.noneList=list;
	}
	public void setNoneCount(int n){
		this.noneCount=n;
	}
	public void setMaxCount(int n){
		this.maxCount=n;
	}
	public void setExplainTextField(JTextField explainText)
	{
		this.explainText = explainText;
	}
}