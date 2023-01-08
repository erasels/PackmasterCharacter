package thePackmaster.cards.eurogamepack;




import thePackmaster.actions.eurogamepack.HarvestAction;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.eurogamepack.VictoryPoints;

public class Harvest extends AbstractVPCard{
    public static final String ID = makeID("Harvest");

    public Harvest() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        //this.baseMagicNumber = this.magicNumber = 4;
        this.baseDamage = this.damage = 8;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(VictoryPoints.POWER_ID)){this.applyToSelf(new VictoryPoints(AbstractDungeon.player, 0));}
        this.addToBot(new HarvestAction(m, AbstractDungeon.player, this.damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
    //All functionality is in HarvestAction
    public void upp() {
            this.upgradeDamage(5);
    }
}
