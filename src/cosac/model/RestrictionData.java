package cosac.model;

public class RestrictionData {

    private String startTime;
    private String endTime;
    private int visitorLimit;

    public RestrictionData(String startTime, String endTime, int visitorLimit) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.visitorLimit = visitorLimit;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getVisitorLimit() {
        return visitorLimit;
    }

    public void setVisitorLimit(int visitorLimit) {
        this.visitorLimit = visitorLimit;
    }

    @Override
    public String toString() {
        return "RestrictionData{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", visitorLimit=" + visitorLimit +
                '}';
    }
}
