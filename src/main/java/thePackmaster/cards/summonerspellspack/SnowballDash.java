package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class SnowballDash extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("SnowballDash");
    private static final int COST = 0;
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;


    public SnowballDash() {
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
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
    }
}
