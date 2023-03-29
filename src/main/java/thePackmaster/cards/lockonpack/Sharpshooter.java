package thePackmaster.cards.lockonpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.LockOnPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Sharpshooter extends AbstractLockonCard {

    public final static String ID = makeID(Sharpshooter.class.getSimpleName());

    public Sharpshooter() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i=0;i<magicNumber;i++) addToBot(new ChannelAction(new Lightning()));

        if (m.hasPower(LockOnPower.POWER_ID)) {
            addToBot(new GainEnergyAction(1));
            addToBot(new DrawCardAction(1));
        }
    }

}
