package service;

import java.util.List;

import bean.DiscountBean;
import dao.DiscountDao;
import dao.DiscountDaoImpl;

public class DiscountServiceImpl implements DiscountService {
    DiscountDao discountDao = new DiscountDaoImpl();

    public List<DiscountBean> fetchShopList(int shopId){
        List<DiscountBean> discountList = null;
        try{
            discountList = discountDao.fetchShopList(shopId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public int deleteDiscountById(int discoutId){
        try{
            discountDao.deleteDiscountById(discoutId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int updateDiscount(DiscountBean discountBean){
        try{
            discountDao.updateDiscount(discountBean);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int addDiscount(DiscountBean discountBean){
        try{
            discountDao.addDiscount(discountBean);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }
}