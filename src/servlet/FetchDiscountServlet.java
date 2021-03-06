package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DiscountBean;
import service.DiscountService;
import service.DiscountServiceImpl;
import util.codingutil;

@WebServlet(name = "FetchDiscountServlet")
public class FetchDiscountServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get it");
        request.setCharacterEncoding("UTF-8");
        DiscountService discountService=new DiscountServiceImpl();
        String shopid= request.getParameter("shopid");
        List<DiscountBean> discountList = discountService.fetchDiscountList(shopid);
        byte[] bytes = codingutil.objtobytes(discountList);
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
