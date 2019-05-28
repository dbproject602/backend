package service;

import java.util.List;

import bean.DiscountBean;

public interface DiscountService {
    List<DiscountBean> fetchDiscountList(String shopId);
    int deleteDiscountById(int discoutId);
    int updateDiscount(DiscountBean discountBean);
    int addDiscount(DiscountBean discountBean);
}
