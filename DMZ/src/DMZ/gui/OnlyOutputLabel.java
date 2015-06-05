package DMZ.gui;

import java.util.ArrayList;

public abstract class OnlyOutputLabel extends ParentLabel {

	final int labelType = 1;

	OnlyOutputLabel() {
	}

	OnlyOutputLabel(String name) {
		super(name);
	}

	public int getLabelType() {
		return labelType;
	}

	public abstract void run();
}