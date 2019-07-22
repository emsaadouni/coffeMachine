public class Machine {

  public String make(Order order) {
    if (order.moneyInserted() < order.drink().price()) {
      final float missingMoney = order.drink().price() - order.moneyInserted();
      return missingMoneyMessage(missingMoney);
    }
    String command = order.drink().code() + (order.hot() ? "h" : "");
    command += order.orderedSugarCount() == 0 ? ":" : ":" + order.orderedSugarCount();
    command += order.orderedSugarCount() == 0 ? ":" : ":0";
    repository.registerSale(order);
    return command;
  }

  public String getMessage(String message) {
    return "M:" + message;
  }

  public int getSalesFor(Drink drink) {
    return repository.getSalesFor(drink);
  }

  public float money() {
    return repository.money();
  }

  public void printReport() {
    System.out.print(repository.report());
  }

  private String missingMoneyMessage(float missingMoney) {
    return getMessage("Missing: " + missingMoney + " €");
  }

  private final MachineDataRepository repository = new MachineDataRepository();
}
