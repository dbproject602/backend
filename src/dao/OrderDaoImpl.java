package dao;

import bean.FoodBean;
import bean.OrderBean;
import util.DBUtil;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import java.sql.*;

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
                "join senders s on s.senderid = o.senderid " +
                "join shops sh on sh.shopid = o.shopid  where userid=?"; //
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
            Date endtime;
            Date starttime = new Date(sdf.parse(e).getTime());
            if(f==null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(starttime);
                cal.add(Calendar.DAY_OF_MONTH, 1);
                endtime = new Date(cal.getTimeInMillis());
            }
            else
                endtime = new Date(sdf.parse(f).getTime());
//            Date endtime = new Date(sdf.parse(f).getTime());

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
        preparedStatement.setInt(3,orderBean.getOrderId());
        int rtn = preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        if(rtn==0) rtn=1; else rtn=0;
        return rtn;
    }
    public int addOrder(OrderBean orderBean) throws  Exception{

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


        String sql = "insert into orders (userid, shopid, senderid, starttime, state) values(?,?,?,?,?)";
       // System.out.println("insert sender sql:"+sql);
        long t = System.currentTimeMillis();
        Random rand =new Random(t);
//        counter = (int)(1+rand.nextInt(1000000-1+1));

        connection = dbutil.getConnection();
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,orderBean.getUserId());
        System.out.println("shopid "+orderBean.getShopId());
        preparedStatement.setString(2,orderBean.getShopId());
        preparedStatement.setInt(3,senderid);
        preparedStatement.setString(4,sdf.format(starttime));

        //get string food list
        String list = "";
        for(FoodBean food: foodlist){
            list+=food.getFoodId()+",";
        }
        list = list.substring(0,list.length()-1);
        System.out.println("插入order, userid:"+orderBean.getUserId());

        preparedStatement.setInt(5,0);
        int rtn = preparedStatement.executeUpdate();

        sql = "select last_insert_id() id from orders";
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement.executeQuery();
        resultSet.next();
        int orderid = resultSet.getInt("id");

        orderfood.addOrderFood(orderid,foodlist);
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
        if (balance >= sum) {
            connection = dbutil.getConnection();
            String sql1 = "update users set money=? where userid=? ";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setDouble(1, balance - sum);
            preparedStatement.setInt(2, userid);
            if (preparedStatement.executeUpdate() > 0)
                System.out.println("扣款成功");
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return 0;
        }
        return 1;
    }
}
