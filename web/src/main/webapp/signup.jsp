<!DOCTYPE html>
<html>
    <head>
        <title>Sign up</title>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css" type="text/css"/>
    </head>
    <body>
        <div class="header">
            <%@ include  file="jspheader.jsp" %>
        </div>


        <div class="main">
            <h1>User Register Form</h1>
            <form action="user_register" method="post">
                <table style="width: 20%">
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="first_name" /></td>
                        <td>  </td>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td><input type="text" name="last_name" /></td>
                    </tr>
                    <tr>
                        <td>UserName</td>
                        <td><input type="text" name="username" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" /></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" /></td>
                    </tr>
                </table>
                <span class="error" style="color:red">${messages.userID}</span>
                <input type="submit" value="Submit" /></form>
        </div>

        <div class="footer">
            <%@ include  file="jspfooter.jsp" %>
        </div>
    </body>
</html>
