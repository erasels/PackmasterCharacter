package thePackmaster.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;


import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Rummage2 extends AbstractPackmasterCard {
    public final static String ID = makeID(Rummage2.class.getSimpleName());

    public Rummage2() {
        super(ID, 2, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        damage = baseDamage = 10;
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        Wiz.atb(new DrawCardAction(1, new RummageDrawAction(magicNumber)));
    }


    public void upp() {
        upgradeDamage(4);
    }

    public static class RummageDrawAction extends AbstractGameAction{

        RummageDrawAction(int reduction){
            amount = reduction;
        }

        @Override
        public void update() {
            for (AbstractCard c : DrawCardAction.drawnCards) {
                if(Wiz.getLogicalCardCost(c) > 0)
                {
                    c.setCostForTurn(Math.max(0, c.costForTurn - amount));
                    c.isCostModifiedForTurn = true;
                    c.superFlash();
                }
            }
            isDone = true;
        }
    }
}