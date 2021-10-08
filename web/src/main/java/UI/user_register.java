package UI;

import Enums.SecurityLevel;
import Logic.Customer;
import Logic.UserManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "user_register", urlPatterns = {"/user_register"})
public class user_register extends HttpServlet {

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
        String firstname = request.getParameter("first_name");
        String lastname = request.getParameter("last_name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        SecurityLevel securitylevel = SecurityLevel.Customer;

        Customer customer = new Customer(firstname, lastname, username, password, securitylevel);
        int userID = UserManager.registerNewUser(customer);
        System.out.println("user_register: " + customer.hashCode());
        if (userID > -1) {
            RequestDispatcher rd = request.getRequestDispatcher("store.jsp");
            request.getSession().setAttribute("username", customer.getUsername());
            rd.forward(request, response);
        } else {
            Map<String, String> messages = new HashMap<String, String>();
            request.setAttribute("messages", messages);
            if (userID == -1) {
                messages.put("userID", "Server error");
            }

            request.getRequestDispatcher("/signup.jsp").forward(request, response);
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
