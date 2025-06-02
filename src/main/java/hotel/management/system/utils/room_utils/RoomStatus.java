package hotel.management.system.utils.room_utils;

public enum RoomStatus {
    AVAILABLE {
        @Override
        public String toString() {
            return "Available";
        }
    },
    OCCUPIED {
        @Override
        public String toString() {
            return "Occupied";
        }
    },
    RESERVED {
        @Override
        public String toString() {
            return "Reserved";
        }
    }
}
