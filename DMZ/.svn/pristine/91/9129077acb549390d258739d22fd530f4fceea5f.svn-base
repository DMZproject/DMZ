package DMZ.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import weka.classifiers.Classifier;
import weka.classifiers.evaluation.CostCurve;
import weka.classifiers.evaluation.MarginCurve;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.clusterers.Clusterer;
import weka.core.Attribute;
import weka.core.Drawable;
import weka.core.Instances;
import weka.core.Utils;
import weka.filters.Filter;
import weka.gui.PropertyDialog;
import weka.gui.ResultHistoryPanel;
import weka.gui.beans.CostBenefitAnalysis;
import weka.gui.explorer.AssociationsPanel;
import weka.gui.explorer.ClassifierPanel;
import weka.gui.explorer.ClustererPanel;
import weka.gui.explorer.PreprocessPanel;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;
import weka.gui.visualize.VisualizePanel;
import weka.gui.visualize.VisualizePanel2;

public class RightPanel extends JPanel {

	JPanel openFilePanel;
	JPanel instSummaryPanel;
	JPanel histogramPanel;

	JTextField explainText;

	PropertyDialog pd;
	RightTabbedPane tp;
	int AssociationLabelCount = 0;
	int ClusterLabelCount = 0;
	int ClassifyLabelCount = 0;
	Object filterName;


	String analyFromName;
	
	RightPanel thisPanel = this;
	PreprocessPanel pp;
	AssociationsPanel sp;
	ClustererPanel cp;
	ClassifierPanel fp;

	int spListSize = 0;
	int cpListSize = 0;
	int fpListSize = 0;
	public int associationCheck = 0;
	public int clusterCheck = 0;
	public int classifyCheck = 0;
	public int filterCheck = 0;

	JPanel jjp = new JPanel();
	ParentLabel cloneLa;
	AssociationLabel clone2La;
	ResultHistoryPanel rp;
	JPanel btnPanel;
	
	JButton visCost;
	JButton visCostBenefit;
	JButton visThreshold;
	JButton visMargin;
	JButton visGrph;
	JButton visErrors;
	

	String selectedName;

	VisualizePanel temp_vp = null;
	String temp_grph = null;
	ArrayList<Prediction> temp_preds = null;
	Attribute temp_classAtt = null;
	Classifier temp_classifier = null;
	Instances temp_trainHeader = null;
	
	
	
	VisualizePanel vp;
	String grph;
	ArrayList<Prediction> preds;
	Attribute classAtt;
	Classifier classifier;
	Instances trainHeader;

	RightPanel() {
		

	}

	public void setExplainTextField(JTextField explainText) {
		this.explainText = explainText;
	}

	public JTextField getExplainTextField() {
		return explainText;
	}

	void addOpenFilePanel(ParentLabel la) {

		removeAll();

		openFilePanel = la.getInputData().getOpenFilePanel();
		instSummaryPanel = la.getInputData().getInstancesSummaryPanel();
		la.getInputData().setLabel(la);

		openFilePanel.setVisible(true);
		instSummaryPanel.setVisible(true);

		setLayout(null);

		openFilePanel.setBackground(Color.white);
		instSummaryPanel.setBackground(Color.WHITE);
		add(openFilePanel);
		add(instSummaryPanel);

		validate();
		repaint();

	}

	void addHistogramPanel(ParentLabel histogramLabel) {
		removeAll();

		histogramPanel = histogramLabel.getInputData().getHistogram()
				.getHistogramPanel();
		histogramPanel.setVisible(true);

		setLayout(new BorderLayout());

		add(histogramPanel);

		validate();
		repaint();

	}

