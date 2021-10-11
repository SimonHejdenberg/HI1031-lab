<%@page import="java.util.Iterator"%>
<%@page import="Logic.User"%>
<%@page import="Logic.UserManager"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Collection"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Accounts Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css" type="text/css"/>
    </head>
    <body>
        <div class="header">
            <%@ include  file="jspheader.jsp" %>
        </div>
        <div class="main">
            <h1>Admin restricted</h1>
            <%if (request.getParameter("status") != null) {
                    String status = request.getParameter("status");
                    if (status == "1") {%>
            <div><h1>Successfully updated user</h1></div>
            <%} else {%>
            <div><h1>Unsuccessful!</h1></div>
            <%}
                }%>
            <form action="user_edit" method="post">
                <table>
                    <thead>
                        <tr>
                            <th>
                                Id
                            </th>
                            <th>
                                First name
                            </th>
                            <th>
                                Last name
                            </th>
                            <th>
                                Username
                            </th>
                            <th>
                                Account Type
                            </th>
                            <th>
                                Edit
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <% Collection users = UserManager.GetAllUsers();
                            Iterator<UserInfo> it = users.iterator();

                            for (; it.hasNext();) {
                                UserInfo user = it.next();%>
                        <tr>
                            <td>
                                <%=user.getUserID()%>
                            </td>
                            <td>
                                <%=user.getFirstname()%>
                            </td>
                            <td>
                                <%=user.getLastname()%>
                            </td>
                            <td>
                                <%=user.getUsername()%>
                            </td>
                            <td>
                                <%=user.getSecLevel()%>
                            </td>
                            <td>
                                <button type="submit" name='editUserAccount' value="<%=user.getUserID()%>" > <i class="fa fa-pencil"></i></button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>         
        </div>
        <div class="footer">
            <%@ include  file="jspfooter.jsp" %>
        </div>
    </body>
</html>
