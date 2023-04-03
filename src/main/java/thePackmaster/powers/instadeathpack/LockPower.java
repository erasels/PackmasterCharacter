package thePackmaster.powers.instadeathpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.ExtraIconsPatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.IconPayload;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.TexLoader;

import java.util.ArrayList;


public class LockPower extends AbstractPackmasterPower implements NonStackablePower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("LockPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private static final Texture t = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/powers/LockPower84.png");

    public AbstractCard card;

    private IconPayload icon;

    public LockPower(final AbstractCreature owner, AbstractCard c, int amount)
    {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);

        this.card = c;
        updateDescription();

        icon = new IconPayload(ExtraIcons.icon(t).text(String.valueOf(amount)).textOffsetY(-13f));
    }

    @Override
    public boolean isStackable(AbstractPower power) {
        return (power instanceof LockPower && ((LockPower) power).card.uuid.equals(this.card.uuid));
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (!c.isEthereal && c.uuid.equals(this.card.uuid)) {
                    c.retain = true;
                    c.flash(); //maybe super flash, test it out
                }
            }
        }
        addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.uuid.equals(this.card.uuid))
        {
            this.flash();
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, c);

        if (card != null) {
            if (!icon.getText().equals(String.valueOf(amount)))
                ReflectionHacks.setPrivate(icon, IconPayload.class, "text", String.valueOf(amount));

            //To avoid unnecessarily filling the list as it isn't cleared if the card isn't rendered.
            //And also because I didn't want to make a separate cardmod which could maybe somehow get desynced from the power.
            ArrayList<IconPayload> icons = ExtraIconsPatch.ExtraIconsField.extraIcons.get(card);
            if (!icons.contains(icon)) {
                icons.add(icon);
            }
        }
    }

    public void updateDescription() {
        if (card == null)
        {
            this.description = "hwat";
        }
        else
        {
            this.description = DESCRIPTIONS[0] + FontHelper.colorString(card.name, "y") + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }
}