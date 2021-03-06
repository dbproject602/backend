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
    private Connection connection = null;
    private DBUtil dbutil = new DBUtil();
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;

    private ShopBean parseResultSet(ResultSet resultSet) throws Exception{
//        String shopId = resultSet.getString("shopid");

        FoodDao foodDao = new FoodDaoImpl();
    //    List<FoodBean> foodItems = foodDao.fetchFoodList(shopId);

        List<SenderBean> senderItems = new ArrayList<SenderBean>();
        return new ShopBean(
                resultSet.getString("shopid"),
                resultSet.getString("shopname"),
//                resultSet.getString("password"),
                "",
                resultSet.getInt("shoptype"),
                resultSet.getString("telephone"),
                resultSet.getString("address"),
                resultSet.getBoolean("isopen"),
                resultSet.getDouble("reputation"),
                resultSet.getDouble("distance"),
//                0,
                resultSet.getDouble("longitude"),
                resultSet.getDouble("latitude"),
                null,
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
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            result = parseResultSet(resultSet);
            list.add(result);
        }

        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return list;
    }

    public List<ShopBean> fetchShopList(double longitude, double latitude) throws Exception {
        List<ShopBean> result = new ArrayList<ShopBean>();
        connection = dbutil.getConnection();

        String sql = "select * from shops s " +
                "where (power((s.longitude - ?) * 2 * 3.14 * 6400 / 360, 2)) + " +
                "(power((s.latitude - ?) * 2 * 3.14 * 6400 / 360, 2)) < 4";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, longitude);
        preparedStatement.setDouble(2, latitude);
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            result.add(parseResultSet(resultSet));
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }

    public List<ShopBean> fetchShopList(String findStr) throws Exception {
        List<ShopBean> result = new ArrayList<ShopBean>();
        connection = dbutil.getConnection();

        String sql = "select * from shops s where s.shopname like ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%%"+findStr+"%%");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            result.add(parseResultSet(resultSet));
        }

        return result;
    }

    public ShopBean fetchShop(String shopName) throws Exception{
        ShopBean result = null;
        connection = dbutil.getConnection();

        String sql = "select * from shops s where shopname = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, shopName);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            result = parseResultSet(resultSet);
        }

        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }

    public int deleteShopById(String shopId) throws Exception {
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
        preparedStatement.setString(1, orderBean.getShopId());
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
        return result;
    }
}
