package com.mycompany.mazewelcome;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ae
 */
public class Runner extends javax.swing.JFrame 
{

    public Runner() 
    {
        this.setVisible(true);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() 
    {

        time = new javax.swing.JTextField();
        get = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        time.setText("jTextField1");

        get.setText("jButton1");
        get.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                getActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(time)
                    .addComponent(get, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap(129, Short.MAX_VALUE)));

                
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(get)
                .addGap(50, 50, 50)));


        pack();
    }

    private void getActionPerformed(java.awt.event.ActionEvent evt) 
    {
       double time2 ;
        try 
        {

            time2 = Double.parseDouble(time.getText());
           try 
           {
               var users = new Users(SelectProfile.UsernameInall, time2);
           } 
           
           catch (IOException ex) 
           {
               Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
           }
        }

        catch (NumberFormatException e) 
        {
            JOptionPane.showConfirmDialog(rootPane, "please enter number!");
        }

    }

    /**
     * @param args
     */
    public static void main(String args[]) 
    {
        try 
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
            {
                if ("Nimbus".equals(info.getName())) 
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        
        catch (ClassNotFoundException ex) 
        {
            java.util.logging.Logger.getLogger(Runner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        
        catch (InstantiationException ex) 
        {
            java.util.logging.Logger.getLogger(Runner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        
        catch (IllegalAccessException ex) 
        {
            java.util.logging.Logger.getLogger(Runner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        
        catch (javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(Runner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new Runner().setVisible(true);
            }
        });
    }

    private javax.swing.JButton get;
    private javax.swing.JTextField time;
}
