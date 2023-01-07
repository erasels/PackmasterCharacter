package thePackmaster.cards.arcanapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.patches.arcanapack.AnimatedCardsPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class TheHermit extends AbstractAstrologerCard {
    public final static String ID = makeID("TheHermit");
    // intellij stuff skill, none, uncommon, , , , , , 

    public TheHermit() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;

        AnimatedCardsPatch.loadFrames(this, 5, 0.1f);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            boolean first = true;
            @Override
            public void update() {
                if (first) {
                    first = false;

                    if (AbstractDungeon.getMonsters().areMonstersBasicallyDead() || p.hand.isEmpty()) {
                        this.isDone = true;
                        return;
                    }

                    if (p.hand.size() == 1) {
                        AbstractCard c = p.hand.getTopCard();
                        p.hand.moveToDiscardPile(c);
                        c.triggerOnManualDiscard();
                        GameActionManager.incrementDiscard(false);
                        drawFromCard(c);

                        AbstractDungeon.player.hand.applyPowers();
                        this.isDone = true;
                        return;
                    }

                    AbstractDungeon.handCardSelectScreen.open(DiscardAction.TEXT[0], 1, false);
                    AbstractDungeon.player.hand.applyPowers();
                }
                else {
                    if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                        for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                            p.hand.moveToDiscardPile(c);
                            c.triggerOnManualDiscard();
                            GameActionManager.incrementDiscard(false);
                            drawFromCard(c);
                        }

                        AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                        AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
                    }

                    this.isDone = true;
                }
            }
        });
    }
    private void drawFromCard(AbstractCard c) {
        int amt = c.costForTurn == -1 ? EnergyPanel.getCurrentEnergy() : c.costForTurn;
        if (amt >= 1)
            att(new DrawCardAction(amt));
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }
}