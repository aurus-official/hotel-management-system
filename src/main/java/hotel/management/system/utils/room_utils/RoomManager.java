package hotel.management.system.utils.room_utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoomManager {
    private static RoomManager roomManager;
    private static List<Room> allRooms;
    private static final int MAX_STANDARD_ROOM = 30;
    private static final int MAX_DELUXE_ROOM = 15;
    private static final int MAX_VIP_ROOM = 10;

    private RoomManager() {
    }

    public static RoomManager getRoomManagerInstance() {
        if (roomManager == null) {
            allRooms = new ArrayList<>();
            roomManager = new RoomManager();
            setupAllRooms();
        }
        return roomManager;
    }

    private static void setupAllRooms() {
        for (int i = 0; i < MAX_STANDARD_ROOM; ++i) {
            StandardRoom standardRoom = new StandardRoom();
            standardRoom.setRoomId(i + 1);
            standardRoom.setRoomType(RoomType.StandardRoom);
            allRooms.add(standardRoom);
        }
        for (int i = 0; i < MAX_DELUXE_ROOM; ++i) {
            DeluxeRoom deluxeRoom = new DeluxeRoom();
            deluxeRoom.setRoomId(MAX_STANDARD_ROOM + i + 1);
            deluxeRoom.setRoomType(RoomType.DeluxeRoom);
            allRooms.add(deluxeRoom);
        }
        for (int i = 0; i < MAX_VIP_ROOM; ++i) {
            VIPRoom vipRoom = new VIPRoom();
            vipRoom.setRoomId(MAX_STANDARD_ROOM + MAX_DELUXE_ROOM + i + 1);
            vipRoom.setRoomType(RoomType.VIPRoom);
            allRooms.add(vipRoom);
        }
    }

    public List<Room> filter(Map<RoomStatus, Boolean> filterSettings) {
        return allRooms.stream().filter(room -> filterSettings.get(room.getRoomStatus())).collect(Collectors.toList());
    }
}
