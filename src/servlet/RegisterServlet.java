package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import bean.*;
import service.*;


@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("ISO-8859-1");
        String str = request.getParameter("userBean");
        byte[] bs = str.getBytes("ISO-8859-1");
        System.out.println("get it");

        UserBean result = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bs);
            ObjectInputStream ois = new ObjectInputStream (bis);
            result = (UserBean)ois.readObject();
            ois.close();
            bis.close();
            System.out.println(result.getName());
        } catch (Exception e) {
            System.out.println("fail to get");
            e.printStackTrace();
        }
        UserService userservice = new UserServiceImpl();
        int flag = 0;
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

