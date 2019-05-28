package service;

import bean.FoodBean;
import dao.FoodDao;
import dao.FoodDaoImpl;

import java.util.List;

public class FoodServiceImpl implements FoodService {
    FoodDao foodDao =new FoodDaoImpl();

    public List<FoodBean> fetchFoodList(String shopid){
        List<FoodBean> foodList=null;
        try{
            foodList= foodDao.fetchFoodList(shopid);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return foodList;
    }
    public int deleteFoodById(String id){
        int rtn = 1;
        try{
            rtn = foodDao.deleteFoodById(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rtn;
    }
    public int updateFood(FoodBean bookBean){
        int rtn = 1;
        try{
            rtn = foodDao.updateFood(bookBean);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rtn;
    }
    public int addFood(FoodBean bookBean){
        int rtn = 1;
        try{
           rtn =  foodDao.addFood(bookBean);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rtn;
    }
}
