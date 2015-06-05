package DMZ.xml;

import java.util.ArrayList;
import java.util.List;

public class XmlLabelList {


	List<XmlLabel> x_List;

	public XmlLabelList() {
		x_List = new ArrayList<XmlLabel>();
	}

	public void add(XmlLabel XmlLabel) {
		x_List.add(XmlLabel);
	}
	public List getXmlLabelList(){
		return this.x_List;
	}
}
