/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.login.Endpoint;

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
import com.mycompany.login.Helper.User;
import com.mycompany.login.Business.LoginBusiness;
import java.io.IOException;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("login")
public class LoginResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.login.Endpoint.LoginResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("login/{username}/{pass}")
    public String getXml(@PathParam("username") String username, @PathParam("pass") String pass) throws IOException {
        System.out.println("accName getXML" + username);
        LoginBusiness loginBusiness = new LoginBusiness();
        User user = loginBusiness.getUser(username,pass);
        
        if (user == null) {
            return("");
        }
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(User.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(user, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(LoginResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
    }


    /**
     * PUT method for updating or creating an instance of LoginResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(java.lang.String content) {
    }
}
