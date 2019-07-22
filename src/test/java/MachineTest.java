import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.assertj.core.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MachineTest {

  private Machine machine;
  private ByteArrayOutputStream output = new ByteArrayOutputStream();

  @Before
  public void init() {
    machine = new Machine();
    System.setOut(new PrintStream(output));
  }

  @After
  public void end() {
    System.setOut(null);
  }

  @Test
  public void can_order_tea_with_one_sugar() {
    final Order order = new Order(Drink.TEA, 1, 2f);
    final String command = machine.make(order);
    Assertions.assertThat(command).isEqualTo("T:1:0");
  }

  @Test
  public void can_order_tea_with_two_sugars() {
    final Order order = new Order(Drink.TEA, 2, 2f);
    final String command = machine.make(order);
    Assertions.assertThat(command).isEqualTo("T:2:0");
  }

  @Test
  public void can_order_tea_with_no_sugar() {
    final Order order = new Order(Drink.TEA, 0, 2f);
    final String command = machine.make(order);
    Assertions.assertThat(command).isEqualTo("T::");
  }

  @Test
  public void can_order_chocolate_with_two_sugars() {
    final Order order = new Order(Drink.CHOCOLATE, 2, 2f);
    final String command = machine.make(order);
    Assertions.assertThat(command).isEqualTo("H:2:0");
  }

  @Test
  public void can_order_coffee_with_no_sugar() {
    final Order order = new Order(Drink.COFFEE, 0, 2f);
    final String command = machine.make(order);
    Assertions.assertThat(command).isEqualTo("C::");
  }

  @Test
  public void can_format_a_message_command() {
    final String command = machine.getMessage("hello, world");
    Assertions.assertThat(command).isEqualTo("M:hello, world");
  }

  @Test
  public void displays_a_message_if_some_money_is_missing() {
    final Order order = new Order(Drink.COFFEE, 0, .3f);
    final String command = machine.make(order);
    Assertions.assertThat(command).isEqualTo("M:Missing: 0.3 €");
  }

  @Test
  public void the_exact_price_is_enough() {
    final Order order = new Order(Drink.TEA, 1, .4f);
    final String command = machine.make(order);
    Assertions.assertThat(command).isEqualTo("T:1:0");
  }

  @Test
  public void can_order_an_orange_juice_for_the_right_price() {
    final Order order = Order.forOrangeJuice(.6f);
    final String command = machine.make(order);
    Assertions.assertThat(command).isEqualTo("O::");
  }

  @Test
  public void can_make_a_hot_tea() {
    final Order order = new Order(Drink.TEA, 1, 2).setHot();
    final String command = machine.make(order);
    Assertions.assertThat(command).isEqualTo("Th:1:0");
  }

  @Test
  public void can_make_a_hot_coffee() {
    final Order order = new Order(Drink.COFFEE, 2, 2).setHot();
    final String command = machine.make(order);
    Assertions.assertThat(command).isEqualTo("Ch:2:0");
  }

  @Test
  public void can_make_a_hot_chocolate() {
    final Order order = new Order(Drink.CHOCOLATE, 0, 2).setHot();
    final String command = machine.make(order);
    Assertions.assertThat(command).isEqualTo("Hh::");
  }

  @Test
  public void cannot_make_a_hot_orange_juice() {
    final Order order = Order.forOrangeJuice(2).setHot();
    final String command = machine.make(order);
    Assertions.assertThat(command).isEqualTo("O::");
  }

  @Test
  public void adds_orders_to_the_repository() {
    final Order order = new Order(Drink.COFFEE, 1, 1);
    machine.make(order);
    Assertions.assertThat(machine.money()).isEqualTo(.6f);
    Assertions.assertThat(machine.getSalesFor(Drink.COFFEE)).isEqualTo(1);
  }

  @Test
  public void ignores_an_order_if_enough_money_was_inserted() {
    final Order order = Order.forOrangeJuice(0).setHot();
    machine.make(order);
    Assertions.assertThat(machine.money()).isEqualTo(0f);
    Assertions.assertThat(machine.getSalesFor(Drink.ORANGE_JUICE)).isEqualTo(0);
  }

  @Test
  public void can_print_reports() {
    final Order order = Order.forOrangeJuice(1);
    machine.make(order);
    machine.printReport();
    Assertions.assertThat(output.toString()).isEqualTo("OJ: 1; T: 0; C: 0; H: 0; €: 0.6");
  }
}
