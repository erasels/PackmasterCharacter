package thePackmaster.cards.replicatorspack;

import basemod.cardmods.ExhaustMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.FlexibleDiscoveryAction;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class MagicHand extends AbstractReplicatorCard {


    public final static String ID = makeID("MagicHand");

    public MagicHand() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup masterdeck = new CardGroup(AbstractDungeon.player.masterDeck, CardGroup.CardGroupType.UNSPECIFIED);
        int uniqueCount = 0;
        ArrayList<AbstractCard> uniqueCards = new ArrayList<>();
        for (AbstractCard card: masterdeck.group) {
            if (!containsCard(uniqueCards, card)) {
                uniqueCount++;
                uniqueCards.add(card);
            }
        }
        if (uniqueCount <= magicNumber) {
            if (uniqueCount > 0) {
                if(upgraded){
                    atb(new FlexibleDiscoveryAction(uniqueCards, true));
                } else {
                    atb(new FlexibleDiscoveryAction(uniqueCards, true, new ExhaustMod()));
                }
            }
        } else {
            ArrayList<AbstractCard> selectedCards = new ArrayList<>();
            while (selectedCards.size() < magicNumber) {
                AbstractCard randomCard = masterdeck.group.get(AbstractDungeon.cardRandomRng.random(masterdeck.size() - 1));
                if (!containsCard(selectedCards, randomCard)) {
                    selectedCards.add(randomCard);
                }
            }
            if(upgraded){
                atb(new FlexibleDiscoveryAction(uniqueCards, true));
            } else {
                atb(new FlexibleDiscoveryAction(uniqueCards, true, new ExhaustMod()));
            }
        }
    }

    public static boolean containsCard(ArrayList<AbstractCard> cards, AbstractCard card) {
        for (AbstractCard c : cards) {
            if (c.cardID.equals(card.cardID)) {
                return true;
            }
        }
        return false;
    }
}