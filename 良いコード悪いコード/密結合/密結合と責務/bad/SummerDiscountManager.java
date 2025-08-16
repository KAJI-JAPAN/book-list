class SummerDiscountManager {
  DiscountManager discountManager;

  /**
   * 商品を追加する
   *
   * @param product 商品
   * @return 追加に成功した場合true
   */
  boolean add(Product product) {
    if (product.id < 0) {
      throw new IllegalArgumentException();
    }
    if (product.name.isEmpty()) {
      throw new IllegalArgumentException();
    }

    int tmp;
    if (product.canDiscount) {
      tmp = discountManager.totalPrice + DiscountManager.getDiscountPrice(product.price);
    } else {
      tmp = discountManager.totalPrice + product.price;
    }
    if (tmp < 30000) {
      discountManager.totalPrice = tmp;
      discountManager.discountProducts.add(product);
      return true;
    } else {
      return false;
    }
  }
}

// 商品
class Product {
  int id;                 // 商品ID
  String name;            // 商品名
  int price;              // 価格
  boolean canDiscount;    // ←新規追加。夏季割引可能な場合true
}

