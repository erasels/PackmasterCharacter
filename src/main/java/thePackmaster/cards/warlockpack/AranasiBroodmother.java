package thePackmaster.cards.warlockpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class AranasiBroodmother extends AbstractPackmasterCard {
    public final static String ID = makeID(AranasiBroodmother.class.getSimpleName());

    private static final int COST = 1;

    public AranasiBroodmother() {
        super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        baseBlock = 5;
        magicNumber = baseMagicNumber = 3;
        this.shuffleBackIntoDrawPile = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void triggerWhenDrawn() {
        atb(new GainBlockAction(adp(), adp(), magicNumber,true));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upMagic(1);
    }

    @Override
    public float getTitleFontSize() {
        return 20.0f;
    }
}
