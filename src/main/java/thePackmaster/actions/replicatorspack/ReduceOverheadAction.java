package thePackmaster.actions.replicatorspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.att;

public class ReduceOverheadAction extends AbstractGameAction {
    private DamageInfo damageInfo;
    private AbstractMonster target;

    public ReduceOverheadAction(DamageInfo damageInfo, AbstractMonster m) {
        this.actionType = ActionType.DAMAGE;
        this.damageInfo = damageInfo;
        this.target = m;
    }

    public void update() {
        List<AbstractCard> cards = new ArrayList<AbstractCard>();
        AbstractPlayer p = adp();
        cards.addAll(p.drawPile.group);
        cards.addAll(p.hand.group);
        cards.addAll(p.discardPile.group);

        Set<String> ids = new HashSet<String>();
        Set<String> duplicateids = new HashSet<String>();
        List<AbstractCard> cardsToExhaust = new ArrayList<AbstractCard>();
        for (AbstractCard c : cards) {
            String id = c.cardID;
            if (!ids.contains(id)) {
                ids.add(id);
            } else {
                duplicateids.add(id);
            }
        }
        for (AbstractCard c : cards) {
            String id = c.cardID;
            if (duplicateids.contains(id)) {
                cardsToExhaust.add(c);
            }
        }
        for (AbstractCard c : cardsToExhaust) {
            att(new DamageAction(target, damageInfo, AbstractGameAction.AttackEffect.FIRE));
            att(new ImmediateExhaustCardAction(c));
        }
        this.isDone = true;
    }
}
