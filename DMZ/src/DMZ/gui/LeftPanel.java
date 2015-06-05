package DMZ.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LeftPanel extends JPanel {
	
	Cursor cursor;

	Toolkit tk = Toolkit.getDefaultToolkit();
	int cw,ch;
	
	int verticalBar = 0;
	int horizontalBar = 0;
	
	public void setVerticalBar(int verticalBar){
		this.verticalBar=verticalBar;
	}
	public void setHorizontalBar(int horizontalBar){
		this.horizontalBar=horizontalBar;
	}
	ParentLabel dragLabel;
	Point dragPoint;
	// 좌특 패널 버튼
	// 데이터 입력
	RightPanel rightPanel;
	JButton inputBtn = new JButton();
	boolean checkDrag = false;
	JLabel imageTest = new JLabel();
	
	JTextField tf;
	public boolean getCheckDrag(){
		return checkDrag;
		
	}

	InputDataLabel openDataLabel = new InputDataLabel();
	EditDataLabel editDataLabel = new EditDataLabel();
	SaveDataLabel saveDataLabel = new SaveDataLabel();

	// 데이터 시각화
	JButton visualizeBtn = new JButton();
	HistogramLabel histogramLabel = new HistogramLabel();
	ScatterPlotLabel plotLabel = new ScatterPlotLabel();
	
	
	// 데이터 전처리
	JButton preprocessBtn = new JButton();
	
	JPanel proprocessScrollPane = new JPanel();

	
	//속성필터 30개중에 10개 제
	/*
	FilterLabel classAssignerLabel = new FilterLabel("ClassAssigner"); FilterLabel makeIndicatorLabel = new FilterLabel("MakeIndicator");
	FilterLabel pkiDicretizeLabel = new FilterLabel("PKIDiscretize");FilterLabel reorderLabel = new FilterLabel("Reorder");
	FilterLabel obfuscateLabel = new FilterLabel("Obfuscate");FilterLabel removeFoldsLabel = new FilterLabel("RemoveFolds");
	FilterLabel removeMisclassifiedLabel = new FilterLabel("RemoveMisclassified");FilterLabel mathExpressionLabel = new FilterLabel("MathExpression");
	FilterLabel reservoirSampleLabel = new FilterLabel("ReservoirSample");FilterLabel filterLabel30 = new FilterLabel("filter1");
	*/

	FilterLabel normalizeLabel = new FilterLabel("normalize");				FilterLabel addLabel = new FilterLabel("add");
	FilterLabel addExpressionLabel = new FilterLabel("addExpression");		FilterLabel addIDLabel = new FilterLabel("addID");
	FilterLabel copyLabel = new FilterLabel("copy");						FilterLabel numericTransformLabel = new FilterLabel("numericTransform");
	FilterLabel discretizeLabel = new FilterLabel("discretize");			FilterLabel interquartileRangeLabel = new FilterLabel("interquartileRange");
	FilterLabel removeTypeLabel = new FilterLabel("removeType");			FilterLabel replaceMissingValuesLabel = new FilterLabel("replaceMissingValues");
	FilterLabel mergeTwoValuesLabel = new FilterLabel("mergeTwoValues");	FilterLabel nominalToBinaryLabel = new FilterLabel("nominalToBinary");
	FilterLabel nominalToStringLabel = new FilterLabel("nominalToString");	FilterLabel numericCleanerLabel = new FilterLabel("numericCleaner");
	FilterLabel numericToBinaryLabel = new FilterLabel("numericToBinary");	FilterLabel numericToNominalLabel = new FilterLabel("numericToNominal");
	FilterLabel standardizeLabel = new FilterLabel("standardize");			FilterLabel stringToNominalLabel = new FilterLabel("stringToNominal");
	//인스턴스필터 2개 
	FilterLabel removePercentageLabel = new FilterLabel("removePercentage");FilterLabel resampleLabel = new FilterLabel("resample");
	
	
	// 데이터 마이닝
	JButton dataMiningBtn = new JButton();
	
	

	//분류//////////
	JButton classifyBtn = new JButton("분류");
	ClassifyLabel decisionTreeLabel = new ClassifyLabel("decisionTree");
	ClassifyLabel knnLabel = new ClassifyLabel("kNearestNeighbors");
	ClassifyLabel naiveBayesLabel = new ClassifyLabel("naiveBayes");
	ClassifyLabel neuralNetworkLabel = new ClassifyLabel("neuralNetwork");
	ClassifyLabel svmLabel = new ClassifyLabel("svm");
    
	
	///////////////////
	
	
	JButton clusterBtn = new JButton("군집");
	
	ClusterLabel kMeansLabel = new ClusterLabel("kMeans");
	ClusterLabel densityBasedClusterLabel = new ClusterLabel("densityBasedCluster");
	ClusterLabel hierarchicalClusterLabel = new ClusterLabel("hierarchicalCluster");
	ClusterLabel emLabel = new ClusterLabel("em");           

	JButton associateBtn = new JButton("연관");
	
	AssociationLabel aprioriLabel = new AssociationLabel("apriori");
	AssociationLabel fpGrowthLabel = new AssociationLabel("fPGrowth");

	JButton analyzeBtn = new JButton();
	AnalyzeLabel analyzeLabel = new AnalyzeLabel("analyze");
	
	ImageIcon scatterPlotImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/scatterPlotIcon.PNG"));
	ImageIcon histogramImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/histogramIcon.PNG"));
	ImageIcon editImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/editImageIcon.PNG"));
	ImageIcon openImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/openImageIcon.PNG"));
	ImageIcon saveImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/saveImageIcon.PNG"));
	

	ImageIcon enter_scatterPlotImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/enter_scatterPlotIcon.PNG"));
	ImageIcon enter_histogramImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/enter_histogramIcon.PNG"));
	ImageIcon enter_editImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/enter_editImageIcon.PNG"));
	ImageIcon enter_openImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/enter_openImageIcon.PNG"));
	ImageIcon enter_saveImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/enter_saveImageIcon.PNG"));
	
	
	ImageIcon inputBtnImageIcon =  new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/dataInputIcon.png"));
	ImageIcon visualizeBtnImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/dataVisualizeIcon.png"));
	ImageIcon preprocessBtnImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/dataPreprocess.png"));
	ImageIcon dataMiningBtnImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/dataMiningIcon.png"));
	ImageIcon analyzeBtnImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/analysisIcon.png"));
	
	
	ImageIcon associationBtnImageIcon =  new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/associationBtnIcon.png"));
	ImageIcon classifyBtnImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/classifyBtnIcon.png"));
	ImageIcon clusterBtnImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/clusterBtnIcon.png"));
	
	ImageIcon enter_associationBtnImageIcon =  new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/enter_associationBtnIcon.png"));
	ImageIcon enter_classifyBtnImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/enter_classifyBtnIcon.png"));
	ImageIcon enter_clusterBtnImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/enter_clusterBtnIcon.png"));
	
	
	ImageIcon enter_inputBtnImageIcon =  new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/enter_dataInputIcon.png"));
	ImageIcon enter_visualizeBtnImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/enter_dataVisualizeIcon.png"));
	ImageIcon enter_preprocessBtnImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/enter_dataPreprocess.png"));
	ImageIcon enter_dataMiningBtnImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/enter_dataMiningIcon.png"));
	ImageIcon enter_analyzeBtnImageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/enter_analysisIcon.png"));
	
	
	ImageIcon aprioriIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/aprioriIcon.png"));
	ImageIcon decisionTreeIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/decisionTreeIcon.png"));
	ImageIcon densityBasedClusterIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/densityBasedClusterIcon.png"));
	ImageIcon emIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/emIcon.png"));
	ImageIcon fpGrowthIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/fpGrowthIcon.png"));
	ImageIcon hierarchicalClusterIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/hierarchicalClusterIcon.png"));
	ImageIcon kMeansIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/kMeansIcon.png"));
	ImageIcon kNearestNeighborsIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/kNearestNeighborsIcon.png"));
	ImageIcon naiveBayesIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/naiveBayesIcon.png"));
	ImageIcon neuralNetworkIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/neuralNetworkIcon.png"));
	ImageIcon svmIcon =new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/svmIcon.png"));
	
	//enter_algorithm
	ImageIcon enter_aprioriIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/enter_aprioriIcon.png"));
	ImageIcon enter_decisionTreeIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/enter_decisionTreeIcon.png"));
	ImageIcon enter_densityBasedClusterIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/enter_densityBasedClusterIcon.png"));
	ImageIcon enter_emIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/enter_emIcon.png"));
	ImageIcon enter_fpGrowthIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/enter_fpGrowthIcon.png"));
	ImageIcon enter_hierarchicalClusterIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/enter_hierarchicalClusterIcon.png"));
	ImageIcon enter_kMeansIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/enter_kMeansIcon.png"));
	ImageIcon enter_kNearestNeighborsIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/enter_kNearestNeighborsIcon.png"));
	ImageIcon enter_naiveBayesIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/enter_naiveBayesIcon.png"));
	ImageIcon enter_neuralNetworkIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/enter_neuralNetworkIcon.png"));
	ImageIcon enter_svmIcon =new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/algorithmIcon/enter_svmIcon.png"));
	
	//filter
	ImageIcon addExpressionIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/addExpressionImageIcon.png"));
	ImageIcon addIDIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/addIDImageIcon.png"));
	ImageIcon addIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/addImageIcon.png"));
	ImageIcon copyIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/copyImageIcon.png"));
	ImageIcon discretizeIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/discretizeImageIcon.png"));
	ImageIcon interQuartileRangeIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/interQuartileRangeImageIcon.png"));
	ImageIcon mergeTwoValuesIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/mergeTwoValuesImageIcon.png"));
	ImageIcon nominalToBinaryIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/nominalToBinaryImageIcon.png"));
	ImageIcon nominalToStringIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/nominalToStringImageIcon.png"));
	ImageIcon normalizeIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/normalizeImageIcon.png"));
	ImageIcon numericCleanerIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/numericCleanerImageIcon.png"));
	ImageIcon numericToBinaryIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/numericToBinaryImageIcon.png"));
	ImageIcon numericToNominalIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/numericToNominalImageIcon.png"));
	ImageIcon numericTransformIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/numericTransformImageIcon.png"));
	ImageIcon removePercentageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/removePercentageImageIcon.png"));
	ImageIcon removeTypeIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/removeTypeImageIcon.png"));
	ImageIcon replaceMissingValuesIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/replaceMissingValuesImageIcon.png"));
	ImageIcon resampleIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/resampleImageIcon.png"));
	ImageIcon standardizeIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/standardizeImageIcon.png"));
	ImageIcon stringToNominalIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/stringToNominalImageIcon.png"));
	
	//enter_filter
	ImageIcon enter_addExpressionIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_addExpressionImageIcon.png"));
	ImageIcon enter_addIDIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_addIDImageIcon.png"));
	ImageIcon enter_addIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_addImageIcon.png"));
	ImageIcon enter_copyIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_copyImageIcon.png"));
	ImageIcon enter_discretizeIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_discretizeImageIcon.png"));
	ImageIcon enter_interQuartileRangeIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_interQuartileRangeImageIcon.png"));
	ImageIcon enter_mergeTwoValuesIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_mergeTwoValuesImageIcon.png"));
	ImageIcon enter_nominalToBinaryIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_nominalToBinaryImageIcon.png"));
	ImageIcon enter_nominalToStringIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_nominalToStringImageIcon.png"));
	ImageIcon enter_normalizeIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_normalizeImageIcon.png"));
	ImageIcon enter_numericCleanerIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_numericCleanerImageIcon.png"));
	ImageIcon enter_numericToBinaryIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_numericToBinaryImageIcon.png"));
	ImageIcon enter_numericToNominalIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_numericToNominalImageIcon.png"));
	ImageIcon enter_numericTransformIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_numericTransformImageIcon.png"));
	ImageIcon enter_removePercentageIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_removePercentageImageIcon.png"));
	ImageIcon enter_removeTypeIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_removeTypeImageIcon.png"));
	ImageIcon enter_replaceMissingValuesIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_replaceMissingValuesImageIcon.png"));
	ImageIcon enter_resampleIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_resampleImageIcon.png"));
	ImageIcon enter_standardizeIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_standardizeImageIcon.png"));
	ImageIcon enter_stringToNominalIcon = new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/filterIcon/enter_stringToNominalImageIcon.png"));
	
	ImageIcon subAnalysisIcon =new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/subAnalysisIcon.png"));
	ImageIcon enter_subAnalyisIcon =new ImageIcon(ClassLoader.getSystemResource("DMZ/ImageIcon/enter_subAnalysisIcon.png"));
	
	
	
	
	
	boolean inputBtnSelected = false;
	boolean visualizeBtnSelected = false;
	boolean preprocessBtnSelected = false;
	boolean dataMiningBtnSelected = false;
	boolean analyzeBtnSelected = false;

	boolean classifyBtnSelected = false;
	boolean clusterBtnSelected = false;
	boolean associateBtnSelected = false;
	

	EnterLabelListener enterLabelListener = new EnterLabelListener();
	NewLabelListener newLabelListener = new NewLabelListener();
	Point sp;
	LeftPanel thisPanel = this;
	CenterPanel centerPanel;
	int xmlId=0;
	LeftPanel() {
		
		
		setLayout(null);
		inputBtn.setBounds(8, 8, 263, 60);
		visualizeBtn.setBounds(8, 68, 263, 60);
		preprocessBtn.setBounds(8, 128, 263, 60);
		dataMiningBtn.setBounds(8, 188, 263, 60);
		analyzeBtn.setBounds(8, 248, 263, 60);
		
		add(imageTest);
		
		
		inputBtnImageIcon =new ImageIcon( inputBtnImageIcon.getImage().getScaledInstance(inputBtn.getWidth(),inputBtn.getHeight(),  Image.SCALE_SMOOTH));
		visualizeBtnImageIcon =new ImageIcon(  visualizeBtnImageIcon.getImage().getScaledInstance( visualizeBtn.getWidth(), visualizeBtn.getHeight(),  Image.SCALE_SMOOTH));
		preprocessBtnImageIcon = new ImageIcon(preprocessBtnImageIcon.getImage().getScaledInstance(preprocessBtn.getWidth(), preprocessBtn.getHeight(),  Image.SCALE_SMOOTH));
		dataMiningBtnImageIcon = new ImageIcon(dataMiningBtnImageIcon.getImage().getScaledInstance(dataMiningBtn.getWidth(), dataMiningBtn.getHeight(), Image.SCALE_SMOOTH));
		analyzeBtnImageIcon = new ImageIcon(analyzeBtnImageIcon.getImage().getScaledInstance(analyzeBtn.getWidth(), analyzeBtn.getHeight(),Image.SCALE_SMOOTH));
		
		
		enter_inputBtnImageIcon =new ImageIcon( enter_inputBtnImageIcon.getImage().getScaledInstance(inputBtn.getWidth(),inputBtn.getHeight(),  Image.SCALE_SMOOTH));
		enter_visualizeBtnImageIcon =new ImageIcon(enter_visualizeBtnImageIcon.getImage().getScaledInstance( visualizeBtn.getWidth(), visualizeBtn.getHeight(),  Image.SCALE_SMOOTH));
		enter_preprocessBtnImageIcon = new ImageIcon(enter_preprocessBtnImageIcon.getImage().getScaledInstance(preprocessBtn.getWidth(), preprocessBtn.getHeight(),  Image.SCALE_SMOOTH));
		enter_dataMiningBtnImageIcon = new ImageIcon(enter_dataMiningBtnImageIcon.getImage().getScaledInstance(dataMiningBtn.getWidth(), dataMiningBtn.getHeight(), Image.SCALE_SMOOTH));
		enter_analyzeBtnImageIcon = new ImageIcon(enter_analyzeBtnImageIcon.getImage().getScaledInstance(analyzeBtn.getWidth(), analyzeBtn.getHeight(),Image.SCALE_SMOOTH));
		
		
		inputBtn.setIcon(inputBtnImageIcon);
		visualizeBtn.setIcon(visualizeBtnImageIcon);
		preprocessBtn.setIcon(preprocessBtnImageIcon);
		dataMiningBtn.setIcon(dataMiningBtnImageIcon);
		analyzeBtn.setIcon(analyzeBtnImageIcon);
		
		
		scatterPlotImageIcon = new ImageIcon(scatterPlotImageIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		histogramImageIcon = new ImageIcon(histogramImageIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		editImageIcon = new ImageIcon(editImageIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		openImageIcon = new ImageIcon(openImageIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		saveImageIcon = new ImageIcon(saveImageIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		subAnalysisIcon = new ImageIcon(subAnalysisIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		
		
		
		

		enter_scatterPlotImageIcon = new ImageIcon(enter_scatterPlotImageIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		enter_histogramImageIcon = new ImageIcon(enter_histogramImageIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		enter_editImageIcon = new ImageIcon(enter_editImageIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		enter_openImageIcon = new ImageIcon(enter_openImageIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		enter_saveImageIcon = new ImageIcon(enter_saveImageIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		enter_subAnalyisIcon =  new ImageIcon(enter_subAnalyisIcon.getImage().getScaledInstance(80, 89, Image.SCALE_SMOOTH));
		

		normalizeIcon = new ImageIcon(normalizeIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		addIcon = new ImageIcon(addIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		addExpressionIcon = new ImageIcon(addExpressionIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		addIDIcon = new ImageIcon(addIDIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		copyIcon = new ImageIcon(copyIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		discretizeIcon = new ImageIcon(discretizeIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		interQuartileRangeIcon = new ImageIcon(interQuartileRangeIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		removePercentageIcon = new ImageIcon(removePercentageIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		resampleIcon = new ImageIcon(resampleIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		mergeTwoValuesIcon = new ImageIcon(mergeTwoValuesIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		nominalToBinaryIcon = new ImageIcon(nominalToBinaryIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		nominalToStringIcon = new ImageIcon(nominalToStringIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		numericCleanerIcon = new ImageIcon(numericCleanerIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		numericToBinaryIcon = new ImageIcon(numericToBinaryIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		numericTransformIcon = new ImageIcon(numericTransformIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		numericToNominalIcon = new ImageIcon(numericToNominalIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		removeTypeIcon = new ImageIcon(removeTypeIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		replaceMissingValuesIcon = new ImageIcon(replaceMissingValuesIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		standardizeIcon = new ImageIcon(standardizeIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		stringToNominalIcon = new ImageIcon(stringToNominalIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
	

		normalizeLabel.setExpainText("  데이터에 존재라는 모든 수치 속성을 [0,1] 구간 안에 들어오게 스케일을 변경해서 분포시킨다.");	
		addLabel.setExpainText("  새로운 속성과 속성 값을 추가한다. 이 값들은 누락된 속성 값으로 마크된다.");	
		addExpressionLabel.setExpainText("  기존 속성에 특정 수학적 기능을 적용함으로써 새로운 속성을 생성해준다.");
		addIDLabel.setExpainText("  데이터에 존재하는 각 속성에 대해 고유 ID를 담고 있는 속성을 추가한다.");
		copyLabel.setExpainText("  데이터에 존재하는 속성들을 복사한다.");
		numericTransformLabel.setExpainText("  임의의 자바 함수를 이용해 수치 속성을 변환시킨다.");
		discretizeLabel.setExpainText("  수치속성들을 명몽 속성으로 변환한다. 변환할 속성,폭(bin)의 개수, 폭의 개수를 최적화할지, 동일 빈도폭을 사용할지의 여부를 설정한다. ");
		interquartileRangeLabel.setExpainText("  외톨이 항과 극단치(extreme value)를 나타내게 새로운 속성들을 생성한다.사분 범위(interquartile range)를 이용해 무엇이 외톨이 항 또는 극단치를 구성하는지 정의한다.");
		removeTypeLabel.setExpainText("  주어진 타입(명목, 수치, 문자열, 날짜 등)의 속성을 제거한다.");
		replaceMissingValuesLabel.setExpainText("  명목 속성과 수치 속성에 대해 누락된 모든 속성 값을 훈련 데이터릐 평균과 최빈도(mode) 값들로 대체한다.");
		mergeTwoValuesLabel.setExpainText("  주어진 속성의 두 가지 값을 병합시킨다. 병합시킬 두 속성 값들의 인덱스를 지정한다.");
		nominalToBinaryLabel.setExpainText("  속성 값마다 하나씩, 하나의 명목 속성을 여러 가지 바이너리 속성들로 변환시킨다. ");
		nominalToStringLabel.setExpainText("  명목 속성을 문자열 속성으로 변환한다.");
		numericCleanerLabel.setExpainText("  너무 크거나 작은 경우 또는 특정 값에 너무 근사한 수치 속성들의 값을 사용자가 제공하는 기본 값으로 대체한다. ");
		numericToBinaryLabel.setExpainText("  모든 수치 속성들을 바이너리 속성으로 변환한다. 0이 아닌 값들은 1이 된다. ");
		numericToNominalLabel.setExpainText("  단순히 하나의 수치 속성에 대해 관측된 모든 값을 명목 속성 값들의 리스트레 추가시켜 수치 속성들을 명목 속성으로 변환시킨다. ");
		standardizeLabel.setExpainText("  모든 수치 속성의 평균이 0, 분산이 1이 되게 표준화한다. ");		
		stringToNominalLabel.setExpainText("  문자열 속성을 명목 속성으로 변환시킨다. ");
		removePercentageLabel.setExpainText("  데이터에서 주어진 비율 만큼 제거한다. ");
		resampleLabel.setExpainText("  데이터 집합의 임의의 하위 표본을 도출해 대체 데이터로 표본 추출한다. ");
		
		
	
		decisionTreeLabel.setExpainText("  의사 결정 트리 학습기");
		knnLabel.setExpainText("  k 최근접 이웃 분류기 ");
		naiveBayesLabel.setExpainText("  표준 확률 단순 베이지안 분류기");
		neuralNetworkLabel.setExpainText("  신경망 네트워크");
		kMeansLabel.setExpainText("  k-means 알고리즘을 이용한 군집화 ");
		densityBasedClusterLabel.setExpainText(" 분포나 밀도를 반환하게 하기 위해 군집기를 래핑(wrap)한다. ");
		hierarchicalClusterLabel.setExpainText("  병학적인 계보적 군집화");
		emLabel.setExpainText("  기대치 최대화(expectation maxmization) 알고리즘을 이용한 군집화");        
		aprioriLabel.setExpainText("  Apriori 알고리즘을 이용해서 연관 규칙을 찾아낸다. ");
		fpGrowthLabel.setExpainText("  빈도수가 높은 패턴(frequent pattern)을 이용해서 연관 규칙 마이닝을 한다. ");
		svmLabel.setExpainText("SVM 분류를 위한 순차적인 최소 최적화 알고리즘 ");
	

		
		//enter_filter
		enter_normalizeIcon = new ImageIcon(enter_normalizeIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		enter_addIcon = new ImageIcon(enter_addIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		enter_addExpressionIcon = new ImageIcon(enter_addExpressionIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		enter_addIDIcon = new ImageIcon(enter_addIDIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		enter_copyIcon = new ImageIcon(enter_copyIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
	
		enter_discretizeIcon = new ImageIcon(enter_discretizeIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
	
		enter_interQuartileRangeIcon = new ImageIcon(enter_interQuartileRangeIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
	
		enter_removePercentageIcon = new ImageIcon(enter_removePercentageIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		
		enter_resampleIcon = new ImageIcon(enter_resampleIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		enter_mergeTwoValuesIcon = new ImageIcon(enter_mergeTwoValuesIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		
		enter_nominalToBinaryIcon = new ImageIcon(enter_nominalToBinaryIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		enter_nominalToStringIcon = new ImageIcon(enter_nominalToStringIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		
		enter_numericCleanerIcon = new ImageIcon(enter_numericCleanerIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		enter_numericToBinaryIcon = new ImageIcon(enter_numericToBinaryIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
	
		enter_numericTransformIcon = new ImageIcon(enter_numericTransformIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		
		enter_numericToNominalIcon = new ImageIcon(enter_numericToNominalIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		
		enter_removeTypeIcon = new ImageIcon(enter_removeTypeIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		
		enter_replaceMissingValuesIcon = new ImageIcon(enter_replaceMissingValuesIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
		
		enter_standardizeIcon = new ImageIcon(enter_standardizeIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
	
		enter_stringToNominalIcon = new ImageIcon(enter_stringToNominalIcon.getImage().getScaledInstance(55,55,  Image.SCALE_SMOOTH));
	
		
		
		
		add(inputBtn);
		add(visualizeBtn);
		add(preprocessBtn);
		add(dataMiningBtn);
		add(analyzeBtn);
		
		
		inputBtn.addMouseListener(new MouseAdapter(){
			JButton btn;
			public void mouseEntered(MouseEvent me)
			{
				btn = (JButton)me.getSource();
				btn.setIcon(enter_inputBtnImageIcon);
				
				tf.setText(" 데이터를 입력하거나 수정,저장 할 수 있습니다.");

			}
			
			public void mouseExited(MouseEvent me)
			{
				if(inputBtnSelected==false)
				{
					btn = (JButton)me.getSource();
					btn.setIcon(inputBtnImageIcon);
					tf.setText(null);
				}
			}
		
		});
		visualizeBtn.addMouseListener(new MouseAdapter(){
			JButton btn;
			public void mouseEntered(MouseEvent me)
			{
				btn = (JButton)me.getSource();
				btn.setIcon(enter_visualizeBtnImageIcon);
				
				tf.setText(" 데이터를 시각화 합니다. 히스토그램,산포도 ");
			}
			
			public void mouseExited(MouseEvent me)
			{
				if(visualizeBtnSelected==false)
				{
				btn = (JButton)me.getSource();
				btn.setIcon(visualizeBtnImageIcon);
				tf.setText(null);
				}
			}
		
		});
		preprocessBtn.addMouseListener(new MouseAdapter(){
			JButton btn;
			public void mouseEntered(MouseEvent me)
			{
				btn = (JButton)me.getSource();
				btn.setIcon(enter_preprocessBtnImageIcon);
				tf.setText(" 데이터 전처리과정을 수행합니다.");
			}
			
			public void mouseExited(MouseEvent me)
			{
				if(preprocessBtnSelected==false)
				{
				btn = (JButton)me.getSource();
				btn.setIcon(preprocessBtnImageIcon);
				tf.setText(null);
				}
			}
		
		});
		dataMiningBtn.addMouseListener(new MouseAdapter(){
			JButton btn;
			public void mouseEntered(MouseEvent me)
			{
				btn = (JButton)me.getSource();
				btn.setIcon(enter_dataMiningBtnImageIcon);
				tf.setText(" 데이터마이닝 알고리즘을 적용합니다. 분류,군집,연관");
			}
			
			public void mouseExited(MouseEvent me)
			{
				if(dataMiningBtnSelected==false)
				{
				btn = (JButton)me.getSource();
				btn.setIcon(dataMiningBtnImageIcon);
				tf.setText(null);
				}
			}
		
		});
		analyzeBtn.addMouseListener(new MouseAdapter(){
			JButton btn;
			public void mouseEntered(MouseEvent me)
			{
				btn = (JButton)me.getSource();
				btn.setIcon(enter_analyzeBtnImageIcon);
				tf.setText(" 데이터에 알고리즘을 적용한 결과를 확인합니다. ");
			}
			
			public void mouseExited(MouseEvent me)
			{
				if(analyzeBtnSelected==false)
				{
				btn = (JButton)me.getSource();
				btn.setIcon(analyzeBtnImageIcon);
				tf.setText(null);
				}
			}
		
		});
		
		associateBtn.addMouseListener(new MouseAdapter(){
			JButton btn;
			public void mouseEntered(MouseEvent me)
			{
				btn = (JButton)me.getSource();
				btn.setIcon(enter_associationBtnImageIcon);
			}
			
			public void mouseExited(MouseEvent me)
			{
				if(analyzeBtnSelected==false)
				{
				btn = (JButton)me.getSource();
				btn.setIcon(associationBtnImageIcon);
				}
			}
		
		});
		clusterBtn.addMouseListener(new MouseAdapter(){
			JButton btn;
			public void mouseEntered(MouseEvent me)
			{
				btn = (JButton)me.getSource();
				btn.setIcon(enter_clusterBtnImageIcon);
			}
			
			public void mouseExited(MouseEvent me)
			{
				if(analyzeBtnSelected==false)
				{
				btn = (JButton)me.getSource();
				btn.setIcon(clusterBtnImageIcon);
				}
			}
		
		});
		classifyBtn.addMouseListener(new MouseAdapter(){
			JButton btn;
			public void mouseEntered(MouseEvent me)
			{
				btn = (JButton)me.getSource();
				btn.setIcon(enter_classifyBtnImageIcon);
			}
			
			public void mouseExited(MouseEvent me)
			{
				if(analyzeBtnSelected==false)
				{
				btn = (JButton)me.getSource();
				btn.setIcon(classifyBtnImageIcon);
				}
			}
		
		});
		
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickInputBtn();
			}
		});
		visualizeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickVisualizeBtn();
			}
		});
		preprocessBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickPreprocessBtn();
			}
		});

		dataMiningBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickDataMiningBtn();
			}
		});
		classifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickClassifyBtn();
			}
		});

		clusterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickClusterBtn();
			}
		});

		associateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAssociateBtn();
			}
		});
		
			
		
		analyzeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAnalyzeBtn();
			}
		});
		
		
		

		
		
		

		openDataLabel.addMouseListener(newLabelListener);

		openDataLabel.addMouseMotionListener(newLabelListener);
		editDataLabel.addMouseListener(newLabelListener);
		editDataLabel.addMouseMotionListener(newLabelListener);
		saveDataLabel.addMouseListener(newLabelListener);
		saveDataLabel.addMouseMotionListener(newLabelListener);
		histogramLabel.addMouseListener(newLabelListener);
		histogramLabel.addMouseMotionListener(newLabelListener);
		plotLabel.addMouseListener(newLabelListener);
		plotLabel.addMouseMotionListener(newLabelListener);

		decisionTreeLabel.addMouseListener(newLabelListener);
		decisionTreeLabel.addMouseMotionListener(newLabelListener);
		knnLabel.addMouseListener(newLabelListener);
		knnLabel.addMouseMotionListener(newLabelListener);
		naiveBayesLabel.addMouseListener(newLabelListener);
		naiveBayesLabel.addMouseMotionListener(newLabelListener);
		neuralNetworkLabel.addMouseListener(newLabelListener);
		neuralNetworkLabel.addMouseMotionListener(newLabelListener);
		svmLabel.addMouseListener(newLabelListener);
		svmLabel.addMouseMotionListener(newLabelListener);
		
		
		kMeansLabel.addMouseListener(newLabelListener);
		kMeansLabel.addMouseMotionListener(newLabelListener);
		densityBasedClusterLabel.addMouseListener(newLabelListener);
		densityBasedClusterLabel.addMouseMotionListener(newLabelListener);
		hierarchicalClusterLabel.addMouseListener(newLabelListener);
		hierarchicalClusterLabel.addMouseMotionListener(newLabelListener);
		emLabel.addMouseListener(newLabelListener);
		emLabel.addMouseMotionListener(newLabelListener);
		aprioriLabel.addMouseListener(newLabelListener);
		aprioriLabel.addMouseMotionListener(newLabelListener);
		fpGrowthLabel.addMouseListener(newLabelListener);
		fpGrowthLabel.addMouseMotionListener(newLabelListener);
	
		//필터 리스너 여기에 추가 
		
		normalizeLabel.addMouseListener(newLabelListener);					addLabel.addMouseListener(newLabelListener);
		normalizeLabel.addMouseMotionListener(newLabelListener);			addLabel.addMouseMotionListener(newLabelListener);
		addExpressionLabel.addMouseListener(newLabelListener);				addIDLabel.addMouseListener(newLabelListener);
		addExpressionLabel.addMouseMotionListener(newLabelListener);		addIDLabel.addMouseMotionListener(newLabelListener);

		copyLabel.addMouseListener(newLabelListener);						discretizeLabel.addMouseListener(newLabelListener);
		copyLabel.addMouseMotionListener(newLabelListener);					discretizeLabel.addMouseMotionListener(newLabelListener);
		interquartileRangeLabel.addMouseListener(newLabelListener);			mergeTwoValuesLabel.addMouseListener(newLabelListener);
		interquartileRangeLabel.addMouseMotionListener(newLabelListener);	mergeTwoValuesLabel.addMouseMotionListener(newLabelListener);
		
		nominalToBinaryLabel.addMouseListener(newLabelListener);			numericCleanerLabel.addMouseListener(newLabelListener);
		nominalToBinaryLabel.addMouseMotionListener(newLabelListener);		numericCleanerLabel.addMouseMotionListener(newLabelListener);
		nominalToStringLabel.addMouseListener(newLabelListener);			numericToNominalLabel.addMouseListener(newLabelListener);
		nominalToStringLabel.addMouseMotionListener(newLabelListener);		numericToNominalLabel.addMouseMotionListener(newLabelListener);
		
		numericToBinaryLabel.addMouseListener(newLabelListener);			removeTypeLabel.addMouseListener(newLabelListener);
		numericToBinaryLabel.addMouseMotionListener(newLabelListener);		removeTypeLabel.addMouseMotionListener(newLabelListener);
		numericTransformLabel.addMouseListener(newLabelListener);			replaceMissingValuesLabel.addMouseListener(newLabelListener);
		numericTransformLabel.addMouseMotionListener(newLabelListener);		replaceMissingValuesLabel.addMouseMotionListener(newLabelListener);
		
		standardizeLabel.addMouseListener(newLabelListener);				removePercentageLabel.addMouseListener(newLabelListener);
		standardizeLabel.addMouseMotionListener(newLabelListener);			removePercentageLabel.addMouseMotionListener(newLabelListener);
		stringToNominalLabel.addMouseListener(newLabelListener);			resampleLabel.addMouseListener(newLabelListener);
		stringToNominalLabel.addMouseMotionListener(newLabelListener);		resampleLabel.addMouseMotionListener(newLabelListener);
		
		analyzeLabel.addMouseListener(newLabelListener);
		analyzeLabel.addMouseMotionListener(newLabelListener);
		
		
		openDataLabel.setExpainText("  데이터 파일을 불러옵니다. ");
		editDataLabel.setExpainText("  데이터를 수정합니다.");
		saveDataLabel.setExpainText("  데이터를 저장합니다.");
		histogramLabel.setExpainText("  데이터를 히스토그램으로 보여줍니다. ");
		plotLabel.setExpainText("  데이터를 산포도로 보여줍니다. ");
		analyzeLabel.setExpainText(" 비교 & 분석");
	
		
		
	}

	public void setXmlId(int a){
		this.xmlId=a;
	}
	public void clickInputBtn() {
		if (visualizeBtnSelected == true)
			clickVisualizeBtn();
		if (preprocessBtnSelected == true)
			clickPreprocessBtn();
		if (dataMiningBtnSelected == true)
			clickDataMiningBtn();
		if (analyzeBtnSelected == true)
			clickAnalyzeBtn();

		if (inputBtnSelected == false) {
		
			
			visualizeBtn.setLocation(visualizeBtn.getX(),visualizeBtn.getY() + 100);
			preprocessBtn.setLocation(preprocessBtn.getX(),preprocessBtn.getY() + 100);
			dataMiningBtn.setLocation(dataMiningBtn.getX(),dataMiningBtn.getY() + 100);
			analyzeBtn.setLocation(analyzeBtn.getX(), analyzeBtn.getY() + 100);

			openDataLabel.setBounds(inputBtn.getX(), inputBtn.getY() + 60,80, 89);
			openDataLabel.setIcon(openImageIcon);
			openDataLabel.addMouseListener(enterLabelListener);

			editDataLabel.setBounds(inputBtn.getX() + 90, inputBtn.getY()+60,80, 89);
			editDataLabel.setIcon(editImageIcon);
			editDataLabel.addMouseListener(enterLabelListener);

			saveDataLabel.setBounds(inputBtn.getX() + 180,inputBtn.getY() + 60, 80, 89);
			saveDataLabel.setIcon(saveImageIcon);
			saveDataLabel.addMouseListener(enterLabelListener);
			
			openDataLabel.setVisible(true);
			editDataLabel.setVisible(true);
			saveDataLabel.setVisible(true);

			add(openDataLabel);

			add(editDataLabel);
			add(saveDataLabel);

			inputBtnSelected = true;
			inputBtn.setIcon(enter_inputBtnImageIcon);
		}

		else {

			openDataLabel.setVisible(false);
			editDataLabel.setVisible(false);
			saveDataLabel.setVisible(false);

			visualizeBtn.setLocation(visualizeBtn.getX(),
					visualizeBtn.getY() - 100);
			preprocessBtn.setLocation(preprocessBtn.getX(),
					preprocessBtn.getY() - 100);
			dataMiningBtn.setLocation(dataMiningBtn.getX(),
					dataMiningBtn.getY() - 100);
			analyzeBtn.setLocation(analyzeBtn.getX(), analyzeBtn.getY() - 100);
			
			inputBtnSelected = false;
			inputBtn.setIcon(inputBtnImageIcon);
		}
	}

	public void clickVisualizeBtn() {
		if (inputBtnSelected == true)
			clickInputBtn();
		if (preprocessBtnSelected == true)
			clickPreprocessBtn();
		if (dataMiningBtnSelected == true)
			clickDataMiningBtn();
		if (analyzeBtnSelected == true)
			clickAnalyzeBtn();

		if (visualizeBtnSelected == false) {

			preprocessBtn.setLocation(preprocessBtn.getX(),preprocessBtn.getY() + 110);
			dataMiningBtn.setLocation(dataMiningBtn.getX(),dataMiningBtn.getY() + 110);
			analyzeBtn.setLocation(analyzeBtn.getX(), analyzeBtn.getY() + 110);

			histogramLabel.setBounds(visualizeBtn.getX()+40,visualizeBtn.getY() + 70, 80, 89);
			histogramLabel.setIcon(histogramImageIcon);
			histogramLabel.addMouseListener(enterLabelListener);

			plotLabel.setBounds(visualizeBtn.getX() + 140,visualizeBtn.getY() + 74, 80,89);
			plotLabel.setIcon(scatterPlotImageIcon);
			plotLabel.addMouseListener(enterLabelListener);
			

			histogramLabel.setVisible(true);
			plotLabel.setVisible(true);

			add(histogramLabel);
			add(plotLabel);

			visualizeBtnSelected = true;
			visualizeBtn.setIcon(enter_visualizeBtnImageIcon);
		}

		else {
			histogramLabel.setVisible(false);
			plotLabel.setVisible(false);

			preprocessBtn.setLocation(preprocessBtn.getX(),
					preprocessBtn.getY() - 110);
			dataMiningBtn.setLocation(dataMiningBtn.getX(),
					dataMiningBtn.getY() - 110);
			analyzeBtn.setLocation(analyzeBtn.getX(), analyzeBtn.getY() - 110);

			visualizeBtnSelected = false;
			visualizeBtn.setIcon(visualizeBtnImageIcon);
		}
	}

	public void clickPreprocessBtn() {
		if (inputBtnSelected == true)
			clickInputBtn();
		if (visualizeBtnSelected == true)
			clickVisualizeBtn();
		if (dataMiningBtnSelected == true)
			clickDataMiningBtn();
		if (analyzeBtnSelected == true)
			clickAnalyzeBtn();
	

		if (preprocessBtnSelected == false) {
			
			
			
			
			dataMiningBtn.setLocation(dataMiningBtn.getX(),dataMiningBtn.getY() + 345);
			analyzeBtn.setLocation(analyzeBtn.getX(), analyzeBtn.getY() + 345);
			
			
			
		
			
			
			normalizeLabel.setBounds(preprocessBtn.getX() + 5,preprocessBtn.getY() + 70, 55, 55);
			addLabel.setBounds(preprocessBtn.getX() + 70,preprocessBtn.getY() + 70, 55, 55);
			addExpressionLabel.setBounds(preprocessBtn.getX() + 135,preprocessBtn.getY() + 70, 55,55);
			addIDLabel.setBounds(preprocessBtn.getX() + 200,preprocessBtn.getY() + 70, 55, 55);
			numericTransformLabel.setBounds(preprocessBtn.getX() + 5,preprocessBtn.getY() + 135, 55, 55);
			copyLabel.setBounds(preprocessBtn.getX() + 70,preprocessBtn.getY() + 135, 55, 55);
			discretizeLabel.setBounds(preprocessBtn.getX() + 135,preprocessBtn.getY() + 135, 55, 55);
			interquartileRangeLabel.setBounds(preprocessBtn.getX() + 200,preprocessBtn.getY() + 135, 55, 55);
			removePercentageLabel.setBounds(preprocessBtn.getX() + 5,preprocessBtn.getY() + 200, 55, 55);
			resampleLabel.setBounds(preprocessBtn.getX() + 70,preprocessBtn.getY() + 200, 55, 55);
			mergeTwoValuesLabel.setBounds(preprocessBtn.getX() + 135,preprocessBtn.getY() + 200, 55, 55);
			nominalToBinaryLabel.setBounds(preprocessBtn.getX() + 200,preprocessBtn.getY() + 200, 55, 55);
			nominalToStringLabel.setBounds(preprocessBtn.getX() + 5,preprocessBtn.getY() + 265, 55, 55);
			numericCleanerLabel.setBounds(preprocessBtn.getX() + 70,preprocessBtn.getY() + 265, 55, 55);
			numericToBinaryLabel.setBounds(preprocessBtn.getX() + 135,preprocessBtn.getY() + 265, 55, 55);
			numericToNominalLabel.setBounds(preprocessBtn.getX() + 200,preprocessBtn.getY() + 265, 55, 55);
			removeTypeLabel.setBounds(preprocessBtn.getX() + 5,preprocessBtn.getY() + 330, 55, 55);
			replaceMissingValuesLabel.setBounds(preprocessBtn.getX() + 70,preprocessBtn.getY() + 330, 55, 55);
			standardizeLabel.setBounds(preprocessBtn.getX() + 135,preprocessBtn.getY() + 330, 55, 55);
			stringToNominalLabel.setBounds(preprocessBtn.getX() + 200,preprocessBtn.getY() + 330, 55, 55);
		
		
			normalizeLabel.setIcon(normalizeIcon);
			normalizeLabel.addMouseListener(enterLabelListener);
			
			
			addLabel.setIcon(addIcon);
			addLabel.addMouseListener(enterLabelListener);
			
			
			addExpressionLabel.setIcon(addExpressionIcon);
			addExpressionLabel.addMouseListener(enterLabelListener);
			
			
			addIDLabel.setIcon(addIDIcon);
			addIDLabel.addMouseListener(enterLabelListener);
			
			
			copyLabel.setIcon(copyIcon);
			copyLabel.addMouseListener(enterLabelListener);
			
			
			discretizeLabel.setIcon(discretizeIcon);
			discretizeLabel.addMouseListener(enterLabelListener);
			
			
			interquartileRangeLabel.setIcon(interQuartileRangeIcon);
			interquartileRangeLabel.addMouseListener(enterLabelListener);
		
			removePercentageLabel.setIcon(removePercentageIcon);
			removePercentageLabel.addMouseListener(enterLabelListener);
		
			resampleLabel.setIcon(resampleIcon);
			resampleLabel.addMouseListener(enterLabelListener);
			
		
			mergeTwoValuesLabel.setIcon(mergeTwoValuesIcon);
			mergeTwoValuesLabel.addMouseListener(enterLabelListener);
		
			nominalToBinaryLabel.setIcon(nominalToBinaryIcon);
			nominalToBinaryLabel.addMouseListener(enterLabelListener);
		
			nominalToStringLabel.setIcon(nominalToStringIcon);
			nominalToStringLabel.addMouseListener(enterLabelListener);
			
		
			numericCleanerLabel.setIcon(numericCleanerIcon);
			numericCleanerLabel.addMouseListener(enterLabelListener);
		
			numericToBinaryLabel.setIcon(numericToBinaryIcon);
			numericToBinaryLabel.addMouseListener(enterLabelListener);
			
			numericTransformLabel.setIcon(numericTransformIcon);
			numericTransformLabel.addMouseListener(enterLabelListener);
			
			numericToNominalLabel.setIcon(numericToNominalIcon);
			numericToNominalLabel.addMouseListener(enterLabelListener);
			
			removeTypeLabel.setIcon(removeTypeIcon);
			removeTypeLabel.addMouseListener(enterLabelListener);
			
			replaceMissingValuesLabel.setIcon(replaceMissingValuesIcon);
			replaceMissingValuesLabel.addMouseListener(enterLabelListener);
			
			standardizeLabel.setIcon(standardizeIcon);
			standardizeLabel.addMouseListener(enterLabelListener);
			
			stringToNominalLabel.setIcon(stringToNominalIcon);
			stringToNominalLabel.addMouseListener(enterLabelListener);
			
			
			
			
			
		/*	
			obfuscateLabel.setBounds(preprocessBtn.getX() + 70,preprocessBtn.getY() + 330, 55, 55);
			pkiDicretizeLabel.setBounds(preprocessBtn.getX() + 135,preprocessBtn.getY() + 330, 55, 55);		
			reorderLabel.setBounds(preprocessBtn.getX() + 5,preprocessBtn.getY() + 395, 55, 55);
			removeFoldsLabel.setBounds(preprocessBtn.getX() + 5,preprocessBtn.getY() + 460, 55, 55);
			removeMisclassifiedLabel.setBounds(preprocessBtn.getX() + 70,preprocessBtn.getY() + 460, 55, 55);
			
			makeIndicatorLabel.setBounds(preprocessBtn.getX() + 5,preprocessBtn.getY() + 200, 55, 55);
			mathExpressionLabel.setBounds(preprocessBtn.getX() + 70,preprocessBtn.getY() + 200, 55, 55);			
			classAssignerLabel.setBounds(preprocessBtn.getX() + 5,preprocessBtn.getY() + 135, 55, 55);
			reservoirSampleLabel.setBounds(preprocessBtn.getX() + 5,preprocessBtn.getY() + 525,55, 55);
			filterLabel30.setBounds(preprocessBtn.getX() + 70,preprocessBtn.getY() + 525, 55, 55);
			*/
		
			
			

			
			normalizeLabel.setVisible(true);			copyLabel.setVisible(true);				mergeTwoValuesLabel.setVisible(true);
			numericToNominalLabel.setVisible(true);		addLabel.setVisible(true);				discretizeLabel.setVisible(true);
			nominalToBinaryLabel.setVisible(true);		numericTransformLabel.setVisible(true);	addExpressionLabel.setVisible(true);
			interquartileRangeLabel.setVisible(true);	nominalToStringLabel.setVisible(true);	addIDLabel.setVisible(true);
			numericCleanerLabel.setVisible(true);		numericToBinaryLabel.setVisible(true);	removeTypeLabel.setVisible(true);
			stringToNominalLabel.setVisible(true);		removePercentageLabel.setVisible(true);	replaceMissingValuesLabel.setVisible(true);
			resampleLabel.setVisible(true);				standardizeLabel.setVisible(true);
			
			
			add(normalizeLabel);			add(copyLabel);					add(mergeTwoValuesLabel);
			add(numericToNominalLabel);		add(addLabel);					add(discretizeLabel);
			add(nominalToBinaryLabel);		add(numericTransformLabel);		add(addExpressionLabel);
			add(interquartileRangeLabel);	add(nominalToStringLabel);		add(addIDLabel);
			add(numericCleanerLabel);		add(numericToBinaryLabel);		add(removeTypeLabel);
			add(stringToNominalLabel);		add(removePercentageLabel);		add(replaceMissingValuesLabel);
			add(resampleLabel);				add(standardizeLabel);

			preprocessBtnSelected = true;
			preprocessBtn.setIcon(enter_preprocessBtnImageIcon);
		}

		else {
			

			normalizeLabel.setVisible(false);			copyLabel.setVisible(false);
			mergeTwoValuesLabel.setVisible(false);		numericToNominalLabel.setVisible(false);
			addLabel.setVisible(false);					discretizeLabel.setVisible(false);nominalToBinaryLabel.setVisible(false);numericTransformLabel.setVisible(false);
			addExpressionLabel.setVisible(false);interquartileRangeLabel.setVisible(false);nominalToStringLabel.setVisible(false);
			addIDLabel.setVisible(false);numericCleanerLabel.setVisible(false);
			numericToBinaryLabel.setVisible(false);removeTypeLabel.setVisible(false);
			stringToNominalLabel.setVisible(false);removePercentageLabel.setVisible(false);
			replaceMissingValuesLabel.setVisible(false);resampleLabel.setVisible(false);
			standardizeLabel.setVisible(false);
		
			dataMiningBtn.setLocation(dataMiningBtn.getX(),dataMiningBtn.getY() - 345);
			analyzeBtn.setLocation(analyzeBtn.getX(), analyzeBtn.getY() - 345);

			preprocessBtnSelected = false;
			preprocessBtn.setIcon(preprocessBtnImageIcon);
		}
	}

	public void clickDataMiningBtn() {
		if (inputBtnSelected == true)
			clickInputBtn();
		if (visualizeBtnSelected == true)
			clickVisualizeBtn();
		if (preprocessBtnSelected == true)
			clickPreprocessBtn();
		if (analyzeBtnSelected == true)
			clickAnalyzeBtn();
		if (classifyBtnSelected == true)
			clickClassifyBtn();
		if (clusterBtnSelected == true)
			clickClusterBtn();
		if (associateBtnSelected == true)
			clickAssociateBtn();

		if (dataMiningBtnSelected == false) {
			analyzeBtn.setLocation(analyzeBtn.getX(), analyzeBtn.getY() + 190);

			classifyBtn.setBounds(dataMiningBtn.getX()+15,dataMiningBtn.getY() + 70, 220, 50);
			clusterBtn.setBounds(dataMiningBtn.getX()+15,dataMiningBtn.getY() + 130, 220, 50);
			associateBtn.setBounds(dataMiningBtn.getX()+15,dataMiningBtn.getY() + 190, 220, 50);
			classifyBtn.setBackground(Color.white);
			clusterBtn.setBackground(Color.WHITE);
			associateBtn.setBackground(Color.white);
			
			associationBtnImageIcon =  new ImageIcon( associationBtnImageIcon.getImage().getScaledInstance(associateBtn.getWidth()+15,associateBtn.getHeight()+15,  Image.SCALE_SMOOTH));
			clusterBtnImageIcon =  new ImageIcon(clusterBtnImageIcon.getImage().getScaledInstance(clusterBtn.getWidth()+15,clusterBtn.getHeight()+15,  Image.SCALE_SMOOTH));
			classifyBtnImageIcon =  new ImageIcon( classifyBtnImageIcon.getImage().getScaledInstance(classifyBtn.getWidth()+15,classifyBtn.getHeight()+15,  Image.SCALE_SMOOTH));
			
			enter_associationBtnImageIcon =  new ImageIcon( enter_associationBtnImageIcon.getImage().getScaledInstance(associateBtn.getWidth()+15,associateBtn.getHeight()+15,  Image.SCALE_SMOOTH));
			enter_clusterBtnImageIcon =  new ImageIcon(enter_clusterBtnImageIcon.getImage().getScaledInstance(clusterBtn.getWidth()+15,clusterBtn.getHeight()+15,  Image.SCALE_SMOOTH));
			enter_classifyBtnImageIcon =  new ImageIcon( enter_classifyBtnImageIcon.getImage().getScaledInstance(classifyBtn.getWidth()+15,classifyBtn.getHeight()+15,  Image.SCALE_SMOOTH));
			
			classifyBtn.setIcon(classifyBtnImageIcon);
			clusterBtn.setIcon(clusterBtnImageIcon);
			associateBtn.setIcon(associationBtnImageIcon);
		
			
			
			classifyBtn.setVisible(true);
			clusterBtn.setVisible(true);
			associateBtn.setVisible(true);

			add(classifyBtn);
			add(clusterBtn);
			add(associateBtn);

			dataMiningBtnSelected = true;
			dataMiningBtn.setIcon(enter_dataMiningBtnImageIcon);
		} else {

			classifyBtn.setVisible(false);
			clusterBtn.setVisible(false);
			associateBtn.setVisible(false);

			analyzeBtn.setLocation(analyzeBtn.getX(), analyzeBtn.getY() - 190);

			dataMiningBtnSelected = false;
			dataMiningBtn.setIcon(dataMiningBtnImageIcon);
		}

	}

	public void clickClassifyBtn() {
		if (clusterBtnSelected == true)
			clickClusterBtn();
		if (associateBtnSelected == true)
			clickAssociateBtn();

		if (classifyBtnSelected == false) {
			clusterBtn.setLocation(classifyBtn.getX(), clusterBtn.getY() + 270);
			associateBtn.setLocation(classifyBtn.getX(),associateBtn.getY() + 270);
			analyzeBtn.setLocation(analyzeBtn.getX(), analyzeBtn.getY() + 270);
			
			decisionTreeLabel.setBounds(classifyBtn.getX() + 15,classifyBtn.getY() + 55, 80,89 );
			decisionTreeIcon = new ImageIcon(decisionTreeIcon.getImage().getScaledInstance(decisionTreeLabel.getWidth(),decisionTreeLabel.getHeight(),  Image.SCALE_SMOOTH));
			decisionTreeLabel.setIcon(decisionTreeIcon);
			decisionTreeLabel.addMouseListener(enterLabelListener);
			
			knnLabel.setBounds(classifyBtn.getX() + 105,classifyBtn.getY() + 55, 80, 89);
			kNearestNeighborsIcon = new ImageIcon(kNearestNeighborsIcon.getImage().getScaledInstance(knnLabel.getWidth(),knnLabel.getHeight(),  Image.SCALE_SMOOTH));
			knnLabel.setIcon(kNearestNeighborsIcon);
			knnLabel.addMouseListener(enterLabelListener);
			

			naiveBayesLabel.setBounds(classifyBtn.getX() + 15,classifyBtn.getY() + 140, 80, 89);
			naiveBayesIcon = new ImageIcon(naiveBayesIcon.getImage().getScaledInstance(naiveBayesLabel.getWidth(),naiveBayesLabel.getHeight(),  Image.SCALE_SMOOTH));
			naiveBayesLabel.setIcon(naiveBayesIcon);
			naiveBayesLabel.addMouseListener(enterLabelListener);
			
			neuralNetworkLabel.setBounds(classifyBtn.getX() + 105,classifyBtn.getY() + 140, 80, 89);
			neuralNetworkIcon = new ImageIcon(neuralNetworkIcon.getImage().getScaledInstance(neuralNetworkLabel.getWidth(),neuralNetworkLabel.getHeight(),  Image.SCALE_SMOOTH));
			neuralNetworkLabel.setIcon(neuralNetworkIcon);
			neuralNetworkLabel.addMouseListener(enterLabelListener);
			
			
			svmLabel.setBounds(classifyBtn.getX() + 15,classifyBtn.getY() + 225, 80, 89);
			svmIcon = new ImageIcon(svmIcon.getImage().getScaledInstance(svmLabel.getWidth(),svmLabel.getHeight(),  Image.SCALE_SMOOTH));
			svmLabel.setIcon(svmIcon);
			svmLabel.addMouseListener(enterLabelListener);
			

			decisionTreeLabel.setVisible(true);
			knnLabel.setVisible(true);
			naiveBayesLabel.setVisible(true);
			neuralNetworkLabel.setVisible(true);
			svmLabel.setVisible(true);

			add(decisionTreeLabel);
			add(knnLabel);
			add(naiveBayesLabel);
			add(neuralNetworkLabel);
			add(svmLabel);

			classifyBtnSelected = true;
		} else {
			decisionTreeLabel.setVisible(false);
			knnLabel.setVisible(false);
			naiveBayesLabel.setVisible(false);
			neuralNetworkLabel.setVisible(false);
			svmLabel.setVisible(false);
			
			clusterBtn.setLocation(classifyBtn.getX(), clusterBtn.getY() - 270);
			associateBtn.setLocation(classifyBtn.getX(),associateBtn.getY() - 270);
			analyzeBtn.setLocation(dataMiningBtn.getX(), analyzeBtn.getY() - 270);

			classifyBtnSelected = false;

		}
	}

	public void clickClusterBtn() {
		if (classifyBtnSelected == true)
			clickClassifyBtn();
		if (associateBtnSelected == true)
			clickAssociateBtn();

		if (clusterBtnSelected == false) {

			associateBtn.setLocation(classifyBtn.getX(),associateBtn.getY() + 180);
			analyzeBtn.setLocation(analyzeBtn.getX(), analyzeBtn.getY() + 180);

			kMeansLabel.setBounds(classifyBtn.getX() + 15,clusterBtn.getY() + 55, 80, 89);
			kMeansIcon= new ImageIcon(kMeansIcon.getImage().getScaledInstance(kMeansLabel.getWidth(),kMeansLabel.getHeight(),  Image.SCALE_SMOOTH));
			kMeansLabel.setIcon(kMeansIcon);
			kMeansLabel.addMouseListener(enterLabelListener);
			
			densityBasedClusterLabel.setBounds(classifyBtn.getX() + 105,clusterBtn.getY() + 55, 80, 89);
			densityBasedClusterIcon= new ImageIcon(densityBasedClusterIcon.getImage().getScaledInstance(densityBasedClusterLabel.getWidth(),densityBasedClusterLabel.getHeight(),  Image.SCALE_SMOOTH));
			densityBasedClusterLabel.setIcon(densityBasedClusterIcon);
			densityBasedClusterLabel.addMouseListener(enterLabelListener);
			
			hierarchicalClusterLabel.setBounds(classifyBtn.getX() + 15,clusterBtn.getY() + 140, 80, 78);
			hierarchicalClusterIcon= new ImageIcon(hierarchicalClusterIcon.getImage().getScaledInstance(hierarchicalClusterLabel.getWidth(),hierarchicalClusterLabel.getHeight(),  Image.SCALE_SMOOTH));
			hierarchicalClusterLabel.setIcon(hierarchicalClusterIcon);
			hierarchicalClusterLabel.addMouseListener(enterLabelListener);
			
			emLabel.setBounds(classifyBtn.getX() + 105,clusterBtn.getY() + 140, 80, 89);
			emIcon= new ImageIcon(emIcon.getImage().getScaledInstance(emLabel.getWidth(),emLabel.getHeight(),  Image.SCALE_SMOOTH));
			emLabel.setIcon(emIcon);
			emLabel.addMouseListener(enterLabelListener);
			
			
			kMeansLabel.setVisible(true);
			densityBasedClusterLabel.setVisible(true);
			hierarchicalClusterLabel.setVisible(true);
			emLabel.setVisible(true);

			add(kMeansLabel);
			add(densityBasedClusterLabel);
			add(hierarchicalClusterLabel);
			add(emLabel);

			clusterBtnSelected = true;
		} else {
			kMeansLabel.setVisible(false);
			densityBasedClusterLabel.setVisible(false);
			hierarchicalClusterLabel.setVisible(false);
			emLabel.setVisible(false);

			associateBtn.setLocation(classifyBtn.getX(),associateBtn.getY() - 180);
			analyzeBtn.setLocation(analyzeBtn.getX(), analyzeBtn.getY() - 180);

			clusterBtnSelected = false;
		}
	}

	public void clickAssociateBtn() {
		if (clusterBtnSelected == true)
			clickClusterBtn();
		if (classifyBtnSelected == true)
			clickClassifyBtn();

		if (associateBtnSelected == false) {

			analyzeBtn.setLocation(analyzeBtn.getX(), analyzeBtn.getY() + 90);

			aprioriLabel.setBounds(classifyBtn.getX() + 15,associateBtn.getY() + 55, 80, 89);
			aprioriIcon= new ImageIcon(aprioriIcon.getImage().getScaledInstance(aprioriLabel.getWidth(),aprioriLabel.getHeight(),  Image.SCALE_SMOOTH));
			aprioriLabel.setIcon(aprioriIcon);
			aprioriLabel.addMouseListener(enterLabelListener);
			fpGrowthLabel.setBounds(classifyBtn.getX() + 105,associateBtn.getY() + 55, 80, 89);
			fpGrowthIcon= new ImageIcon(fpGrowthIcon.getImage().getScaledInstance(fpGrowthLabel.getWidth(),fpGrowthLabel.getHeight(),  Image.SCALE_SMOOTH));
			fpGrowthLabel.setIcon(fpGrowthIcon);
			fpGrowthLabel.addMouseListener(enterLabelListener);

			aprioriLabel.setVisible(true);
			fpGrowthLabel.setVisible(true);
		

			add(aprioriLabel);
			add(fpGrowthLabel);
		

			associateBtnSelected = true;
		} else {
			aprioriLabel.setVisible(false);
			fpGrowthLabel.setVisible(false);


			analyzeBtn.setLocation(analyzeBtn.getX(), analyzeBtn.getY() - 90);

			associateBtnSelected = false;
		}

	}
	
	
	
	
	public void clickAnalyzeBtn() {
		if (visualizeBtnSelected == true)
			clickVisualizeBtn();
		if (preprocessBtnSelected == true)
			clickPreprocessBtn();
		if (dataMiningBtnSelected == true)
			clickDataMiningBtn();
		if (inputBtnSelected == true)
			clickInputBtn();
		
		if (analyzeBtnSelected == false) {
		
			analyzeLabel.setBounds(analyzeBtn.getX() +5, analyzeBtn.getY() + 70,80, 89);
		
			
			analyzeLabel.setVisible(true);
			analyzeLabel.addMouseListener(enterLabelListener);
			add(analyzeLabel);

			
			repaint();
			analyzeBtnSelected = true;
			analyzeBtn.setIcon(enter_analyzeBtnImageIcon);
			
		}

		else {

			analyzeLabel.setVisible(false);
			
			
			analyzeBtnSelected = false;
			analyzeBtn.setIcon(analyzeBtnImageIcon);
		}
	}
	
class EnterLabelListener extends MouseAdapter{
		
		ParentLabel la;
		public void mouseEntered(MouseEvent e) {
			la = (ParentLabel) e.getSource();
			if(la.getType()=="open") {
				la.setIcon(enter_openImageIcon);
				tf.setText(la.getExpainText());
			}
			else if(la.getType()=="edit"){
				la.setIcon(enter_editImageIcon);
				tf.setText(la.getExpainText());
			}
			else if(la.getType()=="save"){
				la.setIcon(enter_saveImageIcon);
				tf.setText(la.getExpainText());
			}
			else if(la.getType()=="histogram"){
				la.setIcon(enter_histogramImageIcon);
				tf.setText(la.getExpainText());
			}
			else if(la.getType()=="scatterPlot"){
				la.setIcon(enter_scatterPlotImageIcon);
				tf.setText(la.getExpainText());
			}
			else if(la.getType()=="analyze")
			{
				la.setIcon(enter_subAnalyisIcon);
				tf.setText(la.getExpainText());
			}
			else if(la.getType()=="filter")
			{
				String iconName ="DMZ/ImageIcon/filterIcon/"+"enter_"+la.getName()+"ImageIcon.png";
				ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconName));
				la.setIcon(new ImageIcon( icon.getImage().getScaledInstance(55,55,Image.SCALE_SMOOTH)));
				tf.setText(la.getExpainText());
			
			}
			else
			{
				String iconName ="DMZ/ImageIcon/algorithmIcon/"+"enter_"+la.getName()+"Icon.png";
				ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconName));
				la.setIcon(new ImageIcon( icon.getImage().getScaledInstance(80,89,Image.SCALE_SMOOTH)));	
				tf.setText(la.getExpainText());

			}
			
		}

		public void mouseExited(MouseEvent e) {
			la = (ParentLabel) e.getSource();
			if(la.getType()=="open") la.setIcon(openImageIcon);
			else if(la.getType()=="edit")la.setIcon(editImageIcon);
			else if(la.getType()=="save")la.setIcon(saveImageIcon);
			else if(la.getType()=="histogram")la.setIcon(histogramImageIcon);
			else if(la.getType()=="scatterPlot")la.setIcon(scatterPlotImageIcon);
			else if(la.getType()=="analyze")
			{
				la.setIcon(subAnalysisIcon);
			}
			else if(la.getType()=="filter")
			{
				String iconName ="DMZ/ImageIcon/filterIcon/"+la.getName()+"ImageIcon.png";
				ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconName));
				la.setIcon(new ImageIcon( icon.getImage().getScaledInstance(55,55,Image.SCALE_SMOOTH)));
		
			}
			else
			{
				String iconName ="DMZ/ImageIcon/algorithmIcon/"+la.getName()+"Icon.png";
				ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconName));
				la.setIcon(new ImageIcon( icon.getImage().getScaledInstance(80,89,Image.SCALE_SMOOTH)));	
		
			}
			tf.setText(null);
		}
		
	}
	
	

class NewLabelListener extends MouseAdapter {
	ParentLabel clickLabel;
	ParentLabel la = null;

	
	public void mousePressed(MouseEvent e) {

		clickLabel = (ParentLabel) e.getSource();
		if (clickLabel.getType() == "open") {
			la = new InputDataLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
		}

		else if (clickLabel.getType() == "edit") {
			la = new EditDataLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
		}

		else if (clickLabel.getType() == "save") {
			la = new SaveDataLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
		}

		else if (clickLabel.getType() == "histogram") {
			la = new HistogramLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
		}
		else if (clickLabel.getType() == "scatterPlot") {
			la = new ScatterPlotLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
		}
	
	
		else if (clickLabel.getType() == "analyze") {
			la = new AnalyzeLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
		}
		//군집
		
		else if (clickLabel.getName() == "kMeans") {
			la = new ClusterLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
			la.setId(-1);
			((ClusterLabel)la).setAlgorithm("k-means");
			
		}
		else if (clickLabel.getName() == "densityBasedCluster") {
			la = new ClusterLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
			la.setId(-1);
			((ClusterLabel)la).setAlgorithm("Density Based Cluster");
			
		}
		else if (clickLabel.getName() == "hierarchicalCluster") {
			la = new ClusterLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
			la.setId(-1);
			((ClusterLabel)la).setAlgorithm("Hierarchical Cluster");
	
		}
		else if (clickLabel.getName() == "em") {
			la = new ClusterLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
			la.setId(-1);
			((ClusterLabel)la).setAlgorithm("EM");
			
		}
		
		//분류
		else if (clickLabel.getName() == "decisionTree") {
			la = new ClassifyLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
			la.setId(-1);
			((ClassifyLabel)la).setAlgorithm("Decision Tree");
			
		}
		else if (clickLabel.getName() == "neuralNetwork") {
			la = new ClassifyLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
			la.setId(-1);
			((ClassifyLabel)la).setAlgorithm("Neural Network");
			
		}
		else if (clickLabel.getName() == "kNearestNeighbors") {
			la = new ClassifyLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
			la.setId(-1);
			((ClassifyLabel)la).setAlgorithm("KNN");
			
		}
		else if (clickLabel.getName() == "naiveBayes") {
			la = new ClassifyLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
			la.setId(-1);
			((ClassifyLabel)la).setAlgorithm("Naive Bayes");
			
		}
		else if (clickLabel.getName() =="svm") {
			la = new ClassifyLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
			la.setId(-1);
			((ClassifyLabel)la).setAlgorithm("SVM");
			
			
		}
		
		//연관
		else if (clickLabel.getName() == "apriori") {
			la = new AssociationLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
			la.setId(-1);
			((AssociationLabel)la).setAlgorithm("Apriori");
		}
		else if (clickLabel.getName() == "fPGrowth") {
			la = new AssociationLabel();
			((ParentLabel) la).setRightPanel(rightPanel);
			la.setId(-1);							
			((AssociationLabel)la).setAlgorithm("FPGrowth");
		}
		
		
		//필터 부분
		else
		{
			la = setFilter(la,clickLabel.getName());
			
		}
		
		imageTest.setIcon(openImageIcon);
		imageTest.setSize(20,20);
		System.out.println(imageTest);
		

			la.setText(clickLabel.getText());
			if(clickLabel.getType()=="open") la.setIcon(openImageIcon); 
			else if(clickLabel.getType()=="edit") la.setIcon(editImageIcon);
			else if(clickLabel.getType()=="save") la.setIcon(saveImageIcon);
			else if(clickLabel.getType()=="histogram") la.setIcon(histogramImageIcon);
			else if(clickLabel.getType()=="scatterPlot") la.setIcon(scatterPlotImageIcon);
			else if(clickLabel.getType()=="analyze") la.setIcon(subAnalysisIcon);
			else if(clickLabel.getType()=="filter")
			{
				String iconName ="DMZ/ImageIcon/filterIcon/"+clickLabel.getName()+"ImageIcon.png";
				ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconName));
				la.setIcon(new ImageIcon( icon.getImage().getScaledInstance(55,55,Image.SCALE_SMOOTH)));
				la.setName(clickLabel.getName());
			}
			else
			{
				String iconName ="DMZ/ImageIcon/algorithmIcon/"+clickLabel.getName()+"Icon.png";
				ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconName));
				la.setIcon(new ImageIcon(icon.getImage().getScaledInstance(80,89,Image.SCALE_SMOOTH)));	
				la.setName(clickLabel.getName());
			
			}			
			la.setExpainText(clickLabel.getExpainText());
			la.setHorizontalAlignment(SwingConstants.CENTER);
			la.setVerticalAlignment(SwingConstants.CENTER);
			la.setSize(84,99);
			la.setBorder(null);
			
			cursor = tk.createCustomCursor(((ImageIcon) la.getIcon()).getImage(), new Point(16,16), null);
			thisPanel.setCursor(cursor);
			centerPanel.setCursor(cursor);
		

		}
	
		public void mouseReleased(MouseEvent e) {
			thisPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			centerPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			int px,py;
			if(checkDrag){
				centerPanel.addLabel(la);
				la.setXmlId(xmlId++);
				px = dragPoint.x;
				py = dragPoint.y;
				la.setLocation(px-(280-dragLabel.getX()+(dragLabel.getWidth()/2))+horizontalBar,py+(dragLabel.getY()-40)+verticalBar);
				if(la.getLocation().getX()<=0){
					la.setLocation(0,(int) la.getLocation().getY());
				}
				checkDrag=false;
			}

		}

		public void mouseDragged(MouseEvent e) {

			
			
			dragLabel = (ParentLabel) e.getSource();
			dragPoint = e.getPoint();
			
			centerPanel.addMouseListener(new MouseAdapter() {
			      @Override
			      public void mouseEntered(MouseEvent e) {
			    	   checkDrag=true;
			      }
			      public void mouseExited(MouseEvent e) {
			    	   checkDrag=false;
			      }
			      
			    });
			

		}

		public void mouseMoved(MouseEvent e) {
			
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}

	public LeftPanel getLeftPanel() {
		return this;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

	void setCenterPanel(CenterPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

	public void setRightPanel(RightPanel right) {
		this.rightPanel = right;
	}
	
	public ParentLabel setFilter(ParentLabel la,String alName)
	{
		la = new FilterLabel();
		((ParentLabel) la).setRightPanel(rightPanel);
		la.setId(-1);
		((FilterLabel)la).setAlgorithm(alName);
		return la;
	}
	
	public ParentLabel getDragLabel(){
		return dragLabel;
	}
	
	
	public void setExplainTextField(JTextField tf)
	{
		this.tf = tf;
	}


}
