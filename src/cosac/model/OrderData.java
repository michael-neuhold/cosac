package cosac.model;

import java.io.Serializable;

public class OrderData implements Serializable {

    private String userId;
    private String food;
    private int timeSlotId;

    public OrderData(String userId, String food, int timeSlotId) {
        this.userId = userId;
        this.food = food;
        this.timeSlotId = timeSlotId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(int timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "userId='" + userId + '\'' +
                ", food='" + food + '\'' +
                ", timeSlotId=" + timeSlotId +
                '}';
    }
}
