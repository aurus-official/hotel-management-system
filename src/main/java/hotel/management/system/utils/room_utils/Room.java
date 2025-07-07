package hotel.management.system.utils.room_utils;

import java.util.Map;

import hotel.management.system.utils.guest_utils.Guest;

public abstract class Room {
    private RoomStatus roomStatus = RoomStatus.AVAILABLE;
    private Guest guest = null;
    private int roomId;
    private RoomType roomType;

    public RoomType getRoomType() {
        return roomType;
    }

    protected void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public abstract Map<RoomHours, Integer> getRates();

    public int getRoomId() {
        return roomId;
    }

    protected void setRoomId(int roomId) {
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

    public void clearGuest() {
        this.guest = null;
        this.roomStatus = RoomStatus.AVAILABLE;
    }

    public void reserveGuest(Guest guest) {
        this.guest = guest;
        this.roomStatus = RoomStatus.RESERVED;
    }
}
