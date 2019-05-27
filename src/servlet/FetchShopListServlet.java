package servlet;

import bean.ShopBean;
import service.ShopService;
import service.ShopServiceImpl;
import util.codingutil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FetchShopListServlet")
public class FetchShopListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get it");
        request.setCharacterEncoding("UTF-8");

        ShopService shopService = new ShopServiceImpl();
        int shopType = Integer.parseInt(request.getParameter("shoptype"));
        List<ShopBean> shopList = null;
        try {
            shopList = shopService.fetchShopList(shopType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bytes = codingutil.objtobytes(shopList);

        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);
    }
}
