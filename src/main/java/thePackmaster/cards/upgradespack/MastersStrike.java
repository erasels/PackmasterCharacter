package thePackmaster.cards.upgradespack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public class MastersStrike extends AbstractPackmasterCard {

    public final static String ID = SpireAnniversary5Mod.makeID("MastersStrike");

    public MastersStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 1;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (damage > 20) {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        } else {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        }
    }

    public void upp() {
        upgradeDamage(5);
    }


    public static int countUpgrades() {
        int count = 0;// 36

        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            count += c.timesUpgraded;
        }

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            count += c.timesUpgraded;
        }

        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            count += c.timesUpgraded;
        }

        return count;// 52
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;// 70
        this.baseDamage += this.magicNumber * countUpgrades();// 71
        super.calculateCardDamage(mo);// 73
        this.baseDamage = realBaseDamage;// 75
        this.isDamageModified = this.damage != this.baseDamage;// 78
    }// 79

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;// 85
        this.baseDamage += this.magicNumber * countUpgrades();// 86
        super.applyPowers();// 88
        this.baseDamage = realBaseDamage;// 90
        this.isDamageModified = this.damage != this.baseDamage;// 93
    }
}
