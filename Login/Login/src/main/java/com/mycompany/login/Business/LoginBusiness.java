/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.login.Business;
import com.mycompany.login.Helper.User;
import com.mycompany.login.Persistence.User_CRUD;
import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author student
 */
public class LoginBusiness {
    public  User getUser(String user, String pass) throws IOException{
        User bs = User_CRUD.getUser(user,pass);
        Messaging.sendmessage("LOGIN:"+bs.getName()+":"+bs.getPass()+":"+bs.getNumber()+":"+bs.getID()+":"+bs.getPerm());
        return (bs);
    }
    
}
