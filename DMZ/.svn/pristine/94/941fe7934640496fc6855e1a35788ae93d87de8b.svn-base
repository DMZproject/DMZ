package DMZ.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;

class JRoundedCornerBorder extends AbstractBorder 
{   
    private static final long serialVersionUID = 7644739936531926341L;
    private static final int THICKNESS = 2;

    Color color;
    
    JRoundedCornerBorder()
    {
        super();
    }
    
    JRoundedCornerBorder(Color color){
    	super();
    	this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) 
    {
        Graphics2D g2 = (Graphics2D)g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(c.hasFocus())
        {
            g2.setColor(color);
        }
        else
        {
            g2.setColor(color);
        }
        g2.setStroke(new BasicStroke(THICKNESS, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawRoundRect(THICKNESS, THICKNESS, width - THICKNESS - 2, height - THICKNESS - 2, 30, 30);

        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) 
    {
        return new Insets(THICKNESS, THICKNESS, THICKNESS, THICKNESS);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) 
    {
        insets.left = insets.top = insets.right = insets.bottom = THICKNESS;
        return insets;
    }

    public boolean isBorderOpaque() {
        return false;
    }

   
}