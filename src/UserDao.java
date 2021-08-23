import java.util.*;

public class UserDao {
    public static UserDao userDao = null;
    private static BalanceDao balanceDao = BalanceDao.getInstance();
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
        if (userDao == null) userDao = new UserDao();
        return userDao;
    }

    public boolean addUser(User user) {
        if(usersList.add(user)) {
            userMap.put(user.getId(), user);
            balanceDao.initialize(user, userMap);
            return true;
        }
        return false;
    }
}
