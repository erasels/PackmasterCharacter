package thePackmaster.cards.orbpack;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.orbs.Plasma;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class MaterialBoost extends AbstractOrbCard {
    public final static String ID = makeID("MaterialBoost");
    // intellij stuff skill, none, rare, , , , , , 

    public MaterialBoost() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);

        showEvokeValue = true;
        showEvokeOrbCount = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new HandSelectAction(AbstractDungeon.player.hand.size(), (c)->true, (cards) -> {
            int amt = cards.size();
            for (AbstractCard c : cards) {
                p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }

            if (amt > 0) {
                addToTop(new EvokeOrbAction(1));
                addToTop(new AnimateOrbAction(1));
                for(int i = 0; i < amt - 1; ++i) {
                    addToTop(new EvokeWithoutRemovingOrbAction(1));
                }
            }
        }, null, DiscardAction.TEXT[0], false, true, true));
    }

    public void upp() {
        selfRetain = true;
    }
}