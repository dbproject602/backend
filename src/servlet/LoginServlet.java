package servlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import service.*;
@javax.servlet.annotation.WebServlet(name = "LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if ((username == null || username.equals("")) && (password == null || password.equals(""))) {
            request.setAttribute("msg_username", "user name shouldn't be none");
            request.setAttribute("msg_password", "password shouldn't be none");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (password == null || password.equals("")) {
            request.setAttribute("username", username);
            request.setAttribute("msg_password", "password shouldn't be none");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (username == null || username.equals("")) {
            request.setAttribute("password", password);
            request.setAttribute("msg_username", "user name shouldn't be none");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            System.out.println("success");
            UserinfoService userinfoservice = new UserinfoServiceImpl();

            int result = 0;
            result = userinfoservice.login(username, password);
            if (result == 1) {
                System.out.println("visiting database successfully");
             //   request.getRequestDispatcher("BookServlet").forward(request, response);
                // 传递Object
                byte[] bytes = null;
                Object obj = true;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(obj); //
                    oos.flush();
                    bytes = bos.toByteArray ();
                    oos.close();
                    bos.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ServletOutputStream out = response.getOutputStream();
                out.write(bytes);
                out.flush();
                //
            } else {
                request.setAttribute("msg", "the username or password is wrong");
                request.getRequestDispatcher("login.jsp").forward(request, response); }
        }
    }
}
