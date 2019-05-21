package servlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import bean.UserinfoBean;
import service.*;
import util.ObjToBytes;

@javax.servlet.annotation.WebServlet(name = "LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        System.out.println("success");
        UserinfoService userinfoservice = new UserinfoServiceImpl();

        UserinfoBean result = null;
        result = userinfoservice.login(account, password);
        if (result.getId() == 1) {
            System.out.println("visiting database successfully");
         //   request.getRequestDispatcher("BookServlet").forward(request, response);
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
            Object obj = 1;
            bytes = ObjToBytes.objtobytes(obj);
            ServletOutputStream out = response.getOutputStream();
            out.write(bytes);
            out.flush();
        }
        }
}
