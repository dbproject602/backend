package service;


import bean.FoodBean;

import java.util.List;

public interface FoodService {
    List<FoodBean> fetchFoodList(String shopid);
    int deleteFoodById(int id);
    int updateFood(FoodBean bookBean);
    int addFood(FoodBean bookBean);
}
