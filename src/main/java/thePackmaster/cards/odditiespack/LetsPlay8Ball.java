package thePackmaster.cards.odditiespack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.shuffleIn;

public class LetsPlay8Ball extends AbstractOdditiesCard {
    public final static String ID = makeID("LetsPlay8Ball");
    // intellij stuff power, self, uncommon, , , , , , 

    public LetsPlay8Ball() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new EightBall();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = new EightBall();
        if (upgraded) q.upgrade();
        shuffleIn(q);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            ++timesUpgraded;
            upgraded = true;
            name = cardStrings.EXTENDED_DESCRIPTION[0];
            initializeTitle();
            upp();
            if (cardStrings.UPGRADE_DESCRIPTION != null) {
                uDesc();
            }
        }
    }

    public void upp() {
        AbstractCard q = new EightBall();
        q.upgrade();
        cardsToPreview = q;
    }
}