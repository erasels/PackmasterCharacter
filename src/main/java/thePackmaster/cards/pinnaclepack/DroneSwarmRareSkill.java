package thePackmaster.cards.pinnaclepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.pinnaclepack.Capacitor;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DroneSwarmRareSkill extends AbstractPinnacleCard {
    public final static String ID = makeID("DroneSwarmRareSkill");

    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC = 1;
    private static final int MAGIC2 = 4;
    private static final int UPGRADE_MAGIC2 = 1;

    public DroneSwarmRareSkill() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = MAGIC2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new ApplyPowerAction(p, p, new Capacitor(p, secondMagic), secondMagic));
        addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeSecondMagic(UPGRADE_MAGIC2);
    }

}
