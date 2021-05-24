/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.login.Persistence;

import com.mycompany.login.Helper.User;
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
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;

public class User_CRUD {
    
    
    private static Connection getCon(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
                        String connection = System.getenv("DB_URL");
			con=DriverManager.getConnection("jdbc:mysql://" +connection + "/BMS?allowPublicKeyRetrieval=true&useSSL=false","root","student");
			System.out.println("Connection established.");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
    
    public static User getUser(String username, String password){
	User bean=null;
		try{
			Connection con=getCon();
			String q = "SELECT * FROM Users WHERE UserName = ? AND Password = ?";
                        

			PreparedStatement ps=con.prepareStatement(q);
                        ps.setString(1, username);
                        ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				//been= new UserInfo();
				String  name=rs.getString("UserName");
                                String  pass=rs.getString("Password");
                                int     phone=rs.getInt("UserPhoneNumber");
                                int     userid=rs.getInt("USERID") ; 
                                int     adminPerm=rs.getInt("AdminPerm");
                                if (pass.equals(password)){
                                    bean = new User(name, pass, phone, userid, adminPerm);
                                }
			}
			con.close();

		}catch(Exception e){System.out.println(e);}
		return bean;
	}
    public static void UpdateUser(User user){
        User bean=null;
        String username = user.getName();
        String password = user.getPass();
        int phone = user.getNumber();
        int ID = user.getID();
        int perm = user.getPerm();
		try{
			Connection con=getCon();
			String q = "Update Users Set UserName = \'" + username + "\' , Password = \'" + password + "\', UserPhoneNumber = \'" + phone + "\', USERID = \'" + ID + "\', AdminPerm = \'" +perm + "\' WHERE UserName = \'" +username+ "\' AND Password = \'" + password + "\' ";

                        Statement st = con.createStatement();
                        st.executeUpdate(q);

			con.close();

		}catch(Exception e){System.out.println(e);}        
    }
    public static void DeleteUser(User user){
        User bean=null;
        String username = user.getName();
        String password = user.getPass();
		try{
			Connection con=getCon();
			String q = "DELETE USERS WHERE UserName = \'"+username+"\' AND Password =\'"+ password+ "\'";
                        Statement st = con.createStatement();
                        st.executeUpdate(q);
			con.close();

		}catch(Exception e){System.out.println(e);}        
    }


    
}
