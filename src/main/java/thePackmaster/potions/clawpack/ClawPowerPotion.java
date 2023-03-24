package thePackmaster.potions.clawpack;

import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.clawpack.AddClawTagModifier;
import thePackmaster.cards.clawpack.AbstractClawCard;
import thePackmaster.util.Keywords;
import thePackmaster.util.Wiz;


public class ClawPowerPotion extends CustomPotion {
    public static final String POTION_ID = SpireAnniversary5Mod.makeID("ClawPowerPotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public ClawPowerPotion() {
        super(NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.S, PotionColor.NONE);
        this.isThrown = false;
        this.targetRequired = false;
        this.labOutlineColor = Color.TAN.cpy();
        this.resetTips();
    }


    public void initializeData() {
        this.potency = getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + potency + LocalizedStrings.PERIOD;
        this.resetTips();
    }

    private void resetTips() {
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(Keywords.SHARPEN.PROPER_NAME, Keywords.SHARPEN.DESCRIPTION));
    }

    public void use(AbstractCreature target) {
        for (AbstractCard c : Wiz.p().hand.group) {
            if (!c.hasTag(SpireAnniversary5Mod.CLAW) && c.type == AbstractCard.CardType.ATTACK) {
                Wiz.atb(new SimpleAddModifierAction(new AddClawTagModifier(1), c));
            }
        }

        //Game action here to wait for the Modifiers to get added to cards, so that ClawUp will then hit those cards.
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractClawCard.ClawUp(potency);
                this.isDone = true;
            }
        });
    }

    public CustomPotion makeCopy() {
        return new ClawPowerPotion();
    }

    public int getPotency(int ascensionLevel) {
        return 2;
    }
}


