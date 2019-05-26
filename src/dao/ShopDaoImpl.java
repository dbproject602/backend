package dao;

import bean.FoodBean;
import bean.SenderBean;
import bean.ShopBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShopDaoImpl implements ShopDao {
    Connection connection = null;
    DBUtil dbutil = new DBUtil();
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    private ShopBean parseResultSet(ResultSet resultSet) throws Exception{
        int shopId = resultSet.getInt("shopid");

        FoodDao foodDao = new FoodDaoImpl();
        List<FoodBean> foodItems = foodDao.fetchFoodList(shopId);

        List<SenderBean> senderItems = new ArrayList<SenderBean>();
        return new ShopBean(
                resultSet.getInt("shopid"),
                resultSet.getString("shopname"),
                resultSet.getString("password"),
                resultSet.getInt("shoptype"),
                resultSet.getString("telephone"),
                resultSet.getString("address"),
                resultSet.getBoolean("isopen"),
                resultSet.getDouble("reputation"),
                resultSet.getDouble("sendrange"),
                resultSet.getDouble("longitude"),
                resultSet.getDouble("latitude"),
                foodItems,
                senderItems
        );
    }

    public List<ShopBean> fetchShopList(int shopType) throws Exception {
        List<ShopBean> list = new ArrayList<ShopBean>();

        ShopBean result = null;
        connection = dbutil.getConnection();

        String sql = "select * from shops s where shoptype = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, shopType);

        while (resultSet.next()){
            result = parseResultSet(resultSet);
            list.add(result);
        }
        return list;
    }

    public int deleteShopById(int shopId) throws Exception {
        return 0;
    }

    public int updateShop(ShopBean orderBean) throws Exception {
        return 0;
    }

    public int addShop(ShopBean orderBean) throws Exception {
        int result = 0;
        connection = dbutil.getConnection();
        String sql = "insert into shops(shopid, shopname, password, shoptype, telephone, address, " +
                "isopen, reputation, sendrange, longitude, latitude) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, orderBean.getShopId());
        preparedStatement.setString(2, orderBean.getShopName());
        preparedStatement.setString(3, orderBean.getPassword());
        preparedStatement.setInt(4, orderBean.getShopType());
        preparedStatement.setString(5, orderBean.getTelephone());
        preparedStatement.setString(6, orderBean.getAddress());
        preparedStatement.setInt(7, orderBean.isOpen() ? 1 : 0);
        preparedStatement.setDouble(8, orderBean.getReputation());
        preparedStatement.setDouble(9, orderBean.getSendRange());
        preparedStatement.setDouble(10, orderBean.getLongitude());
        preparedStatement.setDouble(11, orderBean.getLatitude());

        result = preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return 0;
    }
}
