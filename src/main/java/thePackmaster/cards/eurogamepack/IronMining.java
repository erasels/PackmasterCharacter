package thePackmaster.cards.eurogamepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.eurogamepack.IronMiningAction;
import thePackmaster.powers.eurogamepack.VictoryPoints;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class IronMining extends AbstractVPCard{
    public static final String ID = makeID("IronMining");

    public IronMining() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseSecondMagic = this.secondMagic = 15;
        this.baseBlock = 11;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(VictoryPoints.POWER_ID)){this.applyToSelf(new VictoryPoints(AbstractDungeon.player, 0));}
        this.addToBot(new IronMiningAction(this.secondMagic, m));
        this.blck();
    }
    public void upp() {
            this.upgradeBlock(5);
            this.upgradeSecondMagic(5);
    }
}
