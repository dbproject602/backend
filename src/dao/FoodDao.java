package dao;

import bean.BookBean;
import bean.FoodBean;

import java.util.List;

public interface FoodDao {
    List<FoodBean> fetchFoodList(int shopid) throws Exception;
    int deleteFoodById(int id) throws Exception;
    int updateFood(FoodBean foodBean) throws Exception;
    int addFood(FoodBean foodBean) throws  Exception;
    List<FoodBean> searchFoodList(String Food,String author) throws Exception;
}
