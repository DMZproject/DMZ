package DMZ.gui;

public abstract class OnlyInputLabel extends ParentLabel {

	final int labelType = 3;

	OnlyInputLabel() {

	}

	OnlyInputLabel(String name) {
		super(name);
	}
	public int getLabelType() {
		return labelType;
	}

	public abstract void run();
}
