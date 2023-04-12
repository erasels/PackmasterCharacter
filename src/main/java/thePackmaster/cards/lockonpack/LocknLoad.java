package thePackmaster.cards.lockonpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.LockOnPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LocknLoad extends AbstractLockonCard {

    public final static String ID = makeID(LocknLoad.class.getSimpleName());

    public LocknLoad() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 2;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new LockOnPower(m, magicNumber)));
        for (int i=0;i<magicNumber;i++) addToBot(new ChannelAction(new Lightning()));
    }
}
