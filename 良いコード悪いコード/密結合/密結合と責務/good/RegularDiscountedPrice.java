class RegularDiscountedPrice {
  private static final int MIN_AMOUNT = 0;
  private static final int DISCOUNT_AMOUNT = 400;
  final int amount;

  RegularDiscountedPrice(final RegularPrice price) {
    int discountedAmount = price.amount - DISCOUNT_AMOUNT;
    if (discountedAmount < MIN_AMOUNT) {
      discountedAmount = MIN_AMOUNT;
    }

    amount = discountedAmount;
  }
}