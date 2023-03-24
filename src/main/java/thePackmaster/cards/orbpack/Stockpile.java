package thePackmaster.cards.orbpack;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.orbs.Plasma;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Stockpile extends AbstractOrbCard {
    public final static String ID = makeID("Stockpile");
    // intellij stuff skill, none, uncommon, , , , , 1, 

    public Stockpile() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;

        showEvokeValue = true;
        showEvokeOrbCount = magicNumber;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        showEvokeOrbCount = magicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int orbs = magicNumber;
        atb(new HandSelectAction(1, (c)->true, (cards) -> {
            for (AbstractCard c : cards) {
                p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);

                for (int i = 0; i < orbs; ++i)
                    att(new ChannelAction(c.type == CardType.POWER ? new Plasma() : new Lightning()));
            }
        }, null, DiscardAction.TEXT[0], false, false, false));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}