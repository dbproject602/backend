package dao;

import bean.ShopBean;

import java.util.List;

public interface ShopDao {
    List<ShopBean> fetchShopList(int shopType) throws Exception;
    List<ShopBean> fetchShopList(double longitude, double latitude) throws Exception;
    ShopBean fetchShop(String shopName) throws Exception;
    int deleteShopById(String shopId) throws Exception;
    int updateShop(ShopBean orderBean) throws Exception;
    int addShop(ShopBean orderBean) throws  Exception;
}
