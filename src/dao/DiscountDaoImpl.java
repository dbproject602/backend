package dao;

import bean.DiscountBean;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountDaoImpl implements DiscountDao {
    DBUtil dbutil = new DBUtil();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;
    public List<DiscountBean> fetchDiscountList(String shopId) throws Exception{
        List<DiscountBean> discountBeanList=null;
        connection = dbutil.getConnection();
        String sql="select * from discount where shopid=?"; //
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, shopId); //将sql段第一个？代替
        resultSet=preparedStatement.executeQuery();
        discountBeanList=new ArrayList<DiscountBean>();
        while(resultSet.next()){
            DiscountBean foodBean = new DiscountBean(
                    resultSet.getInt("discountid"),
                    resultSet.getString("shopid"),
                    resultSet.getString("foodid"),
                    resultSet.getString("discountname"),
                    resultSet.getDate("starttime"),
                    resultSet.getDate("endtime"),
                    resultSet.getDouble("discountratio")
            );
            discountBeanList.add(foodBean);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return discountBeanList;
    }
    public int deleteDiscountById(int discountId) throws Exception{
        connection = dbutil.getConnection();
        String sql = "delete from discount where discountid=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, discountId);
        int rtn =preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        if(rtn==0) rtn=1; else rtn=0;
        return rtn;
    }
    public int updateDiscount(DiscountBean bookBean) throws Exception{
        connection = dbutil.getConnection();
        String sql = "update food set shopid = ?, foodid = ?, discountname = ?, starttime = ?, endtime = ?, discountratio where discountid=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, bookBean.getShopId());
        preparedStatement.setString(2, bookBean.getFoodId());
        preparedStatement.setString(3, bookBean.getDiscountName());
        preparedStatement.setDate(4, bookBean.getStartTime());
        preparedStatement.setDate(5, bookBean.getEndTime());
        preparedStatement.setDouble(6,bookBean.getDiscountRatio());
        preparedStatement.setInt(7,bookBean.getDiscountId());
        int rtn =preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        if(rtn==0) rtn=1; else rtn=0;
        return rtn;
    }
    public int addDiscount(DiscountBean bookBean) throws  Exception{
        connection = dbutil.getConnection();
        String sql = "insert into food (shopid, foodid, discountname, starttime, endtime, discountratio) values(?,?,?,?,?,?)";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, bookBean.getShopId());
        preparedStatement.setString(2, bookBean.getFoodId());
        preparedStatement.setString(3, bookBean.getDiscountName());
        preparedStatement.setDate(4, bookBean.getStartTime());
        preparedStatement.setDate(5, bookBean.getEndTime());
        preparedStatement.setDouble(6,bookBean.getDiscountRatio());
        int rtn =preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        if(rtn==0) rtn=1; else rtn=0;
        return rtn;
    }
}
