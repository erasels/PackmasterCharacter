package thePackmaster.potions;


import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.EasyModalChoiceAction;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.cards.PackmasterModalChoiceCard;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class BoosterBrew extends CustomPotion {
    public static final String POTION_ID = SpireAnniversary5Mod.makeID("BoosterBrew");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public BoosterBrew() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.CARD, PotionColor.NONE);
        this.isThrown = false;
        this.targetRequired = false;
        this.labOutlineColor = Color.TAN.cpy();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }


    public void initializeData() {
        this.potency = getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        ArrayList<AbstractCardPack> choices = SpireAnniversary5Mod.getRandomPacks(false, 3);
        ArrayList<AbstractCard> packCards = new ArrayList<>();

        for (AbstractCardPack pack:choices) {
            packCards.add(new PackmasterModalChoiceCard(pack.previewPackCard, true, () -> action(pack)));
        }

        addToBot(new EasyModalChoiceAction(packCards, 1, CardCrawlGame.languagePack.getUIString(makeID("ModalChoice")).TEXT[0]));
    }


    public void action(AbstractCardPack pack) {
        for (int i = 0; i < potency; i++) {
            AbstractCard card = SpireAnniversary5Mod.getRandomCardFromPack(pack);
            if (card.cost > 0) {
                card.cost = 0;
                card.costForTurn = 0;
                card.isCostModified = true;
            }

            this.addToBot(new MakeTempCardInDrawPileAction(card, 1, true, true));
        }
    }


    public CustomPotion makeCopy() {
        return new BoosterBrew();
    }

    public int getPotency(int ascensionLevel) {
        return 5;
    }
}


