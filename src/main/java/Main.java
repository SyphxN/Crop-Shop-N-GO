
package main.java;

import ComputerCamera.ComputerCamera;
import main.java.login_register.Event;

/**
 *
 * @author larry
 */
public class Main extends javax.swing.JFrame {

    private ComputerCamera camera;
    
    public Main() {
        
        initComponents();
        camera = new ComputerCamera();
        Event event=new Event(){
            @Override
            public void loggedIn() {
                mainPanel1.setVisible(false);
                mainPanel2.setVisible(false);
                camera.setVisible(true);
            }

            @Override
            public void loggedOut() {
                mainPanel1.setVisible(true);
                mainPanel2.setVisible(true);
                camera.setVisible(false);
            }
        };
        mainPanel1.setEventLogin(event);
        camera.setEventLogin(event);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel2 = new main.java.login_register.MainPanel();
        mainPanel1 = new main.java.login_register.MainPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel1.setMinimumSize(new java.awt.Dimension(32767, 32767));
        mainPanel1.setPreferredSize(new java.awt.Dimension(32767, 32767));
        getContentPane().add(mainPanel1, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   
    public static void main(String[] args) {
        SerpAPI.search("https://fanatics.frgimages.com/new-york-yankees/mens-new-era-black-new-york-yankees-team-logo-59fifty-fitted-hat_pi4733000_ff_4733474-dce6347d1e60d489ea20_full.jpg?_hv=2&w=600");
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.java.login_register.MainPanel mainPanel1;
    private main.java.login_register.MainPanel mainPanel2;
    // End of variables declaration//GEN-END:variables
}
