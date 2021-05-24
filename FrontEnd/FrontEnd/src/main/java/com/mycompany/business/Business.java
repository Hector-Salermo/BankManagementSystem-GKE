/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.business;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import com.mycompany.helper.BalanceXML;
import com.mycompany.helper.Balance;
import com.mycompany.helper.User;

/**
 *
 * @author student
 */
public class Business {

    public static boolean isAuthenticated(String username, String password) throws IOException {
        Client loginClient = ClientBuilder.newClient();
        if(username.length()>0&&password.length()>0){
            WebTarget loginwebTarget
                    = loginClient.target("http://localhost:8080/login/webresources/login/login");
            InputStream is
                    = loginwebTarget.path(username).path(password).request(MediaType.APPLICATION_XML).get(InputStream.class);
            String xml = IOUtils.toString(is, "utf-8");
            User user = userxmltoObjects(xml);
            if(user!=null){
            return true;
        
            } else{
                return false;
            }
        }
        
        return false;
        
        
    }
    
    public static boolean updateBalanceService(String amountChequing, String amountSavings,String balanceChequing, String balanceSavings,String accID, String UID, String accName, String token) throws IOException {
        
//        Client updateBalanceClient = ClientBuilder.newClient();
//        WebTarget updateBalancewebTarget
//                = updateBalanceClient.target("http://localhost:8080/updatebalance/webresources/updateBalance/update");
//        InputStream is
//                = updateBalancewebTarget.path(amountChequing).path(amountSavings).path(balanceChequing).path(balanceSavings).path(accID).path(UID).path(accName
//                ).queryParam("token", token).request(MediaType.TEXT_PLAIN).get(InputStream.class);
//        String xml = IOUtils.toString(is, "utf-8");
//        if(xml.equals("Inserted")){
//            return true;
//        }else{
//            return false;
//        }
return true;
        
    }

    public static Balance getServices(String accName, String token) throws IOException {

        Client balanceClient = ClientBuilder.newClient();  
        String updateBalance= System.getenv("updateBalance");

        WebTarget balancewebTarget
                = balanceClient.target("http://"+updateBalance+"/updatebalance/webresources/balance/balance");
        InputStream is
                = balancewebTarget.path(accName).queryParam("token", token).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        Balance balance = balancexmltoObjects(xml);
        if (token != null) {
            Client holdclient = ClientBuilder.newClient();
            WebTarget holdwebTarget
                    = holdclient.target("http://"+updateBalance+"updatebalance/webresources/balance/balance");
        }

        return (balance);

    }

    private static Balance balancexmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Balance.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Balance balance = (Balance) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return balance;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static User userxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(User.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            User user = (User) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return user;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
