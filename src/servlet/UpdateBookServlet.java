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

@WebServlet(name = "UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = -1;
        String bookName="";
        double price= -1;
        String author="";
        try {
            id = Integer.parseInt(request.getParameter("id"));
        }catch (Exception e){
            request.setAttribute("msg", "The id of this user is null");
            return;
        }
        bookName=request.getParameter("bookName");
        author=request.getParameter("author");
        try {
            price = Double.parseDouble(request.getParameter("price"));
            if(price<0){
                request.setAttribute("msg", "price input error1");
                request.getRequestDispatcher("UpdateBook.jsp").forward(request, response);
                return;
            }
        }catch (Exception e){
            System.out.println(request.getParameter("price"));
            if (request.getParameter("price")==null||request.getParameter("price").equals("")){
                price = -1;
            }else {
                request.setAttribute("msg", "price input error2");
                request.getRequestDispatcher("UpdateBook.jsp").forward(request, response);
                return;
            }
        }
        BookService bookService=new BookServiceImpl();
        BookBean bookBean=new BookBean();
        bookBean.setId(id);
        bookBean.setBookName(bookName);
        bookBean.setAuthor(author);
        bookBean.setPrice(price);
        int result=bookService.updateBook(bookBean);
        System.out.println("In servlet"+result);
        if(result==1){
                    request.setAttribute("msg", "update is success");
                    request.getRequestDispatcher("BookServlet").forward(request, response);
        }else{
                    request.setAttribute("msg", "update is failed");
                    request.getRequestDispatcher("UpdateBook.jsp").forward(request, response);
        }
    }
}
