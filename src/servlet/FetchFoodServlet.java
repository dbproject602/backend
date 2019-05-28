package servlet;

import bean.FoodBean;
import service.FoodService;
import service.FoodServiceImpl;
import util.codingutil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FetchFoodServlet")
public class FetchFoodServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        FoodService foodService=new FoodServiceImpl();

        String shopid= (request.getParameter("shopid"));
        System.out.println("get fetchshopId:"+shopid);
        List<FoodBean> foodList = foodService.fetchFoodList(shopid);
        System.out.println("get fetchFood:"+foodList.size());
        byte[] bytes = codingutil.objtobytes(foodList);
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
