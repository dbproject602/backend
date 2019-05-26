package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import bean.*;
import service.*;
import util.codingutil;


@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int flag = 0;
        String str = request.getParameter("userBean");
        UserBean result = null;
        try {
            result = (UserBean) codingutil.stringtoObj(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserService userservice = new UserServiceImpl();
        try {
            flag = userservice.registerUser(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (flag==0) {
            System.out.println("false");
            ServletOutputStream out = response.getOutputStream();
            out.write(flag);
            out.flush();
        } else {
            System.out.println("success");
            ServletOutputStream out = response.getOutputStream();
            out.write(flag);
            out.flush();
        }

    }

    }

