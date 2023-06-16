package main.java;

import javax.swing.JTextField;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import main.java.MainPanel;

/**
 *
 * @author larry
 */
public class PasswordField extends JPasswordField {
    
    public String getHint(){
        return hint;
    }
    
    public void setHint(String hint){
        this.hint = hint;
    }
    
    public boolean isShowAndHide() {
        return showAndHide;
    }

    public void setShowAndHide(boolean showAndHide) {
        this.showAndHide = showAndHide;
        repaint();
    }
    
    private String hint;
    private final Image eye;
    private final Image eye_hide;
    private boolean hide = true;
    private boolean showAndHide;

    public PasswordField() {
        setBorder(new EmptyBorder(10, 30, 10, 30));
        setBackground(new Color(0, 0, 0, 0));
        setHorizontalAlignment(JTextField.CENTER);
        setSelectionColor(MainPanel.mainColor);
        addMouseListener(new MouseAdapter() {
            

           @Override
            public void mousePressed(MouseEvent me) {
                if (showAndHide) {
                    int x = getWidth() - 100;
                    if (new Rectangle(x, 0, 30, 30).contains(me.getPoint())) {
                        hide = !hide;
                        if (hide) {
                            setEchoChar('\u2022');
                        } else {
                            setEchoChar((char) 0);
                        }
                        repaint();
                    }
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                if (showAndHide) {
                    int x = getWidth() - 30;
                    if (new Rectangle(x, 0, 30, 30).contains(me.getPoint())) {
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                    } else {
                        setCursor(new Cursor(Cursor.TEXT_CURSOR));
                    }
                }
            }
        });
        eye = new ImageIcon(getClass().getResource("/main/java/components/eye.png")).getImage();
        eye_hide = new ImageIcon(getClass().getResource("/main/java/components/eye_hide.png")).getImage();
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        int w = getWidth() - 1;
        int h = getHeight() - 1;
        g2.draw(new RoundRectangle2D.Double(0, 0, w, h, h, h));
        if (!hint.equals("") && getPassword().length == 0) {
            createHintText(g2);
        }
        if (!showAndHide) {
            createShowHide(g2);
        }
        g2.dispose();

        super.paintComponent(g);
    }

    private void createShowHide(Graphics2D g2) {
        int x = getWidth() - 30;
        int y = (getHeight() - 20) / 2;
        g2.drawImage(hide ? eye_hide : eye, x, y, null);
    }
    
    private void createHintText(Graphics2D g) {
        FontMetrics fm = g.getFontMetrics();
        int c0 = getBackground().getRGB();
        int c1 = getForeground().getRGB();
        int m = 0xfefefefe;
        int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
        g.setColor(new Color(c2, true));
        g.drawString(hint, ((getWidth() - fm.stringWidth(hint)) / 2), getHeight() / 2 + fm.getAscent() / 2 - 2);
    }
}

    

