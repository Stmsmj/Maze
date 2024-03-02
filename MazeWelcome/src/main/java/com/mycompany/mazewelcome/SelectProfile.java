package com.mycompany.mazewelcome;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;


public class SelectProfile extends javax.swing.JFrame 
{
    private javax.swing.JButton SelectProfile;
    private javax.swing.JComboBox<String> Profile_comboBox;
    private javax.swing.JLabel Button_label;
    static String UsernameInall = new String();   // string obj for storing name of the profile user selected
    static boolean Select = false;  // boolean obj for showing if user selected a profile or not

    ArrayList<String>Allprofile = new ArrayList<>() ;   // list of all profiles user made
    String[]Allprofile2=new String[Allprofile.size()];  // a variable that will be a copy of Allprofile
     

    public SelectProfile()  // reads all profile names in Username.txt then store it in Allprofile
    {
        File profile=new File("Username.txt");

        try 
        {
            Scanner Reader= new Scanner(profile);
            while(Reader.hasNextLine())
            {
                String data = Reader.nextLine();  
                Allprofile.add(data);
            }
        } 

        catch (FileNotFoundException e)   // show error if there isnt any profile
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
        Profile_comboBox = new javax.swing.JComboBox<>();
        Button_label = new javax.swing.JLabel();

        // setting the size of panel and etc
        setMaximumSize(new java.awt.Dimension(370, 460));
        setMinimumSize(new java.awt.Dimension(370, 460));
        setPreferredSize(new java.awt.Dimension(370, 460));

        SelectProfile.setBackground(new java.awt.Color(153, 153, 153));     // background color of button
        SelectProfile.setText("select profile");    // the string written on it

        SelectProfile.addActionListener(new java.awt.event.ActionListener()     // actionListener is necessary for button to work
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                SelectProfileActionPerformed(evt);
            }
        });

        getContentPane().add(SelectProfile);    // because this is a subframe we need to add widgets to ContentPane and configuring it
        SelectProfile.setBounds(120, 342, 130, 30);

        Profile_comboBox.setBackground(new java.awt.Color(153, 153, 153));
        Profile_comboBox.setModel(new DefaultComboBoxModel<String>(Allprofile2));

        getContentPane().add(Profile_comboBox);   // adding comboBox to ContentPane and configuring it
        Profile_comboBox.setBounds(135, 70, 110, 26);

        Button_label.setIcon(new javax.swing.ImageIcon("src/main/java/image/mazebg.jpg"));   // background picture

        getContentPane().add(Button_label);  // adding label to ContentPane and configuring it
        Button_label.setBounds(0, 0, 360, 425);

        pack();
    }

    private void SelectProfileActionPerformed(java.awt.event.ActionEvent evt)   // for action to perform after pressing the Button_label
    {
        UsernameInall=Profile_comboBox.getSelectedItem().toString();    // getting the profile name user chose
        Select=true;
        JOptionPane.showMessageDialog(rootPane, " you select"+" "+UsernameInall+ "!", "Succsfuly selected", JOptionPane.INFORMATION_MESSAGE);
                
        this.setVisible(false);

    }

}

