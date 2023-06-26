/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ComputerCamera;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import main.java.SerpAPI;
import main.java.login_register.Event;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import main.java.DisplayResults;


/**
 *
 * @author sidha
 */
public class ComputerCamera extends javax.swing.JFrame {

    /**
     * Creates new form ComputerCamera
     */
    private Event event;
    
    public ComputerCamera() {
        initComponents();
    }
    
    public void setEventLogin(Event event){
        this.event=event;
    }
    
    private DaemonThread myThread = null;
    private VideoCapture webSource = null;
    private final Mat frame = new Mat (1000, 1000, 1);
    private final MatOfByte mem = new MatOfByte();
    
    private class DaemonThread implements Runnable {
        protected volatile boolean runnable = false;
        private JLabel display;
        public DaemonThread (JLabel displayLabel) {
            this.display = displayLabel;
        }
        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);
                            Highgui.imencode(".bmp", frame, mem);
                            Image im = ImageIO. read(new ByteArrayInputStream (mem.toArray()));
                            
                            BufferedImage buff = (BufferedImage) im;
                            Graphics g = display.getGraphics();
                            
                            if (g.drawImage(buff, 1, 1, display.getWidth(), display.getHeight(), null)){
                                if (runnable == false) {
                                    this.wait();
                                }
                            }
                        } catch (Exception e){
                        }
                    }
                }
            }
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        display = new javax.swing.JLabel();
        StartButton = new main.java.components.Button();
        CaptureButton = new main.java.components.Button();
        StopButton = new main.java.components.Button();
        button1 = new main.java.components.Button();
        button2 = new main.java.components.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(79, 67, 67));
        jPanel1.setMinimumSize(new java.awt.Dimension(32767, 32767));
        jPanel1.setLayout(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(display, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(display, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(97, 41, 866, 539);

        StartButton.setBackground(new java.awt.Color(102, 255, 102));
        StartButton.setPreferredSize(new java.awt.Dimension(86, 86));
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });
        jPanel1.add(StartButton);
        StartButton.setBounds(250, 600, 190, 50);

        CaptureButton.setBackground(new java.awt.Color(102, 102, 102));
        CaptureButton.setForeground(new java.awt.Color(153, 153, 153));
        CaptureButton.setText("O");
        CaptureButton.setFont(new java.awt.Font("Tw Cen MT", 1, 60)); // NOI18N
        CaptureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaptureButtonActionPerformed(evt);
            }
        });
        jPanel1.add(CaptureButton);
        CaptureButton.setBounds(480, 590, 70, 70);

        StopButton.setBackground(new java.awt.Color(255, 51, 51));
        StopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopButtonActionPerformed(evt);
            }
        });
        jPanel1.add(StopButton);
        StopButton.setBounds(590, 600, 190, 50);

        button1.setBackground(new java.awt.Color(51, 102, 255));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Logout");
        button1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel1.add(button1);
        button1.setBounds(1090, 40, 110, 40);

        button2.setText("Results");
        button2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel1.add(button2);
        button2.setBounds(1110, 280, 270, 130);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        event.loggedOut();
    }//GEN-LAST:event_button1ActionPerformed

    private void CaptureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaptureButtonActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to capture an image?");
        if (option == 0) {
            CaptureButton.setEnabled(false);
            try {
                File file = new File("Pictures");
                boolean flag = true;
                if (!file.isDirectory ()) {
                    flag = file.mkdir();
                }
                if (!flag) {
                    throw new Exception ("Folder not exist");
                }
                int image_number = 1 + rand.nextInt (999999999);
                imageFileName = file.getAbsolutePath()+"\\"+image_number + ".jpg";
                Highgui.imwrite (imageFileName, frame);
                getImageFile=file;
                setWebcamCapturedImageOnLabel(display);
                JOptionPane.showMessageDialog(null, "Image Saved Successfully!\nDirectory: " + imageFileName);
            } catch (Exception e) {
                stopWebCam();
                JOptionPane.showMessageDialog(null, e, "Warning", 0);
            }
        }
    }//GEN-LAST:event_CaptureButtonActionPerformed

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        // TODO add your handling code here:
        webSource= new VideoCapture (0);
        myThread = new DaemonThread (display);
        Thread t = new Thread (myThread);
        t.setDaemon (true);
        myThread.runnable = true;
        t.start();
        StartButton.setEnabled(false);
        StopButton.setEnabled(true);
        CaptureButton.setEnabled(true);
    }//GEN-LAST:event_StartButtonActionPerformed

    private void StopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopButtonActionPerformed
        // TODO add your handling code here:
        stopWebCam();
        StartButton.setEnabled(true);
        StopButton.setEnabled(false);
        CaptureButton.setEnabled(false);
    }//GEN-LAST:event_StopButtonActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        ArrayList<ArrayList<String>> items = SerpAPI.getItems();

                // Create and display the JSONTableApp frame
                DisplayResults app = new DisplayResults(items);
                app.setVisible(true);
        
    }//GEN-LAST:event_button2ActionPerformed

    private void stopWebCam(){
        if (myThread != null){
            if (myThread.runnable == true){
                myThread.runnable = false;
                webSource.release();
            }
        }
    }
    
    private static File getImageFile;
    private static final SecureRandom rand = new SecureRandom();
    private static String imageFileName = null;
    
    
    
    
    
    private void setWebcamCapturedImageOnLabel (JLabel image) {
        try {
            stopWebCam();
            if (getImageFile != null) {
              ImageIcon imageIcon = new ImageIcon (new ImageIcon (imageFileName).getImage().getScaledInstance(image.getWidth(),image.getHeight(),Image.SCALE_DEFAULT));
              image.setIcon(imageIcon);
            }
        } catch (Exception e){
          JOptionPane.showMessageDialog(null, e, "Warning", 0);
        }
    }

    
    

    static{
        File file = new File("lib/opencv_java249.dll");
        System.load(file.getAbsolutePath());
    
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ComputerCamera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComputerCamera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComputerCamera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComputerCamera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComputerCamera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.java.components.Button CaptureButton;
    private main.java.components.Button StartButton;
    private main.java.components.Button StopButton;
    private main.java.components.Button button1;
    private main.java.components.Button button2;
    private javax.swing.JLabel display;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
