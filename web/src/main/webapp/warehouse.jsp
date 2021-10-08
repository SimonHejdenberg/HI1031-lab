<%@page import="java.util.Iterator"%>
<%@page import="Logic.ItemHandler"%>
<%@page import="UI.ItemInfo"%>
<%@page import="java.util.Collection"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Stock</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css" type="text/css"/>
    </head>
    <body>
        <div class="header">
            <%@ include  file="html/header.html" %>
        </div>
        <div class="main">
            <p>Some form of search input here....</p>


            <table id="warehouseTable" >
                <thead>
                    <tr>
                        <td>
                            Id
                        </td>
                        <td>
                            Name
                        </td>
                        <td>
                            Price
                        </td>
                        <td>
                            Description
                        </td>
                        <td>
                            Quantity
                        </td>
                        <td>
                            Category
                        </td>
                        <td>
                            Picture
                        </td>
                        <td>
                            Edit
                        </td> 
                        <td>
                            Delete
                        </td>
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
                            <button class="btn"><i class="fa fa-pencil"></i> Edit</button>
                        </td>
                        <td>
                            <button class="btn"><i class="fa fa-trash"></i> Delete</button>
                        </td>
                    </tr>
                    <%}%>
                    %>
                </tbody>
            </table>
        </div>

        <div class="footer">
            <%@ include  file="html/footer.html" %>
        </div>
    </body>
</html>
