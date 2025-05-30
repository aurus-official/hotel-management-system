package hotel.management.system.utils.guest_utils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import hotel.management.system.utils.room_utils.RoomHours;

public class Guest {
    private List<String> guestList = new ArrayList<>();
    private ZonedDateTime checkInDateTime;
    private ZonedDateTime checkOutDateTime;
    private RoomHours lengthOfStay;

    public void addGuest(String guest) {
        if (guestList.size() > 2) {
            throw new IllegalArgumentException("Guests number have exceeded");
        }
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

    public void setGuestList(List<String> guestList) {
        this.guestList = guestList;
    }

    public ZonedDateTime getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(ZonedDateTime checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public ZonedDateTime getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(ZonedDateTime checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    public RoomHours getLengthOfStay() {
        return lengthOfStay;
    }

    public void setLengthOfStay(RoomHours lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }

}
