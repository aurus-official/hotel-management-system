package hotel.management.system.utils.billing_utils;

import java.time.ZonedDateTime;

public class Transaction {
    private int roomId;
    private double totalPayment;
    private String guestNames;
    private ZonedDateTime checkedInTime;
    private ZonedDateTime checkedOutTime;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getGuestNames() {
        return guestNames;
    }

    public void setGuestNames(String guestNames) {
        this.guestNames = guestNames;
    }

    public ZonedDateTime getCheckedInTime() {
        return checkedInTime;
    }

    public void setCheckedInTime(ZonedDateTime checkedInTime) {
        this.checkedInTime = checkedInTime;
    }

    public ZonedDateTime getCheckedOutTime() {
        return checkedOutTime;
    }

    public void setCheckedOutTime(ZonedDateTime checkedOutTime) {
        this.checkedOutTime = checkedOutTime;
    }
}
