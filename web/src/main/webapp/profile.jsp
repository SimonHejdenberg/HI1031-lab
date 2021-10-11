<%-- 
    Document   : profile
    Created on : 10 okt. 2021, 15:42:46
    Author     : simon
--%>

<%@page import="UI.UserInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%String username = "";
        if (session.getAttribute("user") != null) {
            UserInfo user = (UserInfo) session.getAttribute("user");
            username = user.getUsername();
        }%>
        <title>Profile</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css" type="text/css"/>
    </head>
    <body>
        <div class="header">
            <%@ include  file="jspheader.jsp" %>
        </div>
        
        <div class="main">
            <h1>My profile</h1>
            <form action="user_edit" method="post">
                <table style="width: 20%">
                    <tr>
                        <td>Username: </td>
                        <td><p><%=username%></p></td>
                    </tr>
                    <tr>
                        <td>Change email</td>
                        <td><input type="text" name="new_email" /></td>
                        <td><button type="submit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button></td>
                    </tr>
                    <tr>
                        <td>Change password</td>
                        <td><input type="password" name="new_password" /></td>
                        <td><button type="submit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button></td>
                    </tr>
                </table>
                <span class="error" style="color:red">${messages.userID}</span>
                <%--<input type="submit" value="Save" />--%>
                <input type="submit" value="Log out" />
            </form>
        </div>
        <div class="footer">
            <%@ include  file="jspfooter.jsp" %>
        </div>
    </body>
</html>
