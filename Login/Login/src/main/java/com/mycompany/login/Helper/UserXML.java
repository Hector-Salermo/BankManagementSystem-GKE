/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.login.Helper;

/**
 *
 * @author student
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

 @XmlRootElement(name = "user")
@XmlAccessorType (XmlAccessType.FIELD)
       public class UserXML{
           private User user;
           
           
           public User getUser(){
               return user;
               
           }
          public UserXML(){
               
               
           }
           public void setUser(User us){
               user=us;
               
           }
           
       }