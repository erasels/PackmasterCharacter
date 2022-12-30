package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PackRip extends AbstractPackmasterCard {
    public final static String ID = makeID("PackRip");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public PackRip() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //TODO
    }

    public void upp() {
        exhaust = false;
    }
}