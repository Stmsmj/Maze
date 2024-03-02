package com.mycompany.mazewelcome;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class CreateProfile extends javax.swing.JFrame
{
    private javax.swing.JTextField UsernameTxtfield;
    private javax.swing.JButton create_button;
    private javax.swing.JLabel jLabel_create_profile;
    private javax.swing.JLabel jLabel_username;
    private javax.swing.JLabel jLabel_img;

    static boolean Create=false;

    //checking validation of username
    boolean checkvalid(String c)
    {
        if(c.contains(".") || c.contains(";") || c.contains(":") || c.contains("!") || c.contains("?")||c.contains(","))
        {
            return false;
        }

        else
        {
            return true;
        } 
    }   

    public CreateProfile() 
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() 
    {
        //init needed components
        jLabel_create_profile = new javax.swing.JLabel();
        jLabel_username = new javax.swing.JLabel();
        UsernameTxtfield = new javax.swing.JTextField();
        create_button = new javax.swing.JButton();
        jLabel_img = new javax.swing.JLabel();

        //set the default size of create profile window
        setMaximumSize(new java.awt.Dimension(720, 472));
        setMinimumSize(new java.awt.Dimension(720, 172));
        setPreferredSize(new java.awt.Dimension(720, 472));
        setSize(new java.awt.Dimension(720, 472));
        getContentPane().setLayout(null);

        //initialize create profile jlabel
        jLabel_create_profile.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel_create_profile.setText("       Create Profile");
        jLabel_create_profile.setBorder(javax.swing.BorderFactory.createSoftBevelBorder(javax.swing.border.SoftBevelBorder.RAISED));
        getContentPane().add(jLabel_create_profile);
        jLabel_create_profile.setBounds(280, 10, 180, 28);

        //initialize username jlabel
        jLabel_username.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_username.setBorder(javax.swing.BorderFactory.createSoftBevelBorder(javax.swing.border.SoftBevelBorder.RAISED));
        jLabel_username.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel_username.setText("  username");
        getContentPane().add(jLabel_username);
        jLabel_username.setBounds(200, 105, 100, 29);

        //add action to username textfield
        UsernameTxtfield.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                UsernameTxtfieldActionPerformed(evt);
            }

            private void UsernameTxtfieldActionPerformed(ActionEvent evt) {
            }
        });
        getContentPane().add(UsernameTxtfield);
        UsernameTxtfield.setBounds(330, 100, 156, 36);

        //initialize create button
        create_button.setBackground(new java.awt.Color(204, 204, 204));
        create_button.setText("create");

        //action to the create button
        create_button.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                createActionPerformed(evt);
            }
        });

        getContentPane().add(create_button);
        create_button.setBounds(300, 320, 140, 40);
        
        //add image 
        jLabel_img.setIcon(new javax.swing.ImageIcon("src/main/java/image/create.jpg"));
        getContentPane().add(jLabel_img);
        jLabel_img.setBounds(0, 0, 720, 470);

        pack();
    }
    
    //action of create button
    private void createActionPerformed(java.awt.event.ActionEvent evt) 
    {
        String username=UsernameTxtfield.getText();
        boolean chekexist=false;

        if (!username.equals("") && checkvalid(UsernameTxtfield.getText()))
        {
            File user_list=new File("Username.txt");
            try 
            {
                Scanner Reader=new Scanner(user_list);
                while(Reader.hasNextLine())
                {
                    String data=Reader.nextLine();
                    if(data.equals(username)){chekexist=true;}
                }
            }

            catch (FileNotFoundException e) 
            {
                JOptionPane.showMessageDialog(null, "nemishe");
            } 

            if(chekexist==false)
            {
                JOptionPane.showMessageDialog(rootPane, "The profile succesfully created !", "Succsfuly created", JOptionPane.INFORMATION_MESSAGE);
                Users new_user = new Users(username);
                Create=true;
                this.setVisible(false);
            }

            else
            { 
                JOptionPane.showMessageDialog(rootPane, "The Profile alreay exist", "eror", JOptionPane.WARNING_MESSAGE); 
            }
        }
        
        else
        {
            JOptionPane.showMessageDialog(rootPane, "The username shouldn't be empty and shoud not include(.,;:!?)  ", "eror", JOptionPane.WARNING_MESSAGE);
        }
    }
}
