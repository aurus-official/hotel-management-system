package hotel.management.system.utils.room_utils;

import java.util.List;

public class RoomManager {
    private static RoomManager roomManager;
    private static List<StandardRoom> allStandardRooms;
    private static List<DeluxeRoom> allDeluxeRooms;
    private static List<VIPRoom> allVIPRooms;
    private static final int MAX_STANDARD_ROOM = 30;
    private static final int MAX_DELUXE_ROOM = 15;
    private static final int MAX_VIP_ROOM = 10;

    private RoomManager() {
    }

    public static RoomManager getRoomManagerInstance() {
        if (roomManager == null) {
            roomManager = new RoomManager();
            setupAllRooms();
        }
        return roomManager;
    }

    private static void setupAllRooms() {
        for (int i = 0; i < MAX_STANDARD_ROOM; ++i) {
            StandardRoom standardRoom = new StandardRoom();
            standardRoom.setRoomId(i + 1);
            allStandardRooms.add(standardRoom);
        }
        for (int i = 0; i < MAX_DELUXE_ROOM; ++i) {
            DeluxeRoom deluxeRoom = new DeluxeRoom();
            deluxeRoom.setRoomId(i + 1);
            allDeluxeRooms.add(deluxeRoom);
        }
        for (int i = 0; i < MAX_VIP_ROOM; ++i) {
            VIPRoom vipRoom = new VIPRoom();
            vipRoom.setRoomId(i + 1);
            allVIPRooms.add(vipRoom);
        }
    }
}
