package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.FoodBean;
import service.FoodService;
import service.FoodServiceImpl;
import util.codingutil;

@WebServlet(name = "UpdateFoodServlet")
public class UpdateFoodServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int flag = 0;
        String str = request.getParameter("foodbean");
        FoodBean result = null;
        try {
            result = (FoodBean) codingutil.stringtoObj(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FoodService foodservice = new FoodServiceImpl();
        try {
            flag = foodservice.updateFood(result);
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
