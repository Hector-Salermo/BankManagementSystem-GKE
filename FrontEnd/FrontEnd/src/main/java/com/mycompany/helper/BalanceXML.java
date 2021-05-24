/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helper;

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

 @XmlRootElement(name = "balance")
@XmlAccessorType (XmlAccessType.FIELD)
       public class BalanceXML{
           private Balance balance;
           
           
           public Balance getBalance(){
               return balance;
               
           }
          public BalanceXML(){
               
               
           }
           public void setBalance(Balance bal){
               balance=bal;
               
           }
           
       }