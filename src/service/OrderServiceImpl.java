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
        try{
            orderDao.deleteOrderById(orderId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int updateOrder(OrderBean orderBean){
        try{
            orderDao.updateOrder(orderBean);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public int addOrder(OrderBean orderBean){
        try{
            orderDao.addOrder(orderBean);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }

}