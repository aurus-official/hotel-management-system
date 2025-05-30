package hotel.management.system.utils.billing_utils;

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

    public static Transaction createTransaction(Room room) {
        return null;
    }
}
