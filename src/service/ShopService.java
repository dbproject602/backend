package service;

import bean.SenderBean;
import bean.ShopBean;

import java.util.List;

public interface ShopService {
    ShopBean login(String shopName, String password) throws Exception;
    List<ShopBean> fetchShopList(int shopType) throws Exception;
    int registerUser(ShopBean shopBean) throws Exception;
}
