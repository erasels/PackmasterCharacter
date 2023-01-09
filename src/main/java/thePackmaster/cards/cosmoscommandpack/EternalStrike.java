package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class EternalStrike extends AbstractPackmasterCard {
    public final static String ID = makeID("EternalStrike");

    public EternalStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = 8;
        magicNumber = baseMagicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    public void triggerOnExhaust() {
        AbstractCard copyOfThis = this.makeStatEquivalentCopy();
        copyOfThis.damage = copyOfThis.baseDamage += magicNumber;
        atb(new MakeTempCardInHandAction(copyOfThis));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}