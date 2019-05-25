package servlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import bean.UserBean;
import service.*;
import util.ObjToBytes;

@javax.servlet.annotation.WebServlet(name = "LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("account");
        String password = request.getParameter("password");

        System.out.println("success");
        UserService userservice = new UserServiceImpl();

        UserBean result = null;
        result = userservice.login(username, password);
        if (result!=null) {
            System.out.println("visiting database successfully");
            // 传递Object
            byte[] bytes = null;
            Object obj = result;
            bytes = ObjToBytes.objtobytes(obj);
            ServletOutputStream out = response.getOutputStream();
            out.write(bytes);
            out.flush();
            //
        } else {
            System.out.println("false");
            byte[] bytes = null;
            Object obj = null;
            bytes = ObjToBytes.objtobytes(obj);
            ServletOutputStream out = response.getOutputStream();
            out.write(bytes);
            out.flush();
        }
        }
}
