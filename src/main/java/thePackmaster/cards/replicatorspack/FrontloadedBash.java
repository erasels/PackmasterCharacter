package thePackmaster.cards.replicatorspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class FrontloadedBash extends AbstractReplicatorCard {


    public final static String ID = makeID("FrontloadedBash");

    public FrontloadedBash() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseMagicNumber = magicNumber = 3;
        isInnate=true;
    }

    @Override
    public void applyPowers() {
        int temp = baseDamage;
        AbstractPlayer p = adp();
        if(p!=null){
            baseDamage += adp().drawPile.size()/magicNumber;
        }
        super.applyPowers();
        baseDamage=temp;
        isDamageModified = (damage != baseDamage);

    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        AbstractPlayer p = adp();
        if(p!=null){
            baseDamage += adp().drawPile.size()/magicNumber;
        }
        super.calculateCardDamage(mo);
        baseDamage=temp;
        isDamageModified = (damage != baseDamage);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if(upgraded){
            atb(new DrawCardAction(adp(),1));
        }
    }
}
