import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MachineDataRepository {

  public MachineDataRepository() {
    List<Drink> drinks = Arrays.asList(Drink.values());
    for(int i=0; i < drinks.size(); i++){
      sales.put(drinks.get(i), 0);
    }
  }

  public void registerSale(Order order) {
    final int currentSales = sales.get(order.drink());
    sales.put(order.drink(), currentSales + 1);
    money += order.drink().price();
  }

  public int getSalesFor(Drink drink) {
    return sales.get(drink);
  }

  public float money() {
    return money;
  }

  public String report() {
    return "OJ: " + sales.get(Drink.ORANGE_JUICE) +
        "; T: " + sales.get(Drink.TEA) +
        "; C: " + sales.get(Drink.COFFEE) +
        "; H: " + sales.get(Drink.CHOCOLATE) +
        "; €: " + money;
  }

  private Map<Drink, Integer> sales = new HashMap();
  private float money;
}
