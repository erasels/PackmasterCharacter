package thePackmaster.cards.dragonwrathpack;

import com.megacrit.cardcrawl.cards.green.Backflip;
import com.megacrit.cardcrawl.cards.green.WraithForm;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;
import thePackmaster.powers.dragonwrathpack.JudgementDayPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeCardPath;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class JudgementDay extends AbstractDragonwrathCard {

    public static final String ID = makeID(JudgementDay.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("DivineJolt.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATIO

    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DMG = 2;  // UPGRADE_PLUS_DMG = 2

    // /STAT DECLARATION/


    public JudgementDay(){
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
        secondMagic = baseSecondMagic = 1;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++){
            addToBot(new ManifestAction(new Lightning()));
        }
        Wiz.applyToSelf(new JudgementDayPower(p,secondMagic));
    }


    // Upgraded stats.
    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(2);
    }
    @Override
    public String cardArtCopy() {
        return WraithForm.ID;
    }
}

