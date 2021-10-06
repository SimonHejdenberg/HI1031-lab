<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css" type="text/css"/>
    </head>
    <body>
        <div class="header">
            <%@ include  file="html/header.html" %>
        </div>
        <div class="main">
            <form action="user_login" method="post">
                <table style="width: 20%">
                    <tr>
                        <td>UserName</td>
                        <td><input type="text" name="username" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" /></td>
                    </tr>
                    <tr>
                        <td>
                            <a href="signup.jsp"> <button type="button" name="signup">Sign up here</button></a>
                        </td>
                        <td>
                            <input type="submit" value="Login" /> 
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <div class="footer">
            <%@ include  file="html/footer.html" %>
        </div>
    </body>
</html>
