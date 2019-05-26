package servlet;

import java.io.IOException;
import java.util.List;

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

@WebServlet(name = "FetchOrderServlet")
public class FetchOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get it");
        request.setCharacterEncoding("UTF-8");
        OrderService orderService=new OrderServiceImpl();
        int userid= Integer.parseInt(request.getParameter("userid"));
        List<OrderBean> orderList = orderService.fetchOrderList(userid);
        byte[] bytes = codingutil.objtobytes(orderList);
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
