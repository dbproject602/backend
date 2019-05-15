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

@WebServlet(name = "AddBookServlet")
public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {{
        request.setCharacterEncoding("UTF-8");
        String bookName="";
        double price= -1;
        String author="";
        bookName=request.getParameter("bookName");
        author=request.getParameter("author");
        try {
            price = Double.parseDouble(request.getParameter("price"));
            if(price<0){
                request.setAttribute("msg", "price input error");
                request.getRequestDispatcher("AddBook.jsp").forward(request, response);
                return;
            }
        }catch (Exception e){
            if (request.getParameter("price")==null||request.getParameter("price").equals("")){
                price = -1;
            }else {
                request.setAttribute("msg", "price input error");
                request.getRequestDispatcher("AddBook.jsp").forward(request, response);
                return;
            }
        }
        if ((bookName == null || bookName.equals(""))) {
            request.setAttribute("msg_bookName", "bookname shouldn't be none");
            request.getRequestDispatcher("AddBook.jsp").forward(request, response);
        } else if (author == null || author.equals("")) {
            request.setAttribute("msg_author", "author shouldn't be none");
            request.getRequestDispatcher("AddBook.jsp").forward(request, response);
        } else if (price == -1) {
            request.setAttribute("msg_price", "price input error");
            request.getRequestDispatcher("AddBook.jsp").forward(request, response);
        } else {
            BookService bookService = new BookServiceImpl();
            BookBean bookBean = new BookBean();
            bookBean.setBookName(bookName);
            bookBean.setAuthor(author);
            bookBean.setPrice(price);
            int result = bookService.addBook(bookBean);
            if (result == 1) {
                request.setAttribute("msg", "AddBook is success");
                request.getRequestDispatcher("BookServlet").forward(request, response);
            } else {
                request.setAttribute("msg", "AddBook is failed");
                request.getRequestDispatcher("AddBook.jsp").forward(request, response);
            }
        }
    }

    }
}
