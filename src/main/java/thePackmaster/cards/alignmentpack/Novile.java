package thePackmaster.cards.alignmentpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.arcanapack.AbstractAstrologerCard;
import thePackmaster.vfx.alignmentpack.FlashImageEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Novile extends AbstractAstrologerCard {
    public final static String ID = makeID("Novile");

    public Novile() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = 1;
        isEthereal = true;
        this.initializeDescription();
    }

    @Override
    public void triggerWhenDrawn() {
        atb(new VFXAction(new FlashImageEffect(this.portrait)));
        atb(new GainEnergyAction(this.magicNumber));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void upgrade() {
        super.upgrade();
        initializeDescription();
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void initializeDescription() {
        if (cardStrings != null)
        {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            for (int i = 0; i < this.baseMagicNumber; i++)
            {
                this.rawDescription = this.rawDescription.concat(cardStrings.EXTENDED_DESCRIPTION[1]);
            }
            this.rawDescription = this.rawDescription.concat(cardStrings.EXTENDED_DESCRIPTION[2]);
        }
        super.initializeDescription();
    }
}