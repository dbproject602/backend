package service;

import java.util.List;

import bean.OrderBean;

public interface OrderService {
    List<OrderBean> fetchOrderList(int userid);
    int deleteOrderById(int orderId);
    int updateOrder(OrderBean orderBean);
    int addOrder(OrderBean orderBean);
}
