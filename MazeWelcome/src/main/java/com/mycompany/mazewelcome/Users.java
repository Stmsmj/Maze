package com.mycompany.mazewelcome;

 //importing needed modules

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

//creating class Users
public class Users
{

    //initializing the class variables
    String username;
    double time;

    static ArrayList<String>user1=new ArrayList<>();
    static  ArrayList<String>time1=new ArrayList<>();
    static  ArrayList<String>date1=new ArrayList<>();
    static  ArrayList<String>Level1=new ArrayList<>();
    static  int numberOfprofile = 0;

    //initializing class constructor for adding the user
    public Users(String username) 
    {
        
        //opening users file
        File userfile = new File("Username.txt");
        this.username=username;

        //writing the new user on the Username.txt file 
        try 
        {
            FileWriter file = new FileWriter(userfile, true);
            BufferedWriter username_buffer_writer = new BufferedWriter(file);
            username_buffer_writer.write(username+"\n");
            username_buffer_writer.close();
        } 
        
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Username File does not exist");        
        }
    }

    //initializing second constructor for add user  information to the All.txt
    public Users(String username , double time ) throws IOException 
    {   
        this.time = time;
        this.username=username;

        //specifying the format of date
        DateTimeFormatter date_format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH.mm.ss");  

        //finding the current time
        LocalDateTime now = LocalDateTime.now();  
        String date = date_format.format(now);

        File User = new File("All.txt");

        //writing the username and current date on All.txt
        try 
        {   
            FileWriter file_writer = new FileWriter(User, true);
            BufferedWriter username_buffer_writer= new BufferedWriter(file_writer);
            username_buffer_writer.write(username+":"+time+":"+date+":"+myPanel.Level+"\n");
            username_buffer_writer.close();

            //adding the information using scanner
            Scanner myReader = new Scanner(User);
               while (myReader.hasNextLine())
               {
                    String data = myReader.nextLine();
                    String[]data1 = data.split(":");
                    user1.add(data1[0]);
                    time1.add(data1[1]);
                    date1.add(data1[2]);
                    Level1.add(data1[3]);   
                    numberOfprofile = numberOfprofile+1;
               }
        } 
        
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Username File doesn't exist"); 
        }
  
        //opening the result window by creating an instance of it
        Result result = new Result();
    }
}
    
    

