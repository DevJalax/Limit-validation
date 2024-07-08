import java.util.HashMap;
import java.util.Map;

public class User {
    private String accountType; // credit or debit
    private String channel; // IMPS, RTGS, NEFT, UPI
    private double limit; // default limit

    // Map to store limits for each account type and channel
    private static Map<String, Map<String, Double>> limits = new HashMap<>();

    static {
        limits.put("credit", new HashMap<>());
        limits.get("credit").put("UPI", 500000.0);
        limits.get("credit").put("IMPS", 500000.0);
        limits.get("credit").put("RTGS", 500000.0);
        limits.get("credit").put("NEFT", 500000.0);

        limits.put("debit", new HashMap<>());
        limits.get("debit").put("UPI", 100000.0);
        limits.get("debit").put("IMPS", 500000.0);
        limits.get("debit").put("RTGS", 500000.0);
        limits.get("debit").put("NEFT", 500000.0);
    }

    public User(String accountType, String channel) {
        this.accountType = accountType;
        this.channel = channel;
        setLimit(); // set limit based on account type and channel
    }

    private void setLimit() {
        limit = limits.get(accountType).get(channel);
    }

    public double getLimit() {
        return limit;
    }

    public static void main(String[] args) {
        User user1 = new User("debit", "UPI");
        System.out.println("User 1 limit: " + user1.getLimit()); // prints 100000.0

        User user2 = new User("credit", "IMPS");
        System.out.println("User 2 limit: " + user2.getLimit()); // prints 500000.0

        User user3 = new User("debit", "RTGS");
        System.out.println("User 3 limit: " + user3.getLimit()); // prints 500000.0
    }
}
