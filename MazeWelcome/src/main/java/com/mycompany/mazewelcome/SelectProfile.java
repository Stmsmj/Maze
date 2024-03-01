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
    static String UsernameInall = new String();   
    static boolean Select = false;

    ArrayList<String>Allprofile = new ArrayList<>() ;
    String[]Allprofile2=new String[Allprofile.size()];
     

    public SelectProfile() 
    {
        File profile=new File("Username.txt");

        try {

        Scanner Reader= new Scanner(profile);

             while(Reader.hasNextLine()){

                String data = Reader.nextLine();  
                Allprofile.add(data);
            }

        } 

        catch (FileNotFoundException e) 
        {
            JOptionPane.showMessageDialog(rootPane, "please creat profile","Error",JOptionPane.WARNING_MESSAGE);
        }

        Allprofile2=Allprofile.toArray(Allprofile2);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() 
    {

        SelectProfile = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(370, 460));
        setMinimumSize(new java.awt.Dimension(370, 460));
        setPreferredSize(new java.awt.Dimension(370, 460));

        SelectProfile.setBackground(new java.awt.Color(153, 153, 153));
        SelectProfile.setText("select profile");
        SelectProfile.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                SelectProfileActionPerformed(evt);
            }
        });
        getContentPane().add(SelectProfile);
        SelectProfile.setBounds(120, 342, 130, 30);

        jComboBox1.setBackground(new java.awt.Color(153, 153, 153));
        jComboBox1.setModel(new DefaultComboBoxModel<String>
            (Allprofile2));

        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(135, 70, 110, 26);

        jLabel1.setIcon(new javax.swing.ImageIcon("src/main/java/image/mazebg.jpg"));

        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 360, 425);

        pack();
    }

    private void SelectProfileActionPerformed(java.awt.event.ActionEvent evt) 
    {
        UsernameInall=jComboBox1.getSelectedItem().toString();
        Select=true;
        JOptionPane.showMessageDialog(rootPane, " you select"+" "+UsernameInall+ "!", "Succsfuly selected", JOptionPane.INFORMATION_MESSAGE);
                
        this.setVisible(false);

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

        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new SelectProfile().setVisible(true);
            }
        });
    }

    private javax.swing.JButton SelectProfile;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
}