	void addScatterPlot(ParentLabel la) {
		int[] m_selectedAttribs;
		Instances m_data = null;
		JList m_attribList = new JList();
		String[] tempAttribNames;

		int extpad = 3;
		int cellSize = 3;
		int m_classIndex;
		JComboBox m_classAttrib = new JComboBox();

		String type;
		m_data = la.getInputData().getInstances();
		tempAttribNames = new String[m_data.numAttributes()];

		removeAll();
		m_classAttrib.removeAllItems();
		for (int i = 0; i < tempAttribNames.length; i++) {
			type = " (" + Attribute.typeToStringShort(m_data.attribute(i))
					+ ")";
			tempAttribNames[i] = new String("Colour: "
					+ m_data.attribute(i).name() + " " + type);
			m_classAttrib.addItem(tempAttribNames[i]);
		}
		if (m_data.classIndex() == -1) {
			m_classAttrib.setSelectedIndex(tempAttribNames.length - 1);
		} else {
			m_classAttrib.setSelectedIndex(m_data.classIndex());
		}
		m_attribList.setListData(tempAttribNames);
		m_attribList.setSelectionInterval(0, tempAttribNames.length - 1);
		m_classAttrib.setSelectedIndex(tempAttribNames.length - 1);
		m_classIndex = m_classAttrib.getSelectedIndex();
		m_selectedAttribs = m_attribList.getSelectedIndices();

		VisualizePanel2 vp = new VisualizePanel2();
		vp.setBackground(Color.white);

		try {
			PlotData2D pd = new PlotData2D(m_data);
			pd.setPlotName("Master Plot");
			vp.setMasterPlot(pd);
			// System.out.println("x: "+i+" y: "+j);
			vp.setXIndex(m_selectedAttribs[0]);
			vp.setYIndex(m_selectedAttribs[0]);
			vp.m_ColourCombo.setSelectedIndex(m_classIndex);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		vp.setSize(440, 600);
		vp.setVisible(true);
		setLayout(new BorderLayout());
		this.add(vp, BorderLayout.CENTER);
		validate();
		repaint();
	}

	void addAssociationPanel(AssociationLabel la) {
		sp = la.getSp();
		try {
			System.out.println(la.getInputData().getFilePath() + "@@");
			clusterCheck = 0;
			associationCheck = 1;
			classifyCheck = 0;
			filterCheck = 0;
			if (la.getId() == -1)
				la.setId(AssociationLabelCount++);

			int x = 0;
			int y = 0;
			removeAll();
			pd = new PropertyDialog();

			weka.gui.LogPanel lp = new weka.gui.LogPanel();

			// jf.getContentPane().add(lp, BorderLayout.SOUTH);

			sp.setRightPanel(this);
			sp.getAssociatorEditor().setRightPanel(this);
			sp.setInstances(la.getInputData().getInstances());

			if (((AssociationLabel) la).getAlgorithm() == "Apriori")
				sp.getAssociatorEditor().setApriori();
			else if (((AssociationLabel) la).getAlgorithm() == "FPGrowth")
				sp.getAssociatorEditor().setFPGrowth();

			JPanel jp = pd.initialize1(sp.getAssociatorEditor(), x, y);
			sp.setBackground(Color.white);
			sp.getHistory().setBackground(Color.WHITE);
			sp.add(sp.getHistory(), BorderLayout.SOUTH);
			
			
			// this.add(jp,BorderLayout.CENTER);
			tp = new RightTabbedPane(this, jp, sp, "Algorithm");
			validate();
			repaint();

			// this.add(sp,BorderLayout.CENTER); //ouput패널
			// this.add(lp,BorderLayout.SOUTH); //log 패널
			// sp.startAssociator();
			
			//------------------------
			/*
			if(sp.getHistory().getList().getModel().getSize()>=1){
	            System.out.println(sp.getHistory().getList().getModel().getElementAt(0));
	            StringBuffer buff = sp.getHistory().getResult().get(sp.getHistory().getList().getModel().getElementAt(0));
	            System.out.println(buff);
	            String str = buff.toString();
	            System.out.println(str.substring(str.indexOf("Minimum support: "),(str.indexOf("Generated sets of large itemsets:"))));
	            
	         }
	         */
			//-----------------------

		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		}
	}

	/*
	 * public void AssociationEdit(){ removeAll(); JPanel
	 * jp=pd.initialize(sp.getAssociatorEditor(), 0, 0); setLayout(new
	 * BorderLayout()); add(jp,BorderLayout.CENTER); validate(); repaint();
	 * 
	 * }
	 */
	public void AssociationOutput() {
		sp.startAssociator();
		tp.setSelectedIndex(1);
	}

	public void addClusterPanel(ClusterLabel la) {
		cp = la.getCp();

		try {
			System.out.println(la.getInputData().getFilePath() + "@@");
			associationCheck = 0;
			clusterCheck = 1;
			classifyCheck = 0;
			filterCheck = 0;
			int x = 0, y = 0;
			if (la.getId() == -1)
				la.setId(ClusterLabelCount++);

			pd = new PropertyDialog();
			removeAll();
			// add(sp, BorderLayout.CENTER);
			// weka.gui.LogPanel lp = new weka.gui.LogPanel();
			// sp.setLog(lp);
			// add(lp, BorderLayout.SOUTH);

			cp.setRightPanel(this);
			cp.getClustererEditor().setRightPanel(this);
			cp.setInstances(la.getInputData().getInstances());

			if (((ClusterLabel) la).getAlgorithm() == "k-means")
				cp.getClustererEditor().setKmeans();
			else if (((ClusterLabel) la).getAlgorithm() == "Density Based Cluster")
				cp.getClustererEditor().setDensityBasedClusterer();
			else if (((ClusterLabel) la).getAlgorithm() == "Hierarchical Cluster")
				cp.getClustererEditor().setHierarchicalClusterer();
			else if (((ClusterLabel) la).getAlgorithm() == "EM")
				cp.getClustererEditor().setEM();

			cp.getHistory().setBackground(Color.WHITE);
			cp.setBackground(Color.WHITE);
			cp.add(cp.getHistory(), BorderLayout.SOUTH);
			JPanel jp = pd.initialize1(cp.getClustererEditor(), x, y);
			JPanel trainPanel = new JPanel();
			trainPanel.setLayout(new BorderLayout());
			trainPanel.setBackground(Color.white);
			trainPanel.add(cp.getTrainPanel(), BorderLayout.NORTH);
			cp.setCheck(0);
			tp = new RightTabbedPane(this, trainPanel, jp, cp, "train");

			validate();
			repaint();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		}
	}

	public void ClusterOutput() {
		cp.startClusterer();
		tp.setSelectedIndex(2);
	}

	public void addClassifyPanel(ClassifyLabel la) {
		fp = la.getFp();
		try {
			associationCheck = 0;
			clusterCheck = 0;
			classifyCheck = 1;
			filterCheck = 0;
			System.out.println(la.getInputData().getFilePath() + "@@@");

			removeAll();
			int x = 0, y = 0;
			if (la.getId() == -1)
				la.setId(ClassifyLabelCount++);
			// weka.gui.LogPanel lp = new weka.gui.LogPanel();
			// fp.setLog(lp);
			// fp.add(lp, BorderLayout.SOUTH);

			pd = new PropertyDialog();
			fp.setRightPanel(this);
			fp.getClassifierEditor().setRightPanel(this);
			fp.setInstances(la.getInputData().getInstances());

			if (((ClassifyLabel) la).getAlgorithm() == "Decision Tree")
				fp.getClassifierEditor().setDecisionTree();
			else if (((ClassifyLabel) la).getAlgorithm() == "Neural Network")
				fp.getClassifierEditor().setNeuralNetwork();
			else if (((ClassifyLabel) la).getAlgorithm() == "KNN")
				fp.getClassifierEditor().setKnn();
			else if (((ClassifyLabel) la).getAlgorithm() == "Naive Bayes")
				fp.getClassifierEditor().setNaiveBayes();
			else if (((ClassifyLabel) la).getAlgorithm() == "SVM")
				fp.getClassifierEditor().setSVM();

			JPanel jp = pd.initialize1(fp.getClassifierEditor(), x, y);
			JPanel trainPanel = new JPanel();
			trainPanel.setLayout(new BorderLayout());
			fp.getTrainPanel().setBackground(Color.WHITE);
			fp.setBackground(Color.WHITE);
			fp.getHistory().setBackground(Color.WHITE);
			trainPanel.setBackground(Color.white);
			trainPanel.add(fp.getTrainPanel(), BorderLayout.NORTH);
			fp.setCheck(0);
			fp.add(fp.getHistory(), BorderLayout.SOUTH);
			tp = new RightTabbedPane(this, trainPanel, jp, fp, "train");

			repaint();
			validate();

		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		}

	}

	public void classifyOutput() {
		fp.startClassifier();
		tp.setSelectedIndex(2);
	}

	public void addPreprocessPanel(ParentLabel la) {
		try {
			clusterCheck = 0;
			classifyCheck = 0;
			filterCheck = 1;
			associationCheck = 0;
			removeAll();
			pp = ((FilterLabel) la).getPp();
			// weka.gui.LogPanel lp = new weka.gui.LogPanel();
			// sp.setLog(lp);

			pd = new PropertyDialog();
			pp.setLabel(la);
			pp.setInstances(la.getInputData().getInstances());
			pp.setRightPanel(this);
			pp.getFilterEditor().setRightPanel(this);
			JPanel jp = pd.initialize1(pp.getFilterEditor(), 0, 0);

			// 필터 추가

			if (((FilterLabel) la).getAlgorithm() == "normalize")
				pp.getFilterEditor().setNormalize(); // 나중에 필터추가
			else if (((FilterLabel) la).getAlgorithm() == "add")
				pp.getFilterEditor().setAdd();
			else if (((FilterLabel) la).getAlgorithm() == "addExpression")
				pp.getFilterEditor().setAddExpression();
			else if (((FilterLabel) la).getAlgorithm() == "addID")
				pp.getFilterEditor().setAddID();

			else if (((FilterLabel) la).getAlgorithm() == "copy")
				pp.getFilterEditor().setCopy();
			else if (((FilterLabel) la).getAlgorithm() == "discretize")
				pp.getFilterEditor().setDiscretize();
			else if (((FilterLabel) la).getAlgorithm() == "interquartileRange")
				pp.getFilterEditor().setInterquartileRange();
			else if (((FilterLabel) la).getAlgorithm() == "mergeTwoValues")
				pp.getFilterEditor().setMergeTwoValues();

			else if (((FilterLabel) la).getAlgorithm() == "nominalToBinary")
				pp.getFilterEditor().setNominalToBinary();
			else if (((FilterLabel) la).getAlgorithm() == "nominalToString")
				pp.getFilterEditor().setNominalToString();
			else if (((FilterLabel) la).getAlgorithm() == "numericCleaner")
				pp.getFilterEditor().setNumericCleaner();
			else if (((FilterLabel) la).getAlgorithm() == "numericToBinary")
				pp.getFilterEditor().setNumericToBinary();

			else if (((FilterLabel) la).getAlgorithm() == "numericToNominal")
				pp.getFilterEditor().setNumericToNominal();
			else if (((FilterLabel) la).getAlgorithm() == "numericTransform")
				pp.getFilterEditor().setNumericTransform();
			else if (((FilterLabel) la).getAlgorithm() == "removeType")
				pp.getFilterEditor().setRemoveType();
			else if (((FilterLabel) la).getAlgorithm() == "replaceMissingValues")
				pp.getFilterEditor().setReplaceMissingValues();

			else if (((FilterLabel) la).getAlgorithm() == "standardize")
				pp.getFilterEditor().setStandardize();
			else if (((FilterLabel) la).getAlgorithm() == "stringToNominal")
				pp.getFilterEditor().setStringToNominal();
			else if (((FilterLabel) la).getAlgorithm() == "removePercentage")
				pp.getFilterEditor().setRemovePercentage();
			else if (((FilterLabel) la).getAlgorithm() == "resample")
				pp.getFilterEditor().setResample();

			// else if(((FilterLabel)la).getAlgorithm()=="필터명")
			// pp.getFilterEditor().set필터명();

			// 여기에 추가

			setLayout(new BorderLayout());
			add(jp, BorderLayout.CENTER);
			setVisible(true);
			repaint();
			validate();

		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		}
	}

	public void filterApply() {
		pp.applyFilter((Filter) pp.getFilterEditor().getValue());
		filterName = pp.getFilterEditor().getValue();

	}

	public Object getFiterName() {
		return filterName;
	}

	public void addAnalyzePanel(ParentLabel la) {
		JButton analyzeBtn =  new JButton("알고리즘 비교");
		removeAll();
		analyFromName = la.getFromLabel().getName();
		
		if (la.getFromLabel().getType() == "association") {
			sp = ((AssociationLabel) la.getFromLabel()).getSp();
			
			btnPanel = new JPanel();
			btnPanel.setBackground(Color.white);
			btnPanel.setLayout(null);
			analyzeBtn.setBounds(10, 10, 200, 30);
		
			btnPanel.add(analyzeBtn);
			add(btnPanel, BorderLayout.CENTER);
			add(sp.getHistory(), BorderLayout.NORTH);
			analyzeBtn.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					newFrame(sp.getHistory(),analyFromName);
				}
				
				
			});

		} else if (la.getFromLabel().getType() == "cluster") {
			cp = ((ClusterLabel) la.getFromLabel()).getCp();
			cp.setCheck(1);
			btnPanel = new JPanel();
			btnPanel.setBackground(Color.white);
			JButton cBtn = new JButton("군집할당 시각화(cluster assignments)");
			JButton treeBtn = new JButton(" 트리 시각화");
			
			
			treeBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					int index = cp.getHistory().getList().getSelectedIndex();
					if (index != -1) {
						String selectedName = cp.getHistory().getNameAtIndex(
								index);
						ArrayList<Object> o = null;
						if (selectedName != null) {
							o = (ArrayList<Object>) cp.getHistory()
									.getNamedObject(selectedName);
						}
						String temp_grph = null;

					    if (o != null) {
					      for (int i = 0; i < o.size(); i++) {
					        Object temp = o.get(i);
					       
					       if (temp instanceof String) { // graphable output
					          temp_grph = (String) temp;
					        }
					      }
					    }
					    final String grph = temp_grph;
					    if (grph != null) {
					      
					            String title;
					            if (vp != null) {
					              title = vp.getName();
					            } else {
					              title = selectedName;
					            }
					            cp.visualizeTree(grph, title);
					          }
					       
					      }

					
				}
				
			});
			cBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					int index = cp.getHistory().getList().getSelectedIndex();
					if (index != -1) {
						String selectedName = cp.getHistory().getNameAtIndex(
								index);
						ArrayList<Object> o = null;
						if (selectedName != null) {
							o = (ArrayList<Object>) cp.getHistory()
									.getNamedObject(selectedName);
						}
						System.out.println(selectedName);
						VisualizePanel temp_vp = null;

						if (o != null) {
							for (int i = 0; i < o.size(); i++) {
								Object temp = o.get(i);

								if (temp instanceof VisualizePanel) { // normal
									temp_vp = (VisualizePanel) temp;
								}
							}

							final VisualizePanel vp = temp_vp;

							if (vp != null) {
								cp.visualizeClusterAssignments(vp);
							}
						}

					}
				}
			});
			cBtn.setBounds(10, 10, 200, 30);
			treeBtn.setBounds(220, 10, 200, 30);
			analyzeBtn.setBounds(10, 50, 200, 30);
			btnPanel.setLayout(null);
			btnPanel.add(cBtn);
			btnPanel.add(treeBtn);
			btnPanel.add(analyzeBtn);
			analyzeBtn.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					newFrame(cp.getHistory(),analyFromName);
				}
				
				
			});
			add(btnPanel, BorderLayout.CENTER);
			add(cp.getHistory(), BorderLayout.NORTH);
		} else if (la.getFromLabel().getType() == "classify") {

			visCost = new JButton("비용곡선(cost curve)");
			visCostBenefit = new JButton("비용(Cost)/이익(Benefit)곡선");
			visThreshold = new JButton("역치곡선(threshold curve)");
			visMargin = new JButton("마진곡선(margin curve)");
			visGrph = new JButton("트리 시각화(tree Visualize)");
			visErrors = new JButton("분류에러(Classifier errors)");

			visCost.setBounds(20, 10, 200, 30);
			visCostBenefit.setBounds(240, 10, 200, 30);
			visThreshold.setBounds(20, 45, 200, 30);
			visMargin.setBounds(240, 45, 200, 30);
			visGrph.setBounds(20, 80, 200, 30);
			visErrors.setBounds(240, 80, 200, 30);
			analyzeBtn.setBounds(20, 115, 200, 30);
			
			fp = ((ClassifyLabel) la.getFromLabel()).getFp();
		
			if(fp.getHistory().getList().getModel().getSize()==0)
			{
				 visCost.setEnabled(false);
				 visCostBenefit.setEnabled(false);
				 visThreshold.setEnabled(false);
				 visMargin.setEnabled(false);
				 visGrph.setEnabled(false);
				 visErrors.setEnabled(false);
				 analyzeBtn.setEnabled(false);
			}
			fp.setCheck(1);
			btnPanel = new JPanel();
			btnPanel.setBackground(Color.white);
			btnPanel.setLayout(null);


			visErrors.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					int index = fp.getHistory().getList().getSelectedIndex();
					if (index != -1) {
						String selectedName = fp.getHistory().getNameAtIndex(index);
						ArrayList<Object> o = null;
						if (selectedName != null) {
							o = (ArrayList<Object>) fp.getHistory().getNamedObject(selectedName);
						}
						temp_vp = null;
						temp_grph = null;
						temp_preds = null;
						temp_classAtt = null;
						temp_classifier = null;
						temp_trainHeader = null;

						if (o != null) {
							for (int i = 0; i < o.size(); i++) {
								Object temp = o.get(i);
								if (temp instanceof Classifier) {
									temp_classifier = (Classifier) temp;
								} else if (temp instanceof Instances) { // training
																		// header
									temp_trainHeader = (Instances) temp;
								} else if (temp instanceof VisualizePanel) { // normal
																				// errors
									temp_vp = (VisualizePanel) temp;
								} else if (temp instanceof String) { // graphable
																		// output
									temp_grph = (String) temp;
								} else if (temp instanceof ArrayList<?>) { // predictions
									temp_preds = (ArrayList<Prediction>) temp;
								} else if (temp instanceof Attribute) { // class
																		// attribute
									temp_classAtt = (Attribute) temp;
								}
							}
						}

						vp = temp_vp;
						grph = temp_grph;
						preds = temp_preds;
						classAtt = temp_classAtt;
						classifier = temp_classifier;
						trainHeader = temp_trainHeader;
					}

					if (vp != null) {
						if ((vp.getXIndex() == 0) && (vp.getYIndex() == 1)) {
							try {
								vp.setXIndex(vp.getInstances().classIndex()); // class
								vp.setYIndex(vp.getInstances().classIndex() - 1); // predicted
																					// class
							} catch (Exception e) {
								// ignored
							}
						}
						fp.visualizeClassifierErrors(vp);
					} else {
						visErrors.setEnabled(false);
					}
					
				}

			});
			visGrph.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = fp.getHistory().getList().getSelectedIndex();
					if (index != -1) {
						String selectedName = fp.getHistory().getNameAtIndex(index);
						ArrayList<Object> o = null;
						if (selectedName != null) {
							o = (ArrayList<Object>) fp.getHistory()
									.getNamedObject(selectedName);
						}
						temp_vp = null;
						temp_grph = null;
						temp_preds = null;
						temp_classAtt = null;
						temp_classifier = null;
						temp_trainHeader = null;

						if (o != null) {
							for (int i = 0; i < o.size(); i++) {
								Object temp = o.get(i);
								if (temp instanceof Classifier) {
									temp_classifier = (Classifier) temp;
								} else if (temp instanceof Instances) { // training
																		// header
									temp_trainHeader = (Instances) temp;
								} else if (temp instanceof VisualizePanel) { // normal
																				// errors
									temp_vp = (VisualizePanel) temp;
								} else if (temp instanceof String) { // graphable
																		// output
									temp_grph = (String) temp;
								} else if (temp instanceof ArrayList<?>) { // predictions
									temp_preds = (ArrayList<Prediction>) temp;
								} else if (temp instanceof Attribute) { // class
																		// attribute
									temp_classAtt = (Attribute) temp;
								}
							}
						}

						vp = temp_vp;
						grph = temp_grph;
						preds = temp_preds;
						classAtt = temp_classAtt;
						classifier = temp_classifier;
						trainHeader = temp_trainHeader;
					}
					if (grph != null) {
						if (((Drawable) temp_classifier).graphType() == Drawable.TREE) {
							String title;
							if (vp != null) {
								title = vp.getName();
							} else {
								title = selectedName;
							}
							fp.visualizeTree(grph, title);

						} else {
							visGrph.setEnabled(false);
						}
					} else {
						visGrph.setEnabled(false);
					}
					
				}
			});

			visMargin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = fp.getHistory().getList().getSelectedIndex();
					if (index != -1) {
						String selectedName = fp.getHistory().getNameAtIndex(index);
						ArrayList<Object> o = null;
						if (selectedName != null) {
							o = (ArrayList<Object>) fp.getHistory().getNamedObject(selectedName);
						}
						temp_vp = null;
						temp_grph = null;
						temp_preds = null;
						temp_classAtt = null;
						temp_classifier = null;
						temp_trainHeader = null;

						if (o != null) {
							for (int i = 0; i < o.size(); i++) {
								Object temp = o.get(i);
								if (temp instanceof Classifier) {
									temp_classifier = (Classifier) temp;
								} else if (temp instanceof Instances) { // training
																		// header
									temp_trainHeader = (Instances) temp;
								} else if (temp instanceof VisualizePanel) { // normal
																				// errors
									temp_vp = (VisualizePanel) temp;
								} else if (temp instanceof String) { // graphable
																		// output
									temp_grph = (String) temp;
								} else if (temp instanceof ArrayList<?>) { // predictions
									temp_preds = (ArrayList<Prediction>) temp;
								} else if (temp instanceof Attribute) { // class
																		// attribute
									temp_classAtt = (Attribute) temp;
								}
							}
						}

						vp = temp_vp;
						grph = temp_grph;
						preds = temp_preds;
						classAtt = temp_classAtt;
						classifier = temp_classifier;
						trainHeader = temp_trainHeader;
					}
					
					if ((preds != null) && (classAtt != null) && (classAtt.isNominal())) {

						try {
							MarginCurve tc = new MarginCurve();
							Instances result = tc.getCurve(preds);
							VisualizePanel vmc = new VisualizePanel();
							vmc.setName(result.relationName());
							PlotData2D tempd = new PlotData2D(result);
							tempd.setPlotName(result.relationName());
							tempd.addInstanceNumberAttribute();
							vmc.addPlot(tempd);
							fp.visualizeClassifierErrors(vmc);
						} catch (Exception ex) {
							ex.printStackTrace();
						}

					} else {
						visMargin.setEnabled(false);
					}
				
				}

			});
			
			visCost.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					int index = fp.getHistory().getList().getSelectedIndex();
					if (index != -1) {
						String selectedName = fp.getHistory().getNameAtIndex(index);
						ArrayList<Object> o = null;
						if (selectedName != null) {
							o = (ArrayList<Object>) fp.getHistory().getNamedObject(selectedName);
						}
						
						temp_vp = null;
						temp_grph = null;
						temp_preds = null;
						temp_classAtt = null;
						temp_classifier = null;
						temp_trainHeader = null;

						if (o != null) {
							for (int i = 0; i < o.size(); i++) {
								Object temp = o.get(i);
								if (temp instanceof Classifier) {
									temp_classifier = (Classifier) temp;
								} else if (temp instanceof Instances) { 
																		
									temp_trainHeader = (Instances) temp;
								} else if (temp instanceof VisualizePanel) {
																			
									temp_vp = (VisualizePanel) temp;
								} else if (temp instanceof String) { 
																		
									temp_grph = (String) temp;
								} else if (temp instanceof ArrayList<?>) { 
									temp_preds = (ArrayList<Prediction>) temp;
								} else if (temp instanceof Attribute) { 
																		
									temp_classAtt = (Attribute) temp;
								}
							}
						}

						vp = temp_vp;
						grph = temp_grph;
						preds = temp_preds;
						classAtt = temp_classAtt;
						classifier = temp_classifier;
						trainHeader = temp_trainHeader;
					}

					JPopupMenu popUpMenu = new JPopupMenu();
					

				
					 if ((preds != null) && (classAtt != null) && (classAtt.isNominal())) {
					      for (int i = 0; i < classAtt.numValues(); i++) {
					        JMenuItem clv = new JMenuItem(classAtt.value(i));
					        final int classValue = i;
					        clv.addActionListener(new ActionListener() {
					          @Override
					          public void actionPerformed(ActionEvent e) {
					            try {
					              CostCurve cc = new CostCurve();
					              Instances result = cc.getCurve(preds, classValue);
					              VisualizePanel vmc = new VisualizePanel();
					             
					              vmc.setName(result.relationName() + ". (Class value "
					                + classAtt.value(classValue) + ")");
					              PlotData2D tempd = new PlotData2D(result);
					              tempd.m_displayAllPoints = true;
					              tempd.setPlotName(result.relationName());
					              boolean[] connectPoints = new boolean[result.numInstances()];
					              for (int jj = 1; jj < connectPoints.length; jj += 2) {
					                connectPoints[jj] = true;
					              }
					              tempd.setConnectPoints(connectPoints);
					              // tempd.addInstanceNumberAttribute();
					              vmc.addPlot(tempd);
					              fp.visualizeClassifierErrors(vmc);
					            } catch (Exception ex) {
					              ex.printStackTrace();
					            }
					          }
					        });
					        popUpMenu.add(clv);
					      
					      }
					    } else {
					      visCost.setEnabled(false);
					    }

						popUpMenu.show(btnPanel, visCost.getX()+visCost.getWidth(),visCost.getY());
					
					}
				
			});
			
			
			visCostBenefit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					int index = fp.getHistory().getList().getSelectedIndex();
					if (index != -1) {
						String selectedName = fp.getHistory().getNameAtIndex(index);
						ArrayList<Object> o = null;
						if (selectedName != null) {
							o = (ArrayList<Object>) fp.getHistory().getNamedObject(selectedName);
						}
						
						temp_vp = null;
						temp_grph = null;
						temp_preds = null;
						temp_classAtt = null;
						temp_classifier = null;
						temp_trainHeader = null;

						if (o != null) {
							for (int i = 0; i < o.size(); i++) {
								Object temp = o.get(i);
								if (temp instanceof Classifier) {
									temp_classifier = (Classifier) temp;
								} else if (temp instanceof Instances) { 
																		
									temp_trainHeader = (Instances) temp;
								} else if (temp instanceof VisualizePanel) {
																			
									temp_vp = (VisualizePanel) temp;
								} else if (temp instanceof String) { 
																		
									temp_grph = (String) temp;
								} else if (temp instanceof ArrayList<?>) { 
									temp_preds = (ArrayList<Prediction>) temp;
								} else if (temp instanceof Attribute) { 
																		
									temp_classAtt = (Attribute) temp;
								}
							}
						}

						vp = temp_vp;
						grph = temp_grph;
						preds = temp_preds;
						classAtt = temp_classAtt;
						classifier = temp_classifier;
						trainHeader = temp_trainHeader;
					}

					JPopupMenu popUpMenu = new JPopupMenu();
					
				    if ((preds != null) && (classAtt != null) && (classAtt.isNominal())) {
				        for (int i = 0; i < classAtt.numValues(); i++) {
				          JMenuItem clv = new JMenuItem(classAtt.value(i));
				          final int classValue = i;
				          clv.addActionListener(new ActionListener() {
				            @Override
				            public void actionPerformed(ActionEvent e) {
				              try {
				                ThresholdCurve tc = new ThresholdCurve();
				                Instances result = tc.getCurve(preds, classValue);

				                // Create a dummy class attribute with the chosen
				                // class value as index 0 (if necessary).
				                Attribute classAttToUse = classAtt;
				                if (classValue != 0) {
				                  ArrayList<String> newNames = new ArrayList<String>();
				                  newNames.add(classAtt.value(classValue));
				                  for (int k = 0; k < classAtt.numValues(); k++) {
				                    if (k != classValue) {
				                      newNames.add(classAtt.value(k));
				                    }
				                  }
				                  classAttToUse = new Attribute(classAtt.name(), newNames);
				                }

				                CostBenefitAnalysis cbAnalysis = new CostBenefitAnalysis();

				                PlotData2D tempd = new PlotData2D(result);
				                tempd.setPlotName(result.relationName());
				                tempd.m_alwaysDisplayPointsOfThisSize = 10;
				                // specify which points are connected
				                boolean[] cp = new boolean[result.numInstances()];
				                for (int n = 1; n < cp.length; n++) {
				                  cp[n] = true;
				                }
				                tempd.setConnectPoints(cp);

				                String windowTitle = "";
				                if (classifier != null) {
				                  String cname = classifier.getClass().getName();
				                  if (cname.startsWith("weka.classifiers.")) {
				                    windowTitle = ""
				                      + cname.substring("weka.classifiers.".length()) + " ";
				                  }
				                }
				                windowTitle += " (class = " + classAttToUse.value(0) + ")";

				                // add plot
				                cbAnalysis.setCurveData(tempd, classAttToUse);
				                fp.visualizeCostBenefitAnalysis(cbAnalysis, windowTitle);
				              } catch (Exception ex) {
				                ex.printStackTrace();
				              }
				            }
				          });
				          popUpMenu.add(clv);
				        }
				      } else {
				        visCostBenefit.setEnabled(false);
				      }
				    //위치 나중에 조정
					popUpMenu.show(btnPanel, visCostBenefit.getX()+visCostBenefit.getWidth(),visCostBenefit.getY());
					
				}
				
			});
			
			visThreshold.addActionListener(new ActionListener(){

			
				public void actionPerformed(ActionEvent e) {
					int index = fp.getHistory().getList().getSelectedIndex();
					if (index != -1) {
						String selectedName = fp.getHistory().getNameAtIndex(index);
						ArrayList<Object> o = null;
						if (selectedName != null) {
							o = (ArrayList<Object>) fp.getHistory().getNamedObject(selectedName);
						}
						
						temp_vp = null;
						temp_grph = null;
						temp_preds = null;
						temp_classAtt = null;
						temp_classifier = null;
						temp_trainHeader = null;

						if (o != null) {
							for (int i = 0; i < o.size(); i++) {
								Object temp = o.get(i);
								if (temp instanceof Classifier) {
									temp_classifier = (Classifier) temp;
								} else if (temp instanceof Instances) { 
																		
									temp_trainHeader = (Instances) temp;
								} else if (temp instanceof VisualizePanel) {
																			
									temp_vp = (VisualizePanel) temp;
								} else if (temp instanceof String) { 
																		
									temp_grph = (String) temp;
								} else if (temp instanceof ArrayList<?>) { 
									temp_preds = (ArrayList<Prediction>) temp;
								} else if (temp instanceof Attribute) { 
																		
									temp_classAtt = (Attribute) temp;
								}
							}
						}

						vp = temp_vp;
						grph = temp_grph;
						preds = temp_preds;
						classAtt = temp_classAtt;
						classifier = temp_classifier;
						trainHeader = temp_trainHeader;
					}

					JPopupMenu popUpMenu = new JPopupMenu();
					
					if ((preds != null) && (classAtt != null) && (classAtt.isNominal())) {
					      for (int i = 0; i < classAtt.numValues(); i++) {
					        JMenuItem clv = new JMenuItem(classAtt.value(i));
					        final int classValue = i;
					        clv.addActionListener(new ActionListener() {
					          @Override
					          public void actionPerformed(ActionEvent e) {
					            try {
					              ThresholdCurve tc = new ThresholdCurve();
					              Instances result = tc.getCurve(preds, classValue);
					              // VisualizePanel vmc = new VisualizePanel();
					              ThresholdVisualizePanel vmc = new ThresholdVisualizePanel();
					              vmc.setROCString("(Area under ROC = "
					                + Utils.doubleToString(ThresholdCurve.getROCArea(result), 4)
					                + ")");
					         
					              vmc.setName(result.relationName() + ". (Class value "
					                + classAtt.value(classValue) + ")");
					              PlotData2D tempd = new PlotData2D(result);
					              tempd.setPlotName(result.relationName());
					              tempd.addInstanceNumberAttribute();
					              // specify which points are connected
					              boolean[] cp = new boolean[result.numInstances()];
					              for (int n = 1; n < cp.length; n++) {
					                cp[n] = true;
					              }
					              tempd.setConnectPoints(cp);
					              // add plot
					              vmc.addPlot(tempd);
					              fp.visualizeClassifierErrors(vmc);
					            } catch (Exception ex) {
					              ex.printStackTrace();
					            }
					          }
					        });
					        popUpMenu.add(clv);
					      }
					    } else {
					      visThreshold.setEnabled(false);
					    }
					popUpMenu.show(btnPanel,visThreshold.getX()+visThreshold.getWidth(),visThreshold.getY());
				}
				
			});
		
			btnPanel.add(visCost);
			btnPanel.add(visCostBenefit);
			btnPanel.add(visThreshold);
			btnPanel.add(visMargin);
			btnPanel.add(visGrph);
			btnPanel.add(visErrors);
			btnPanel.add(analyzeBtn);
			analyzeBtn.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					newFrame(fp.getHistory(),analyFromName);
				}
				
				
			});
			add(btnPanel, BorderLayout.CENTER);

			add(fp.getHistory(), BorderLayout.NORTH);
		}

		validate();
		repaint();

	}
	
	
