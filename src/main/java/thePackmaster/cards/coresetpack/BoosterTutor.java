package thePackmaster.cards.coresetpack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BoosterTutor extends AbstractPackmasterCard {
    public final static String ID = makeID("BoosterTutor");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public BoosterTutor() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;

                //Fill list with non-basic cards from packs
                ArrayList<AbstractCard> targets = new ArrayList<>();
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (SpireAnniversary5Mod.cardParentMap.get(c.cardID) != null && c.rarity != CardRarity.BASIC) {
                        targets.add(c);
                    }
                }

                //Add cards equals to magicNumber into your hand, if into discard
                for (int i = 0; i < magicNumber; i++) {
                    if (targets.isEmpty()) return;

                    AbstractCard current = Wiz.getRandomItem(targets, AbstractDungeon.cardRandomRng);
                    if (current != null) {
                        String parentID = SpireAnniversary5Mod.cardParentMap.get(current.cardID);
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
                        //Remove all cards from targets that have the same parent as card added to hand
                        targets.removeIf(c -> SpireAnniversary5Mod.cardParentMap.get(c.cardID).equals(parentID));
                    }
                }
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}