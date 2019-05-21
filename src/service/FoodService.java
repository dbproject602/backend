package service;


import bean.FoodBean;

import java.util.List;

public interface FoodService {
    List<FoodBean> fetchFoodList(int shopid);
    int deleteFoodById(int id);
    int updateFood(FoodBean bookBean);
    int addFood(FoodBean bookBean);
    List<FoodBean> searchFoodList(String bookName,String author);
}
