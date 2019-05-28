package service;

import java.util.List;

import bean.OrderBean;
import dao.OrderDao;
import dao.OrderDaoImpl;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();

    public List<OrderBean> fetchOrderList(int userid){
        List<OrderBean> orderList = null;
        try{
            orderList= orderDao.fetchOrderList(userid);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return orderList;
    }
    public int deleteOrderById(int orderId){
        int rtn = 1;
        try{
            rtn = orderDao.deleteOrderById(orderId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rtn;
    }
    public int updateOrder(OrderBean orderBean){
        int rtn = 1;
        try{
            rtn = orderDao.updateOrder(orderBean);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rtn;
    }
    public int addOrder(OrderBean orderBean){
        int rtn = 1;
        try{
            rtn = orderDao.addOrder(orderBean);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rtn;
    }

}
