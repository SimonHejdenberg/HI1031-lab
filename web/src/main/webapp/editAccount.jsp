<%-- 
    Document   : editAccount
    Created on : 11 Oct 2021, 18:20:45
    Author     : jemsann
--%>

<%@page import="Logic.UserManager"%>
<%@page import="UI.UserInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Account</title>
        <%String userId = (String) request.getAttribute("userId");
            UserInfo userToEdit = UserManager.GetUser(Integer.parseInt(userId));
        %>
    </head>
    <body>
        <h1>Edit account <%=userToEdit.getUsername()%></h1>
        <form action ='editAccount' method='POST'>
            <table style='width: 50%'>
                <tr>
                    <td>Id</td>
                    <td><%=userToEdit.getUserID()%></td>
                </tr>

                <tr>
                    <td>Username</td>
                    <td><input type="text" name="username" value="<%=userToEdit.getUsername()%>"/></td>
                </tr>
                <tr>
                    <td>Security Level</td>
                    <td><input type="text" name="secLevel" value="<%=userToEdit.getSecLevel()%>"/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" name="password" value="Enter new password"/></td>
                </tr>
            </table>

            <input type="hidden" name="userId" value=""<%=userToEdit.getUserID()%>/>
            <input type="submit" value="Update"/>

            <button type="button" onclick="history.back()">Cancel</button>

        </form>
    </body>
</html>
