package main.java.login_register;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

/**
 *
 * @author larry
 */

public class MainPanel extends javax.swing.JPanel {

    private Login login;
    private Register register;
    private MigLayout layout;
    private Animator animation;
    private boolean isLogin;
    
    public static Color mainColor = new Color(0x444444);
    
    public void setAnimate(int animate){
        layout.setComponentConstraints(register,"pos (50%)-290px-"+ animate+"0.5al n n");
        layout.setComponentConstraints(login,"pos (50%)-10px+"+ animate+"0.5al n n");
        revalidate();

    }
    public MainPanel() {
        initComponents();
        init();
        initAnimate();
    }
   
    private void initAnimate(){
        animation = new Animator(1000, new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction){
                
            }
            
        });
        animation.addTarget(new PropertySetter(this, "animate",0,30,0));
        animation.setResolution(0);
    }
    private void init(){
        setBackground(mainColor);
        layout = new MigLayout("fill","fill","fill");
        setLayout(layout);
        register = new Register();
        login = new Login();
        add(register,"pos (50%)-290px 0.5al n n");
        add(login, "pos (50%)-10px 0.5al n n");
        register.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                if(SwingUtilities.isLeftMouseButton(me)){
                    showLogin(false);
                    
                }
            }
        });
        login.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                if(SwingUtilities.isLeftMouseButton(me)){
                    showLogin(true);
                    
                }
            }
        });
        
    }
    
    public void showLogin(boolean show){
        if(show!=isLogin){
            if(!animation.isRunning()){
                isLogin=show;
                animation.start();
            }
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
