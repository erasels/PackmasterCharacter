package thePackmaster.cards.lockonpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.LockOnPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GatheringStorm extends AbstractLockonCard {

    public final static String ID = makeID(GatheringStorm.class.getSimpleName());

    public GatheringStorm() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (!o.ID.equals(Lightning.ORB_ID) || upgraded) {
                addToTop(new ChannelAction(new Lightning()));
            }
        }
    }
}
