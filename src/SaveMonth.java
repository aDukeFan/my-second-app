public class SaveMonth {
    public String itemName;
    public Boolean isExpense;
    public int quantity;
    public int unitPrice;

    public String month;

    public SaveMonth(String itemName, Boolean isExpense, int quantity, int unitPrice, String month) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.month = month;
    }
}
