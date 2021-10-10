<%-- 
    Document   : loginmanager
    Created on : 10 okt. 2021, 16:41:52
    Author     : simon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
    String redirectURL = "";
    if (session.getAttribute("user") != null) {
        redirectURL = "profile.jsp";
    } else {
        redirectURL = "login.jsp";
    }
    response.sendRedirect(redirectURL);
%>
    <title>Login manager</title>
</html>
