package thePackmaster.cards.summonspack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.orbs.summonspack.Louse;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class SummonLice extends AbstractPackmasterCard {
    public final static String ID = makeID(SummonLice.class.getSimpleName());
    private static final int COST = 3;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;

    public SummonLice() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            atb(new ChannelAction(new Louse()));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
