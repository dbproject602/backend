package dao;

import bean.FoodBean;
import bean.OrderBean;
import util.DBUtil;

import java.text.SimpleDateFormat;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while(resultSet.next()){
            int a = resultSet.getInt("orderid");
            int b = resultSet.getInt("userid");
            String c = resultSet.getString("shopid");
            int d = resultSet.getInt("senderid");
            String e = resultSet.getString("starttime");
            String f = resultSet.getString("endtime");
//            String g =  resultSet.getString("fooditems");
            int h = resultSet.getInt("state");
            String sendername = resultSet.getString("sendername");
            String senderpwd = resultSet.getString("password");
            String shopname = resultSet.getString("shopname");
            ArrayList<FoodBean> list = new ArrayList<FoodBean>();
//            List<String> foodlist = Arrays.asList(g.split(","));
//            for(String s: foodlist){
//                list.add(food.getFoodById(s));
//            }

            Date starttime = new Date(sdf.parse(e).getTime());
            Date endtime = new Date(sdf.parse(f).getTime());
            OrderBean foodBean = new OrderBean(a,b,c,d,starttime,endtime,list,h,
                    shop.fetchShop(shopname),sender.fetchSender(sendername, senderpwd));
            orderBeanList.add(foodBean);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);

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

        String sql = "update orders set  endtime=?, state=? where orderid=?";
        preparedStatement=connection.prepareStatement(sql);
//        preparedStatement.setInt(1,orderBean.getUserId());
//        preparedStatement.setString(2,orderBean.getShopId());
//        preparedStatement.setInt(3,orderBean.getSenderId());
//        preparedStatement.setString(4,sdf.format(orderBean.getStartTime()));
        preparedStatement.setString(2,sdf.format(endtime));

//        String list = "";
        List<FoodBean> foodlist = orderBean.getFoodItems();
//        for(FoodBean food: foodlist){
//            list+=food.getFoodId()+",";
//        }
//        list = list.substring(0,list.length()-1);
//
//        preparedStatement.setString(6,list);
        preparedStatement.setInt(1,2);
        int rtn = preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        if(rtn==0) rtn=1; else rtn=0;
        return rtn;
    }
    public int addOrder(OrderBean orderBean) throws  Exception{
        connection = dbutil.getConnection();
        SenderDao senderdao = new SenderDaoImpl();
        OrderFoodDao orderfood = new OrderFoodDaoImpl();
        //get available sender
        int senderid = senderdao.fetchAvailSenderId();
        if(senderid==0)
            return 1;
        //check balance
        if(checkBalance(orderBean.getUserId(), orderBean.getFoodItems())==1)
            return 1;
        //get current time
        Date starttime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //get food list
        String list = "";
        List<FoodBean> foodlist = orderBean.getFoodItems();
        for(FoodBean food: foodlist){
            list+=food.getFoodId()+",";
        }
        list = list.substring(0,list.length()-1);
        String sql = "insert into orders (userid, shopid, senderid, starttime, fooditem, state) values(?,?,?,?,?,?)";
        String sql = "insert into orders (userid, shopid, senderid, starttime,  state) values(?,?,?,?,?)";
       // System.out.println("insert sender sql:"+sql);
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,orderBean.getUserId());
        System.out.println("shopid in order"+orderBean.getShopId());
        preparedStatement.setString(2,orderBean.getShopId());
        preparedStatement.setInt(3,senderid);
        preparedStatement.setString(4,sdf.format(starttime));

//        String list = "";
        List<FoodBean> foodlist = orderBean.getFoodItems();
//        for(FoodBean food: foodlist){
//            list+=food.getFoodId()+",";
//        }
//        list = list.substring(0,list.length()-1);
        System.out.println("尝试插入order, userid:"+orderBean.getUserId()+"\norder id:");
        orderfood.addOrderFood(orderBean.getOrderId(),foodlist);

//        preparedStatement.setString(5,list);
        preparedStatement.setInt(5,0);
        int rtn = preparedStatement.executeUpdate();
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
