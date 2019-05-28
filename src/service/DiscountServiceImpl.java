package service;

import java.util.List;

import bean.DiscountBean;
import dao.DiscountDao;
import dao.DiscountDaoImpl;

public class DiscountServiceImpl implements DiscountService {
    DiscountDao discountDao = new DiscountDaoImpl();

    public List<DiscountBean> fetchDiscountList(int shopId){
        List<DiscountBean> discountList = null;
        try{
            discountList = discountDao.fetchDiscountList(shopId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return discountList;
    }
    public int deleteDiscountById(int discoutId){
        try{
            discountDao.deleteDiscountById(discoutId);
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public int updateDiscount(DiscountBean discountBean){
        try{
            discountDao.updateDiscount(discountBean);
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public int addDiscount(DiscountBean discountBean){
        try{
            discountDao.addDiscount(discountBean);
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
