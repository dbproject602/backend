package servlet;

import bean.ShopBean;
import service.ShopService;
import service.ShopServiceImpl;
import util.codingutil;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "FetchShopListServlet")
public class FetchShopListServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("get it");
        request.setCharacterEncoding("UTF-8");

        ShopService shopService = new ShopServiceImpl();
        int shopType = Integer.parseInt(request.getParameter("shoptype"));
        List<ShopBean> shopList = shopService.fetchShopList(shopType);
        byte[] bytes = codingutil.objtobytes(shopList);

        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response);
    }
}
