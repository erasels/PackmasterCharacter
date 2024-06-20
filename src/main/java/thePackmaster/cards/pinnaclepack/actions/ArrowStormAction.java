package thePackmaster.cards.pinnaclepack.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ArrowStormAction extends AbstractGameAction {

    protected static AbstractPlayer pl(){
        return AbstractDungeon.player;
    }

    public int[] multiDamage;
    private float startingDuration;
    private int MagicB;

    public ArrowStormAction(int Magic, int[] multiDamage) {
        this.multiDamage = multiDamage;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.attackEffect = AbstractGameAction.AttackEffect.FIRE;
        this.damageType = DamageInfo.DamageType.NORMAL;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        MagicB = Magic;
    }

    public void update() {
        boolean hasExhausted = false;
        for (AbstractCard handcards : pl().hand.group) {
            if (handcards.type == AbstractCard.CardType.ATTACK) {
                addToTop(new ExhaustSpecificCardAction(handcards, pl().hand, true));
                hasExhausted = true;
            }
        }
        if (hasExhausted) {
            int i;
            for (i = 0; i < MagicB; i++) {
                addToBot(new DamageAllEnemiesAction(pl(), this.multiDamage, this.damageType, this.attackEffect));
            }
        }
        this.isDone = true;
    }

}
