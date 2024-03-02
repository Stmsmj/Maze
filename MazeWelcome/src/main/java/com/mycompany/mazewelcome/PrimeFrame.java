package com.mycompany.mazewelcome;


import java.awt.*;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class PrimeFrame extends javax.swing.JFrame 
{
    private javax.swing.JButton Start;
    private javax.swing.JButton create;
    private javax.swing.JLabel background_picture;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton select;
    static String languageInAll=new String();
    static boolean Startgame=false;
        
    public PrimeFrame() 
    {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() 
    {
        Start = new javax.swing.JButton();
        select = new javax.swing.JButton();
        create = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        background_picture = new javax.swing.JLabel();

        // set panel configuration
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(612, 692));
        setMinimumSize(new java.awt.Dimension(612, 692));
        setPreferredSize(new java.awt.Dimension(612, 692));
        setResizable(false);
        setSize(new java.awt.Dimension(612, 692));
        getContentPane().setLayout(null);

        // start button and its configurations
        Start.setBackground(new java.awt.Color(100, 100, 100));
        Start.setForeground(new java.awt.Color(0, 0, 10));
        Start.setText("Start");

        // select button and its configurations
        select.setBackground(new java.awt.Color(100, 100, 100));
        select.setForeground(new java.awt.Color(0, 0, 20));
        select.setText("Select Profile");

        // create button and its configurations
        create.setBackground(new java.awt.Color(100, 100, 100));
        create.setForeground(new java.awt.Color(0, 0, 10));
        create.setText("Create profile");


        Start.addActionListener(new java.awt.event.ActionListener() // adding actionListener for start button for function
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                StartActionPerformed(evt);
            }
        });

        select.addActionListener(new java.awt.event.ActionListener() // adding actionListener for select button for function
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                selectActionPerformed(evt);
            }
        });

        create.addActionListener(new java.awt.event.ActionListener() // adding actionListener for create button for function
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                createActionPerformed(evt);
            }
        });

        getContentPane().add(Start);    // adding start button to ContentPane and setting its configurations
        Start.setBounds(440, 612, 130, 40);

        getContentPane().add(select);    // adding select button to ContentPane and setting its configurations
        select.setBounds(260, 610, 130, 40);

        getContentPane().add(create);   // adding create button to ContentPane and setting its configurations
        create.setBounds(70, 612, 140, 40);

        // this is for big size maze label on middle top and its configurations
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 20));
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("                        Maze");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.SoftBevelBorder.LOWERED));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(170, 50, 300, 30);

        // background picture and its configurations
        background_picture.setIcon(new javax.swing.ImageIcon("src/main/java/image/prime.jpg"));
        getContentPane().add(background_picture);
        background_picture.setBounds(-3, 0, 610, 690);

        pack();
    }

    // actions to perform if user pressed start button
    private void StartActionPerformed(java.awt.event.ActionEvent evt) 
    {
        if(SelectProfile.Select==true) // check if user did select a profile or not
        {
            Startgame=true;
        }

        else    // show error if user didnt select a profile
        { 
            JOptionPane.showMessageDialog(rootPane, "please first select a profile", "Error", JOptionPane.WARNING_MESSAGE);
        }

        if(Startgame==true)     // if everything is ok the game gonna open
        {
            new myFrame();
            this.setVisible(false);
        }

    }

    // actions to perform if user pressed create button
    private void createActionPerformed(java.awt.event.ActionEvent evt) 
    {
        new CreateProfile().setVisible(true);
    }

    private void selectActionPerformed(java.awt.event.ActionEvent evt) 
    {

        // checking that is there created profiles or we should create one!

        File Users = new File("Username.txt");
        boolean UserListIsEmpty ;
    
        if(Users.length()==0)
        {
            UserListIsEmpty = true;
        }

        else
        {
            UserListIsEmpty=false;
        }


        // opening the select frame
        if(UserListIsEmpty==false)
        {
            SelectProfile a = new SelectProfile();
            a.setVisible(true);
        }
        // showing the error for creating a profile
        else
        {
            JOptionPane.showMessageDialog(rootPane, "please first create a profile", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}
