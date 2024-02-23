/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mazewelcome;

/**
 *
 * @author ae
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class Users 
{
    
    String username;
    double time;
    static ArrayList<String>user1=new ArrayList<>();
    static  ArrayList<String>time1=new ArrayList<>();
    static  ArrayList<String>date1=new ArrayList<>();
    static  ArrayList<String>Level1=new ArrayList<>();
    static  int numberOfprofile =0;

    public Users(String username) 
    {
        
        File userfile=new File("Username.txt");
        this.username=username;
        try 
        {
            FileWriter file=new FileWriter(userfile, true);
            BufferedWriter usernamefile= new BufferedWriter(file);
            usernamefile.write(username+"\n");
            usernamefile.close();
        } 
        
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Username File does'nt exist");        
        }
    }

    public Users(String username , double time ) throws IOException 
    {
        
        this.time = time;
        this.username=username;
           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH.mm.ss");  
           LocalDateTime now = LocalDateTime.now();  
           String date=dtf.format(now);
        File User=new File("All.txt");
        try 
        {   
            FileWriter file=new FileWriter(User, true);
            BufferedWriter usernamefile= new BufferedWriter(file);
            usernamefile.write(username+":"+time+":"+date+":"+myPanel.Level+"\n");
            usernamefile.close();
             Scanner myReader = new Scanner(User);
               while (myReader.hasNextLine())
               {
                    String data = myReader.nextLine();
                    String[]data1=data.split(":");
                    user1.add(data1[0]);
                    time1.add(data1[1]);
                    date1.add(data1[2]);
                    Level1.add(data1[3]);   //+" "+Level1.get(numberOfprofile-1)
                    numberOfprofile=numberOfprofile+1;
              //  System.out.println(user1.get(numberOfprofile-1)+"   "+time1.get(numberOfprofile-1)+"    "+date1.get(numberOfprofile-1)+" "+Level1.get(numberOfprofile-1));
               }
        } 
        
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Username File does'nt exist"); 
        }
  
    new Result();         
    
    }


}
    
    

