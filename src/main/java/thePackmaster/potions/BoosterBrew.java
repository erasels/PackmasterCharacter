package thePackmaster.potions;


import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.packs.AbstractCardPack;

import java.util.ArrayList;


public class BoosterBrew extends CustomPotion {
    public static final String POTION_ID = SpireAnniversary5Mod.makeID("BoosterBrew");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public BoosterBrew() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.CARD, PotionColor.NONE);
        this.isThrown = false;
        this.targetRequired = false;
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }


    public void initializeData() {
        this.potency = getPotency();
        if (potency > 1){
            this.description = potionStrings.DESCRIPTIONS[0]  + potionStrings.DESCRIPTIONS[1] + potency + potionStrings.DESCRIPTIONS[2];
        } else {
            this.description = potionStrings.DESCRIPTIONS[0];
        }
    }

    public void use(AbstractCreature target) {
        ArrayList<AbstractCardPack> choices;
        ArrayList<AbstractCard> packCards = new ArrayList<>();

        for (int i = 0; i < potency; i++) {
            packCards.clear();
            choices = SpireAnniversary5Mod.getRandomPacks(true, 3);

            for (AbstractCardPack pack : choices) {
                AbstractCard c = SpireAnniversary5Mod.getRandomCardFromPack(pack).makeStatEquivalentCopy();
                packCards.add(c);
            }

            //Duplicate the array into a new instance in case of potency being greater than 1.  otherwise we're operating multiple actions off of the same ref and you'll see the same choice each trigger.
            ArrayList<AbstractCard> sentCards = new ArrayList<>(packCards);

            addToBot(new FlexibleDiscoveryAction(sentCards, true));
        }
    }

    public CustomPotion makeCopy() {
        return new BoosterBrew();
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }
}


