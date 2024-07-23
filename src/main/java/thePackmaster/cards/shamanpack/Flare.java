package thePackmaster.cards.shamanpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.shamanpack.IgnitePower;

public class Flare extends AbstractShamanCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Flare");
    private static final int COST = 0;
    private static final int DAMAGE = 2;
    private static final int UPGRADE_DAMAGE = 1;
    private static final int IGNITE = 2;
    private static final int UPGRADE_IGNITE = 1;

    public Flare() {
        super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = IGNITE;
    }

    @Override
    public void upp() {
        this.upgradeDamage(UPGRADE_DAMAGE);
        this.upgradeMagicNumber(UPGRADE_IGNITE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        this.addToBot(new ApplyPowerAction(m, p, new IgnitePower(m, this.magicNumber)));

    }
}
