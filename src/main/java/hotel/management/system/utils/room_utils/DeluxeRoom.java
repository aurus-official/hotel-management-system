package hotel.management.system.utils.room_utils;

import java.util.HashMap;
import java.util.Map;

public class DeluxeRoom extends Room {

    private static Map<Integer, Integer> rates = null;

    public static Map<Integer, Integer> getRates() {
        if (rates == null) {
            Map<Integer, Integer> rates = new HashMap<>();
            rates.put(3, 800);
            rates.put(6, 1500);
            rates.put(9, 2300);
            rates.put(12, 3100);
            rates.put(24, 3900);
            DeluxeRoom.rates = rates;
        }
        return rates;
    }
}
