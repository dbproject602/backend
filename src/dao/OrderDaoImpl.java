package dao;

import bean.FoodBean;
import bean.OrderBean;
import util.DBUtil;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderDaoImpl implements OrderDao {
    static int counter = 1;
    DBUtil dbutil = new DBUtil();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;
    public List<OrderBean> fetchOrderList(int userid) throws Exception{
        List<OrderBean> orderBeanList=null;
        connection = dbutil.getConnection();
        String sql="select * " +
                "from orders o " +
                "join sender s on s.senderid = o.senderid " +
                "join shop sh on sh.shopid = o.shopid  where userid=?"; //
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, userid); //将sql段第一个？代替
        resultSet=preparedStatement.executeQuery();

        orderBeanList=new ArrayList<OrderBean>();
        FoodDao food = new FoodDaoImpl();
        ShopDao shop = new ShopDaoImpl();
        SenderDao sender = new SenderDaoImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<FoodBean> list = new ArrayList<FoodBean>();

        while(resultSet.next()){
            int a = resultSet.getInt("orderid");
            int b = resultSet.getInt("userid");
            String c = resultSet.getString("shopid");
            int d = resultSet.getInt("senderid");
            String e = resultSet.getString("starttime");
            String f = resultSet.getString("endtime");
            int h = resultSet.getInt("state");
            String sendername = resultSet.getString("sendername");
            String senderpwd = resultSet.getString("password");
            String shopname = resultSet.getString("shopname");
            Date starttime = new Date(sdf.parse(e).getTime());
            Date endtime = new Date(sdf.parse(f).getTime());

            OrderBean foodBean = new OrderBean(a,b,c,d,starttime,endtime,list,h, shop.fetchShop(shopname),sender.fetchSender(sendername, senderpwd));
            orderBeanList.add(foodBean);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);

        for(OrderBean orderbean: orderBeanList){
            connection = dbutil.getConnection();
            sql = "select * from orders_food where orderid=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,orderbean.getOrderId());
            resultSet=preparedStatement.executeQuery();
            list = new ArrayList<FoodBean>();
            while(resultSet.next()){
                list.add(food.getFoodById(resultSet.getString("foodid")));
            }
            orderbean.setFoodItems(list);
        }

        System.out.println("in orderDao: 订单长度:"+orderBeanList.size());
        return orderBeanList;
    }

    public int deleteOrderById(int orderId) throws Exception{
        connection = dbutil.getConnection();
        String sql = "delete from orders where orderid=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, orderId);
        int rtn = preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        if(rtn==0) rtn=1; else rtn=0;
        return rtn;
    }
    public int updateOrder(OrderBean orderBean) throws Exception{
        connection = dbutil.getConnection();
        Date endtime = new Date(System.currentTimeMillis());//设置endtime
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SenderDao senderdao = new SenderDaoImpl();
        senderdao.recoverSenderById(orderBean.getSenderId());//恢复sender状态

        String sql = "update orders set endtime=?, state=? where orderid=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,sdf.format(endtime));
        preparedStatement.setInt(2,2);
        int rtn = preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        if(rtn==0) rtn=1; else rtn=0;
        return rtn;
    }
    public int addOrder(OrderBean orderBean) throws  Exception{
        connection = dbutil.getConnection();

        SenderDao senderdao = new SenderDaoImpl();
        OrderFoodDao orderfood = new OrderFoodDaoImpl();
        List<FoodBean> foodlist = orderBean.getFoodItems();

        //get available sender
        int senderid = senderdao.fetchAvailSenderId();
        if(senderid==0)
            return 1;

        //check balance
        if(checkBalance(orderBean.getUserId(), foodlist)==1)
            return 1;

        //get current time
        Date starttime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        String sql = "insert into orders (orderid, userid, shopid, senderid, starttime, state) values(?,?,?,?,?,?)";
       // System.out.println("insert sender sql:"+sql);
        long t = System.currentTimeMillis();
        Random rand =new Random(t);
        counter = (int)(1+rand.nextInt(1000000-1+1));
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,counter);
        preparedStatement.setInt(2,orderBean.getUserId());
        System.out.println("shopid "+orderBean.getShopId());
        preparedStatement.setString(3,orderBean.getShopId());
        preparedStatement.setInt(4,senderid);
        preparedStatement.setString(5,sdf.format(starttime));

        //get string food list
        String list = "";
        for(FoodBean food: foodlist){
            list+=food.getFoodId()+",";
        }
        list = list.substring(0,list.length()-1);
        System.out.println("插入order, userid:"+orderBean.getUserId());

        preparedStatement.setInt(6,0);
        int rtn = preparedStatement.executeUpdate();

        sql = "select last_insert_id()";
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement.executeQuery();
        orderfood.addOrderFood(resultSet.getInt("orderid"),foodlist);
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return 0;
    }

    private int checkBalance(int userid, List<FoodBean> foodlist) throws Exception{
        connection = dbutil.getConnection();
        String sql="select * from users where userid=?"; //
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, userid); //将sql段第一个？代替
        resultSet=preparedStatement.executeQuery();
        double balance = 0;
        resultSet.next();
        balance = resultSet.getDouble("money");
        double sum = 0;
        for(FoodBean foodbean: foodlist){
            sum += foodbean.getPrice();
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        if(balance>=sum) return 0;
        return 1;
    }
}
