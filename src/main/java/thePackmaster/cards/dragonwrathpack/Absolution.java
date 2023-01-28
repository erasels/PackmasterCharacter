package thePackmaster.cards.dragonwrathpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.purple.Swivel;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.dragonwrathpack.AbsolutionPower;
import thePackmaster.powers.dragonwrathpack.PenancePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeCardPath;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Absolution extends AbstractDragonwrathCard {

    public static final String ID = makeID(Absolution.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("DivineJolt.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.

    public Absolution(){
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF_AND_ENEMY);
        magicNumber = baseMagicNumber = 5;
        secondMagic = baseSecondMagic = 2;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new PenancePower(m, p, magicNumber)));
        Wiz.applyToSelf(new AbsolutionPower(p, secondMagic));
    }

    @Override
    public String cardArtCopy() {
        return Swivel.ID;
    }

    // Upgraded stats.
    @Override
    public void upp() {
        upgradeMagicNumber(3);
        //upgradeSecondMagic(1);
    }
}

