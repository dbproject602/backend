package service;

import java.util.List;

import bean.DiscountBean;
import dao.DiscountDao;
import dao.DiscountDaoImpl;

public class DiscountServiceImpl implements DiscountService {
    DiscountDao discountDao = new DiscountDaoImpl();

    public List<DiscountBean> fetchDiscountList(String shopId){
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
        int rtn = 1;
        try{
            rtn = discountDao.deleteDiscountById(discoutId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rtn;
    }
    public int updateDiscount(DiscountBean discountBean){
        int rtn = 1;
        try{
            rtn = discountDao.updateDiscount(discountBean);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rtn;
    }
    public int addDiscount(DiscountBean discountBean){
        int rtn = 1;
        try{
            rtn = discountDao.addDiscount(discountBean);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rtn;
    }
}
