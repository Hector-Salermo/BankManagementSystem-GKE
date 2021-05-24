/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.updatebalance.Endpoint;


import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import com.mycompany.updatebalance.Helper.Balance;
import com.mycompany.updatebalance.Business.UpdateBalanceBusiness;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("updateBalance")
public class UpdateBalanceResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UpdateBalanceResource
     */
    public UpdateBalanceResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.updatebalance.Endpoint.UpdateBalanceResource
     * @return an instance of java.lang.String
     */

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("update/{amountChequing}/{amountSavings}/{chequingbal}/{savingsbal}/{ID}/{UID}/{accName}")
    public java.lang.String getXml(@PathParam("amountChequing") String amountChequing, @PathParam("amountSavings") String amountSavings, @PathParam("chequingbal") String chequingbal,
            @PathParam("savingsbal") String savingsbal, @PathParam("ID") String ID, @PathParam("UID") String UID, @PathParam("accName") String accName)  {
         int UIDInt;
        try {
           UIDInt = Integer.parseInt(UID);
        }
        catch (NumberFormatException e)
        {
           UIDInt = 0;
        }
        int accIDInt;
        try {
           accIDInt = Integer.parseInt(ID);
        }
        catch (NumberFormatException e)
        {
           accIDInt = 0;
        }
        int amountChequingInt;
        try {
           amountChequingInt = Integer.parseInt(amountChequing);
        }
        catch (NumberFormatException e)
        {
           amountChequingInt = 0;
        }
        int amountSavingsInt;
        try {
           amountSavingsInt = Integer.parseInt(amountSavings);
        }
        catch (NumberFormatException e)
        {
           amountSavingsInt = 0;
        }
        
        
        
        
        
        int balanceChequingInt;
        try {
           balanceChequingInt = Integer.parseInt(chequingbal);
        }
        catch (NumberFormatException e)
        {
           balanceChequingInt = 0;
        }
        int balanceSavingsInt;
        try {
           balanceSavingsInt = Integer.parseInt(savingsbal);
        }
        catch (NumberFormatException e)
        {
           balanceSavingsInt = 0;
        }
        
        
        
        int finalChequing=balanceChequingInt;
        int finalSavings=balanceSavingsInt;
  
        
        if(amountChequingInt>balanceChequingInt||amountSavingsInt>balanceSavingsInt) {
            return "Not inserted";
        } else {
            
            if(amountChequingInt>0){
                finalChequing = balanceChequingInt-amountChequingInt;
                finalSavings = balanceSavingsInt + amountChequingInt;
            }
            else if(amountSavingsInt>0){
                finalSavings = balanceSavingsInt-amountSavingsInt;
                finalChequing = balanceChequingInt+amountSavingsInt;
            }


          Balance balance = new Balance(accName, finalChequing, finalSavings, accIDInt, UIDInt);
          UpdateBalanceBusiness balanceBusiness = new UpdateBalanceBusiness();
          balanceBusiness.setBalance(balance);
//          if(bs)
//              return("Inserted");
//          else
//              return("Not inserted");

            if (balance == null) {
                return("");
            }
            JAXBContext jaxbContext;
            try {
                jaxbContext = JAXBContext.newInstance(Balance.class);

                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                StringWriter sw = new StringWriter();
                jaxbMarshaller.marshal(balance, sw);

                return (sw.toString());

            } catch (JAXBException ex) {
                Logger.getLogger(BalanceResource.class.getName()).log(Level.SEVERE, null, ex);
                return ("error happened");
            }
          
    }
    }

    /**
     * PUT method for updating or creating an instance of UpdateBalanceResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(java.lang.String content) {
    }
}
