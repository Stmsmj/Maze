package com.mycompany.mazewelcome;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author ae
 */
public class CreateProfile extends javax.swing.JFrame
{
    static boolean Create=false;

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        UsernameTxtfield = new javax.swing.JTextField();

        create = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setMaximumSize(new java.awt.Dimension(720, 472));
        setMinimumSize(new java.awt.Dimension(720, 172));
        setPreferredSize(new java.awt.Dimension(720, 472));
        setSize(new java.awt.Dimension(720, 472));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel1.setText("       Create Profile");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(280, 10, 180, 28);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel2.setText("  username");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(210, 110, 100, 29);

        UsernameTxtfield.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                UsernameTxtfieldActionPerformed(evt);
            }
        });
        getContentPane().add(UsernameTxtfield);
        UsernameTxtfield.setBounds(330, 100, 156, 36);

        create.setBackground(new java.awt.Color(204, 204, 204));
        create.setText("create");
        create.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        create.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                createActionPerformed(evt);
            }
        });
        getContentPane().add(create);
        create.setBounds(320, 320, 140, 40);

        jLabel3.setIcon(new javax.swing.ImageIcon("create.jpg"));

        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 720, 470);

        pack();
    }

    private void UsernameTxtfieldActionPerformed(java.awt.event.ActionEvent evt) 
    {

    }

    private void createActionPerformed(java.awt.event.ActionEvent evt) 
    {
        String username=UsernameTxtfield.getText();
        boolean chekexist=false;

        if (!username.equals("") && checkvalid(UsernameTxtfield.getText()))
        {
            File user=new File("Username.txt");
            try 
            {
                Scanner Reader=new Scanner(user);
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
                Users ejra=new Users(username);
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

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(CreateProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 

        catch (InstantiationException ex) 
        {
            java.util.logging.Logger.getLogger(CreateProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 

        catch (IllegalAccessException ex) 
        {
            java.util.logging.Logger.getLogger(CreateProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 

        catch (javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(CreateProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new CreateProfile().setVisible(true);
            }
        });
    }

    private javax.swing.JTextField UsernameTxtfield;
    private javax.swing.JButton create;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
}
