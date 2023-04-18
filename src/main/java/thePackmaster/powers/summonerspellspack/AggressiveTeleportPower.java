package thePackmaster.powers.summonerspellspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.p;

public class AggressiveTeleportPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("AggressiveTeleportPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public AggressiveTeleportPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        boolean flashed = false;

        if (!p().discardPile.getAttacks().isEmpty()) {
            CardGroup g = p().discardPile.getAttacks();
            for (int i = 0; i < this.amount; i++) {
                int highestAttack = -999;
                AbstractCard highestAttackCard = null;
                for (int j = 0; j < g.size(); j++) {
                    AbstractCard c = g.group.get(j);
                    if (c.damage > highestAttack) {
                        highestAttackCard = c;
                        highestAttack = c.damage;
                    }
                }
                if (highestAttackCard != null) {
                    addToBot(new DiscardToHandAction(highestAttackCard));
                    g.removeCard(highestAttackCard);
                }

                if (!flashed) {
                    flash();
                    flashed = true;
                }
            }
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = this.amount == 1 ?
                DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] :
                DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
    }

}
