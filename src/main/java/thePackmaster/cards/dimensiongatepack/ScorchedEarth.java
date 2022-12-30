package thePackmaster.cards.dimensiongatepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class ScorchedEarth extends AbstractDimensionalCard {
    public final static String ID = makeID("ScorchedEarth");
    // intellij stuff attack, enemy, basic, 6, 3, , , ,

    public ScorchedEarth() {
        super(ID, 2, CardRarity.RARE, AbstractCard.CardType.ATTACK, AbstractCard.CardTarget.ENEMY);
        baseDamage = 6;

        setFrame("scorchedearthframe.png");
        exhaust = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {

        int count = 0;
        for (AbstractCard c : p.discardPile.group) {
            if (c.type != AbstractCard.CardType.ATTACK) {
                addToBot(new ExhaustSpecificCardAction(c, p.discardPile));
                count++;
            }
        }
        for (int i = 0; i < count; i++)
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractMonster q = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster) null, true, AbstractDungeon.cardRandomRng);
                   att(new DamageAction(q, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.FIRE));
                }
            });
    }

    public void upp() {
        upgradeDamage(3);
    }
}