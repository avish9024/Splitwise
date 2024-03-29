public class UserService {
    public static UserService userService = null;
    private UserDao userDao = UserDao.getInstance();
    private UserService() {}

    public static UserService getInstance() {
        if(userService == null) userService = new UserService();
        return userService;
    }

    public void addUser(String name, String phone) {
        int id = IdGenerator.getId();
        User user = new User(id, name, phone);
        if (userDao.addUser(user)) {
            System.out.println("User created successfully " + user);
        } else {
            System.out.println("User already exists");
        }
        System.out.println("***********************************************");
    }
}