//	if(sp.getHistory().getList().getModel().getSize()>=1){
//		System.out.println(sp.getHistory().getList().getModel().getElementAt(0));
//		StringBuffer buff = sp.getHistory().getResult().get(sp.getHistory().getList().getModel().getElementAt(0));
//		System.out.println(buff);
//		String str = buff.toString();
//		System.out.println(str.substring(str.indexOf("Minimum support: "),(str.indexOf("Generated sets of large itemsets:"))));
//		openFrame(str.substring(str.indexOf("Minimum support: "),(str.indexOf("Generated sets of large itemsets:"))));
//		
//	}

	public void newFrame(ResultHistoryPanel history, String type) {
		Hashtable<String, StringBuffer> result = history.getResult();
		System.out.println(history.getList().getModel().getSize());
		JFrame jf = new JFrame("비교");
		switch(type){
		
		case "apriori":
			for (int i = 0; i < history.getList().getModel().getSize(); i++) {
				StringBuffer buff = result.get(history.getNameAtIndex(i));
				if ((buff != null)) {
					JTextArea ta = new JTextArea();
					ta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
					ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
					ta.setEditable(false);
					String str = buff.toString();
					ta.append(str.substring(str.indexOf("=== Run information "),(str.indexOf("Relation:"))));
					
					ta.append(str.substring(str.indexOf("Minimum support: "),(str.indexOf("Best rules found:"))));
				
					jf.getContentPane().setLayout(new GridLayout(1, 4));
					jf.getContentPane().add(new JScrollPane(ta));
					jf.pack();
				}

				}
		break;
		case "fPGrowth":
			for (int i = 0; i < history.getList().getModel().getSize(); i++) {
				StringBuffer buff = result.get(history.getNameAtIndex(i));
				if ((buff != null)) {
					JTextArea ta = new JTextArea();
					ta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
					ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
					ta.setEditable(false);
					String str = buff.toString();
					ta.append(str.substring(str.indexOf("=== Run information "),(str.indexOf("Relation:"))));
					
					ta.append(str.substring(str.indexOf("FPGrowth found")));
				
					jf.getContentPane().setLayout(new GridLayout(1, 4));
					jf.getContentPane().add(new JScrollPane(ta));
					jf.pack();
				}

				}
		break;
		case "kMeans":
			for (int i = 0; i < history.getList().getModel().getSize(); i++) {
				StringBuffer buff = result.get(history.getNameAtIndex(i));
				if ((buff != null)) {
					JTextArea ta = new JTextArea();
					ta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
					ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
					ta.setEditable(false);
					String str = buff.toString();
					
					ta.append(str.substring(str.indexOf("=== Run information "),(str.indexOf("Relation:"))));
					
					ta.append(str.substring(str.indexOf("Test mode: "),(str.indexOf("=== Clustering model"))));
					ta.append(str.substring(str.indexOf("Number of iterations:"),(str.indexOf("Within cluster sum of "))));
					ta.append(str.substring(str.indexOf("Within cluster sum of "),(str.indexOf("Initial starting points"))));
					ta.append(str.substring(str.indexOf("Initial starting points "),(str.indexOf("Missing values globally"))));
					ta.append(str.substring(str.indexOf("Final cluster centroids:"),(str.indexOf("======================"))));
					ta.append(str.substring(str.indexOf("Time taken to build model"),(str.indexOf("=== Model and evaluation "))));
					ta.append(str.substring(str.indexOf("=== Model and evaluation")));
					jf.getContentPane().setLayout(new GridLayout(1, 4));
					jf.getContentPane().add(new JScrollPane(ta));
					jf.pack();
				}

				}
		break;
		case "densityBasedCluster":
			for (int i = 0; i < history.getList().getModel().getSize(); i++) {
				StringBuffer buff = result.get(history.getNameAtIndex(i));
				if ((buff != null)) {
					JTextArea ta = new JTextArea();
					ta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
					ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
					ta.setEditable(false);
					String str = buff.toString();
					ta.append(str.substring(str.indexOf("=== Run information "),(str.indexOf("Relation:"))));
					
					String substr =	str.substring(str.indexOf("Number of iterations: ")+22,(str.indexOf("Within cluster")-1));
					System.out.println("substr~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+substr);
					int clusterNum = Integer.parseInt(substr);

					ta.append(str.substring(str.indexOf("Test mode: "),(str.indexOf("=== Clustering"))));
					ta.append(str.substring(str.indexOf("Number of iterations: "),(str.indexOf("==========================="))));
					ta.append(str.substring(str.indexOf("Fitted estimators"),(str.indexOf("Attribute:"))));
					for(int i1 = 0 ; i1< clusterNum;i1++)
					ta.append(str.substring(str.indexOf("Cluster: "),(str.indexOf("Attribute:"))));
					ta.append(str.substring(str.indexOf("Time taken to build model ")));
					
					jf.getContentPane().setLayout(new GridLayout(1, 4));
					jf.getContentPane().add(new JScrollPane(ta));
					jf.pack();
				}

				}
		break;
		case "hierarchicalCluster":
			for (int i = 0; i < history.getList().getModel().getSize(); i++) {
				StringBuffer buff = result.get(history.getNameAtIndex(i));
				if ((buff != null)) {
					JTextArea ta = new JTextArea();
					ta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
					ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
					ta.setEditable(false);
					String str = buff.toString();
					ta.append(str.substring(str.indexOf("=== Run information "),(str.indexOf("Relation:"))));
					
					ta.append(str.substring(str.indexOf("Test mode: "),(str.indexOf("=== Clustering"))));
					ta.append(str.substring(str.indexOf("Time taken to build model ")));
					
					
					jf.getContentPane().setLayout(new GridLayout(1, 4));
					jf.getContentPane().add(new JScrollPane(ta));
					jf.pack();
				}

				}
		break;
		case "em":
			for (int i = 0; i < history.getList().getModel().getSize(); i++) {
				StringBuffer buff = result.get(history.getNameAtIndex(i));
				if ((buff != null)) {
					JTextArea ta = new JTextArea();
					ta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
					ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
					ta.setEditable(false);
					String str = buff.toString();
					ta.append(str.substring(str.indexOf("=== Run information "),(str.indexOf("Relation:"))));
					
					ta.append(str.substring(str.indexOf("Test mode: "),(str.indexOf("=== Clustering"))));
					ta.append(str.substring(str.indexOf("Number of "),(str.indexOf("                Cluster"))));
					ta.append(str.substring(str.indexOf("Time taken to build model ")));
					
					jf.getContentPane().setLayout(new GridLayout(1, 4));
					jf.getContentPane().add(new JScrollPane(ta));
					jf.pack();
				}

				}
		break;
		case "neuralNetwork":
			for (int i = 0; i < history.getList().getModel().getSize(); i++) {
				StringBuffer buff = result.get(history.getNameAtIndex(i));
				if ((buff != null)) {
					JTextArea ta = new JTextArea();
					ta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
					ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
					ta.setEditable(false);
					String str = buff.toString();
					ta.append(str.substring(str.indexOf("=== Run information "),(str.indexOf("Relation:"))));
					
					ta.append(str.substring(str.indexOf("Minimum support: "),(str.indexOf("Best rules found:"))));
				
					jf.getContentPane().setLayout(new GridLayout(1, 4));
					jf.getContentPane().add(new JScrollPane(ta));
					jf.pack();
				}

				}
		break;
		case "kNearestNeighbors":
			for (int i = 0; i < history.getList().getModel().getSize(); i++) {
				StringBuffer buff = result.get(history.getNameAtIndex(i));
				if ((buff != null)) {
					JTextArea ta = new JTextArea();
					ta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
					ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
					ta.setEditable(false);
					String str = buff.toString();
					ta.append(str.substring(str.indexOf("=== Run information "),(str.indexOf("Relation:"))));
					
					ta.append(str.substring(str.indexOf("Minimum support: "),(str.indexOf("Best rules found:"))));
				
					jf.getContentPane().setLayout(new GridLayout(1, 4));
					jf.getContentPane().add(new JScrollPane(ta));
					jf.pack();
				}

				}
		break;
		case "naiveBayes":
			for (int i = 0; i < history.getList().getModel().getSize(); i++) {
				StringBuffer buff = result.get(history.getNameAtIndex(i));
				if ((buff != null)) {
					JTextArea ta = new JTextArea();
					ta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
					ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
					ta.setEditable(false);
					String str = buff.toString();
					
					ta.append(str.substring(str.indexOf("=== Run information "),(str.indexOf("Relation:"))));
				
					jf.getContentPane().setLayout(new GridLayout(1, 4));
					jf.getContentPane().add(new JScrollPane(ta));
					jf.pack();
				}

				}
		break;
		case "svm":
			for (int i = 0; i < history.getList().getModel().getSize(); i++) {
				StringBuffer buff = result.get(history.getNameAtIndex(i));
				if ((buff != null)) {
					JTextArea ta = new JTextArea();
					ta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
					ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
					ta.setEditable(false);
					String str = buff.toString();
					ta.append(str.substring(str.indexOf("=== Run information "),(str.indexOf("Relation:"))));
					
					ta.append(str.substring(str.indexOf("Minimum support: "),(str.indexOf("Best rules found:"))));
				
					jf.getContentPane().setLayout(new GridLayout(1, 4));
					jf.getContentPane().add(new JScrollPane(ta));
					jf.pack();
				}

				}
		break;
				
		case "decisionTree":
			ArrayList<Double> correctly = new ArrayList();
			ArrayList<JTextArea> decisionScrollPane = new ArrayList(); 
			int index;
			for (int i = 0; i < history.getList().getModel().getSize(); i++) {
				StringBuffer buff = result.get(history.getNameAtIndex(i));
				if ((buff != null)) {
					JTextArea ta = new JTextArea();
					ta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
					ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
					ta.setEditable(false);
					String str = buff.toString();
					ta.append(str.substring(str.indexOf("=== 실행 정보 ==="),(str.indexOf("데이터:"))));
					
					ta.append(str.substring(str.indexOf("테스트 모드:    "),(str.indexOf("=== 분류(Classifier)"))));
					ta.append(str.substring(str.indexOf("단말노드 개수  : "),(str.indexOf("=== 요약"))));
					ta.append(str.substring(str.indexOf("=== 요약"),(str.indexOf("=== 클래스별 세부 정확도"))));
				//	System.out.println("decisionTree~~~~~~~~~~~~~~~~~~~~~~~"
				//			+str.substring(str.indexOf("상대적 제곱근 오차")+24,str.indexOf("vud")-2)
				//			+str.substring(str.indexOf("상대저")+28,str.indexOf("Coverage of")-2));
					correctly.add(Double.parseDouble(str.substring(str.indexOf("부정확하게")-10,str.indexOf("부정확하게")-3)));
				//	relativeerror = Integer.parseInt(str.substring(str.indexOf("상대적 제곱근")+28,str.indexOf("경우의 범위")-2));
					
					
					decisionScrollPane.add(ta);
					jf.getContentPane().setLayout(new GridLayout(1, 4));
					jf.getContentPane().add(new JScrollPane(decisionScrollPane.get(decisionScrollPane.size()-1)));
					jf.pack();
				}

			}
			index = getMax(correctly);
			decisionScrollPane.get(index).setBackground(new Color(255,167,167));
			break;
		case "cluster":
			
		
		}

		jf.setLocation(200, 50);
		jf.setSize(1200, 800);
		jf.setVisible(true);
	}
	/*
	public void openFrame(JList list) {
		
	    if ((list != null) ) {
	    
	      JTextArea ta = new JTextArea();
	      ta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	      ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
	      ta.setEditable(false);
	     

	      final JFrame jf = new JFrame("알고리즘 비교 결과");
	      jf.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {

	          jf.dispose();
	        }
	      });
	      jf.getContentPane().setLayout(new GridLayout(4,1));
	      for(int i = 0; i< list.getModel().getSize();i++)
	      {
	    	  ta.setText(list.getModel().getElementAt(i).toString());
	    	  jf.getContentPane().add(new JScrollPane(ta), BorderLayout.CENTER);
	      }
	    	  jf.pack();
	      jf.setSize(450, 350);
	      jf.setVisible(true);
	     
	    }
	  }
*/
	  public int getMax(ArrayList<Double> list){
         Double max=0.0;
         int index=0;
         max = list.get(0);
         for(int i=0; i<list.size(); i++){
            if(list.get(i)>max){
               max = list.get(i);
               index = i;
            }
         }
         
         return index;
         
      }
}

