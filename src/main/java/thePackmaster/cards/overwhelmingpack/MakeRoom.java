package thePackmaster.cards.overwhelmingpack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.patches.overwhelmingpack.MakeRoomPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MakeRoom extends AbstractOverwhelmingCard {
    public final static String ID = makeID("MakeRoom");

    public MakeRoom() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);

        this.magicNumber = this.baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeRoomAction(magicNumber));
    }

    public static class MakeRoomAction extends AbstractGameAction {
        public MakeRoomAction(int draw) {
            this.amount = draw;
        }

        @Override
        public void update() {
            this.isDone = true;

            if (AbstractDungeon.player.hand.size() >= BaseMod.MAX_HAND_SIZE && !AbstractDungeon.player.hand.isEmpty()) {
                //The hand is full, play the leftmost card.
                //This action is on pause until the card is used.
                //A new one will be queued after.

                AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);

                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (AbstractDungeon.actionManager.cardQueue.stream().anyMatch(queueItem->queueItem.card==c))
                        continue; //Card is already queued

                    c.isInAutoplay = true;
                    if (!c.canUse(AbstractDungeon.player, m)) {
                        c.isInAutoplay = false;
                        continue; //No good
                    }
                    c.isInAutoplay = false;

                    MakeRoomPatch.makeRoom(c, m, new MakeRoomAction(amount));
                    break;
                }
                //If the whole hand is queued already... well, uh... alright.
            }
            else {
                //Draw a card, repeat this action.
                --amount;
                if (amount > 0)
                    addToTop(new MakeRoomAction(amount));
                addToTop(new DrawCardAction(1));
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}