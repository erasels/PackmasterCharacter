package thePackmaster.cards.eurogamepack;




import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.eurogamepack.RoadbuildingPower;
import thePackmaster.powers.eurogamepack.VictoryPoints;


public class Harvest extends AbstractVPCard{
    public static final String ID = makeID("Harvest");

    public Harvest() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        //this.baseMagicNumber = this.magicNumber = 4;
        this.baseDamage = this.damage = 8;
        this.baseSecondMagic = secondMagic = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //this.addToBot(new HarvestAction(m, AbstractDungeon.player, this.damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.addToBot(new DamageCallbackAction(m,new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),AbstractGameAction.AttackEffect.SLASH_DIAGONAL, i -> {        if (i > 0) {
            int x = i;
            if (AbstractDungeon.player.hasPower(RoadbuildingPower.POWER_ID)) {
                x = i + AbstractDungeon.player.getPower(RoadbuildingPower.POWER_ID).amount;
            }
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VictoryPoints(AbstractDungeon.player, x), x));
        }}));


    }


    //All functionality is in HarvestAction
    public void upp() {
            this.upgradeDamage(5);
    }
}
