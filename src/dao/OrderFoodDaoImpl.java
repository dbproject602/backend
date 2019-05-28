package dao;

import bean.FoodBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class OrderFoodDaoImpl implements OrderFoodDao{
    private Connection connection = null;
    private DBUtil dbutil = new DBUtil();
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;

    @Override
    public int addOrderFood(int orderId, List<FoodBean> foodItems) throws Exception {
        int result = 0;
        connection = dbutil.getConnection();
        System.out.println("Start to add order_food in dao!");
        for (FoodBean foodBean : foodItems) {
            String sql = "insert into food(orderid, foodid) values (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setString(2, foodBean.getFoodId());
            result = preparedStatement.executeUpdate();
        }

        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        System.out.println("Finish adding order_food in dao!");
        return result;
    }

    @Override
    public int updateOrderFood(int orderId, List<FoodBean> foodItems) throws Exception {
        return 0;
    }

    @Override
    public int deleteOrderFood(int orderId) throws Exception {
        int result = 0;

        connection = dbutil.getConnection();
        System.out.println("Start to delete order_food in dao!");
        String sql = "delete from food where orderid=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, orderId);
        result = preparedStatement.executeUpdate();

        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        System.out.println("Finish deleting order_food in dao!");
        return result;
    }
}
