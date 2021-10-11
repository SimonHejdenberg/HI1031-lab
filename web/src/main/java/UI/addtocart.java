/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Logic.Cart;
import Logic.ItemHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jemsann
 */
@WebServlet(name = "addtocart", urlPatterns = {"/addtocart"})
public class addtocart extends HttpServlet {

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

        Cart tempCart = (Cart) request.getSession().getAttribute("cart");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // get all parameter names
        Set<String> paramNames = request.getParameterMap().keySet();

        // iterating over parameter names and get its value
        for (String name : paramNames) {
            String value = request.getParameter(name);
            out.write("GET: " + name + ": " + value);
        }

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
        Cart tempCart = (Cart) request.getSession().getAttribute("cart");
        String id = request.getParameter("itemId");
        Collection<ItemInfo> c = ItemHandler.getItems();
        for (ItemInfo i : c) {
            if (i.getId() == Integer.parseInt(id)) {
                ItemInfo revItem = new ItemInfo(i.getId(), i.getName(), i.getPrice(), i.getDescription(), i.getQuantity(), i.getCategory(), i.getPictureUrl());
                tempCart.addItem(revItem, 1);
                System.out.println("added " + i.getId());
            }
        }

        request.getSession().setAttribute("cart", tempCart);
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
