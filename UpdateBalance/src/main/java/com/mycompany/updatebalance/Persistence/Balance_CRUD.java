/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.updatebalance.Persistence;

import com.mycompany.updatebalance.Helper.Balance;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author student
 */
public class Balance_CRUD {
        
    
    private static Connection getCon(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/BMS?autoReconnect=true&useSSL=false","root","student");
			System.out.println("Connection established.");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
    
    public static Balance getAccount(String accname){
	Balance bean=null;
		try{
			Connection con=getCon();
                        String q = "SELECT * FROM Account WHERE AccountName = ?";
			PreparedStatement ps=con.prepareStatement(q);
                        ps.setString(1, accname);
			ResultSet rs=ps.executeQuery();		
                        rs.next();
                        int     userid=rs.getInt("UID") ; 
                        int     chequingbal=rs.getInt("ChequingBal");
                        int     savingsbal=rs.getInt("SavingBal");    
                        String  name=rs.getString("AccountName");
                        int     accountID=rs.getInt("AccID");                                
                        bean = new Balance(name, chequingbal, savingsbal, userid, accountID);
			
			con.close();

		}catch(Exception e){System.out.println(e);}
		return bean;
	}
    public static void UpdateAccount(Balance account){
        //Account bean=null;
        String accname = account.getName();
        int chequingbal = account.getChequingBal();
        int savingsbal = account.getSavingsBal();
        int ID = account.getID();
        int UID = account.getUID();
		try{
			Connection con=getCon();
			String q = "Update Account Set AccountName = \'" + accname + "\', ChequingBal = \'" + chequingbal + "\', SavingBal = \'" + savingsbal + "\',AccID = \'" + ID + "\', UID = \'" +UID + "\' WHERE AccountName = \'" +accname+ "\' AND AccID = \'" + ID + "\' ";
                        Statement st = con.createStatement();
                        st.executeUpdate(q);

			con.close();

		}catch(Exception e){System.out.println(e);
                }        
    }
    public static void DeleteAccount(Balance account){
        //Account bean=null;
        String accname = account.getName();
        int ID = account.getID();
		try{
			Connection con=getCon();
			String q = "DELETE USERS WHERE AccountName = \'"+accname+"\' AND AccID =\'"+ ID + "\'";
                        Statement st = con.createStatement();
                        st.executeUpdate(q);
			con.close();

		}catch(Exception e){System.out.println(e);}        
    }
}
