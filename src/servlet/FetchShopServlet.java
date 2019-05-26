package servlet;

import bean.ShopBean;
import service.ShopService;
import service.ShopServiceImpl;
import util.*;

import bean.OrderBean;
import service.OrderService;
import service.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FetchShopServlet")
public class FetchShopServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("get it");
        request.setCharacterEncoding("UTF-8");

        ShopService shopService = new ShopServiceImpl();
        String shopName= request.getParameter("shopname");
        ShopBean shop = shopService.fetchShop(shopName);
        byte[] bytes = codingutil.objtobytes(shop);

        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response);
    }
}
