package thePackmaster.cards.dragonwrathpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Worship;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.dragonwrathpack.PenancePower;
import static thePackmaster.SpireAnniversary5Mod.makeCardPath;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WeightofContrition extends AbstractDragonwrathCard{


    // TEXT DECLARATION

    public static final String ID =  makeID(WeightofContrition.class.getSimpleName());// USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Skill.png");// "public static final String IMG = makeCardPath("HolySmite.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION
     //
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // /STAT DECLARATION/


    public WeightofContrition() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);

        magicNumber = baseMagicNumber = 2;
        secondMagic = baseSecondMagic = 4;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new PenancePower(m, p,  magicNumber)));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                PenancePower.Power += secondMagic;
                isDone = true;
            }

    @Override
    public String cardArtCopy() {
        return Worship.ID;
    }
    // Upgraded stats.

    @Override
    public void upp() {
        upgradeMagicNumber(2);
        initializeDescription();
    }
}