package dao;

import bean.DiscountBean;

import java.util.List;

public interface DiscountDao {
    List<DiscountBean> fetchDiscountList(String shopId) throws Exception;
    int deleteDiscountById(int discoutId) throws Exception;
    int updateDiscount(DiscountBean discountBean) throws Exception;
    int addDiscount(DiscountBean discountBean) throws  Exception;
}
