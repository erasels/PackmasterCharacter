package thePackmaster.powers.frostpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.frostpack.FrozenMod;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ColdStoragePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("ColdStoragePower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public ColdStoragePower(final AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);

        this.priority = 0;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer && !AbstractDungeon.player.hand.isEmpty()) {
            this.addToTop(new HandSelectAction(this.amount, card -> (!card.hasTag(SpireAnniversary5Mod.FROZEN)), (cards)->{
                for (AbstractCard c : cards) {
                    FrozenMod f = new FrozenMod();
                    Wiz.att(new SimpleAddModifierAction(f, c));

                    //Trigger the FrozenMod's end of turn effect instantly as we've already passed the end of turn trigger for cardmods.
                    Wiz.att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            this.isDone = true;
                            f.atEndOfTurn(c, AbstractDungeon.player.hand);
                        }
                    });
                }
            }, null, DESCRIPTIONS[3], true, true, true));
        }
    }

    public void updateDescription() {
        if (this.amount == 1)
        {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
        else
        {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }
}