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
    public int deleteFoodById(int id){
        try{
            foodDao.deleteFoodById(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int updateFood(FoodBean bookBean){
        try{
            foodDao.updateFood(bookBean);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int addFood(FoodBean bookBean){
        try{
            foodDao.addFood(bookBean);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }
}
