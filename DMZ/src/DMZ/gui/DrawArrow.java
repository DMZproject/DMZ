package DMZ.gui;

import static java.awt.geom.AffineTransform.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import javax.swing.*;

public class DrawArrow {

	private final int ARR_SIZE = 10;

	public void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
		Graphics2D g = (Graphics2D) g1.create();
		
		GeneralPath path = new GeneralPath();
		double dx = x2 - x1, dy = y2 - y1;
		double angle = Math.atan2(dy, dx);
		int len = (int) Math.sqrt(dx * dx + dy * dy);
		AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
		at.concatenate(AffineTransform.getRotateInstance(angle));
		g.transform(at);

		g.drawLine(0, 0, len-2, 0);
		g.fillPolygon(new int[] { len, len - ARR_SIZE, len - ARR_SIZE, len }, new int[] { 0, -ARR_SIZE, ARR_SIZE, 0 }, 4);
		
		
		  
		  
		// path.moveTo(x1, y1);
		// path.curveTo((x1+x2)*0.3, (y1+y2)*0.3,(x1+x2)*0.7, (y1+y2)*0.7,x2, y2);
		// g.draw(path);
		
		
		
	}

}