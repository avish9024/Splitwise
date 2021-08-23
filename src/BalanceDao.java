import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceDao {
    public static BalanceDao balanceDao = null;

    public Map<Integer, User> userMap = new HashMap<>();
    public Map<Integer, Map<Integer, Double>> balanceSheet = new HashMap<>();

    private BalanceDao() {
    }

    public static BalanceDao getInstance() {
        if (balanceDao == null) balanceDao = new BalanceDao();
        return balanceDao;
    }

    public void initialize(User user, Map<Integer, User> userMap) {
        balanceSheet.put(user.getId(), new HashMap<>());
        this.userMap = userMap;
    }

    public void updateBalanceSheet(Integer id, Integer paidBy, double amount) {
        balanceSheet.get(id).putIfAbsent(paidBy, 0.0);
        balanceSheet.get(id).put(paidBy, balanceSheet.get(id).get(paidBy) + amount);
    }

    public void showAll() {
        for (Map.Entry<Integer, Map<Integer, Double>> entry : balanceDao.balanceSheet.entrySet()) {
            User user = userMap.get(entry.getKey());
            System.out.println("Showing sheet for user " + user.getId());
            Map<Integer, Double> balance = balanceSheet.get(user.getId());
            if (balance.isEmpty()) {
                System.out.println("User " + user.getId()  +" owes nothing to anyone");
            } else {
                for (Map.Entry<Integer, Double> e : balance.entrySet()) {
                    User u = userMap.get(e.getKey());
                    double amount = e.getValue();
                    System.out.println("User " + user.getId() + " owes Rs. " + amount + " to User" + u.getId());
                }
            }
        }
    }

    public void showUserBalance(Integer id) {
        if (!balanceSheet.containsKey(id)) {
            System.out.println("User doesn't exists");
            return;
        }
        Map<Integer, Double> balance = balanceSheet.get(id);
        if (balance.isEmpty()) {
            System.out.println("User " + id  +" owes nothing to anyone");
        } else {
            for (Map.Entry<Integer, Double> e : balance.entrySet()) {
                User u = userMap.get(e.getKey());
                double amount = e.getValue();
                System.out.println("User " + id + " owes Rs. " + amount + " to User" + u.getId());
            }
        }
    }
}
