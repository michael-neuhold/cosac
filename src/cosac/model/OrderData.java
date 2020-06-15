package cosac.model;

import java.io.Serializable;

public class OrderData implements Serializable {

    private int orderId;
    private String timeslot;
    private String userId;
    private String userFirstname;
    private String userLastname;
    private String food;

    public OrderData(int orderId, String timeslot, String userId, String userFirstname, String userLastname, String food) {
        this.orderId = orderId;
        this.timeslot = timeslot;
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

    public String getUserFirstname() {
        return userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public String getFood() {
        return food;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "orderId=" + orderId +
                ", timeslot='" + timeslot + '\'' +
                ", userId='" + userId + '\'' +
                ", userFirstname='" + userFirstname + '\'' +
                ", userLastname='" + userLastname + '\'' +
                ", food='" + food + '\'' +
                '}';
    }
}
