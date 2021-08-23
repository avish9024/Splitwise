import java.util.List;
import java.util.Map;

public class BalanceService {

    public static BalanceService balanceService = null;
    BalanceDao balanceDao = BalanceDao.getInstance();
    private BalanceService() {}

    public static BalanceService getInstance() {
        if (balanceService == null) balanceService = new BalanceService();
        return balanceService;
    }

    public void addExpense(Split.SplitType type, List<Integer> ids, Integer paidBy, double amount, List<Double> part, int noOfPeople) {
        switch (type) {
            case EQUAL: splitEqually(ids, paidBy, amount, noOfPeople); break;
            case EXACT: splitExactly(ids, paidBy, part, noOfPeople); break;
            case PERCENT: splitPercentWise(ids, paidBy, part, noOfPeople, amount); break;
            default: throw new UnsupportedOperationException("The option you chose is not implemented");
        }
    }

    private void splitEqually(List<Integer> ids, Integer paidBy, double amount, int noOfPeople) {
        double equalAmount = amount / noOfPeople;
        for (Integer id : ids) {
            balanceDao.updateBalanceSheet(id, paidBy, equalAmount);
        }
    }

    private void splitExactly(List<Integer> ids, Integer paidBy, List<Double> part, int noOfPeople) {
        int i = 0;
        for (Integer id: ids) {
            balanceDao.updateBalanceSheet(id, paidBy, part.get(i));
            i++;
        }
    }

    private void splitPercentWise(List<Integer> ids, Integer paidBy, List<Double> percentage, int noOfPeople, double amount) {
        int i = 0;
        for (Integer id: ids) {
            double exactAmount = (percentage.get(i) * amount ) / 100;
            balanceDao.updateBalanceSheet(id, paidBy, exactAmount);
            i++;
        }
    }

   public void showAll() {
        balanceDao.showAll();
        System.out.println("************************************************");
   }

   public void showUserBalanceSheet(Integer id) {
        balanceDao.showUserBalance(id);
        System.out.println("************************************************");
   }
}
