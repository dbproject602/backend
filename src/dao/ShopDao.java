package dao;

import bean.ShopBean;

import java.util.List;

public interface ShopDao {
    List<ShopBean> fetchShopList(int shopType) throws Exception;
    ShopBean fetchShop(String shopName) throws Exception;
    int deleteShopById(int shopId) throws Exception;
    int updateShop(ShopBean orderBean) throws Exception;
    int addShop(ShopBean orderBean) throws  Exception;
}
