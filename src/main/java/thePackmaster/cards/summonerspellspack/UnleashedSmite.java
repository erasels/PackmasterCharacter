package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.summonerspellspack.SnowballStrikePower;

public class UnleashedSmite extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("UnleashedSmite");
    private static final int COST = 1;
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 4;
    private static final int MAGIC = 1;

    public UnleashedSmite() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new ModifyDamageAction(this.uuid, this.magicNumber * p.hand.group.size()));
    }
}
