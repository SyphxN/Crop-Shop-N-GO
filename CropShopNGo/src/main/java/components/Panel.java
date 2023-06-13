package main.java.components;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;


/**
 *
 * @author larry
 */
public class Panel extends JComponent{
    public Panel(){
        
    }
    
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),10,10));
        g2.dispose();
        super.paintComponent(g);
    }
}
