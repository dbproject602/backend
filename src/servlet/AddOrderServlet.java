package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderBean;
import service.OrderService;
import service.OrderServiceImpl;
import util.codingutil;

@WebServlet(name = "AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int flag = 0;
        String str = request.getParameter("orderbean");
        OrderBean result = null;
        try {
            result = (OrderBean) codingutil.stringtoObj(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OrderService orderservice = new OrderServiceImpl();
        try {
            flag = orderservice.addOrder(result);
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
