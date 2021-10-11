/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Enums.Category;
import Logic.ItemHandler;
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
@WebServlet(name = "editProduct", urlPatterns = {"/editProduct"})
public class editProduct extends HttpServlet {

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
        String productId = (String) request.getParameter("productId");
        String productName = (String) request.getParameter("product_name");
        String product_desc = (String) request.getParameter("product_desc");
        String product_quantity = (String) request.getParameter("product_quantity");
        String product_price = (String) request.getParameter("product_price");
        String product_category = (String) request.getParameter("product_category");
        String product_url = (String) request.getParameter("product_url");

        Category cat = Category.valueOf(product_category);
        ItemInfo original_product = ItemHandler.getItem(Integer.parseInt(productId));
        if (original_product.getId() == Integer.parseInt(productId)) {
            ItemInfo updated_product = new ItemInfo(Integer.parseInt(productId), productName, Double.parseDouble(product_price), product_desc, Integer.parseInt(product_quantity), cat, product_url);
            boolean status = ItemHandler.UpdateProduct(updated_product);
            RequestDispatcher rd = request.getRequestDispatcher("warehouse.jsp");
            request.setAttribute("successful", status);
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("warehouse.jsp");
            request.setAttribute("successful", "false");
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
