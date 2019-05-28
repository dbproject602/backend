package bean;

import java.io.Serializable;
import java.sql.Date;

public class DiscountBean implements Serializable {
    private int discountId;
    private String shopId;
    private String foodId;
    private String discountName;
    private Date startTime;
    private Date endTime;
    private double discountRatio;

    public DiscountBean(int discountId, String shopId, String foodId,
                        String discountName, Date startTime, Date endTime, double discountRatio) {
        this.discountId = discountId;
        this.shopId = shopId;
        this.foodId = foodId;
        this.discountName = discountName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discountRatio = discountRatio;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
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

    public double getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(double discountRatio) {
        this.discountRatio = discountRatio;
    }
}
