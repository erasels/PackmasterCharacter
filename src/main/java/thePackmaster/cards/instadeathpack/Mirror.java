package thePackmaster.cards.instadeathpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.patches.ScryCallbackPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Mirror extends AbstractInstadeathCard {
    public final static String ID = makeID("Mirror");

    public Mirror() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;

                if (p.drawPile.isEmpty())
                    return;

                AbstractCard c = p.drawPile.getTopCard();
                addToTop(new MakeTempCardInHandAction(c));
            }
        });
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
