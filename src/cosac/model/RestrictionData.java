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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("start time:" + startTime + ", ");
        sb.append("end time: " + endTime + ", ");
        sb.append("visitor limit: " + visitorLimit);
        return sb.toString();
    }
}
