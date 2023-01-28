package thePackmaster.cards.dimensiongateabstracts;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractDimensionalCardEden extends AbstractDimensionalCard {

    public AbstractDimensionalCardEden(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target) {
        super(cardID, cost, rarity, type, target, "dimension/eden");
    }

    public AbstractDimensionalCardEden(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target, final CardColor color) {
        super(cardID, cost, rarity, type, target, "dimension/eden", color);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}