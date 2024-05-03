import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Calculator {
    public void getMaxProfitAndExpense(LinkedHashMap<String, Integer> debAndCred) {
        String maxProfitItemName = "";
        int maxProfitItemValue = 0;
        String maxExpenseItemName = "";
        int maxExpenseValue = 0;
        for (Map.Entry<String, Integer> item : debAndCred.entrySet()) {
            if (maxProfitItemValue < item.getValue()) {
                maxProfitItemName = item.getKey();
                maxProfitItemValue = item.getValue();
            } else if (maxExpenseValue > item.getValue()) {
                maxExpenseItemName = item.getKey();
                maxExpenseValue = item.getValue();
            }
        }
        System.out.printf("most profitable product is %s, sum: %d\n"
                        + "the biggest expense is %s, sum: %d\n",
                maxProfitItemName, maxProfitItemValue,
                maxExpenseItemName, maxExpenseValue);
    }

    public int getAverageValue(ArrayList<Integer> sum) {
        int total = 0;
        for (Integer i : sum) {
            total += i;
        }
        return total / sum.size();
    }
}
