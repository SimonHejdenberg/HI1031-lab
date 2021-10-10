<%@page import="java.util.Iterator"%>
<%@page import="Logic.ItemHandler"%>
<%@page import="UI.ItemInfo"%>
<%@page import="java.util.Collection"%>
<!DOCTYPE html>
<html>
    <%
        try {
            UserInfo user = (UserInfo) session.getAttribute("user");
            if (user != null && (user.getSecLevel() == SecurityLevel.Admin || user.getSecLevel() == SecurityLevel.Staff)) {%>
                <head>
                    <title>Stock</title>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <link href="css/main.css" rel="stylesheet" type="text/css"/>     
                    <script src="js/sorttable.js"></script>
                </head>
                <body>
                    <div class="header">
                        <%@ include  file="jspheader.jsp" %>
                    </div>
                    <div class="main">
                        <form action='product' method='post'>
                            <table class="sortable">
                                <thead>
                                    <tr>
                                        <th>
                                            Id
                                        </th>
                                        <th>
                                            Name
                                        </th>
                                        <th>
                                            Price
                                        </th>
                                        <th>
                                            Description
                                        </th>
                                        <th>
                                            Quantity
                                        </th>
                                        <th>
                                            Category
                                        </th>
                                        <th>
                                            Picture
                                        </th>
                                        <th>
                                            Edit
                                        </th> 
                                    </tr>
                                </thead>
                                <tbody>
                                    <% Collection<ItemInfo> products = ItemHandler.getItems();
                                        Iterator<ItemInfo> it = products.iterator();
                                        for (; it.hasNext();) {
                                            ItemInfo product = it.next();%>
                                    <tr>
                                        <td>
                                            <%=product.getId()%>
                                        </td>
                                        <td>
                                            <%=product.getName()%>
                                        </td>
                                        <td>
                                            <%=product.getPrice()%>
                                        </td>
                                        <td>
                                            <%=product.getDescription()%>
                                        </td>
                                        <td>
                                            <%=product.getQuantity()%>
                                        </td>
                                        <td>
                                            <%=product.getCategory()%>
                                        </td>
                                        <td>
                                            <%=product.getPictureUrl()%>
                                        </td>
                                        <td>
                                            <button type="submit" name='editProductBtn' value="<%=product.getId()%>" > <i class="fa fa-pencil"></i></button>
                                        </td>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>
                        </form>

                    </div>
                    <div class="footer">
                        <%@ include  file="jspfooter.jsp" %>
                    </div>
                </body>
            <%
                } else {
                    response.sendRedirect("login.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    %>
</html>
