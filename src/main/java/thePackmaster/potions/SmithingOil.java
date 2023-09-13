package thePackmaster.potions;


import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

import static thePackmaster.util.Wiz.atb;


public class SmithingOil extends CustomPotion {
    public static final String POTION_ID = SpireAnniversary5Mod.makeID("SmithingOil");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public SmithingOil() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.ANVIL, PotionColor.NONE);
        this.isThrown = false;
        this.targetRequired = false;
        this.labOutlineColor = Color.TAN.cpy();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }


    public void initializeData() {
        this.potency = getPotency();
        if (potency > 1) {
            this.description = potionStrings.DESCRIPTIONS[0] + potency + potionStrings.DESCRIPTIONS[2];
        } else {
            this.description = potionStrings.DESCRIPTIONS[0] + potency + potionStrings.DESCRIPTIONS[1];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {

        atb(new SelectCardsInHandAction(potency, potionStrings.DESCRIPTIONS[3], this::upgradeCheck, (cards) -> {
            for (AbstractCard c2 : cards) {
                addToBot(new UpgradeSpecificCardAction(c2));

                for (AbstractCard c : Wiz.p().masterDeck.group) {
                    if (c.uuid == c2.uuid) {
                        c.upgrade();
                        break;
                    }
                }
            }
        }));
    }

    private boolean upgradeCheck(AbstractCard c) {
        if(c.canUpgrade()) return true;
        if(c.upgraded) {
            AbstractCard mstEquiv = StSLib.getMasterDeckEquivalent(c);
            return mstEquiv != null && mstEquiv.canUpgrade();
        }
        return false;
    }

    public CustomPotion makeCopy() {
        return new SmithingOil();
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }
}


