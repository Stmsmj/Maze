/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.mazewelcome;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
/**
 *
 * @author ae
 */
public class SelectProfile extends javax.swing.JFrame 
{
    static String UsernameInall=new String();   
    static boolean Select=false;
    /**
     * Creates new form SelectProfile
     */     ArrayList<String>Allprofile = new ArrayList<>() ;
    String[]Allprofile2=new String[Allprofile.size()];
     

    public SelectProfile() 
    {
        File profile=new File("Username.txt");
         try {
        Scanner Reader= new Scanner(profile);
             while(Reader.hasNextLine()){
         String data=Reader.nextLine();  
             Allprofile.add(data);}
        } 

        catch (FileNotFoundException e) 
        {
            JOptionPane.showMessageDialog(rootPane, "please creat profile","eror",JOptionPane.WARNING_MESSAGE);
        }

//for(int i=0;i<Allprofile.size();i++){
//Allprofile2[i]=Allprofile.get(i);
//System.out.println(Allprofile.get(i));
 //Allprofile3.addItem(Allprofile.get(i));
//}
//Allprofile3.setModel(new DefaultComboBoxModel(Allprofile2));
        Allprofile2=Allprofile.toArray(Allprofile2);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() 
    {

        SelectProfile = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(397, 460));
        setMinimumSize(new java.awt.Dimension(397, 460));
        setPreferredSize(new java.awt.Dimension(397, 460));
        setSize(new java.awt.Dimension(397, 460));
        getContentPane().setLayout(null);

        SelectProfile.setBackground(new java.awt.Color(153, 153, 153));
        SelectProfile.setForeground(new java.awt.Color(255, 255, 255));
        SelectProfile.setText("select profile");
        SelectProfile.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                SelectProfileActionPerformed(evt);
            }
        });
        getContentPane().add(SelectProfile);
        SelectProfile.setBounds(140, 342, 130, 30);

        jComboBox1.setBackground(new java.awt.Color(153, 153, 153));
        jComboBox1.setModel(new DefaultComboBoxModel<String>
            (Allprofile2));

        jComboBox1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(147, 110, 110, 26);

        jLabel1.setIcon(new javax.swing.ImageIcon("mazebg.jpg"));

        getContentPane().add(jLabel1);
        jLabel1.setBounds(1, 0, 360, 410);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SelectProfileActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_SelectProfileActionPerformed
        UsernameInall=jComboBox1.getSelectedItem().toString();
        Select=true;
        JOptionPane.showMessageDialog(rootPane, " you select"+" "+UsernameInall+ "!", "Succsfuly selected", JOptionPane.INFORMATION_MESSAGE);
                
        this.setVisible(false);

       // System.out.println(UsernameInall);
    }//GEN-LAST:event_SelectProfileActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
            java.util.logging.Logger.getLogger(SelectProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        
        catch (InstantiationException ex) 
        {
            java.util.logging.Logger.getLogger(SelectProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        
        catch (IllegalAccessException ex) 
        {
            java.util.logging.Logger.getLogger(SelectProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        
        catch (javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(SelectProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new SelectProfile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SelectProfile;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
