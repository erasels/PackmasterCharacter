package thePackmaster.cards.transmutationpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.transmutationpack.TransmuteCardAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class SearingSynthesis extends AbstractHydrologistCard {
    public final static String ID = makeID("SearingSynthesis");
    // intellij stuff ATTACK, ENEMY, COMMON, 9, , , , 1, 1

    public SearingSynthesis() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, Subtype.STEAM);
        baseDamage = 9;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        hydrologistDamage(p, m, damage);
        atb(new TransmuteCardAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}