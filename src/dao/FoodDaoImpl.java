package dao;

import bean.BookBean;
import bean.FoodBean;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao{
    DBUtil dbutil = new DBUtil();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;
    public List<FoodBean> fetchFoodList(int shopid) throws Exception{
        List<FoodBean> foodBeanList=null;
        connection = dbutil.getConnection();
        String sql="select * from food where shopid=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, shopid);
        resultSet=preparedStatement.executeQuery();
        foodBeanList=new ArrayList<FoodBean>();
        while(resultSet.next()){
            FoodBean foodBean = new FoodBean(
                    resultSet.getString("foodname"),
                    resultSet.getInt("price")
            );
            foodBeanList.add(foodBean);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return foodBeanList;
    }
    public int deleteFoodById(int id) throws Exception{
        return 1;
    }
    public int updateFood(FoodBean bookBean) throws Exception{
        return 1;
    }
    public int addFood(FoodBean bookBean) throws  Exception{
        return 1;
    }
    public List<FoodBean> searchFoodList(String Food,String author) throws Exception{
        List<FoodBean> foodBeanList=null;
        return foodBeanList;
    }
}

