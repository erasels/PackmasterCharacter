package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.BoosterTutorAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BoosterTutor extends AbstractPackmasterCard {
    public final static String ID = makeID("BoosterTutor");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public BoosterTutor() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new BoosterTutorAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}