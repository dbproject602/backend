package service;

import bean.ShopBean;

import java.util.List;

public interface ShopService {
    ShopBean fetchShop(String shopName) throws Exception;
    List<ShopBean> fetchShopList(int shopType) throws Exception;
    List<ShopBean> fetchShopList(double longitude, double latitude) throws Exception;
    List<ShopBean> fetchShopList(String findStr) throws Exception;
    int registerUser(ShopBean shopBean) throws Exception;
}
