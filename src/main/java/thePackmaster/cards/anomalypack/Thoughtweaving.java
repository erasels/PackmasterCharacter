package thePackmaster.cards.anomalypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.anomalypack.ThoughtweavingPower;

public class Thoughtweaving extends AbstractAnomalyCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Thoughtweaving");
    private static final int COST = 2;
    private static final int MAGIC = 1;

    public Thoughtweaving() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void upp() {  this.upgradeBaseCost(1); }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new ThoughtweavingPower(p, magicNumber), magicNumber));
    }
}
