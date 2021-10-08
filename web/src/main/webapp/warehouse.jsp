<%@page import="java.util.Iterator"%>
<%@page import="Logic.ItemHandler"%>
<%@page import="UI.ItemInfo"%>
<%@page import="java.util.Collection"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Stock</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/main.css" rel="stylesheet" type="text/css"/>     
        <script src="js/sorttable.js"></script>
    </head>
    <body>
        <div class="header">
            <%@ include  file="html/header.html" %>
        </div>
        <div class="main">
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
                            <form action='product' method='post'>
                                
                                <button type="submit" name='editProductBtn' value="<%=product.getId()%>" > <i class="fa fa-pencil"></i> </button>
                            </form>

                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
        <div class="footer">
            <%@ include  file="html/footer.html" %>
        </div>
    </body>
</html>
