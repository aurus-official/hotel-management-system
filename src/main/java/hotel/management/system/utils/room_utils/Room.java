package hotel.management.system.utils.room_utils;

import hotel.management.system.utils.guest_utils.Guest;

public abstract class Room {
    private RoomStatus roomStatus = RoomStatus.AVAILABLE;
    private Guest guest = null;
    private int roomId;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
        this.roomStatus = RoomStatus.OCCUPIED;
    }

    public void resetGuest() {
        this.guest = null;
        this.roomStatus = RoomStatus.AVAILABLE;
    }

    public void reserveGuest(Guest guest) {
        this.guest = guest;
        this.roomStatus = RoomStatus.RESERVED;
    }
}
