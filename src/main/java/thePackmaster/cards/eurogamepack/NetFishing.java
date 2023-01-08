package thePackmaster.cards.eurogamepack;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.eurogamepack.VictoryPoints;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NetFishing extends AbstractVPCard{
    public static final String ID = makeID("NetFishing");

    public NetFishing() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        //this.baseMagicNumber = this.magicNumber = 4;
        this.baseSecondMagic = secondMagic = 8;
        this.baseBlock = 7;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(VictoryPoints.POWER_ID)){this.applyToSelf(new VictoryPoints(AbstractDungeon.player, 0));}
        this.applyToSelf(new VictoryPoints(AbstractDungeon.player, this.secondMagic));
        this.blck();
    }
    public void upp() {
            this.upgradeBlock(4);
            this.upgradeSecondMagic(6);
    }
}
