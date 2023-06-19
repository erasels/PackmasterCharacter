package thePackmaster.cards.fueledpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.fueledpack.ConsumeToDoAction;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Conflagration extends AbstractFueledCard {
    public final static String ID = makeID(Conflagration.class.getSimpleName());
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;

    private static final int MAGIC = 7;
    private static final int UPGRADE_MAGIC = 2;

    public Conflagration() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ApplyPowerAction igniteAction = new ApplyPowerAction(m, adp(), new IgnitePower(m, magicNumber));
        atb(new ConsumeToDoAction(igniteAction));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
