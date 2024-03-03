
// 真偽値を渡しているがなんの処理をしているか分かりにくい。メソッドを見に行く必要がある
damage(true, damageAmount);

/*
damageメソッドではbooleanによってヒットポイントのダメージか魔法のダメージが切り替え
このようなフラグ引数は可読性が低下する
*/
void damage(boolean damageFlag, int damageAmount) {
    if (damageFlag == true) {
        // ヒットポイントダメージ
        member.hitPoint -= damageAmount;
        if (0 < member.hitPoint) return;

        member.hitPoint = 0;
        member.addState(StateType.dead);
    } else {
        // 魔法力ダメージ
        member.magicPoint -= damageAmount;
        if (0 < member.magicPoint) return;

        member.magicPoint = 0;
    }
}

// 数値で条件分岐しているが、こちらも同じようにダメ
void execute(int processNumber) {
    if (processNumber == 0) {
        // アカウント登録処理
    }
    else if (processNumber == 1) {
        // 配送完了メール送信処理
    }
    else if (processNumber == 2) {
        // 注文処理
    }
    else if (processNumber == 3) { ...



// ヒットポイント用のメソッドと魔法用のメソッドを分けてあげる必要がある
        void hitPointDamage(final int damageAmount) {
            member.hitPoint -= damageAmount;
            if (0 < member.hitPoint) return;

            member.hitPoint = 0;
            member.addState(StateType.dead);
        }

        void magicPointDamage(final int damageAmount) {
            member.magicPoint -= damageAmount;
            if (0 < member.magicPoint) return;

            member.magicPoint = 0;
        }

// ヒットポイントダメージか魔法力ダメージかを切り替えたいケースがあるかも
        interface Damage {
            void execute(final int damageAmount);
        }

        class HitPointDamage implements Damage {
            // 中略
            public void execute(final int damageAmount) {
                member.hitPoint -= damageAmount;
                if (0 < member.hitPoint) return;

                member.hitPoint = 0;
                member.addState(StateType.dead);
            }
        }

        // 魔法力ダメージ
        class MagicPointDamage implements Damage {
            // 中略
            public void execute(final int damageAmount) {
                member.magicPoint -= damageAmount;
                if (0 < member.magicPoint) return;

                member.magicPoint = 0;
            }
        }

    enum DamageType {
        hitPoint,
        magicPoint
    }

    private final Map<DamageType, Damage> damages;

        void applyDamage(final DamageType damageType, final int damageAmount) {
            final Damage damage = damage.get(damageType)
                    damages.execute(damage);
        }
// damages.put(DamageType.hitPoint, new HitPointDamage(/* 依存関係等 */));
//  damages.put(DamageType.magicPoint, new MagicPointDamage(/* 依存関係等 */));
//        実際には上記のようなコードが入る
        applyDamage(DamageType.magicPoint, damageAmount);
