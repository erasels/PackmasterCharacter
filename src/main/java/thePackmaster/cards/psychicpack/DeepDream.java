package thePackmaster.cards.psychicpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.psychicpack.DeepDreamPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class DeepDream extends AbstractPsychicCard {
    public final static String ID = makeID("DeepDream");
    // intellij stuff skill, none, rare, , , , , 5, 2

    public DeepDream() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DeepDreamPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}