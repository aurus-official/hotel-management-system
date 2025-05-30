package hotel.management.system.utils.room_utils;

import java.util.HashMap;
import java.util.Map;

class VIPRoom extends Room {
    public static Map<RoomHours, Integer> getRates() {
        Map<RoomHours, Integer> rates = new HashMap<>();
        rates.put(RoomHours.HOURS_6, 900);
        rates.put(RoomHours.HOURS_12, 1500);
        rates.put(RoomHours.HOURS_24, 2500);
        return rates;
    }
}
