package cosac.model;

import java.io.Serializable;

public class OrderData implements Serializable {

    private int orderId;
    private String userId;
    private String userFirstname;
    private String userLastname;
    private String food;

    public OrderData(int orderId, String userId, String userFirstname, String userLastname, String food) {
        this.orderId = orderId;
        this.userId = userId;
        this.userFirstname = userFirstname;
        this.userLastname = userLastname;
        this.food = food;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public String getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "orderId=" + orderId +
                ", userId='" + userId + '\'' +
                ", userFirstname='" + userFirstname + '\'' +
                ", userLastname='" + userLastname + '\'' +
                ", food='" + food + '\'' +
                '}';
    }
}
