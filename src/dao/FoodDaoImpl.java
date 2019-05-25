package dao;

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
        String sql="select * from food where shopid=?"; //
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, shopid); //将sql段第一个？代替
        resultSet=preparedStatement.executeQuery();
        foodBeanList=new ArrayList<FoodBean>();
        while(resultSet.next()){
            FoodBean foodBean = new FoodBean(
                    resultSet.getInt("foodid"),
                    resultSet.getString("foodname"),
                    resultSet.getInt("shopid"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("remaining")
            );
            foodBeanList.add(foodBean);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return foodBeanList;
    }

    public FoodBean getFoodById(int foodid) throws Exception{
        connection = dbutil.getConnection();
        String sql = "select * from food where foodid=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,foodid);
        resultSet=preparedStatement.executeQuery();
        FoodBean food = new FoodBean(
                resultSet.getInt("foodid"),
                resultSet.getString("foodname"),
                resultSet.getInt("shopid"),
                resultSet.getDouble("price"),
                resultSet.getInt("remaining")
        );
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return food;
    }


    public int deleteFoodById( int foodId) throws Exception{
        connection = dbutil.getConnection();
        String sql = "delete from food where foodid=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, foodId);
        resultSet=preparedStatement.executeQuery();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return 1;
    }

    public int updateFood(FoodBean bookBean) throws Exception{
        connection = dbutil.getConnection();
        String sql = "update food set foodname = ?, shopid = ?, price = ?, remaining = ? where foodid=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, bookBean.getFoodName());
        preparedStatement.setInt(2, bookBean.getShopId());
        preparedStatement.setDouble(3, bookBean.getPrice());
        preparedStatement.setInt(4, bookBean.getRemaining());
        preparedStatement.setInt(5, bookBean.getFoodId());
        resultSet=preparedStatement.executeQuery();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return 1;
    }

    public int addFood(FoodBean bookBean) throws  Exception{
        connection = dbutil.getConnection();
        String sql = "insert into food (foodid, foodname, shopid, price, remaining) values(?,?,?,?,?)";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,bookBean.getFoodId());
        preparedStatement.setString(2,bookBean.getFoodName());
        preparedStatement.setInt(3,bookBean.getShopId());
        preparedStatement.setDouble(4,bookBean.getPrice());
        preparedStatement.setInt(5,bookBean.getRemaining());
        resultSet=preparedStatement.executeQuery();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return 1;
    }

}

