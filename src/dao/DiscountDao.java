package dao;

import bean.DiscountBean;

import java.util.List;

public interface DiscountDao {
    List<DiscountBean> fetchShopList(int shopId) throws Exception;
    int deleteDiscountById(int discoutId) throws Exception;
    int updateDiscount(DiscountBean discountBean) throws Exception;
    int addDiscout(DiscountBean discountBean) throws  Exception;
}