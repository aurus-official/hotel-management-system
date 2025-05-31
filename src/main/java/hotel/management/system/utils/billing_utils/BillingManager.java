package hotel.management.system.utils.billing_utils;

import java.util.stream.Collectors;

import hotel.management.system.utils.room_utils.Room;

public class BillingManager {
    private static BillingManager billingManager;

    private BillingManager() {
    }

    public static BillingManager getBillingManagerInstance() {
        if (BillingManager.billingManager == null) {
            billingManager = new BillingManager();
        }
        return billingManager;
    }

    public Transaction createTransaction(Room room) {
        Transaction transaction = new Transaction();
        transaction.setGuestNames(room.getGuest().getGuestList().stream().collect(Collectors.joining(", ")));

        transaction.setRoomId(room.getRoomId());
        transaction.setCheckedInTime(room.getGuest().getCheckedInDateTime());
        transaction.setCheckedOutTime(room.getGuest().getCheckedOutDateTime());
        transaction.setTotalPayment(room.getRates().get(room.getGuest().getLengthOfStay()));
        return transaction;
    }

    public void payTransaction(Transaction transaction) {

    }
}
