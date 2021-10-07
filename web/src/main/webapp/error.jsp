<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"  language="java"   %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <%if (response.getStatus() == 400) {%>
        <font color="red">Error: <%=exception.getMessage()%></font><br>
        <%@ include file="signup.jsp"%>
        <%} else {%>
        Hi There, error code is <%=response.getStatus()%><br>
        <%}%>
    </body>
</html>
