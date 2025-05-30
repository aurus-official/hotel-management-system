package hotel.management.system.utils.room_utils;

import java.util.HashMap;
import java.util.Map;

class VIPRoom extends Room {
    private static Map<RoomHours, Integer> vipRoomRates;

    @Override
    public Map<RoomHours, Integer> getRates() {
        if (vipRoomRates == null) {
            vipRoomRates = new HashMap<>();
            vipRoomRates.put(RoomHours.HOURS_6, 900);
            vipRoomRates.put(RoomHours.HOURS_12, 1500);
            vipRoomRates.put(RoomHours.HOURS_24, 2500);
        }
        return vipRoomRates;
    }
}
