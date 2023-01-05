package thePackmaster.cards.arcanapack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.patches.arcanapack.AnimatedCardsPatch;
import thePackmaster.powers.arcanapack.HangedPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class TheHangedMan extends AbstractAstrologerCard {
    public final static String ID = makeID("TheHangedMan");
    // intellij stuff skill, none, uncommon, , , , , 3, 2

    public TheHangedMan() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;

        AnimatedCardsPatch.loadFrames(this, 16, 0.20f);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new HangedPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}