import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceDao {
    public static BalanceDao balanceDao = null;
    UserDao userDao = UserDao.getInstance();

    public Map<Integer, Map<Integer, Double>> balanceSheet = new HashMap<>();

    private BalanceDao() {
    }

    public static BalanceDao getInstance() {
        if (balanceDao == null) {
            synchronized (BalanceDao.class) {
                if (balanceDao == null) balanceDao = new BalanceDao();
            }
        }
        return balanceDao;
    }

    public void initialize(User user) {
        balanceSheet.put(user.getId(), new HashMap<>());
    }

    public void updateBalanceSheet(Integer id, Integer paidBy, double amount) {
        balanceSheet.get(id).putIfAbsent(paidBy, 0.0);
        balanceSheet.get(id).put(paidBy, balanceSheet.get(id).get(paidBy) + amount);
    }

    public void showAll() {
        for (Map.Entry<Integer, Map<Integer, Double>> entry : balanceDao.balanceSheet.entrySet()) {
            User user = userDao.userMap.get(entry.getKey());
            System.out.println("Showing sheet for user " + user.getId());
            Map<Integer, Double> balance = balanceSheet.get(user.getId());
            if (balance.isEmpty()) {
                System.out.println("No one owes to user "+ user.getId());
            } else {
                for (Map.Entry<Integer, Double> e : balance.entrySet()) {
                    User u = userDao.userMap.get(e.getKey());
                    double amount = e.getValue();
                    System.out.println("User " + u.getId() + " owes Rs. " + amount + " to User" + user.getId());
                }
            }
        }
    }

    public void showUserBalance(Integer id) {
        Map<Integer, Double> balance = balanceSheet.get(id);
        if (balance.isEmpty()) {
            System.out.println("No one owes to user "+ id);
        } else {
            for (Map.Entry<Integer, Double> e : balance.entrySet()) {
                User u = userDao.userMap.get(e.getKey());
                double amount = e.getValue();
                System.out.println("User " + u.getId() + " owes Rs. " + amount + " to User" + id);
            }
        }
    }
}
