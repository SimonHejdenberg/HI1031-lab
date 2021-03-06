/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Enums.SecurityLevel;
import Logic.User;
import Logic.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jemsann
 */
@WebServlet(name = "adminedituser", urlPatterns = {"/adminedituser"})
public class adminedituser extends HttpServlet {

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
        doPost(request, response);
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
        String userId = (String) request.getParameter("userId");
        String username = (String) request.getParameter("username");
        String seclevel = (String) request.getParameter("secLevel");
        String password;
        if (request.getParameter("password") != null) {
            password = (String) request.getParameter("password");
        } else {
            password = null;
            System.out.println(password);
        }

        SecurityLevel newLevel = SecurityLevel.valueOf(seclevel);
        System.out.println("new sec " + newLevel.toString());
        System.out.println("new sec " + newLevel.ordinal());

        System.out.println("new username " + username.toString());

        User original_user = UserManager.GetUser(Integer.parseInt(userId));
        System.out.println("orig " + original_user.getSecLevel());
        System.out.println("orig username " + original_user.getUsername());

        boolean status = UserManager.EditUser(original_user, username, password, newLevel);

        RequestDispatcher rd = request.getRequestDispatcher("accounts.jsp");
        request.setAttribute("status", status);
        rd.forward(request, response);
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

}
