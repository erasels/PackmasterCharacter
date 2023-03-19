package thePackmaster.powers.pixiepack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.PixiePack;
import thePackmaster.powers.AbstractPackmasterPower;

public class FetchPower extends AbstractPackmasterPower implements NonStackablePower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("FetchPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FetchPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (PixiePack.isForeign(card) && card.type == AbstractCard.CardType.SKILL) {
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(amount));
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
