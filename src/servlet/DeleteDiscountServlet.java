package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DiscountService;
import service.DiscountServiceImpl;
import util.codingutil;

@WebServlet(name = "DeleteDiscountServlet")
public class DeleteDiscountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int flag = 0;
        String str = request.getParameter("discountId");
        int result = 0;
        try {
            result = (Integer) codingutil.stringtoObj(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DiscountService discountservice = new DiscountServiceImpl();
        try {
            flag = discountservice.deleteDiscountById(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (flag==0) {
            System.out.println("success");
        } else {
            System.out.println("null");
        }
        response.getWriter().print(1);
        response.flushBuffer();

    }
}
