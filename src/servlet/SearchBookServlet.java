package servlet;

import bean.BookBean;
import service.BookService;
import service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchBookServlet")
public class SearchBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService=new BookServiceImpl();
        String bookName = request.getParameter("bookName");
        String author = request.getParameter("author");
        List<BookBean> bookList=bookService.searchBookList(bookName,author);
        request.setAttribute("bookList", bookList);
        request.getRequestDispatcher("BookList.jsp").forward(request, response);
    }
}
