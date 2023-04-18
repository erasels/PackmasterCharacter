package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class DashSlap extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("DashingStrike");
    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;


    public DashSlap() {
        super(ID, COST, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.damage = this.baseDamage = DAMAGE;
        this.isEthereal = true;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }
}
