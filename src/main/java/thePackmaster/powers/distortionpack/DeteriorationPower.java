package thePackmaster.powers.distortionpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DeteriorationPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("DeteriorationPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public DeteriorationPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                if (AbstractDungeon.player.discardPile.isEmpty())
                    return;

                DeteriorationPower.this.flash();
                addToTop(new GainBlockAction(DeteriorationPower.this.owner, DeteriorationPower.this.owner, DeteriorationPower.this.amount));
                AbstractCard c = AbstractDungeon.player.discardPile.getRandomCard(true);
                AbstractCard visual = c.makeStatEquivalentCopy();

                visual.current_x = visual.target_x = Settings.WIDTH / 2f;
                visual.current_y = visual.target_y = Settings.HEIGHT / 2f;
                visual.drawScale = visual.targetDrawScale = 1.0F;
                addToTop(new VFXAction(new ExhaustCardEffect(visual)));

                addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
            }
        });
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}