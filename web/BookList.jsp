<%--
  Created by IntelliJ IDEA.
  User: cly
  Date: 2018/10/27
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="bean.BookBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookList</title>
</head>
<body>
<%List<BookBean> bookBeanList = (List<BookBean>) request.getAttribute("bookList"); %>
<table>
    <caption id="a"><h2>BookList</h2>
    </caption>
    <tr>
        <th>BookID</th>
        <th>BookName</th>
        <th>Author</th>
        <th>Price</th>
        <th>Adding Date</th>
        <th>Operation</th>
    </tr>
<% if (bookBeanList != null && bookBeanList.size() > 0) {
    for (int i=0;i<bookBeanList.size();i++) {
        %>
    <tr>
        <td>
            <%=i+1%>
        </td>
        <td>
            <%=bookBeanList.get(i).getBookName()%>
        </td>
        <td>
            <%=bookBeanList.get(i).getAuthor()%>
        </td>
        <td>
            <%=bookBeanList.get(i).getPrice() %>
        </td>
        <td>
            <%=bookBeanList.get(i).getAddingDate() %>
        </td>
        <td>
            <a href="DeleteBookServlet?id=<%=bookBeanList.get(i).getId()%>">delete</a>
        </td>
        <td>
            <a href="UpdateBook.jsp?bookName=<%=bookBeanList.get(i).getBookName()%>&id=<%=bookBeanList.get(i).getId()%>&author=<%=bookBeanList.get(i).getAuthor()%>&price=<%=bookBeanList.get(i).getPrice()%>">update</a>
        </td>
    </tr>
<%
    }
}else {
%>
    <tr>
        <td colspan="6">can not get the book infomation</td>
    </tr> <%
}
%>
</table>
<form action="SearchBookServlet" method="post">
    <caption id="b"><h2>SearchBook</h2>
    bookName: <input type="text" name="bookName"
                     value="<%=request.getAttribute("bookName") != null ? request.getAttribute("bookName") : ""%>"/>
    <%=request.getAttribute("msg_bookName") != null ? request.getAttribute("msg_bookName") : ""%>
    <br/>
    author: <input type="text" name="author"
                   value="<%=request.getAttribute("author") != null?request.getAttribute("author") : ""%>"/>
    <%=request.getAttribute("msg_author") != null ? request.getAttribute("msg_author") :""%>
    <br />
    <input type="submit" value="search" />
    <br />
</form>
<br/>
<a href="AddBook.jsp">AddBook</a>
<br/>
<%=request.getAttribute("msg")!=null?request.getAttribute("msg"):""%>
</body>
</html>
