package thePackmaster.cards.farmerpack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.pixiepack.DrawSpecificCardAction;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class SowTheSeeds extends AbstractFarmerCard {
    public final static String ID = makeID("SowTheSeeds");
    public SowTheSeeds() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new Fertilizer();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new MakeTempCardInHandAction(new Fertilizer()));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;


                ArrayList<AbstractCard> targets = new ArrayList<>();
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                        targets.add(c);
                   
                }

                //Add cards equals to magicNumber into your hand, if into discard
                for (int i = 0; i < magicNumber; i++) {
                    if (targets.isEmpty()) return;

                    AbstractCard current = Wiz.getRandomItem(targets, AbstractDungeon.cardRandomRng);
                    if (current != null) {
                        if (AbstractDungeon.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                            AbstractDungeon.player.drawPile.moveToDiscardPile(current);
                            AbstractDungeon.player.createHandIsFullDialog();
                        } else {
                            current.unhover();
                            current.lighten(true);
                            current.setAngle(0.0F);
                            current.drawScale = 0.12F;
                            current.targetDrawScale = 0.75F;
                            current.current_x = CardGroup.DRAW_PILE_X;
                            current.current_y = CardGroup.DRAW_PILE_Y;
                            AbstractDungeon.player.drawPile.removeCard(current);
                            AbstractDungeon.player.hand.addToTop(current);
                            AbstractDungeon.player.hand.refreshHandLayout();
                            AbstractDungeon.player.hand.applyPowers();
                        }
                        targets.removeIf(c -> c.type == current.type);
                        //targets.removeIf(c -> SpireAnniversary5Mod.cardParentMap.get(c.CardType).equals(current.Card));
                    }
                }
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
