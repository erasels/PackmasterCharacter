package thePackmaster.cards.evenoddpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public abstract class AbstractEvenOddCard extends AbstractPackmasterCard {
    public AbstractEvenOddCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                makeImagePath("512/evenodd/" + type.name().toLowerCase() + ".png"),
                makeImagePath("1024/evenodd/" + type.name().toLowerCase() + ".png")
        );
    }
    
    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = this.createEvenOddText();
        initializeDescription();
    }
    
    protected abstract String createEvenOddText();
}
