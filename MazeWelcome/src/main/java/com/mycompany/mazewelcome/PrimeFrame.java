package com.mycompany.mazewelcome;
import java.awt.*;


import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ae
 */
public class PrimeFrame extends javax.swing.JFrame 
{
    static String languageInAll=new String();
    static boolean Startgame=false;
        
    public PrimeFrame() 
    {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() 
    {
        javax.swing.ButtonGroup language = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        Start = new javax.swing.JButton();
        select = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        create = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE));
            

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE));


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(612, 692));
        setMinimumSize(new java.awt.Dimension(612, 692));
        setPreferredSize(new java.awt.Dimension(612, 692));
        setResizable(false);
        setSize(new java.awt.Dimension(612, 692));
        getContentPane().setLayout(null);

        Start.setBackground(new java.awt.Color(0, 204, 0));
        Start.setForeground(new java.awt.Color(255, 51, 0));
        Start.setText("Start");


        Start.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                StartActionPerformed(evt);
            }
        });


        getContentPane().add(Start);
        Start.setBounds(440, 612, 130, 40);

        select.setBackground(new java.awt.Color(0, 204, 0));
        select.setForeground(new java.awt.Color(255, 0, 0));
        select.setText("Select Profile");


        select.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                selectActionPerformed(evt);
            }
        });


        getContentPane().add(select);
        select.setBounds(260, 610, 130, 40);

        jPanel2.setBackground(new java.awt.Color(0, 204, 0));
        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);


        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE));


        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE));


        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 150, 150);

        create.setBackground(new java.awt.Color(0, 204, 0));
        create.setForeground(new java.awt.Color(255, 51, 0));
        create.setText("Create profile");


        create.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                createActionPerformed(evt);
            }
        });


        getContentPane().add(create);
        create.setBounds(70, 612, 140, 40);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("             Welcom To maze");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(190, 50, 300, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon("prime.jpg"));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-3, 0, 610, 690);

        pack();
    }

    private void StartActionPerformed(java.awt.event.ActionEvent evt) 
    {

        if(SelectProfile.Select==true)
        {
            Startgame=true;
        }

        else 

        { 
            JOptionPane.showMessageDialog(rootPane, "please first select profile", "eror", JOptionPane.WARNING_MESSAGE);
        }

        if(Startgame==true){new myFrame();}

    }

    private void createActionPerformed(java.awt.event.ActionEvent evt) 
    {
        new CreateProfile().setVisible(true);
    }

    private void selectActionPerformed(java.awt.event.ActionEvent evt) 
    {
        if(CreateProfile.Create==true)
        {
            SelectProfile a=new SelectProfile();
            a.setVisible(true);
        }

        else
        {
            JOptionPane.showMessageDialog(rootPane, "please first create profile", "eror", JOptionPane.WARNING_MESSAGE);
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
            java.util.logging.Logger.getLogger(PrimeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        
        catch (InstantiationException ex) 
        {
            java.util.logging.Logger.getLogger(PrimeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        
        catch (IllegalAccessException ex) 
        {
            java.util.logging.Logger.getLogger(PrimeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        
        catch (javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(PrimeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new PrimeFrame().setVisible(true);
            }
        });
    }

    private javax.swing.JButton Start;
    private javax.swing.JButton create;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton select;
}
