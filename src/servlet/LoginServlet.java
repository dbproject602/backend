package servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import bean.UserBean;
import service.*;
import util.codingutil;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("success");
        UserService userservice = new UserServiceImpl();

        UserBean result = null;
        try {
            result = userservice.login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result!=null) {
            System.out.println("visiting database successfully");
            // 传递Object
            byte[] bytes = null;
            Object obj = result;
            bytes = codingutil.objtobytes(obj);
            ServletOutputStream out = response.getOutputStream();
            out.write(bytes);
            out.flush();
            //
        } else {
            System.out.println("false");
            byte[] bytes = null;
            Object obj = null;
            bytes = codingutil.objtobytes(obj);
            ServletOutputStream out = response.getOutputStream();
            out.write(bytes);
            out.flush();
        }
        }
}
