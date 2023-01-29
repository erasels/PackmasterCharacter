package thePackmaster.cards.evenoddpack;

import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.EvenOddPack;

import static thePackmaster.SpireAnniversary5Mod.modID;

public abstract class AbstractEvenOddCard extends AbstractPackmasterCard {
    public AbstractEvenOddCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "evensandodds");
    }
    
    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = this.createEvenOddText();
        initializeDescription();
    }
    
    public static String makeCardTextGray(String input)
    {
        input = input.replace(modID + ":", "");
        return input.replaceAll("(\\s)((?!!|\\[E]|NL))"," " + EvenOddPack.GRAY + "$2");
    }
    
    protected abstract String createEvenOddText();
}
