package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.strikepack.StrikeABargainPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StrikeABargain extends AbstractStrikePackCard {
    public final static String ID = makeID("StrikeABargain");

    public StrikeABargain() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new StrikeABargainPower(p));
        for (AbstractCard c:p.hand.group
             ) {
            if (c.type == CardType.ATTACK){
                Wiz.atb(new ReduceCostForTurnAction(c, 99));
            }
        }
    }

    public void upp() {
        this.upgradeBaseCost(1);
    }
}