package servlet;

import bean.ShopBean;
import service.ShopService;
import service.ShopServiceImpl;
import util.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FetchShopServlet")
public class FetchShopServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get it");
        request.setCharacterEncoding("UTF-8");

        ShopService shopService = new ShopServiceImpl();
        String shopName= request.getParameter("shopname");
        ShopBean shop = null;
        try {
            shop = shopService.fetchShop(shopName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bytes = codingutil.objtobytes(shop);

        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
