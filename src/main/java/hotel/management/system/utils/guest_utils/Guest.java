package hotel.management.system.utils.guest_utils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import hotel.management.system.utils.room_utils.RoomHours;

public class Guest {
    private List<String> guestList = new ArrayList<>();
    private ZonedDateTime checkedInDateTime;

    private ZonedDateTime checkedOutDateTime;

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

    public void setGuestList(List<String> guestList) {
        this.guestList = guestList;
    }

    public ZonedDateTime getCheckedInDateTime() {
        return checkedInDateTime;
    }

    public void setCheckedInDateTime(ZonedDateTime checkedInDateTime) {
        this.checkedInDateTime = checkedInDateTime;
    }

    public RoomHours getLengthOfStay() {
        return lengthOfStay;
    }

    public ZonedDateTime getCheckedOutDateTime() {
        return checkedOutDateTime;
    }

    public void setCheckedOutDateTime(ZonedDateTime checkedOutDateTime) {
        this.checkedOutDateTime = checkedOutDateTime;
    }

    public void setLengthOfStay(RoomHours lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }

}
