public class Order {

  public Order(Drink drink, int orderedSugarCount, float moneyInserted) {
    this.drink = drink;
    this.orderedSugarCount = orderedSugarCount;
    this.moneyInserted = moneyInserted;
  }

  public static Order forOrangeJuice(float moneyInserted) {
    return new Order(Drink.ORANGE_JUICE, 0, moneyInserted);
  }

  public Drink drink() {
    return drink;
  }

  public int orderedSugarCount() {
    return orderedSugarCount;
  }

  public float moneyInserted() {
    return moneyInserted;
  }

  public boolean hot() {
    return hot;
  }

  public Order setHot() {
    if (drink != Drink.ORANGE_JUICE) {
      this.hot = true;
    }
    return this;
  }

  private Drink drink;
  private int orderedSugarCount;
  private float moneyInserted;
  private boolean hot = false;
}
