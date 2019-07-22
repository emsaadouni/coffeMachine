public enum Drink {
  CHOCOLATE("H", .5f), COFFEE("C", .6f), ORANGE_JUICE("O", .6f), TEA("T", .4f);

  private String code;
  private float price;

  Drink(String code, float price) {
    this.code = code;
    this.price = price;
  }

  public String code() {
    return code;
  }

  public float price() {
    return price;
  }
}
