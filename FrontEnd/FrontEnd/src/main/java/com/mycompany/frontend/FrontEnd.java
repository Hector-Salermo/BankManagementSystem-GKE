/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.NewCookie;
import com.mycompany.business.Business;
import com.mycompany.helper.BalanceXML;
import com.mycompany.helper.Balance;
/**
 *
 * @author student
 */
@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEnd extends HttpServlet {

    Authenticate autho;

    public FrontEnd() {
        autho = new Authenticate();
    }
    private final String authenticationCookieName = "login_token";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        
        System.out.println("TOKEN IS");
        try {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {

        }
        if (!token.isEmpty())
           try {
            if (this.autho.verify(token).getKey()) {
                  Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>
                             (token,this.autho.verify(token).getValue());
            return entry;

            } else {
                 Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
        }

       Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = isAuthenticated(request).getKey();
        String uname = isAuthenticated(request).getValue();
        String hiddenParam = request.getParameter("pageName");
        switch (hiddenParam) {
            case "login":
                Balance result;
                String username = (String) request.getParameter("UserName");
                String password = (String) request.getParameter("Password");
                
                boolean isAuthenticated = Business.isAuthenticated(username, password);
                if (isAuthenticated) {
                    request.setAttribute("UserName", username);
                    token = autho.createJWT("FrontEnd", username, 100000);
                    
                    Cookie newCookie = new Cookie(authenticationCookieName, token);
                    response.addCookie(newCookie);
                    result = retreiveServicesFromBackend(username, token);
                    request.setAttribute("balanceChequing", result.getChequingBal());
                    request.getSession().setAttribute("balanceSavings", result.getSavingsBal());
                    request.getSession().setAttribute("AccID", result.getID());
                    request.getSession().setAttribute("AccountName", result.getName());
                    request.getSession().setAttribute("UID", result.getUID());


                    RequestDispatcher requestDispatcher = request.
                            getRequestDispatcher("transfer.jsp");

                    requestDispatcher.forward(request, response);

                } else {
                    RequestDispatcher requestDispatcher = request.
                            getRequestDispatcher("loginfailed.jsp");

                    requestDispatcher.forward(request, response);

                
                }
                break;
                case "Transfer From Chequing":
                    boolean resultInsert;
                    String amountChequing=(String)request.getParameter("amountChequing");
                    String amountSavings=(String)request.getParameter("amountSavings");
                    String balanceChequing=request.getParameter("balanceChequing");
                    String balanceSavings=request.getParameter("balanceSavings");
                    String accID= request.getParameter("accID");
                    String UID= request.getParameter("UID");
                    String accName=request.getParameter("AccountName");
                    Cookie newCookie = new Cookie(authenticationCookieName, token);
                    response.addCookie(newCookie);
                    resultInsert=updateBalanceFromBackend(amountChequing, amountSavings,balanceChequing,balanceSavings,accID,UID,accName,token);
                    

//                BalanceXML result;
//                String accName = request.getParameter("UserName");
//                if (token.isEmpty()) {
//                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginfailed.jsp");
//                    requestDispatcher.forward(request, response);
//                    break;
//                } else {
//                    request.setAttribute("username", accName);
//                    result = retreiveServicesFromBackend(accName, token);

//                    request.setAttribute("bookResults", result);

//                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontpageWithLogin.jsp");

//                    requestDispatcher.forward(request, response);
//                }
                break;
           
        }

        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Balance retreiveServicesFromBackend(String accName, String token) {
        try {
            return (Business.getServices(accName, token));
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }
    
    private boolean loginFromBackend(String user, String pass) {
    try {
        return (Business.isAuthenticated(user, pass));
    } catch (IOException ex) {
        Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }

    }
    
        private boolean updateBalanceFromBackend(String amountChequing, String amountSavings,String balanceChequing, String balanceSavings,String accID, String UID, String accName, String token) {
        try {
            return (Business.updateBalanceService(amountChequing, amountSavings,balanceChequing,balanceSavings,accID,UID,accName,token));
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
