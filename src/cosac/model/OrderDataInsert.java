package cosac.model;

public class OrderDataInsert {

    private int restrictionId;
    private String userId;
    private int foodId;

    public OrderDataInsert(int restrictionId, String userId, int foodId) {
        this.restrictionId = restrictionId;
        this.userId = userId;
        this.foodId = foodId;
    }

    public int getRestrictionId() {
        return restrictionId;
    }

    public void setRestrictionId(int restrictionId) {
        this.restrictionId = restrictionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
