package thePackmaster.cards.upgradespack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public class RedHotHammer extends AbstractPackmasterCard {

    public final static String ID = SpireAnniversary5Mod.makeID("RedHotHammer");
    // intellij stuff ATTACK, ENEMY, COMMON, 10, 3, , , ,

    public RedHotHammer() {
        this(0);
    }

    public RedHotHammer(int upgrades) {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 10;
        timesUpgraded = upgrades;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(3);// 49
        ++this.timesUpgraded;// 50
        this.upgraded = true;// 51
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;// 52
        this.initializeTitle();
    }

    @Override
    public void upp() {
        upgrade();
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new RedHotHammer(timesUpgraded);
    }
}
