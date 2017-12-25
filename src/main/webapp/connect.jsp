<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connection to database</title>
</head>
<body>
    <h1>You should connect to database</h1>
    <form method="post" action="/sqlweb/">
                <center>
                <table border="1" width="30%" cellpadding="3">
                    <thead>
                        <tr>
                            <th colspan="2">Login Here</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Database name</td>
                            <td><input type="text" name="database" value="SQLCm" /></td>
                        </tr>
                        <tr>
                            <td>User name</td>
                            <td><input type="text" name="userName" value="postgres" /></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password" name="password" value="postgres" /></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Login" /></td>
                            <td><input type="reset" value="Reset" /></td>
                        </tr>
                    </tbody>
                </table>
                </center>
            </form>
</body>
</html>