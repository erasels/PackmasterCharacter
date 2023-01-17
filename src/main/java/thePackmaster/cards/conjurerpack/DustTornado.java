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
import static thePackmaster.util.Wiz.atb;

public class DustTornado extends ConjurerCard
{
    public final static String ID = makeID(DustTornado.class);
    private static final int MAGIC = 2;
    private static final int SECONDARY = 1;


    public DustTornado() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECONDARY;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player.hand.size() > 0)
        {
            atb(new ExhaustAction(secondMagic, false));
            atb(new PlayRandomCardAction(m, p.discardPile, magicNumber));
        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
