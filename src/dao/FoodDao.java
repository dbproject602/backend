package dao;

import bean.FoodBean;

import java.util.List;

public interface FoodDao {
    List<FoodBean> fetchFoodList(int shopid) throws Exception;
    int deleteFoodById(int foodId) throws Exception;
    int updateFood(FoodBean foodBean) throws Exception;
    int addFood(FoodBean foodBean) throws  Exception;
}
