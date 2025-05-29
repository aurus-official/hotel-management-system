package hotel.management.system.utils.guest_utils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Guest {
    private List<String> guestList = new ArrayList<>();
    private ZonedDateTime checkInDateTime;
    private ZonedDateTime checkOutDateTime;
    private int lengthOfStay = 0;

    public void addGuest(String guest) {
        guestList.add(guest);
    }

    public void removeGuest(String guest) {
        guestList.remove(guest);
    }

    public List<String> getGuestList() {
        return guestList;
    }

    public void setCheckInDateTime() {
        this.checkInDateTime = ZonedDateTime.now();
    }

    public ZonedDateTime getCheckedInDateTime() {
        return checkInDateTime;
    }

    public void setCheckOutDateTime() {
        this.checkOutDateTime = ZonedDateTime.now();
    }

    public ZonedDateTime getCheckedOutDateTime() {
        return checkOutDateTime;
    }

    public int getLengthOfStay() {
        return lengthOfStay;
    }

    public void setLengthOfStay(int lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }
}
