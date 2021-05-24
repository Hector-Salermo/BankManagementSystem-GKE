/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.updatebalance.Business;
import com.mycompany.updatebalance.Helper.Balance;
import com.mycompany.updatebalance.Persistence.Balance_CRUD;
/**
 *
 * @author student
 */
public class UpdateBalanceBusiness {
    public  Balance getBalanceByName(String accName){
        Balance bs = Balance_CRUD.getAccount(accName);
        return (bs);
    }
    public  void setBalance(Balance balance){
        Balance_CRUD.UpdateAccount(balance);
    }
    
}
