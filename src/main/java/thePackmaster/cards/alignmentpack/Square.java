package thePackmaster.cards.alignmentpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.arcanapack.AbstractAstrologerCard;
import thePackmaster.vfx.alignmentpack.FlashImageEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Square extends AbstractAstrologerCard {
    public final static String ID = makeID("Square");

    public Square() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = 2;
        isEthereal = true;
    }

    @Override
    public void triggerWhenDrawn() {
        atb(new VFXAction(new FlashImageEffect(this.portrait)));
        atb(new DrawCardAction(this.magicNumber));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}