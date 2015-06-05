/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 *    PropertySheet.java
 *    Copyright (C) 1999-2012 University of Waikato, Hamilton, New Zealand
 *
 */

package weka.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.BeanInfo;
import java.beans.Beans;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import weka.core.Capabilities;
import weka.core.Capabilities.Capability;
import weka.core.CapabilitiesHandler;
import weka.core.Environment;
import weka.core.EnvironmentHandler;
import weka.core.MultiInstanceCapabilitiesHandler;
import weka.gui.beans.GOECustomizer;

/**
 * Displays a property sheet where (supported) properties of the target object
 * may be edited.
 * 
 * @author Len Trigg (trigg@cs.waikato.ac.nz)
 * @version $Revision: 10558 $
 */
public class PropertySheetPanel extends JPanel implements
PropertyChangeListener, EnvironmentHandler {

	/** for serialization. */
	private static final long serialVersionUID = -8939835593429918345L;

	/**
	 * A specialized dialog for displaying the capabilities.
	 */
	protected class CapabilitiesHelpDialog extends JDialog implements
	PropertyChangeListener {

		/** for serialization. */
		private static final long serialVersionUID = -1404770987103289858L;

		/** the dialog itself. */
		private CapabilitiesHelpDialog m_Self;

		/**
		 * default constructor.
		 * 
		 * @param owner the owning frame
		 */
		public CapabilitiesHelpDialog(Frame owner) {
			super(owner);

			initialize();
		}

		/**
		 * default constructor.
		 * 
		 * @param owner the owning dialog
		 */
		public CapabilitiesHelpDialog(Dialog owner) {
			super(owner);

			initialize();
		}

		/**
		 * Initializes the dialog.
		 */
		protected void initialize() {
			setTitle("Information about Capabilities");

			m_Self = this;

			m_CapabilitiesText = new JTextArea();
			m_CapabilitiesText.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			m_CapabilitiesText.setLineWrap(true);
			m_CapabilitiesText.setWrapStyleWord(true);
			m_CapabilitiesText.setEditable(false);
			updateText();
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					m_Self.dispose();
					if (m_CapabilitiesDialog == m_Self) {
						m_CapabilitiesBut.setEnabled(true);
					}
				}
			});
			getContentPane().setLayout(new BorderLayout());
			getContentPane().add(new JScrollPane(m_CapabilitiesText),
					BorderLayout.CENTER);
			pack();
		}

		/**
		 * updates the content of the capabilities help dialog.
		 */
		protected void updateText() {
			StringBuffer helpText = new StringBuffer();

			if (m_Target instanceof CapabilitiesHandler) {
				helpText.append(addCapabilities("CAPABILITIES",
						((CapabilitiesHandler) m_Target).getCapabilities()));
			}

			if (m_Target instanceof MultiInstanceCapabilitiesHandler) {
				helpText.append(addCapabilities("MI CAPABILITIES",
						((MultiInstanceCapabilitiesHandler) m_Target)
						.getMultiInstanceCapabilities()));
			}

			m_CapabilitiesText.setText(helpText.toString());
			m_CapabilitiesText.setCaretPosition(0);
		}

		/**
		 * This method gets called when a bound property is changed.
		 * 
		 * @param evt the change event
		 */
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			updateText();
		}
	}

	/**
	 * returns a comma-separated list of all the capabilities.
	 * 
	 * @param c the capabilities to get a string representation from
	 * @return the string describing the capabilities
	 */
	public static String listCapabilities(Capabilities c) {
		String result;
		Iterator<Capability> iter;

		result = "";
		iter = c.capabilities();
		while (iter.hasNext()) {
			if (result.length() != 0) {
				result += ", ";
			}
			result += iter.next().toString();
		}

		return result;
	}

	/**
	 * generates a string from the capapbilities, suitable to add to the help
	 * text.
	 * 
	 * @param title the title for the capabilities
	 * @param c the capabilities
	 * @return a string describing the capabilities
	 */
	public static String addCapabilities(String title, Capabilities c) {
		String result;
		String caps;

		result = title + "\n";

		// class
		caps = listCapabilities(c.getClassCapabilities());
		if (caps.length() != 0) {
			result += "Class -- ";
			result += caps;
			result += "\n\n";
		}

		// attribute
		caps = listCapabilities(c.getAttributeCapabilities());
		if (caps.length() != 0) {
			result += "Attributes -- ";
			result += caps;
			result += "\n\n";
		}

		// other capabilities
		caps = listCapabilities(c.getOtherCapabilities());
		if (caps.length() != 0) {
			result += "Other -- ";
			result += caps;
			result += "\n\n";
		}

		// additional stuff
		result += "Additional\n";
		result += "min # of instances: " + c.getMinimumNumberInstances() + "\n";
		result += "\n";

		return result;
	}

	/** The target object being edited. */
	private Object m_Target;

	/** Holds the customizer (if one exists) for the object being edited */
	private GOECustomizer m_Customizer;

	/** Holds properties of the target. */
	private PropertyDescriptor m_Properties[];

	/** Holds the methods of the target. */
	private MethodDescriptor m_Methods[];

	/** Holds property editors of the object. */
	private PropertyEditor m_Editors[];

	/** Holds current object values for each property. */
	private Object m_Values[];

	/** Stores GUI components containing each editing component. */
	private JComponent m_Views[];

	/** The labels for each property. */
	private JLabel m_Labels[];

	/** The tool tip text for each property. */
	private String m_TipTexts[];

	/** StringBuffer containing help text for the object being edited. */
	private StringBuffer m_HelpText;

	/** Help dialog. */
	private JDialog m_HelpDialog;

	/** Capabilities Help dialog. */
	private CapabilitiesHelpDialog m_CapabilitiesDialog;

	/** Button to pop up the full help text in a separate dialog. */
	private JButton m_HelpBut;

	/** Button to pop up the capabilities in a separate dialog. */
	private JButton m_CapabilitiesBut;

	/** the TextArea of the Capabilities help dialog. */
	private JTextArea m_CapabilitiesText;

	/** A count of the number of properties we have an editor for. */
	private int m_NumEditable = 0;

	/**
	 * The panel holding global info and help, if provided by the object being
	 * editied.
	 */
	private JPanel m_aboutPanel;

	/** Environment variables to pass on to any editors that can handle them */
	private transient Environment m_env;

	/**
	 * Creates the property sheet panel.
	 */
	public PropertySheetPanel() {

		// setBorder(BorderFactory.createLineBorder(Color.red));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		m_env = Environment.getSystemWide();
	}

	/**
	 * Return the panel containing global info and help for the object being
	 * edited. May return null if the edited object provides no global info or tip
	 * text.
	 * 
	 * @return the about panel.
	 */
	public JPanel getAboutPanel() {
		return m_aboutPanel;
	}

	/** A support object for handling property change listeners. */
	private final PropertyChangeSupport support = new PropertyChangeSupport(this);

	/**
	 * Updates the property sheet panel with a changed property and also passed
	 * the event along.
	 * 
	 * @param evt a value of type 'PropertyChangeEvent'
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		wasModified(evt); // Let our panel update before guys downstream
		support.firePropertyChange("", null, null);
	}

	/**
	 * Adds a PropertyChangeListener.
	 * 
	 * @param l a value of type 'PropertyChangeListener'
	 */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener l) {
		support.addPropertyChangeListener(l);
	}

	/**
	 * Removes a PropertyChangeListener.
	 * 
	 * @param l a value of type 'PropertyChangeListener'
	 */
	@Override
	public void removePropertyChangeListener(PropertyChangeListener l) {
		support.removePropertyChangeListener(l);
	}

	/**
	 * Sets a new target object for customisation.
	 * 
	 * @param targ a value of type 'Object'
	 */
	public synchronized void setTarget(Object targ) {

		if (m_env == null) {
			m_env = Environment.getSystemWide();
		}

		// used to offset the components for the properties of targ
		// if there happens to be globalInfo available in targ
		int componentOffset = 0;

		// Close any child windows at this point
		removeAll();

		setLayout(new BorderLayout());
		JPanel scrollablePanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(scrollablePanel);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(scrollPane, BorderLayout.NORTH);

		GridBagLayout gbLayout = new GridBagLayout();

		scrollablePanel.setLayout(gbLayout);
		setVisible(false);
		m_NumEditable = 0;
		m_Target = targ;
		Class<?> custClass = null;
		try {
			BeanInfo bi = Introspector.getBeanInfo(m_Target.getClass());
			m_Properties = bi.getPropertyDescriptors();
			m_Methods = bi.getMethodDescriptors();
			custClass = Introspector.getBeanInfo(m_Target.getClass())
					.getBeanDescriptor().getCustomizerClass();
		} catch (IntrospectionException ex) {
			System.err.println("PropertySheet: Couldn't introspect");
			return;
		}

		JTextArea jt = new JTextArea();
		m_HelpText = null;
		// Look for a globalInfo method that returns a string
		// describing the target
		Object args[] = {};
		boolean firstTip = true;
		StringBuffer optionsBuff = new StringBuffer();
		for (MethodDescriptor m_Method : m_Methods) {
			String name = m_Method.getDisplayName();
			Method meth = m_Method.getMethod();

			if (name.endsWith("TipText")) {
				if (meth.getReturnType().equals(String.class)) {
					try {
						String tempTip = (String) (meth.invoke(m_Target, args));
						// int ci = tempTip.indexOf('.');

						if (firstTip) {
							optionsBuff.append("OPTIONS\n");
							firstTip = false;
						}
						tempTip = tempTip.replace("<html>", "").replace("</html>", "")
								.replace("<br>", "\n").replace("<p>", "\n\n");
						optionsBuff.append(name.replace("TipText", "")).append(" -- ");
						optionsBuff.append(tempTip).append("\n\n");
						// jt.setText(m_HelpText.toString());

					} catch (Exception ex) {

					}
					// break;
				}
			}

			if (name.equals("globalInfo")) {
				if (meth.getReturnType().equals(String.class)) {
					try {
						// Object args[] = { };
						String globalInfo = (String) (meth.invoke(m_Target, args));
						String summary = globalInfo;
						int ci = globalInfo.indexOf('.');
						if (ci != -1) {
							summary = globalInfo.substring(0, ci + 1);
						}
						final String className = targ.getClass().getName();
						m_HelpText = new StringBuffer("NAME\n");
						//m_HelpText.append(className).append("\n\n");
						m_HelpText.append("SYNOPSIS\n").append(globalInfo).append("\n\n");
						m_HelpBut = new JButton("더보기");
						m_HelpBut.setToolTipText("More information about " + className);

						m_HelpBut.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent a) {
								openHelpFrame();
								m_HelpBut.setEnabled(false);
							}
						});

						if (m_Target instanceof CapabilitiesHandler) {
							m_CapabilitiesBut = new JButton("기능");
							m_CapabilitiesBut.setToolTipText("The capabilities of "
									+ className);

							m_CapabilitiesBut.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent a) {
									openCapabilitiesHelpDialog();
									m_CapabilitiesBut.setEnabled(false);
								}
							});
						} else {
							m_CapabilitiesBut = null;
						}

						jt.setColumns(20);
						jt.setFont(new Font("SansSerif", Font.PLAIN, 12));
						jt.setEditable(false);
						jt.setLineWrap(true);
						jt.setWrapStyleWord(true);
						jt.setText(summary);
						jt.setBackground(getBackground());
						JPanel jp = new JPanel();
						jp.setBorder(BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder("요약"),
								BorderFactory.createEmptyBorder(5, 5, 5, 5)));
						jp.setLayout(new BorderLayout());
						jt.setBackground(Color.white);
						jp.add(jt, BorderLayout.CENTER);
						JPanel p2 = new JPanel();
						p2.setBackground(Color.WHITE);
						p2.setLayout(new BorderLayout());
						p2.add(m_HelpBut, BorderLayout.NORTH);
						if (m_CapabilitiesBut != null) {
							JPanel p3 = new JPanel();
							p3.setLayout(new BorderLayout());
							p3.add(m_CapabilitiesBut, BorderLayout.NORTH);
							p2.add(p3, BorderLayout.CENTER);
						}
						jp.add(p2, BorderLayout.EAST);
						GridBagConstraints gbConstraints = new GridBagConstraints();
						// gbConstraints.anchor = GridBagConstraints.EAST;
						gbConstraints.fill = GridBagConstraints.BOTH;
						// gbConstraints.gridy = 0; gbConstraints.gridx = 0;
						gbConstraints.gridwidth = 2;
						gbConstraints.insets = new Insets(0, 5, 0, 5);
						gbLayout.setConstraints(jp, gbConstraints);
						m_aboutPanel = jp;
						m_aboutPanel.setBackground(Color.WHITE);
						scrollablePanel.add(m_aboutPanel);
						componentOffset = 1;


						scrollablePanel.setBackground(Color.white);

						// break;
					} catch (Exception ex) {

					}
				}
			}
		}

		if (m_HelpText != null) {
			m_HelpText.append(optionsBuff.toString());
		}

		if (custClass != null) {
			// System.out.println("**** We've found a customizer for this object!");
			try {
				Object customizer = custClass.newInstance();

				if (customizer instanceof JComponent
						&& customizer instanceof GOECustomizer) {
					m_Customizer = (GOECustomizer) customizer;

					m_Customizer.dontShowOKCancelButtons();
					m_Customizer.setObject(m_Target);

					GridBagConstraints gbc = new GridBagConstraints();
					gbc.fill = GridBagConstraints.BOTH;
					gbc.gridwidth = 2;
					gbc.gridy = componentOffset;
					gbc.gridx = 0;
					gbc.insets = new Insets(0, 5, 0, 5);
					gbLayout.setConstraints((JComponent) m_Customizer, gbc);
					scrollablePanel.add((JComponent) m_Customizer);

					validate();

					// sometimes, the calculated dimensions seem to be too small and the
					// scrollbars show up, though there is still plenty of space on the
					// screen. hence we increase the dimensions a bit to fix this.
					Dimension dim = scrollablePanel.getPreferredSize();
					dim.height += 20;
					dim.width += 20;
					scrollPane.setPreferredSize(dim);
					validate();

					setVisible(true);
					return;
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		m_Editors = new PropertyEditor[m_Properties.length];
		m_Values = new Object[m_Properties.length];
		m_Views = new JComponent[m_Properties.length];
		m_Labels = new JLabel[m_Properties.length];
		m_TipTexts = new String[m_Properties.length];
		// boolean firstTip = true;
		for (int i = 0; i < m_Properties.length; i++) {

			// Don't display hidden or expert properties.
			if (m_Properties[i].isHidden() || m_Properties[i].isExpert()) {
				continue;
			}

			String name = m_Properties[i].getDisplayName();
			Class<?> type = m_Properties[i].getPropertyType();
			Method getter = m_Properties[i].getReadMethod();
			Method setter = m_Properties[i].getWriteMethod();

			// Only display read/write properties.
			if (getter == null || setter == null) {
				continue;
			}

			JComponent view = null;

			try {
				// Object args[] = { };
				Object value = getter.invoke(m_Target, args);
				m_Values[i] = value;

				PropertyEditor editor = null;
				Class<?> pec = m_Properties[i].getPropertyEditorClass();
				if (pec != null) {
					try {
						editor = (PropertyEditor) pec.newInstance();
					} catch (Exception ex) {
						// Drop through.
					}
				}
				if (editor == null) {
					editor = PropertyEditorManager.findEditor(type);
				}
				m_Editors[i] = editor;

				// If we can't edit this component, skip it.
				if (editor == null) {
					// If it's a user-defined property we give a warning.
					// String getterClass = m_Properties[i].getReadMethod()
					// .getDeclaringClass().getName();
					/*
					 * System.err.println("Warning: Can't find public property editor" +
					 * " for property \"" + name + "\" (class \"" + type.getName() +
					 * "\").  Skipping.");
					 */
					continue;
				}
				if (editor instanceof GenericObjectEditor) {
					((GenericObjectEditor) editor).setClassType(type);
				}

				if (editor instanceof EnvironmentHandler) {
					((EnvironmentHandler) editor).setEnvironment(m_env);
				}

				// Don't try to set null values:
				if (value == null) {
					// If it's a user-defined property we give a warning.
					// String getterClass = m_Properties[i].getReadMethod()
					// .getDeclaringClass().getName();
					/*
					 * if (getterClass.indexOf("java.") != 0) {
					 * System.err.println("Warning: Property \"" + name +
					 * "\" has null initial value.  Skipping."); }
					 */
					continue;
				}

				editor.setValue(value);

				// now look for a TipText method for this property
				String tipName = name + "TipText";
				for (MethodDescriptor m_Method : m_Methods) {
					String mname = m_Method.getDisplayName();
					Method meth = m_Method.getMethod();
					if (mname.equals(tipName)) {
						if (meth.getReturnType().equals(String.class)) {
							try {
								String tempTip = (String) (meth.invoke(m_Target, args));
								int ci = tempTip.indexOf('.');
								if (ci < 0) {
									m_TipTexts[i] = tempTip;
								} else {
									m_TipTexts[i] = tempTip.substring(0, ci);
								}
								/*
								 * if (m_HelpText != null) { if (firstTip) {
								 * m_HelpText.append("OPTIONS\n"); firstTip = false; }
								 * m_HelpText.append(name).append(" -- ");
								 * m_HelpText.append(tempTip).append("\n\n");
								 * //jt.setText(m_HelpText.toString()); }
								 */
							} catch (Exception ex) {

							}
							break;
						}
					}
				}
				// Now figure out how to display it...
				if (editor.isPaintable() && editor.supportsCustomEditor()) {
					view = new PropertyPanel(editor);
				} else if (editor.supportsCustomEditor()
						&& (editor.getCustomEditor() instanceof JComponent)) {
					view = (JComponent) editor.getCustomEditor();
				} else if (editor.getTags() != null) {
					view = new PropertyValueSelector(editor);
				} else if (editor.getAsText() != null) {
					view = new PropertyText(editor);
				} else {
					System.err.println("Warning: Property \"" + name
							+ "\" has non-displayabale editor.  Skipping.");
					continue;
				}

				editor.addPropertyChangeListener(this);

			} catch (InvocationTargetException ex) {
				System.err.println("Skipping property " + name
						+ " ; exception on target: " + ex.getTargetException());
				ex.getTargetException().printStackTrace();
				continue;
			} catch (Exception ex) {
				System.err.println("Skipping property " + name + " ; exception: " + ex);
				ex.printStackTrace();
				continue;
			}


			if(name.equals("debug")) 
				m_Labels[i] = new JLabel("디버그", SwingConstants.RIGHT);
			else if(name.equals("IDIndex"))
				m_Labels[i] = new JLabel("ID 인덱스", SwingConstants.RIGHT);
			else if(name.equals("attributeName"))
				m_Labels[i] = new JLabel("속성 이름", SwingConstants.RIGHT);
			else if(name.equals("doNotCheckCapabilities"))
				m_Labels[i] = new JLabel("기능 확인 여부", SwingConstants.RIGHT);
			else if(name.equals("attributeIndices"))
				m_Labels[i] = new JLabel("적용할 속성 범위 설정", SwingConstants.RIGHT);
			else if(name.equals("detectionPerAttribute"))
				m_Labels[i] = new JLabel("적용할 속성 범위 설정", SwingConstants.RIGHT);
			else if(name.equals("attributeIndex"))
				m_Labels[i] = new JLabel("속성인덱스", SwingConstants.RIGHT);
			else if(name.equals("attributeType"))
				m_Labels[i] = new JLabel("속성타입", SwingConstants.RIGHT);
			else if(name.equals("dateFormat"))
				m_Labels[i] = new JLabel("날짜포맷", SwingConstants.RIGHT);
			else if(name.equals("nominalLabels"))
				m_Labels[i] = new JLabel("명목형 인스턴스", SwingConstants.RIGHT);
			else if(name.equals("ignoreClass"))
				m_Labels[i] = new JLabel("클래스 무시", SwingConstants.RIGHT);
			else if(name.equals("scale"))
				m_Labels[i] = new JLabel("스케일", SwingConstants.RIGHT);
			else if(name.equals("translation"))
				m_Labels[i] = new JLabel("시작값", SwingConstants.RIGHT);
			else if(name.equals("expression"))
				m_Labels[i] = new JLabel("수식입력", SwingConstants.RIGHT);
			else if(name.equals("name"))
				m_Labels[i] = new JLabel("속성 이름", SwingConstants.RIGHT);
			else if(name.equals("className"))
				m_Labels[i] = new JLabel("클래스이름", SwingConstants.RIGHT);
			else if(name.equals("invertSelection"))
				m_Labels[i] = new JLabel("선택 인버터", SwingConstants.RIGHT);
			else if(name.equals("methodName"))
				m_Labels[i] = new JLabel("메소드 이름", SwingConstants.RIGHT);
			else if(name.equals("bins"))
				m_Labels[i] = new JLabel("인스턴스 개수", SwingConstants.RIGHT);

			else if(name.equals("desiredWeightOfInstancesPerInterval"))
				m_Labels[i] = new JLabel("간격당 인스턴스 가중치", SwingConstants.RIGHT);
			else if(name.equals("findNumBins"))
				m_Labels[i] = new JLabel("인스턴스 개수 찾기", SwingConstants.RIGHT);
			else if(name.equals("makeBinary"))
				m_Labels[i] = new JLabel("바이너리로 변환 ", SwingConstants.RIGHT);
			else if(name.equals("useBinNumbers"))
				m_Labels[i] = new JLabel("입력된 인스턴스개수 사용", SwingConstants.RIGHT);
			else if(name.equals("useEqualFrequency"))
				m_Labels[i] = new JLabel("동일 빈도 사용", SwingConstants.RIGHT);
			else if(name.equals("extremeValuesAsOutliers"))
				m_Labels[i] = new JLabel("극단치값과 이상치값", SwingConstants.RIGHT);
			else if(name.equals("extremeValuesFactor"))
				m_Labels[i] = new JLabel("극단치 임계값", SwingConstants.RIGHT);
			else if(name.equals("outlierFactor"))
				m_Labels[i] = new JLabel("이상치 임계값", SwingConstants.RIGHT);
			else if(name.equals("outputOffsetMultiplier"))
				m_Labels[i] = new JLabel("Offset속성 추가", SwingConstants.RIGHT);
			else if(name.equals("percentage"))
				m_Labels[i] = new JLabel("퍼센테이지(%)", SwingConstants.RIGHT);
			else if(name.equals("noReplacement"))
				m_Labels[i] = new JLabel("인스턴스 대체", SwingConstants.RIGHT);
			else if(name.equals("randomSeed"))
				m_Labels[i] = new JLabel("랜덤시드", SwingConstants.RIGHT);
			else if(name.equals("sampleSizePercent"))
				m_Labels[i] = new JLabel("샘플크기(%)", SwingConstants.RIGHT);
			else if(name.equals("firstValueIndex"))
				m_Labels[i] = new JLabel("첫번째 인덱스값", SwingConstants.RIGHT);
			else if(name.equals("secondValueIndex"))
				m_Labels[i] = new JLabel("두번째 인덱스값", SwingConstants.RIGHT);
			else if(name.equals("binaryAttributesNominal"))
				m_Labels[i] = new JLabel("바이너리 to 명목", SwingConstants.RIGHT);
			else if(name.equals("transformAllValues"))
				m_Labels[i] = new JLabel("모든값 전환", SwingConstants.RIGHT);
			else if(name.equals("attributeIndexes"))
				m_Labels[i] = new JLabel("속성인덱스", SwingConstants.RIGHT);
			else if(name.equals("closeTo"))
				m_Labels[i] = new JLabel("근사수치설정", SwingConstants.RIGHT);
			else if(name.equals("closeToDefault"))
				m_Labels[i] = new JLabel("근사수치 초기값설정", SwingConstants.RIGHT);
			else if(name.equals("closeToTolerance"))
				m_Labels[i] = new JLabel("근사수치 허용값설정", SwingConstants.RIGHT);
			else if(name.equals("decimals"))
				m_Labels[i] = new JLabel("소수점 설정", SwingConstants.RIGHT);
			else if(name.equals("includeClass"))
				m_Labels[i] = new JLabel("클래스포함", SwingConstants.RIGHT);
			else if(name.equals("maxDefault"))
				m_Labels[i] = new JLabel("최대초기값", SwingConstants.RIGHT);
			else if(name.equals("maxThreshold"))
				m_Labels[i] = new JLabel("최대한계치값", SwingConstants.RIGHT);
			else if(name.equals("minDefault"))
				m_Labels[i] = new JLabel("최소초기값", SwingConstants.RIGHT);
			else if(name.equals("minThreshold"))
				m_Labels[i] = new JLabel("최소한계치값", SwingConstants.RIGHT);
			else if(name.equals("attributeRange"))
				m_Labels[i] = new JLabel("속성범위", SwingConstants.RIGHT);

			else if(name.equals("crossValidate"))
				m_Labels[i] = new JLabel("교차검증", SwingConstants.RIGHT);
			else if(name.equals("distanceWeighting"))
				m_Labels[i] = new JLabel("거리 가중치", SwingConstants.RIGHT);
			else if(name.equals("meanSquared"))
				m_Labels[i] = new JLabel("스퀘어드 평균", SwingConstants.RIGHT);
			else if(name.equals("nearestNeighbourSearchAlgorithm"))
				m_Labels[i] = new JLabel("최근접 탐색 알고리즘", SwingConstants.RIGHT);
			else if(name.equals("windowSize"))
				m_Labels[i] = new JLabel("창크기", SwingConstants.RIGHT);

			else if(name.equals("autoBuild"))
				m_Labels[i] = new JLabel("자동 생성", SwingConstants.RIGHT);
			else if(name.equals("decay"))
				m_Labels[i] = new JLabel("부식(decay)", SwingConstants.RIGHT);
			else if(name.equals("hiddenLayers"))
				m_Labels[i] = new JLabel("음폐층(hiddenLayers)", SwingConstants.RIGHT);
			else if(name.equals("learningRate"))
				m_Labels[i] = new JLabel("학습율", SwingConstants.RIGHT);
			else if(name.equals("momentum"))
				m_Labels[i] = new JLabel("탄력성", SwingConstants.RIGHT);
			else if(name.equals("nominalToBinaryFilter"))
				m_Labels[i] = new JLabel("명목형을 바이너리로 변경", SwingConstants.RIGHT);
			else if(name.equals("normalizeAttributes"))
				m_Labels[i] = new JLabel("정규화속성", SwingConstants.RIGHT);
			else if(name.equals("normalizeNumericClass"))
				m_Labels[i] = new JLabel("정규화 수치클래스", SwingConstants.RIGHT);
			else if(name.equals("reset"))
				m_Labels[i] = new JLabel("리셋", SwingConstants.RIGHT);
			else if(name.equals("seed"))
				m_Labels[i] = new JLabel("시드", SwingConstants.RIGHT);
			else if(name.equals("trainingTime"))
				m_Labels[i] = new JLabel("훈련시간", SwingConstants.RIGHT);
			else if(name.equals("clusterer"))
				m_Labels[i] = new JLabel("군집알고리즘", SwingConstants.RIGHT);
			else if(name.equals("minStdDev"))
				m_Labels[i] = new JLabel("최소표준편차", SwingConstants.RIGHT);
			else if(name.equals("displayModelInOldFormat"))
				m_Labels[i] = new JLabel("이전포맷에서 모델표시", SwingConstants.RIGHT);
			else if(name.equals("maxIterations"))
				m_Labels[i] = new JLabel("최대반복", SwingConstants.RIGHT);
			else if(name.equals("maximumNumberOfClusters"))
				m_Labels[i] = new JLabel("최대 군집개수", SwingConstants.RIGHT);
			else if(name.equals("minLogLikelihoodImprovementCV"))
				m_Labels[i] = new JLabel("최소로그 우도향상CV", SwingConstants.RIGHT);
			else if(name.equals("minLogLikelihoodImprovementIterating"))
				m_Labels[i] = new JLabel("최소로그 우도향상반복", SwingConstants.RIGHT);
			else if(name.equals("numClusters"))
				m_Labels[i] = new JLabel("군집 개수", SwingConstants.RIGHT);
			else if(name.equals("numExecutionSlots"))
				m_Labels[i] = new JLabel("실행슬롯 개수", SwingConstants.RIGHT);
			else if(name.equals("numFolds"))
				m_Labels[i] = new JLabel("폴드 개수", SwingConstants.RIGHT);
			else if(name.equals("numKMeansRuns"))
				m_Labels[i] = new JLabel("KMeans 실행 개수", SwingConstants.RIGHT);
			else if(name.equals("delta"))
				m_Labels[i] = new JLabel("델타값", SwingConstants.RIGHT);
			else if(name.equals("findAllRulesForSupportLevel"))
				m_Labels[i] = new JLabel("지지도 레벨을 위한 모든 규착 찾기", SwingConstants.RIGHT);
			else if(name.equals("lowerBoundMinSupport"))
				m_Labels[i] = new JLabel("최소지지도 하한값", SwingConstants.RIGHT);
			else if(name.equals("maxNumberOfItems"))
				m_Labels[i] = new JLabel("Items의 최대개수", SwingConstants.RIGHT);
			else if(name.equals("metricType"))
				m_Labels[i] = new JLabel("메트릭타입(metricType)", SwingConstants.RIGHT);
			else if(name.equals("minMetric"))
				m_Labels[i] = new JLabel("최소매트릭(Metric)", SwingConstants.RIGHT);
			else if(name.equals("numRulesToFind"))
				m_Labels[i] = new JLabel("찾을 규칙의 개수", SwingConstants.RIGHT);
			else if(name.equals("positiveIndex"))
				m_Labels[i] = new JLabel("낙관인덱스(positiveIndex)", SwingConstants.RIGHT);
			else if(name.equals("rulesMustContain"))
				m_Labels[i] = new JLabel("반드시 포함할 규칙", SwingConstants.RIGHT);
			else if(name.equals("transactionsMustContain"))
				m_Labels[i] = new JLabel("반드시 포함할 트랜젝션", SwingConstants.RIGHT);
			else if(name.equals("upperBoundMinSupport"))
				m_Labels[i] = new JLabel("최소지지도 상한값", SwingConstants.RIGHT);
			else if(name.equals("useORForMustContainList"))
				m_Labels[i] = new JLabel("사용하거나 반드시 포함할 리스트", SwingConstants.RIGHT);
			else if(name.equals("validationSetSize"))
				m_Labels[i] = new JLabel("사이즈설정 확인", SwingConstants.RIGHT);
			else if(name.equals("validationThreshold"))
				m_Labels[i] = new JLabel("역치값(Threshold)확인", SwingConstants.RIGHT);
			else if(name.equals("binarySplits"))
				m_Labels[i] = new JLabel("이진 분할", SwingConstants.RIGHT);
			else if(name.equals("collapseTree"))
				m_Labels[i] = new JLabel("트리 축소", SwingConstants.RIGHT);
			else if(name.equals("confidenceFactor"))
				m_Labels[i] = new JLabel("신뢰 계수", SwingConstants.RIGHT);
			else if(name.equals("doNotMakeSplitPointActualValue"))
				m_Labels[i] = new JLabel("분리점 실제값 이전 여부", SwingConstants.RIGHT);
			else if(name.equals("minNumObj"))
				m_Labels[i] = new JLabel("인스턴스 최소 개수", SwingConstants.RIGHT);
			else if(name.equals("reducedErrorPruning"))
				m_Labels[i] = new JLabel("제거된 오류 가지치기", SwingConstants.RIGHT);
			else if(name.equals("saveInstanceData"))
				m_Labels[i] = new JLabel("훈련데이터 저장", SwingConstants.RIGHT);
			else if(name.equals("subtreeRaising"))
				m_Labels[i] = new JLabel("하위 트리 올림", SwingConstants.RIGHT);
			else if(name.equals("unpruned"))
				m_Labels[i] = new JLabel("가지치기", SwingConstants.RIGHT);
			else if(name.equals("useLaplace"))
				m_Labels[i] = new JLabel("라플라스 사용", SwingConstants.RIGHT);
			else if(name.equals("useMDLcorrection"))
				m_Labels[i] = new JLabel("MDL 보정 사용", SwingConstants.RIGHT);
			else if(name.equals("useKernerlEstimator"))
				m_Labels[i] = new JLabel("커널 추정기 사용", SwingConstants.RIGHT);
			else if(name.equals("useSupervisedDiscretization"))
				m_Labels[i] = new JLabel("감독 이산화 사용", SwingConstants.RIGHT);
			else if(name.equals("canopyMaxNumCanopiesToHoldInMemory"))
				m_Labels[i] = new JLabel("캐노피 최대 수", SwingConstants.RIGHT);
			else if(name.equals("canopyMinimumCanopyDensity"))
				m_Labels[i] = new JLabel("캐노피 최소 밀도", SwingConstants.RIGHT);
			else if(name.equals("canopyT1"))
				m_Labels[i] = new JLabel("캐노피 T1", SwingConstants.RIGHT);
			else if(name.equals("canopyT2"))
				m_Labels[i] = new JLabel("캐노피 T2", SwingConstants.RIGHT);
			else if(name.equals("displayStdDevs"))
				m_Labels[i] = new JLabel("표준 디스플레이", SwingConstants.RIGHT);
			else if(name.equals("distanceFunction"))
				m_Labels[i] = new JLabel("거리 함수", SwingConstants.RIGHT);
			else if(name.equals("dontReplaceMissingValues"))
				m_Labels[i] = new JLabel("누락된 값 대체", SwingConstants.RIGHT);
			else if(name.equals("fastDistanceCalc"))
				m_Labels[i] = new JLabel("거리 가속화 계산", SwingConstants.RIGHT);
			else if(name.equals("initializationMethod"))
				m_Labels[i] = new JLabel("초기화 방법", SwingConstants.RIGHT);
			else if(name.equals("initializationMethod"))
				m_Labels[i] = new JLabel("초기화 방법", SwingConstants.RIGHT);
			else if(name.equals("preserveInstancesOrder"))
				m_Labels[i] = new JLabel("인스턴스 순서", SwingConstants.RIGHT);
			else if(name.equals("reduceNumberOfDistanceCalcsViaCanopies"))
				m_Labels[i] = new JLabel("수행되는 거리 계산 감소", SwingConstants.RIGHT);
			else if(name.equals("canopyPeriodicPruningRate"))
				m_Labels[i] = new JLabel("캐노피 가지치기 속도", SwingConstants.RIGHT);
			
			
			
			

			/*
=======
      } catch (InvocationTargetException ex) {
        System.err.println("Skipping property " + name
          + " ; exception on target: " + ex.getTargetException());
        ex.getTargetException().printStackTrace();
        continue;
      } catch (Exception ex) {
        System.err.println("Skipping property " + name + " ; exception: " + ex);
        ex.printStackTrace();
        continue;
      }
    
     	
      if(name.equals("debug")) 
     		m_Labels[i] = new JLabel("디버그", SwingConstants.RIGHT);
     	else if(name.equals("IDIndex"))
     		m_Labels[i] = new JLabel("ID 인덱스", SwingConstants.RIGHT);
     	else if(name.equals("attributeName"))
     		m_Labels[i] = new JLabel("속성 이름", SwingConstants.RIGHT);
     	else if(name.equals("doNotCheckCapabilities"))
     		m_Labels[i] = new JLabel("기능 확인 여부", SwingConstants.RIGHT);
     	else if(name.equals("attributeIndices"))
     		m_Labels[i] = new JLabel("적용할 속성 범위 설정", SwingConstants.RIGHT);
     	else if(name.equals("detectionPerAttribute"))
     		m_Labels[i] = new JLabel("적용할 속성 범위 설정", SwingConstants.RIGHT);
     	else if(name.equals("attributeIndex"))
     		m_Labels[i] = new JLabel("속성인덱스", SwingConstants.RIGHT);
     	else if(name.equals("attributeType"))
     		m_Labels[i] = new JLabel("속성타입", SwingConstants.RIGHT);
     	else if(name.equals("dateFormat"))
     		m_Labels[i] = new JLabel("날짜포맷", SwingConstants.RIGHT);
     	else if(name.equals("nominalLabels"))
     		m_Labels[i] = new JLabel("명목형 인스턴스", SwingConstants.RIGHT);
     	else if(name.equals("ignoreClass"))
     		m_Labels[i] = new JLabel("클래스 무시", SwingConstants.RIGHT);
     	else if(name.equals("scale"))
     		m_Labels[i] = new JLabel("스케일", SwingConstants.RIGHT);
     	else if(name.equals("translation"))
     		m_Labels[i] = new JLabel("시작값", SwingConstants.RIGHT);
     	else if(name.equals("expression"))
     		m_Labels[i] = new JLabel("수식입력", SwingConstants.RIGHT);
     	else if(name.equals("name"))
     		m_Labels[i] = new JLabel("속성 이름", SwingConstants.RIGHT);
     	else if(name.equals("className"))
     		m_Labels[i] = new JLabel("클래스이름", SwingConstants.RIGHT);
     	else if(name.equals("invertSelection"))
     		m_Labels[i] = new JLabel("선택 인버터", SwingConstants.RIGHT);
     	else if(name.equals("methodName"))
     		m_Labels[i] = new JLabel("메소드 이름", SwingConstants.RIGHT);
     	else if(name.equals("bins"))
     		m_Labels[i] = new JLabel("인스턴스 개수", SwingConstants.RIGHT);
      
     	else if(name.equals("desiredWeightOfInstancesPerInterval"))
     		m_Labels[i] = new JLabel("간격당 인스턴스 가중치", SwingConstants.RIGHT);
       	else if(name.equals("findNumBins"))
     		m_Labels[i] = new JLabel("인스턴스 개수 찾기", SwingConstants.RIGHT);
       	else if(name.equals("makeBinary"))
     		m_Labels[i] = new JLabel("바이너리로 변환 ", SwingConstants.RIGHT);
       	else if(name.equals("useBinNumbers"))
     		m_Labels[i] = new JLabel("입력된 인스턴스개수 사용", SwingConstants.RIGHT);
       	else if(name.equals("useEqualFrequency"))
     		m_Labels[i] = new JLabel("동일 빈도 사용", SwingConstants.RIGHT);
       	else if(name.equals("extremeValuesAsOutliers"))
     		m_Labels[i] = new JLabel("극단치값과 이상치값", SwingConstants.RIGHT);
       	else if(name.equals("extremeValuesFactor"))
     		m_Labels[i] = new JLabel("극단치 임계값", SwingConstants.RIGHT);
       	else if(name.equals("outlierFactor"))
     		m_Labels[i] = new JLabel("이상치 임계값", SwingConstants.RIGHT);
       	else if(name.equals("outputOffsetMultiplier"))
     		m_Labels[i] = new JLabel("Offset속성 추가", SwingConstants.RIGHT);
       	else if(name.equals("percentage"))
     		m_Labels[i] = new JLabel("퍼센테이지(%)", SwingConstants.RIGHT);
       	else if(name.equals("noReplacement"))
     		m_Labels[i] = new JLabel("인스턴스 대체", SwingConstants.RIGHT);
       	else if(name.equals("randomSeed"))
     		m_Labels[i] = new JLabel("랜덤시드", SwingConstants.RIGHT);
       	else if(name.equals("sampleSizePercent"))
     		m_Labels[i] = new JLabel("샘플크기(%)", SwingConstants.RIGHT);
       	else if(name.equals("firstValueIndex"))
     		m_Labels[i] = new JLabel("첫번째 인덱스값", SwingConstants.RIGHT);
       	else if(name.equals("secondValueIndex"))
     		m_Labels[i] = new JLabel("두번째 인덱스값", SwingConstants.RIGHT);
       	else if(name.equals("binaryAttributesNominal"))
     		m_Labels[i] = new JLabel("바이너리 to 명목", SwingConstants.RIGHT);
       	else if(name.equals("transformAllValues"))
     		m_Labels[i] = new JLabel("모든값 전환", SwingConstants.RIGHT);
       	else if(name.equals("attributeIndexes"))
     		m_Labels[i] = new JLabel("속성인덱스", SwingConstants.RIGHT);
       	else if(name.equals("closeTo"))
     		m_Labels[i] = new JLabel("근사수치설정", SwingConstants.RIGHT);
       	else if(name.equals("closeToDefault"))
     		m_Labels[i] = new JLabel("근사수치 초기값설정", SwingConstants.RIGHT);
       	else if(name.equals("closeToTolerance"))
     		m_Labels[i] = new JLabel("근사수치 허용값설정", SwingConstants.RIGHT);
       	else if(name.equals("decimals"))
     		m_Labels[i] = new JLabel("소수점 설정", SwingConstants.RIGHT);
       	else if(name.equals("includeClass"))
     		m_Labels[i] = new JLabel("클래스포함", SwingConstants.RIGHT);
       	else if(name.equals("maxDefault"))
     		m_Labels[i] = new JLabel("최대초기값", SwingConstants.RIGHT);
       	else if(name.equals("maxThreshold"))
     		m_Labels[i] = new JLabel("최대한계치값", SwingConstants.RIGHT);
       	else if(name.equals("minDefault"))
     		m_Labels[i] = new JLabel("최소초기값", SwingConstants.RIGHT);
       	else if(name.equals("minThreshold"))
     		m_Labels[i] = new JLabel("최소한계치값", SwingConstants.RIGHT);
       	else if(name.equals("attributeRange"))
     		m_Labels[i] = new JLabel("속성범위", SwingConstants.RIGHT);
      
       	else if(name.equals("crossValidate"))
     		m_Labels[i] = new JLabel("교차검증", SwingConstants.RIGHT);
       	else if(name.equals("distanceWeighting"))
     		m_Labels[i] = new JLabel("거리 가중치", SwingConstants.RIGHT);
     	else if(name.equals("meanSquared"))
     		m_Labels[i] = new JLabel("스퀘어드 평균", SwingConstants.RIGHT);
    	else if(name.equals("nearestNeighbourSearchAlgorithm"))
     		m_Labels[i] = new JLabel("최근접 탐색 알고리즘", SwingConstants.RIGHT);
    	else if(name.equals("windowSize"))
     		m_Labels[i] = new JLabel("창크기", SwingConstants.RIGHT);
      
    	else if(name.equals("autoBuild"))
     		m_Labels[i] = new JLabel("자동 생성", SwingConstants.RIGHT);
    	else if(name.equals("decay"))
     		m_Labels[i] = new JLabel("부식(decay)", SwingConstants.RIGHT);
    	else if(name.equals("hiddenLayers"))
     		m_Labels[i] = new JLabel("음폐층(hiddenLayers)", SwingConstants.RIGHT);
    	else if(name.equals("learningRate"))
     		m_Labels[i] = new JLabel("학습율", SwingConstants.RIGHT);
    	else if(name.equals("momentum"))
     		m_Labels[i] = new JLabel("탄력성", SwingConstants.RIGHT);
    	else if(name.equals("nominalToBinaryFilter"))
     		m_Labels[i] = new JLabel("명목형을 바이너리로 변경", SwingConstants.RIGHT);
    	else if(name.equals("normalizeAttributes"))
     		m_Labels[i] = new JLabel("정규화속성", SwingConstants.RIGHT);
    	else if(name.equals("normalizeNumericClass"))
     		m_Labels[i] = new JLabel("정규화 수치클래스", SwingConstants.RIGHT);
    	else if(name.equals("reset"))
     		m_Labels[i] = new JLabel("리셋", SwingConstants.RIGHT);
    	else if(name.equals("seed"))
     		m_Labels[i] = new JLabel("시드", SwingConstants.RIGHT);
    	else if(name.equals("trainingTime"))
     		m_Labels[i] = new JLabel("훈련시간", SwingConstants.RIGHT);
    	else if(name.equals("clusterer"))
     		m_Labels[i] = new JLabel("군집알고리즘", SwingConstants.RIGHT);
    	else if(name.equals("minStdDev"))
     		m_Labels[i] = new JLabel("최소표준편차", SwingConstants.RIGHT);
    	else if(name.equals("displayModelInOldFormat"))
     		m_Labels[i] = new JLabel("이전포맷에서 모델표시", SwingConstants.RIGHT);
    	else if(name.equals("maxIterations"))
     		m_Labels[i] = new JLabel("최대반복", SwingConstants.RIGHT);
    	else if(name.equals("maximumNumberOfClusters"))
     		m_Labels[i] = new JLabel("최대 군집개수", SwingConstants.RIGHT);
    	else if(name.equals("minLogLikelihoodImprovementCV"))
     		m_Labels[i] = new JLabel("최소로그 우도향상CV", SwingConstants.RIGHT);
    	else if(name.equals("minLogLikelihoodImprovementIterating"))
     		m_Labels[i] = new JLabel("최소로그 우도향상반복", SwingConstants.RIGHT);
    	else if(name.equals("numClusters"))
     		m_Labels[i] = new JLabel("군집 개수", SwingConstants.RIGHT);
    	else if(name.equals("numExecutionSlots"))
     		m_Labels[i] = new JLabel("실행슬롯 개수", SwingConstants.RIGHT);
    	else if(name.equals("numFolds"))
     		m_Labels[i] = new JLabel("폴드 개수", SwingConstants.RIGHT);
    	else if(name.equals("numKMeansRuns"))
     		m_Labels[i] = new JLabel("KMeans 실행 개수", SwingConstants.RIGHT);
    	else if(name.equals("delta"))
     		m_Labels[i] = new JLabel("델타값", SwingConstants.RIGHT);
    	else if(name.equals("findAllRulesForSupportLevel"))
     		m_Labels[i] = new JLabel("지지도 레벨을 위한 모든 규착 찾기", SwingConstants.RIGHT);
    	else if(name.equals("lowerBoundMinSupport"))
     		m_Labels[i] = new JLabel("최소지지도 하한값", SwingConstants.RIGHT);
    	else if(name.equals("maxNumberOfItems"))
     		m_Labels[i] = new JLabel("Items의 최대개수", SwingConstants.RIGHT);
    	else if(name.equals("metricType"))
     		m_Labels[i] = new JLabel("메트릭타입(metricType)", SwingConstants.RIGHT);
    	else if(name.equals("minMetric"))
     		m_Labels[i] = new JLabel("최소매트릭(Metric)", SwingConstants.RIGHT);
    	else if(name.equals("numRulesToFind"))
     		m_Labels[i] = new JLabel("찾을 규칙의 개수", SwingConstants.RIGHT);
    	else if(name.equals("positiveIndex"))
     		m_Labels[i] = new JLabel("낙관인덱스(positiveIndex)", SwingConstants.RIGHT);
    	else if(name.equals("rulesMustContain"))
     		m_Labels[i] = new JLabel("반드시 포함할 규칙", SwingConstants.RIGHT);
    	else if(name.equals("transactionsMustContain"))
     		m_Labels[i] = new JLabel("반드시 포함할 트랜젝션", SwingConstants.RIGHT);
    	else if(name.equals("upperBoundMinSupport"))
     		m_Labels[i] = new JLabel("최소지지도 상한값", SwingConstants.RIGHT);
    	else if(name.equals("useORForMustContainList"))
     		m_Labels[i] = new JLabel("사용하거나 반드시 포함할 리스트", SwingConstants.RIGHT);
    	else if(name.equals("validationSetSize"))
     		m_Labels[i] = new JLabel("사이즈설정 확인", SwingConstants.RIGHT);
    	else if(name.equals("validationThreshold"))
     		m_Labels[i] = new JLabel("역치값(Threshold)확인", SwingConstants.RIGHT);
    	else if(name.equals("classIndex"))
     		m_Labels[i] = new JLabel("클래스 인덱스", SwingConstants.RIGHT);
    	else if(name.equals("numRules"))
     		m_Labels[i] = new JLabel("규칙 개수 ", SwingConstants.RIGHT);
    	else if(name.equals("outputItemSets"))
     		m_Labels[i] = new JLabel("항목집합출력", SwingConstants.RIGHT);
    	else if(name.equals("removeAllMissingCols"))
     		m_Labels[i] = new JLabel("모든결측치열 제거", SwingConstants.RIGHT);
    	else if(name.equals("significanceLevel"))
     		m_Labels[i] = new JLabel("중요레벨", SwingConstants.RIGHT);
    	else if(name.equals("treatZeroAsMissing"))
     		m_Labels[i] = new JLabel("결측치를 0으로 변경", SwingConstants.RIGHT);
    	else if(name.equals("verbose"))
     		m_Labels[i] = new JLabel("버보스(verbose)모드", SwingConstants.RIGHT);
    	else if(name.equals("distanceFunction"))
     		m_Labels[i] = new JLabel("거리행렬", SwingConstants.RIGHT);
    	else if(name.equals("distanceIsBranchLength"))
     		m_Labels[i] = new JLabel("거리=나뭇잎길이", SwingConstants.RIGHT);
    	else if(name.equals("linkType"))
     		m_Labels[i] = new JLabel("링크타입", SwingConstants.RIGHT);
    	else if(name.equals("printNewick"))
     		m_Labels[i] = new JLabel("Newick출력", SwingConstants.RIGHT); 
    	else if(name.equals("buildLogisticModels"))
     		m_Labels[i] = new JLabel("로지스텍 모델 설정", SwingConstants.RIGHT); 
    	else if(name.equals("checksTurnedOff"))
     		m_Labels[i] = new JLabel("체크기능끄기", SwingConstants.RIGHT); 
    	else if(name.equals("epsilon"))
     		m_Labels[i] = new JLabel("앱실론(epsilon)", SwingConstants.RIGHT); 
    	else if(name.equals("filterType"))
     		m_Labels[i] = new JLabel("필터 타입", SwingConstants.RIGHT); 
    	else if(name.equals("kernel"))
     		m_Labels[i] = new JLabel("커널", SwingConstants.RIGHT); 
      	else if(name.equals("toleranceParameter"))
     		m_Labels[i] = new JLabel("오차파라메터", SwingConstants.RIGHT); 
      
      
      /*
>>>>>>> .r212
     	    else if(name.equals("detectionPerAttribute"))
     		m_Labels[i] = new JLabel("적용할 속성 범위 설정", SwingConstants.RIGHT);

			 */
			else
				m_Labels[i] = new JLabel(name, SwingConstants.RIGHT);
			m_Labels[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 5));
			m_Views[i] = view;
			GridBagConstraints gbConstraints = new GridBagConstraints();
			gbConstraints.anchor = GridBagConstraints.EAST;
			gbConstraints.fill = GridBagConstraints.HORIZONTAL;
			gbConstraints.gridy = i + componentOffset;
			gbConstraints.gridx = 0;
			gbLayout.setConstraints(m_Labels[i], gbConstraints);
			scrollablePanel.add(m_Labels[i]);
			JPanel newPanel = new JPanel();
			if (m_TipTexts[i] != null) {
				m_Views[i].setToolTipText(m_TipTexts[i]);
				m_Labels[i].setToolTipText(m_TipTexts[i]);
			}
			newPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 10));
			newPanel.setLayout(new BorderLayout());
			newPanel.add(m_Views[i], BorderLayout.CENTER);
			gbConstraints = new GridBagConstraints();
			gbConstraints.anchor = GridBagConstraints.WEST;
			gbConstraints.fill = GridBagConstraints.BOTH;
			gbConstraints.gridy = i + componentOffset;
			gbConstraints.gridx = 1;
			gbConstraints.weightx = 100;
			gbLayout.setConstraints(newPanel, gbConstraints);

			newPanel.setBackground(Color.WHITE);
			scrollablePanel.add(newPanel);
			m_NumEditable++;
		}

		if (m_NumEditable == 0) {
			JLabel empty = new JLabel("No editable properties", SwingConstants.CENTER);
			Dimension d = empty.getPreferredSize();
			empty.setPreferredSize(new Dimension(d.width * 2, d.height * 2));
			empty.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 10));
			GridBagConstraints gbConstraints = new GridBagConstraints();
			gbConstraints.anchor = GridBagConstraints.CENTER;
			gbConstraints.fill = GridBagConstraints.HORIZONTAL;
			gbConstraints.gridy = componentOffset;
			gbConstraints.gridx = 0;
			gbLayout.setConstraints(empty, gbConstraints);
			scrollablePanel.add(empty);
		}

		validate();

		// sometimes, the calculated dimensions seem to be too small and the
		// scrollbars show up, though there is still plenty of space on the
		// screen. hence we increase the dimensions a bit to fix this.
		Dimension dim = scrollablePanel.getPreferredSize();
		dim.height += 20;
		dim.width += 20;
		scrollPane.setPreferredSize(dim);
		validate();

		setVisible(true);
	}

	/**
	 * opens the help dialog.
	 */
	protected void openHelpFrame() {

		JTextArea ta = new JTextArea();
		ta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		// ta.setBackground(getBackground());
		ta.setEditable(false);
		ta.setText(m_HelpText.toString());
		ta.setCaretPosition(0);
		JDialog jdtmp;
		if (PropertyDialog.getParentDialog(this) != null) {
			jdtmp = new JDialog(PropertyDialog.getParentDialog(this), "Information");
		} else if (PropertyDialog.getParentFrame(this) != null) {
			jdtmp = new JDialog(PropertyDialog.getParentFrame(this), "Information");
		} else {
			jdtmp = new JDialog(PropertyDialog.getParentDialog(m_aboutPanel),
					"Information");
		}
		final JDialog jd = jdtmp;
		jd.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				jd.dispose();
				if (m_HelpDialog == jd) {
					m_HelpBut.setEnabled(true);
				}
			}
		});
		jd.getContentPane().setLayout(new BorderLayout());
		jd.getContentPane().add(new JScrollPane(ta), BorderLayout.CENTER);
		jd.pack();
		jd.setSize(400, 350);
		jd.setLocation(m_aboutPanel.getTopLevelAncestor().getLocationOnScreen().x
				+ m_aboutPanel.getTopLevelAncestor().getSize().width, m_aboutPanel
				.getTopLevelAncestor().getLocationOnScreen().y);
		jd.setVisible(true);
		m_HelpDialog = jd;
	}

	/**
	 * opens the help dialog for the capabilities.
	 */
	protected void openCapabilitiesHelpDialog() {
		if (PropertyDialog.getParentDialog(this) != null) {
			m_CapabilitiesDialog = new CapabilitiesHelpDialog(
					PropertyDialog.getParentDialog(this));
		} else {
			m_CapabilitiesDialog = new CapabilitiesHelpDialog(
					PropertyDialog.getParentFrame(this));
		}
		m_CapabilitiesDialog.setSize(400, 350);
		m_CapabilitiesDialog.setLocation(m_aboutPanel.getTopLevelAncestor()
				.getLocationOnScreen().x
				+ m_aboutPanel.getTopLevelAncestor().getSize().width, m_aboutPanel
				.getTopLevelAncestor().getLocationOnScreen().y);
		m_CapabilitiesDialog.setVisible(true);
		addPropertyChangeListener(m_CapabilitiesDialog);
	}

	/**
	 * Gets the number of editable properties for the current target.
	 * 
	 * @return the number of editable properties.
	 */
	public int editableProperties() {

		return m_NumEditable;
	}

	/**
	 * Updates the propertysheet when a value has been changed (from outside the
	 * propertysheet?).
	 * 
	 * @param evt a value of type 'PropertyChangeEvent'
	 */
	synchronized void wasModified(PropertyChangeEvent evt) {

		// System.err.println("wasModified");
		if (evt.getSource() instanceof PropertyEditor) {
			PropertyEditor editor = (PropertyEditor) evt.getSource();
			for (int i = 0; i < m_Editors.length; i++) {
				if (m_Editors[i] == editor) {
					PropertyDescriptor property = m_Properties[i];
					Object value = editor.getValue();
					m_Values[i] = value;
					Method setter = property.getWriteMethod();
					try {
						Object args[] = { value };
						args[0] = value;
						setter.invoke(m_Target, args);
					} catch (InvocationTargetException ex) {
						if (ex.getTargetException() instanceof PropertyVetoException) {
							String message = "WARNING: Vetoed; reason is: "
									+ ex.getTargetException().getMessage();
							System.err.println(message);

							Component jf;
							if (evt.getSource() instanceof JPanel) {
								jf = ((JPanel) evt.getSource()).getParent();
							} else {
								jf = new JFrame();
							}
							JOptionPane.showMessageDialog(jf, message, "error",
									JOptionPane.WARNING_MESSAGE);
							if (jf instanceof JFrame) {
								((JFrame) jf).dispose();
							}

						} else {
							System.err.println(ex.getTargetException().getClass().getName()
									+ " while updating " + property.getName() + ": "
									+ ex.getTargetException().getMessage());
							Component jf;
							if (evt.getSource() instanceof JPanel) {
								jf = ((JPanel) evt.getSource()).getParent();
							} else {
								jf = new JFrame();
							}
							JOptionPane.showMessageDialog(jf, ex.getTargetException()
									.getClass().getName()
									+ " while updating "
									+ property.getName()
									+ ":\n"
									+ ex.getTargetException().getMessage(), "error",
									JOptionPane.WARNING_MESSAGE);
							if (jf instanceof JFrame) {
								((JFrame) jf).dispose();
							}

						}
					} catch (Exception ex) {
						System.err.println("Unexpected exception while updating "
								+ property.getName());
					}
					if (m_Views[i] != null && m_Views[i] instanceof PropertyPanel) {
						// System.err.println("Trying to repaint the property canvas");
						m_Views[i].repaint();
						revalidate();
					}
					break;
				}
			}
		}

		// Now re-read all the properties and update the editors
		// for any other properties that have changed.
		for (int i = 0; i < m_Properties.length; i++) {
			Object o;
			try {
				Method getter = m_Properties[i].getReadMethod();
				Method setter = m_Properties[i].getWriteMethod();

				if (getter == null || setter == null) {
					// ignore set/get only properties
					continue;
				}

				Object args[] = {};
				o = getter.invoke(m_Target, args);
			} catch (Exception ex) {
				o = null;
			}
			if (o == m_Values[i] || (o != null && o.equals(m_Values[i]))) {
				// The property is equal to its old value.
				continue;
			}
			m_Values[i] = o;
			// Make sure we have an editor for this property...
			if (m_Editors[i] == null) {
				continue;
			}
			// The property has changed! Update the editor.
			m_Editors[i].removePropertyChangeListener(this);
			m_Editors[i].setValue(o);
			m_Editors[i].addPropertyChangeListener(this);
			if (m_Views[i] != null) {
				// System.err.println("Trying to repaint " + (i + 1));
				m_Views[i].repaint();
			}
		}

		// Make sure the target bean gets repainted.
		if (Beans.isInstanceOf(m_Target, Component.class)) {
			((Component) (Beans.getInstanceOf(m_Target, Component.class))).repaint();
		}
	}

	/**
	 * Set environment variables to pass on to any editor that can use them
	 * 
	 * @param env the variables to pass on to individual property editors
	 */
	@Override
	public void setEnvironment(Environment env) {
		m_env = env;
	}

	/**
	 * Pass on an OK closing notification to the customizer (if one is in use)
	 */
	public void closingOK() {
		if (m_Customizer != null) {
			// pass on the notification to the customizer so that
			// it can copy values out of its GUI widgets into the object
			// being customized, if necessary
			m_Customizer.closingOK();
		}
	}

	/**
	 * Pass on a CANCEL closing notificiation to the customizer (if one is in
	 * use).
	 */
	public void closingCancel() {
		// pass on the notification to the customizer so that
		// it can revert to previous settings for the object being
		// edited, if neccessary
		if (m_Customizer != null) {
			m_Customizer.closingCancel();
		}
	}
}
