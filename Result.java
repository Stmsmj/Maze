package com.mycompany.mazewelcome;

import javax.swing.border.LineBorder;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


/**
 *
 * @author ae
 */

public class Result extends javax.swing.JFrame
{

    String [] columns = {"Username", "Time", "Date", "Level"};

    public JButton continue_button = new JButton();
    private JPanel continue_panel = new JPanel();

    DefaultTableModel model=new DefaultTableModel(columns,0);

    public Result() 
    {
        for (int j = 0; j < Users.numberOfprofile; j++) 
        {
                model.addRow(new String[]{Users.user1.get(j),Users.time1.get(j),Users.date1.get(j),Users.Level1.get(j)});
        }

    this.setMinimumSize(new Dimension(300,430));
        initComponents();
    this.setVisible(true);

    }
    

    @SuppressWarnings("unchecked")
    private void initComponents() 
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();

    
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        Table.setBackground(new java.awt.Color(0, 255, 255));
        Table.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 0, 255), new java.awt.Color(0, 255, 255)));
        Table.setForeground(new java.awt.Color(255, 0, 0));
        Table.setModel(model);
        jScrollPane1.setViewportView(Table);


        //adding continue panel and button
        continue_panel.setBounds(300, 350, 100, 100);
        continue_button.setText("continue");
        continue_panel.add(continue_button);
        getContentPane().add(continue_panel);



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE));


        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE));


        pack();
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
            java.util.logging.Logger.getLogger(Result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        
        catch (InstantiationException ex) 
        {
            java.util.logging.Logger.getLogger(Result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        
        catch (IllegalAccessException ex) 
        {
            java.util.logging.Logger.getLogger(Result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        
        catch (javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(Result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }

    private javax.swing.JTable Table;
    private javax.swing.JScrollPane jScrollPane1;
}

