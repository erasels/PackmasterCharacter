package thePackmaster.actions.sneckopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.sneckopack.Whirligig;

public class WhirligigAction extends AbstractGameAction {

    private final AbstractCard card;

    public WhirligigAction(AbstractCard card) {
        this.card = card;
    }

    @Override
    public void update() {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped()) {
                addToBot(new AttackDamageRandomEnemyAction(card, AttackEffect.BLUNT_LIGHT));
            }
        }
        isDone = true;
    }
}
