import org.assertj.core.api.Assertions;
import org.junit.Test;

public class DrinkTest {
  @Test
  public void a_drink_is_associated_to_a_code() {
    Assertions.assertThat(Drink.CHOCOLATE.code()).isEqualTo("H");
    Assertions.assertThat(Drink.COFFEE.code()).isEqualTo("C");
    Assertions.assertThat(Drink.TEA.code()).isEqualTo("T");
    Assertions.assertThat(Drink.ORANGE_JUICE.code()).isEqualTo("O");
  }

  @Test
  public void a_drink_is_associated_to_a_price() {
    Assertions.assertThat(Drink.CHOCOLATE.price()).isEqualTo(.5f);
    Assertions.assertThat(Drink.COFFEE.price()).isEqualTo(.6f);
    Assertions.assertThat(Drink.TEA.price()).isEqualTo(.4f);
    Assertions.assertThat(Drink.ORANGE_JUICE.price()).isEqualTo(.6f);
  }
}
