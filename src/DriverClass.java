public class DriverClass {
    public static void main(String[] args) {
        UserService userService = UserService.getInstance();
        BalanceService balanceService = BalanceService.getInstance();
        userService.addUser("User1", "8709831711");
        userService.addUser("User2", "8709831712");
        userService.addUser("User3", "8709831713");
        userService.addUser("User4", "8709831714");
        balanceService.showAll();
    }
}
