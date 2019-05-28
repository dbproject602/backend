package servlet;

import bean.OrderBean;
import bean.UserBean;
import service.OrderService;
import service.OrderServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import util.codingutil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {

    private UserService userservice = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int flag = 0;
        String str = request.getParameter("userBean");  //获取userBean
        UserBean result = null;
        try {
            result = (UserBean) codingutil.stringtoObj(str); //反序列化其转成UserBean
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            flag = userservice.updateUser(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(flag!=0){
            System.out.println("Update user successfully in servlet!");
        }else{
            System.out.println("Fail to update user in servlet!");
        }
        response.getWriter().print(1);
        response.flushBuffer();


    }

}
