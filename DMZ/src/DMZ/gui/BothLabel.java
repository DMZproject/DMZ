package DMZ.gui;

import java.util.ArrayList;

public abstract class BothLabel extends ParentLabel {

	final int labelType = 2;

	BothLabel() {

	}

	BothLabel(String name) {
		super(name);
	}

	public int getLabelType() {
		return labelType;
	}

	public abstract void run();
}
