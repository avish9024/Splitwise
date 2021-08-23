import java.util.*;

public class UserDao {
    public static UserDao userDao = null;
    private HashSet<User> usersList = new HashSet<>();
    public Map<Integer, User> userMap = new HashMap<>();

    public List<User> getUsersList() {
        List<User> users = new ArrayList<>();
        for (User user : usersList) users.add(user);
        return users;
    }

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (userDao == null) {
            synchronized (UserDao.class) {
                if (userDao == null) userDao = new UserDao();
            }
        }
        return userDao;
    }

    private BalanceDao balanceDao = BalanceDao.getInstance();

    public boolean addUser(User user) {
        if(usersList.add(user)) {
            balanceDao.initialize(user);
            userMap.put(user.getId(), user);
            return true;
        }
        return false;
    }
}