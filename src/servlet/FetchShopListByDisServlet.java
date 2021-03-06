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

@WebServlet(name = "FetchShopListByDisServlet")
public class FetchShopListByDisServlet extends HttpServlet {
    private ShopService shopService = new ShopServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("get it");
        request.setCharacterEncoding("UTF-8");

        double longitude = Double.parseDouble(request.getParameter("longitude"));
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        List<ShopBean> shopList = null;
        try {
            shopList = shopService.fetchShopList(longitude, latitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bytes = codingutil.objtobytes(shopList);
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
        System.out.println("out put");

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
