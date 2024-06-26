package thePackmaster.cards.aggressionpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.stances.aggressionpack.AggressionStance;

public class FuriousAssault extends AbstractAggressionCard {
    public static final String ID = SpireAnniversary5Mod.makeID("FuriousAssault");
    private static final int COST = 1;
    private static final int DAMAGE = 2;
    private static final int HITS = 3;
    private static final int UPGRADE_HITS = 1;

    public FuriousAssault() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = HITS;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_HITS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        this.addToBot(new ChangeStanceAction(new AggressionStance()));

    }
}
