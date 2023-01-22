package thePackmaster.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Rummage extends AbstractPackmasterCard {
    public final static String ID = makeID("Rummage");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Rummage() {
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
                if(c.cost >= 0 && c.costForTurn > 0)
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