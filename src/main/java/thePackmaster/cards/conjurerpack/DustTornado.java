package thePackmaster.cards.conjurerpack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.conjurerpack.PlayRandomCardAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DustTornado extends ConjurerCard
{
    public final static String ID = makeID("DustTornado");
    private static final int MAGIC = 2;
    private static final int SECONDARY = 1;


    public DustTornado() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = MAGIC;
        baseSecondMagic = SECONDARY;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (p.hand.size() > 0)
        {
            AbstractDungeon.actionManager.addToBottom(new ExhaustAction(secondMagic, false, false, false));
            AbstractDungeon.actionManager.addToBottom(new PlayRandomCardAction(m, p.discardPile, magicNumber));
        }

    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
