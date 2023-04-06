package thePackmaster.cards.warlockpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RunedMithrilRod extends AbstractWarlockCard {
    public final static String ID = makeID(RunedMithrilRod.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = -2;
    private static final int CARDS = 15;
    private static final int UPGRADE_CARDS = -5;

    private int cardsDrawnWhileInHand = 0;

    public RunedMithrilRod() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = CARDS;
        this.selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public void upp() {
        upMagic(UPGRADE_CARDS);
        if (CardCrawlGame.isInARun() && AbstractDungeon.currMapNode != null && AbstractDungeon.currMapNode.room != null && AbstractDungeon.currMapNode.room.phase == AbstractRoom.RoomPhase.COMBAT) {
            if (this.cardsDrawnWhileInHand >= this.magicNumber) {
                this.trigger();
            }
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void applyPowers() {
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[1].replace("{0}", this.cardsDrawnWhileInHand + "");
        this.initializeDescription();
    }

    public void onCardDrawWhileInHand() {
        if (this.cardsDrawnWhileInHand >= this.magicNumber) {
            return;
        }
        this.cardsDrawnWhileInHand++;
        this.applyPowers();
        if (this.cardsDrawnWhileInHand >= this.magicNumber) {
            this.trigger();
        }
    }

    private void trigger() {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.cost > 0 || c.costForTurn > 0) {
                        c.superFlash(Color.GOLD.cpy());
                    }
                    if (c.costForTurn > 0) {
                        c.costForTurn = c.costForTurn - 1;
                        c.isCostModifiedForTurn = true;
                    }
                }

                cardsDrawnWhileInHand = 0;
                applyPowers();
                this.isDone = true;
            }
        });
        this.addToBot(new DiscardSpecificCardAction(this));
    }
}
