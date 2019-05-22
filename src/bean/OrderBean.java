package bean;

import bean.FoodBean;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class OrderBean implements Serializable {
    private int orderId;
    private int userId;
    private int shopId;
    private int senderId;
    private Date startTime;
    private Date endTime;
    private ArrayList<FoodBean> foodItems;

    public OrderBean(int orderId, int userId, int shopId, int senderId, Date startTime, Date endTime, ArrayList<FoodBean> items) {
        this.orderId = orderId;
        this.userId = userId;
        this.shopId = shopId;
        this.senderId = senderId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.foodItems = items;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public ArrayList<FoodBean> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(ArrayList<FoodBean> foodItems) {
        this.foodItems = foodItems;
    }
}