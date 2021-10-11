<%-- 
    Document   : editProduct
    Created on : 8 Oct 2021, 20:58:46
    Author     : jemsann
--%>

<%@page import="Logic.ItemHandler"%>
<%@page import="UI.ItemInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit item</title>
        <%String itemId = (String) request.getAttribute("itemId");
            ItemInfo product = ItemHandler.getItem(Integer.parseInt(itemId));
        %>
    </head>
    <body>
        <h1>Edit item <%=product.getName()%></h1>
        <form action="editProduct" method="POST">
            <table style="width: 50%">
                <tr>
                    <td>Id</td>
                    <td><input type="hidden" name="productId" value="<%=product.getId()%>"></td>
                </tr>
                <tr>
                    <td>Product Name</td>
                    <td><input type="text" name="product_name" value="<%=product.name%>"/></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><input type="text" name="product_desc" value="<%=product.description%>"/></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" name="product_quantity" value="<%=product.quantity%>"/></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input type="text" name="product_price" value="<%=product.price%>"/></td>
                </tr>
                <tr>
                    <td>Category</td>
                    <td><input type="text" name="product_category" value="<%=product.category%>"/></td>
                </tr>
                <tr>
                    <td>Picture</td>
                    <td><input type="text" name="product_url" value="<%=product.pictureUrl%>"/></td>
                </tr>
            </table>
        
            <input type="submit" value="Update" />

            <button type="button" onclick="history.back()">Cancel</button>
        </form>
    </body>
</html>
