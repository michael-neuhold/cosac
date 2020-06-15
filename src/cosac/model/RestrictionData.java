package cosac.model;

import java.io.Serializable;
import java.util.Objects;

public class RestrictionData implements Serializable {

    private int restrictionId;
    private String startTime;
    private String endTime;
    private int visitorLimit;

    public RestrictionData(int restrictionId, String startTime, String endTime, int visitorLimit) {
        this.restrictionId = restrictionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.visitorLimit = visitorLimit;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        if(isValidTime(startTime)) this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        if(isValidTime(endTime)) this.endTime = endTime;
    }

    public int getVisitorLimit() {
        return visitorLimit;
    }

    public void setVisitorLimit(int visitorLimit) {
        this.visitorLimit = visitorLimit;
    }

    public int getRestrictionId() {
        return restrictionId;
    }

    public void setRestrictionId(int restrictionId) {
        this.restrictionId = restrictionId;
    }

    public String getTimeSlot() {
        StringBuilder sb = new StringBuilder();
        sb.append(startTime);
        sb.append(" - ");
        sb.append(endTime);
        return sb.toString();
    }

    public static boolean isValidTime(String time) {
        return time.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$");
    }

    @Override
    public String toString() {
        return "RestrictionData{" +
                "timeslotId=" + restrictionId +
                ", startTime='" + startTime + '\'' +
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
                restrictionId == that.restrictionId &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime);
    }

}
