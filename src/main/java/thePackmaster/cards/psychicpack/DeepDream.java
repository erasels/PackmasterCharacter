package thePackmaster.cards.psychicpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.psychicpack.DeepDreamAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class DeepDream extends AbstractPackmasterCard {
    public final static String ID = makeID("DeepDream");
    // intellij stuff skill, none, rare, , , , , 5, 2

    public DeepDream() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 5;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DeepDreamAction(this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}