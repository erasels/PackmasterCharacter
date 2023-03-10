package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class TheSpin extends AbstractPackmasterCard {
    public final static String ID = makeID("TheSpin");
    // intellij stuff attack, enemy, rare, 4, 1, , , , 

    public TheSpin() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (int i = 0; i < AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1; i++) {
                    dmgTop(m, AttackEffect.SLASH_HORIZONTAL);
                }
            }
        });
    }

    //TODO: Annoying text thing

    public void upp() {
        upgradeDamage(1);
    }
}