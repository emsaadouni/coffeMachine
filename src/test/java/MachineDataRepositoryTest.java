import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class MachineDataRepositoryTest {
  private MachineDataRepository repository;

  @Before
  public void init() {
    repository = new MachineDataRepository();
  }

  @Test
  public void stores_how_many_drinks_were_sold() {
    final Order order1 = new Order(Drink.TEA, 0, 2);
    final Order order2 = new Order(Drink.COFFEE, 0, 1);
    final Order order3 = new Order(Drink.TEA, 1, 2);
    final Order order4 = new Order(Drink.CHOCOLATE, 2, 2);

    repository.registerSale(order1);
    repository.registerSale(order2);
    repository.registerSale(order3);
    repository.registerSale(order4);

    Assertions.assertThat(repository.getSalesFor(Drink.TEA)).isEqualTo(2);
    Assertions.assertThat(repository.getSalesFor(Drink.COFFEE)).isEqualTo(1);
    Assertions.assertThat(repository.getSalesFor(Drink.CHOCOLATE)).isEqualTo(1);
  }

  @Test
  public void stores_the_amount_of_money_acquired() {
    final Order order1 = new Order(Drink.TEA, 0, 2);
    final Order order2 = new Order(Drink.COFFEE, 0, 1);
    final Order order3 = new Order(Drink.TEA, 1, 2);
    final Order order4 = new Order(Drink.CHOCOLATE, 2, 2);

    repository.registerSale(order1);
    repository.registerSale(order2);
    repository.registerSale(order3);
    repository.registerSale(order4);

    Assertions.assertThat(repository.money()).isEqualTo(1.9f);
  }

  @Test
  public void can_generate_a_simple_report() {
    final Order order1 = new Order(Drink.TEA, 0, 2);
    final Order order2 = new Order(Drink.COFFEE, 0, 1);

    repository.registerSale(order1);
    repository.registerSale(order2);

    Assertions.assertThat(repository.report()).isEqualTo("OJ: 0; T: 1; C: 1; H: 0; €: 1.0");
  }
}
