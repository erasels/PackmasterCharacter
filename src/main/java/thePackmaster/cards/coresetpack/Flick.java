package thePackmaster.cards.coresetpack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Flick extends AbstractPackmasterCard {
    public final static String ID = makeID("Flick");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Flick() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(1, name + ".", (cards) -> {
            att(new DiscardSpecificCardAction(cards.get(0)));
            if (cards.get(0).costForTurn > 0) {
                for (int i = 0; i < cards.get(0).costForTurn; i++) {
                    dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
                }
            }

        }));
    }

    public void upp() {
        upgradeDamage(2);
    }
}