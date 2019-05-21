package service;

import bean.BookBean;
import bean.FoodBean;
import dao.FoodDao;
import dao.FoodDaoImpl;

import java.util.List;

public class FoodServiceImpl implements FoodService {
    FoodDao foodDao =new FoodDaoImpl();

    public List<FoodBean> fetchFoodList(int shopid){
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
        return 1;
    }
    public int updateFood(FoodBean bookBean){
        return 1;
    }
    public int addFood(FoodBean bookBean){
        return 1;
    }
    public List<FoodBean> searchFoodList(String bookName,String author){
        return null;
    }
}
