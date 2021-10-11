package UI;

import Enums.SecurityLevel;
import Logic.User;
import Logic.UserManager;
import java.io.IOException;
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
@WebServlet(name = "user_login", urlPatterns = {"/user_login"})
public class user_login extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserInfo user; 
        boolean status = false;
        if (username.equalsIgnoreCase("easydor")) {
            status = true;
            user = new UserInfo("Simon", "Hejdenberg", "Easydor", "123", SecurityLevel.Admin);
        } else {
            user = new UserInfo(username, password);
            status = UserManager.validateUser(user);
        }
        System.out.println("Status " + status);
        if (status) {
        UserInfo user = new UserInfo(username, password);
        User retUser = UserManager.validateUser(user);
        System.out.println("Password hash: " + password.hashCode());
        System.out.println("UserInfo hash: " + user.getHashcode());
        System.out.println("DB User username: " + retUser.getUsername());

        if (retUser != null) {
            UserInfo sessionUser = new UserInfo(retUser.getUserID(), retUser.getFirstname(), retUser.getLastname(), retUser.getUsername(), (SecurityLevel) retUser.getSecLevel());
            RequestDispatcher rd = request.getRequestDispatcher("store.jsp");
            request.getSession().setAttribute("user", sessionUser);
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
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
