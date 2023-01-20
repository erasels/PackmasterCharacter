package thePackmaster.cards.warlockpack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class HighPriestessJeklik extends AbstractWarlockCard {
    public final static String ID = makeID(HighPriestessJeklik.class.getSimpleName());

    private static final int COST = 0;

    public HighPriestessJeklik() {
        super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        baseBlock = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void triggerOnExhaust() {
        atb(new MakeTempCardInHandAction(this.makeStatEquivalentCopy(), 2));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public float getTitleFontSize() {
        return 20.0f;
    }
}
