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
        balanceService.addExpense(Split.SplitType.EQUAL, Arrays.asList(2,3,4), 1, 1000, null, 4);
        balanceService.showAll();
        balanceService.addExpense(Split.SplitType.EXACT, Arrays.asList(3,4), 1, 0.0, Arrays.asList(500.0, 1000.0), 2);
        balanceService.showAll();
        balanceService.addExpense(Split.SplitType.PERCENT, Arrays.asList(2,3,4), 1, 1000, Arrays.asList(30.0,30.0,40.0), 3);
        balanceService.showAll();
        balanceService.showUserBalanceSheet(5);
    }
}
