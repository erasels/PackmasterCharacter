package thePackmaster.cards.upgradespack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.upgradespack.ScrapMetalAction;

public class ScrapMetal extends AbstractBlacksmithCard {

    public final static String ID = SpireAnniversary5Mod.makeID("ScrapMetal");

    public ScrapMetal() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScrapMetalAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(-1);
    }
}
