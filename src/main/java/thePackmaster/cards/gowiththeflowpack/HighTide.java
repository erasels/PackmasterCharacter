package thePackmaster.cards.gowiththeflowpack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.p;

public class HighTide extends AbstractHydrologistCard implements OnFlowCard {
    public final static String ID = makeID("HighTide");

    public HighTide() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, Subtype.WATER);
        damage = baseDamage = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        hydrologistDamage(p, m, damage);
    }

    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public void onFlow(CardGroup container) {
        if (container == p().discardPile) {
            addToBot(new MoveCardsAction(p().hand, p().discardPile, c -> c == this));
        }
    }
}