package hotel.management.system.utils.room_utils;

import java.util.HashMap;
import java.util.Map;

class DeluxeRoom extends Room {
    private static Map<RoomHours, Integer> deluxeRoomRates;

    @Override
    public Map<RoomHours, Integer> getRates() {
        if (deluxeRoomRates == null) {
            deluxeRoomRates = new HashMap<>();
            deluxeRoomRates.put(RoomHours.HOURS_6, 900);
            deluxeRoomRates.put(RoomHours.HOURS_12, 1500);
            deluxeRoomRates.put(RoomHours.HOURS_24, 2500);
        }
        return deluxeRoomRates;
    }
}
