package thePackmaster.cards.fueledpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConservePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.actions.fueledpack.ConsumeToDoAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Fuel extends AbstractFueledCard {
    public final static String ID = makeID(Fuel.class.getSimpleName());
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 0;
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;

    public Fuel() {
        super(ID, COST, TYPE, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ConsumeToDoAction(new AbstractGameAction() {
            @Override
            public void update() {
                att(new GainEnergyAction(magicNumber));
                isDone = true;
            }
        }));
        applyToSelf(new ConservePower(adp(), 1));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}