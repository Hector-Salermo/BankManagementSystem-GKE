/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.updatebalance.Helper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@XmlRootElement(name = "balance")
@XmlAccessorType(XmlAccessType.FIELD)
public class Balance {
    private String name; 
    private int ID;
    private int chequingbal;
    private int savingsbal;
    private int UID;
        public Balance(String name,int chequingbal, int savingsbal,int ID, int UID){
            this.name = name;
            this.ID = ID;
            this.chequingbal = chequingbal;
            this.savingsbal = savingsbal;
            this.UID = UID;
        }
        public Balance(){
            name="";
            chequingbal=0;
            savingsbal=0;
            ID=0;
            UID=0;
        }
        public String getName(){
            return name;
        }

        public int getChequingBal(){
            return chequingbal;
        }
        public int getSavingsBal(){
            return savingsbal;
        }
        public int getID(){
            return ID;
        }
        public int getUID(){
            return UID;
        }
}
