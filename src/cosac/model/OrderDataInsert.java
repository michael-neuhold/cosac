package cosac.model;

public class OrderDataInsert {

    private int restrictionId;
    private int userId;
    private int foodId;

    public OrderDataInsert(int restrictionId, int userId, int foodId) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
