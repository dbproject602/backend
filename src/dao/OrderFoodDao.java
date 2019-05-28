package dao;

import bean.FoodBean;
import bean.SenderBean;

import java.util.List;

public interface OrderFoodDao {
    int addOrderFood(int orderId, List<FoodBean> foodItems) throws Exception;
    int updateOrderFood(int orderId, List<FoodBean> foodItems) throws Exception;
    int deleteOrderFood(int orderId) throws Exception;
}
