<%--
  Created by IntelliJ IDEA.
  User: cly
  Date: 2018/10/28
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateBook</title>
</head>
<body>
</br>
<form action="UpdateBookServlet" method="post">
    id:<input type="text" name="id"
              value="<%=request.getParameter("id")%>" readonly="true"
    <%=request.getAttribute("msg_id") != null ? request.getAttribute("msg_id") : ""%>/>
    <br/>
    bookName:<input type="text" name="bookName"
                    value="<%=request.getParameter("bookName")%>"
                    value="<%=request.getAttribute("bookName") != null ?request.getAttribute("bookName") : ""%>" />
    <%=request.getAttribute("msg_bookName") != null ? request.getAttribute("msg_bookName") : ""%>
    <br />
    author:<input type="text" name="author"
                  value="<%=request.getParameter("author")%>"
                    value="<%=request.getAttribute("author") != null ?request.getAttribute("author") : ""%>" />
    <%=request.getAttribute("msg_author") != null ? request.getAttribute("msg_author") :""%>
    <br/>
    price:<input type="number" step="0.0001" name="price"
                 value="<%=request.getParameter("price")%>"
                            value="<%=request.getAttribute("price") != null ?request.getAttribute("price") : ""%>" />
    <%=request.getAttribute("msg_price") != null ?request.getAttribute("msg_price") : ""%>
    <br />
    <input type="submit" value="Update" />
</form>
<br />
<a href="BookServlet">cancel</a>
<%=request.getAttribute("msg") != null ? request.getAttribute("msg") : ""%>
</body>
</html>
