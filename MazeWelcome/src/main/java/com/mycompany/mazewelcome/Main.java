package com.mycompany.mazewelcome;

public class Main 
{
    public static void main(String args[]) 
    {

        try 
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) // for making widgets look better
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
}
