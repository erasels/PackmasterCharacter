package thePackmaster.cards.evenoddpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public abstract class AbstractEvenOddCard extends AbstractPackmasterCard {
    public AbstractEvenOddCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
    }
    
    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = this.createEvenOddText();
        initializeDescription();
    }
    
    protected abstract String createEvenOddText();
}
