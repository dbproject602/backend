package dao;

import bean.FoodBean;
import bean.OrderBean;
import util.DBUtil;
import java.util.Arrays;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    DBUtil dbutil = new DBUtil();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;
    public List<OrderBean> fetchOrderList(int userid) throws Exception{
        List<OrderBean> orderBeanList=null;
        connection = dbutil.getConnection();
        String sql="select * from order where userid=?"; //
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, userid); //将sql段第一个？代替
        resultSet=preparedStatement.executeQuery();
        orderBeanList=new ArrayList<OrderBean>();
        FoodDao food = new FoodDaoImpl();
        while(resultSet.next()){
            int a = resultSet.getInt("orderid");
            int b = resultSet.getInt("userid");
            String c = resultSet.getString("shopid");
            int d = resultSet.getInt("senderid");
            Date e = resultSet.getDate("starttime");
            Date f = resultSet.getDate("endtime");
            String g =  resultSet.getString("fooditems");
            int h = resultSet.getInt("state");
            ArrayList<FoodBean> list = new ArrayList<FoodBean>();
            List<String> foodlist = Arrays.asList(g.split(","));
            for(String s: foodlist){
                int id = 0;
                for(int i=0; i<s.length(); i++){
                    id = id*10+s.charAt(i)-'0';
                }
                list.add(food.getFoodById(id));
            }
            OrderBean foodBean = new OrderBean(a,b,c,d,e,f,list,h);
            orderBeanList.add(foodBean);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return orderBeanList;
    }

    public int deleteOrderById(int orderId) throws Exception{
        connection = dbutil.getConnection();
        String sql = "delete from order where orderid=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, orderId);
        resultSet=preparedStatement.executeQuery();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return 1;
    }
    public int updateOrder(OrderBean bookBean) throws Exception{
        connection = dbutil.getConnection();
        Date endtime = new Date(System.currentTimeMillis());//设置endtime
        SenderDao senderdao = new SenderDaoImpl();
        senderdao.recoverSenderById(bookBean.getSenderId());//恢复sender状态

        String sql = "update order set userid=?, shopid=?, senderid=?, starttime=?, endtime=?, fooditems=?, state=? where orderid=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,bookBean.getUserId());
        preparedStatement.setString(2,bookBean.getShopId());
        preparedStatement.setInt(3,bookBean.getSenderId());
        preparedStatement.setDate(4,bookBean.getStartTime());
        preparedStatement.setDate(5,endtime);

        String list = "";
        List<FoodBean> foodlist = bookBean.getFoodItems();
        for(FoodBean food: foodlist){
            list+=food.getFoodId()+",";
        }
        list = list.substring(0,list.length()-1);

        preparedStatement.setString(6,list);
        preparedStatement.setInt(7,2);
        resultSet=preparedStatement.executeQuery();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return 1;
    }
    public int addOrder(OrderBean bookBean) throws  Exception{
        SenderDao senderdao = new SenderDaoImpl();
        int senderid = senderdao.fetchAvailSenderId();
        if(senderid==0){
            return 0;
        }
        Date starttime = new Date(System.currentTimeMillis());

        String sql = "insert into order (userid, shopid, senderid, starttime, fooditems, state) values(?,?,?,?,?,?)";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,bookBean.getUserId());
        preparedStatement.setString(2,bookBean.getShopId());
        preparedStatement.setInt(3,bookBean.getSenderId());
        preparedStatement.setDate(4,bookBean.getStartTime());
        preparedStatement.setDate(5,bookBean.getEndTime());

        String list = "";
        List<FoodBean> foodlist = bookBean.getFoodItems();
        for(FoodBean food: foodlist){
            list+=food.getFoodId()+",";
        }
        list = list.substring(0,list.length()-1);

        preparedStatement.setString(6,list);
        preparedStatement.setString(7,bookBean.getState());
        resultSet=preparedStatement.executeQuery();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return 1;
    }
}
