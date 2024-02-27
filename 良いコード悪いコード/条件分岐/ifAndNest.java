/**
 * これまでの購入金額が10万円以上であること 
 * 1か月あたりの購入頻度が10回以上であること 
 * 返品率が0.1%以内であること 
 条件を全て満たす場合はゴールド会員
 */

// ゴールド会員か判断するメソッド
 /**
  * @return ゴールド会員である場合はtrue 
  * @param history 購入履歴 
  */

// if文がネストしている。早期returnをする
  boolean isGoldCustomar(PurChaseHistory history) {
    if(100000 <= history.totalAmount) {
        if(10 <= history.pirchaseFrequencyPerMonth) {
            if(history.returnRate <= 0.001) {
                return true;
            }
        }
    }
  }

// シルバー会員かどうかを判定するメソッド
/**
 * @return シルバー会員である場合true
 * @param history 購入履歴
 */
boolean isSilverCustomer(PurchaseHistory history) {
    if (10 <= history.purchaseFrequencyPerMonth) {
        if (history.returnRate <= 0.001) {
            return true;
        }
    }
    return false;
  }
 

/********* 以下修正パターン  ************/

// ポリシーパターンで対応する
// 優良顧客のルールを表現するinterfece
interface ExcellentCusomerRule {
    /**
     * @return 条件を満たす場合はtrue
     * @param history 購入履歴
     */
    boolean ok(final PurChaseHistory history);
}

// ゴールド会員の3つのルールを分けたクラスを作成
/**
 *  これまでの購入金額が10万円以上であること 
 * 1か月あたりの購入頻度が10回以上であること 
 * 返品率が0.1%以内であること 
 */

 class GoldCustomerPurchaseAmountRule implements ExcellentCusomerRule {
    public boolean ok (final PurChaseHistory history) {
        return 100000 <= history.totalAmount;
    }
 }

 class PurchaseFrequencyRule implements ExcellentCusomerRule {
    public boolean ok (final PurChaseHistory history) {
        return 10 <= history.pirchaseFrequencyPerMonth;
    }
 }

 class ReturnRateRule implements ExcellentCusomerRule {
    public boolean ok (final PurChaseHistory history) {
        return history.returnRate <= 0.001;
    }
 }

// ポリシークラスを用意してaddメソッドでルールを集約する

class ExcellentCustomerPolicy {
    private final Set<ExcellentCustomerRule> rules;

    ExcellentCustomerPolicy() {
        rules = new HashSet();
    }

    /**
     * ルールを追加する
     * @param rule　ルール 
     */

    void add (final ExcellentCustomerRule rule) {
        rules.add(rule)
    }

    /**
     * @param history 購入履歴
     * @return ルールを全て満たす場合はtrue
     */

    boolean complyWithAll(final PurchaseHistory history) {
        for(ExcellentCusomerRule each : rules) {
            if(!each.ok(history)) return false;
        }
        return true;
    }

}

// 使い方
// ポリシーにルールを追加して条件判定
ExcellentCustomerPolicy goldCustomerPolicy = new ExcellentCustomerPolicy();
goldCustomerPolicy.add(new GoldCustomerPurchaseAmountRule());
goldCustomerPolicy.add(new PurchaseFrequencyRule());
goldCustomerPolicy.add(new ReturnRateRule());
// 顧客の注文履歴から判定する
goldCustomerPolicy.complyWithAll(purchaseHistory);