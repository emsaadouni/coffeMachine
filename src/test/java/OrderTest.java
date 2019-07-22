import org.assertj.core.api.Assertions;
import org.junit.Test;

public class OrderTest {

  @Test
  public void can_select_the_hot_option() {
    final Order order = new Order(Drink.TEA, 1, 1);
    order.setHot();
    Assertions.assertThat(order.hot()).isTrue();
  }

  @Test
  public void an_orange_juice_cannot_be_hot() {
    final Order order = Order.forOrangeJuice(2);
    order.setHot();
    Assertions.assertThat(order.hot()).isFalse();
  }
}
