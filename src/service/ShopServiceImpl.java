package service;

import bean.ShopBean;
import dao.*;

import java.util.List;

public class ShopServiceImpl implements ShopService {
    private ShopDao shopDao = new ShopDaoImpl();

    public ShopBean fetchShop(String shopName) throws Exception {
        ShopBean shopBean = null;
        shopBean = shopDao.fetchShop(shopName);
        return shopBean;
    }

    public List<ShopBean> fetchShopList(int shopType) throws Exception {
        return shopDao.fetchShopList(shopType);
    }

    public int registerUser(ShopBean shopBean) throws Exception {
        int result = 0;
        result = shopDao.addShop(shopBean);
        return result;
    }
}
