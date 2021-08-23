import java.util.Arrays;

public class DriverClass {
    public static void main(String[] args) {
        UserService userService = UserService.getInstance();
        BalanceService balanceService = BalanceService.getInstance();
        userService.addUser("User1", "8709831711");
        userService.addUser("User2", "8709831712");
        userService.addUser("User3", "8709831713");
        userService.addUser("User4", "8709831714");
        balanceService.showAll();
        balanceService.addExpense(Split.EQUAL, Arrays.asList(1,2,3,4), 1, 1000, null, 4);
        balanceService.showAll();
        balanceService.addExpense(Split.EXACT, Arrays.asList(2,3), 1, 1250, Arrays.asList(370.0, 880.0), 2);
        balanceService.showAll();
        balanceService.addExpense(Split.PERCENT, Arrays.asList(1,2,3,4), 4, 1200, Arrays.asList(40.0, 20.0, 20.0, 20.0), 4);
        balanceService.showAll();
        balanceService.showUserBalanceSheet(5);
        userService.addUser("User4", "8709831714");
        balanceService.showAll();
    }
}
