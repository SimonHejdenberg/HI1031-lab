<%-- 
    Document   : jspheader
    Created on : 10 okt. 2021, 18:29:52
    Author     : simon
--%>

<%@page import="Enums.SecurityLevel"%>
<%@page import="UI.UserInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <header>
        <nav class="navbar">
            <a class="active" href="index.jsp"><i class="fa fa-fw fa-home"></i> Home</a>
            <a href="store.jsp"><i class="fa fa-fw fa-search"></i>Store</a>
            <%
                try {
                    UserInfo user = (UserInfo) session.getAttribute("user");
                    if (user != null && (user.getSecLevel() == SecurityLevel.Admin || user.getSecLevel() == SecurityLevel.Staff)) {%>
            <a href="warehouse.jsp"><i class="fa fa-fw fa-envelope"></i>Warehouse</a>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
            <%
                try {
                    UserInfo user = (UserInfo) session.getAttribute("user");
                    if (user != null && (user.getSecLevel() == SecurityLevel.Admin)) {%>
            <a href="accounts.jsp"><i class="fa fa-fw fa-envelope"></i>Accounts</a>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>

            <div>
                <a href="loginmanager.jsp"><i class="fa fa-fw fa-user"></i></a>
                <a href="cart.jsp"><i class="fa fa-shopping-cart" aria-hidden="true"></i>Cart</a>
            </div>
        </nav>
    </header>
</html>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->