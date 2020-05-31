package cosac.model;

import java.io.Serializable;
import java.util.Objects;

public class RestrictionData implements Serializable {

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

    public String getTimeSlot() {
        StringBuilder sb = new StringBuilder();
        sb.append(startTime);
        sb.append(" - ");
        sb.append(endTime);
        return sb.toString();
    }

    @Override
    public String toString() {
        return "RestrictionData{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", visitorLimit=" + visitorLimit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestrictionData that = (RestrictionData) o;
        return visitorLimit == that.visitorLimit &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime);
    }

}
