/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.login.Helper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "login")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    private String name; 
    private String pass;
    private int number;
    private int ID;
    private int perm;
        public User(String name, String pass, int number, int ID, int perm){
            this.name = name;
            this.pass = pass;
            this.number = number;
            this.ID = ID;
            this.perm = perm;
        }
        public User(){
            this.name = "";
            this.pass = "";
            this.number = 0;
            this.ID = 0;
            this.perm = 0;
        }
        public String getName(){
            return name;
        }
        public String getPass(){
            return pass;
        }
        public int getNumber(){
            return number;
        }
        public int getID(){
            return ID;
        }
        public int getPerm(){
            return perm;
        }
}
