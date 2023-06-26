package main.java.login_register;

import main.java.components.Panel; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
/**
 *
 * @author larry
 */
public class Login extends Panel{
    
    private Event event;
    
    public Login() {
        initComponents();
        setAlpha(1);
    }
    
    public void setEventLogin(Event event){
        this.event=event;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField1 = new main.java.components.TextField();
        jLabel1 = new javax.swing.JLabel();
        passwordField1 = new main.java.components.PasswordField();
        button3 = new main.java.components.Button();

        setBackground(new java.awt.Color(62, 65, 62));

        textField1.setForeground(new java.awt.Color(204, 204, 204));
        textField1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        textField1.setHint("USERNAME");
        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LOGIN");

        passwordField1.setForeground(new java.awt.Color(204, 204, 204));
        passwordField1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        passwordField1.setHint("PASSWORD");
        passwordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordField1ActionPerformed(evt);
            }
        });

        button3.setBackground(new java.awt.Color(0, 153, 255));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("LOGIN");
        button3.setToolTipText("SIGN UP");
        button3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(passwordField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(passwordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    private void passwordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordField1ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        String username = textField1.getText();
        char[] passwordChars = passwordField1.getPassword();
        String password = new String(passwordChars);
        
        if (getAlpha() == 0){
            if(login(username,password)){
                event.loggedIn();
                textField1.setText("");
                passwordField1.setText("");
            } else{
                 JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }//GEN-LAST:event_button3ActionPerformed

    private boolean login(String username, String password) {
    try (BufferedReader reader = new BufferedReader(new FileReader("src\\main\\java\\users.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] userData = line.split(",");
            String existingUsername = userData[1];
            String existingPassword = userData[3];
            if (existingUsername.equals(username) && existingPassword.equals(password)) {
                return true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private main.java.components.Button button3;
    private javax.swing.JLabel jLabel1;
    private main.java.components.PasswordField passwordField1;
    private main.java.components.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
