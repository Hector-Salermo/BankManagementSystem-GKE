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
@Path("balance")
public class BalanceResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BalanceResource
     */
    public BalanceResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.updatebalance.Endpoint.BalanceResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("balance/{accName}")
    public String getXml(@PathParam("accName") String accName) {
        System.out.println("accName getXML" + accName);
        UpdateBalanceBusiness balanceBusiness = new UpdateBalanceBusiness();
        Balance balance = balanceBusiness.getBalanceByName(accName);
        
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

    /**
     * PUT method for updating or creating an instance of BalanceResource
     * @param content representation for the resource
     */
  
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(java.lang.String content) {
    }
    
}

        

    
