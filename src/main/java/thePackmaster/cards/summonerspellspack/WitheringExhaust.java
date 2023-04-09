package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.summonerspellspack.WitheringExhaustAction;

public class WitheringExhaust extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("WitheringExhaust");
    private static final int COST = 1;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public WitheringExhaust() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
        addToBot(new WitheringExhaustAction(m, p));
    }
}
