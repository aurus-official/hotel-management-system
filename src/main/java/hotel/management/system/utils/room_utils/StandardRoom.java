package hotel.management.system.utils.room_utils;

import java.util.HashMap;
import java.util.Map;

class StandardRoom extends Room {
    private static Map<RoomHours, Integer> standardRoomRates;

    @Override
    public Map<RoomHours, Integer> getRates() {
        if (standardRoomRates == null) {
            standardRoomRates = new HashMap<>();
            standardRoomRates.put(RoomHours.HOURS_6, 900);
            standardRoomRates.put(RoomHours.HOURS_12, 1500);
            standardRoomRates.put(RoomHours.HOURS_24, 2500);
        }
        return standardRoomRates;
    }
}
