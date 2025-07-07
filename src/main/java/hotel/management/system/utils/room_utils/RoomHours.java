package hotel.management.system.utils.room_utils;

public enum RoomHours {
    HOURS_6 {
        @Override
        public String toString() {
            return "6";
        }
    },
    HOURS_12 {
        @Override
        public String toString() {
            return "12";
        }
    },
    HOURS_24 {
        @Override
        public String toString() {
            return "24";
        }
    }
}
