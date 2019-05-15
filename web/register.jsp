<%--
  Created by IntelliJ IDEA.
  User: cly
  Date: 2018/10/27
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
</br>
<form action="RegisterServlet" method="post">
    username:<input type="text" name="username"
                    value="<%=request.getAttribute("username") != null ?request.getAttribute("username") : ""%>" />
        <%=request.getAttribute("msg_username") != null ? request.getAttribute("msg_username") : ""%>
    <br />
    password:<input type="password" name="password"
                  value="<%=request.getAttribute("password") != null ?request.getAttribute("password") : ""%>" />
    <%=request.getAttribute("msg_password") != null ? request.getAttribute("msg_password") :""%><br/>
    confirm password:<input type="password" name="con_password"
                            value="<%=request.getAttribute("con_password") != null ?request.getAttribute("con_password") : ""%>" />
    <%=request.getAttribute("msg_con_password") != null ?request.getAttribute("msg_con_password") : ""%><br />
    <input type="submit" value="Register" />
</form>
<br/>
<a href="login.jsp">cancel</a>
<br/>
<a href="login.jsp"> </a>
<%=request.getAttribute("msg") != null ? request.getAttribute("msg") : ""%>
</body>
</html>
