package thePackmaster.cards.warriorpack;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.warriorpack.FrontDamage;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class WorkHammer extends AbstractPackmasterCard {

    public final static String ID = makeID(WorkHammer.class.getSimpleName());

    private static final int COST = 1;

    public WorkHammer(){
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        DamageModifierManager.addModifier(this, new FrontDamage());
        baseBlock = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, block));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new GainBlockAction(m, p, block));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
