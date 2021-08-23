public class UserService {
    public static UserService userService = null;
    private static UserDao userDao = null;
    private UserService() {}

    public static UserService getInstance() {
        if(userService == null) {
            synchronized (UserService.class) {
                if (userService == null) userService = new UserService();
            }
        }
        initialize();
        return userService;
    }

    private static void initialize() {
        if (userDao == null) userDao = UserDao.getInstance();
    }

    public void addUser(String name, String phone) {
        int id = IdGenerator.getId();
        User user = new User(id, name, phone);
        if (userDao.addUser(user)) {
            System.out.println("User created successfully " + user);
        } else {
            System.out.println("User already exists");
        }
    }
}
