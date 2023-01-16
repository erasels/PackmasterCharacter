package thePackmaster.powers.alignmentpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GlimpsePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("GlimpsePower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private Color renderColor;

    private int colorPhase = 0;

    public GlimpsePower(final AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        this.renderColor = new Color(1.0f, 0.0f, 0.0f, 1.0f);
    }

    @Override
    public void onCardDraw(AbstractCard abstractCard) {
        if (abstractCard.isEthereal)
        {
            this.flash();
            AbstractDungeon.actionManager.addToTop(new GainBlockAction(this.owner, this.owner, this.amount));
        }
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        float change = Gdx.graphics.getDeltaTime() * 0.33f;
        switch (colorPhase)
        {
            case 0:
                this.renderColor.r -= change;
                this.renderColor.g += change;
                if (this.renderColor.r <= 0)
                {
                    colorPhase += 1;
                    this.renderColor.r = 0;
                    this.renderColor.g = 1;
                }
                break;
            case 1:
                this.renderColor.g -= change;
                this.renderColor.b += change;
                if (this.renderColor.g <= 0)
                {
                    colorPhase += 1;
                    this.renderColor.g = 0;
                    this.renderColor.b = 1;
                }
                break;
            default:
                this.renderColor.b -= change;
                this.renderColor.r += change;
                if (this.renderColor.b <= 0)
                {
                    colorPhase = 0;
                    this.renderColor.b = 0;
                    this.renderColor.r = 1;
                }
                break;
        }
        this.renderColor.a = c.a;
        super.renderIcons(sb, x, y, this.renderColor);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}