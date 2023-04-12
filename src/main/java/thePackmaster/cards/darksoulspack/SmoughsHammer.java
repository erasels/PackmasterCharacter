package thePackmaster.cards.darksoulspack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.weaponspack.WeaponMasteryPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class SmoughsHammer extends AbstractDarkSoulsCard {
    public final static String ID = makeID("SmoughsHammer");
    // intellij stuff attack, enemy, rare, 58, , , , 2, 

    public SmoughsHammer() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 58;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
        Wiz.applyToSelf(new WeakPower(p, magicNumber, false));
        Wiz.applyToSelf(new FrailPower(p, magicNumber, false));
        Wiz.applyToSelf(new VulnerablePower(p, magicNumber, false));
        if (this.upgraded){
            Wiz.atb(new AddTemporaryHPAction(p, p, 10));
        }
    }

    public void upp() {
    }
}