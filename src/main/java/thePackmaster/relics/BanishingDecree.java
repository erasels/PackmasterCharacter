package thePackmaster.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.packs.AbstractPackPreviewCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BanishingDecree extends AbstractPackmasterRelic //implements CustomBottleRelic, CustomSavable<Integer>
{
    public static final String ID = makeID("BanishingDecree");
    public AbstractCard card = null;
    private boolean cardSelected = true;

    public BanishingDecree() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT);
    }

    /*
    @Override
    public Integer onSave() {
         //TODO
        return 0;
    }

    @Override
    public void onLoad(Integer cardIndex) {
        //TODO when pools are saving and loading

        if (cardIndex == null) {
            return;
        }
        if (cardIndex >= 0 && cardIndex < AbstractDungeon.player.masterDeck.group.size()) {
            card = AbstractDungeon.player.masterDeck.group.get(cardIndex);
            if (card != null) {
                BottledD8Patch.inD8.set(card, true);
                setDescriptionAfterLoading();
            }
        }


    }
    */

    @Override
    public void onEquip() {
        cardSelected = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : SpireAnniversary5Mod.getPreviewCardsFromCurrentSet()) {
            tmp.addToTop(c);
        }
        AbstractDungeon.gridSelectScreen.open(tmp,
                1, DESCRIPTIONS[1] + name + ".",
                false, false, false, false);
    }

    @Override
    public void onUnequip() {
        //TODO
        /*
        if (card != null) {
            AbstractCard cardInDeck = AbstractDungeon.player.masterDeck.getSpecificCard(card);
            if (cardInDeck != null) {
                BottledD8Patch.inD8.set(cardInDeck, false);
            }
        }

         */
    }

    @Override
    public void update() {
        super.update();
        if (!cardSelected && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            cardSelected = true;
            AbstractCardPack cp = Wiz.getPackByCard(card);
            SpireAnniversary5Mod.currentPoolPacks.remove(cp);
            card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);

            //TODO - will this be needed for saving/loading?
            //BottledD8Patch.inD8.set(card, true);
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            setDescriptionAfterLoading();
        }
    }


    /*
    @Override
    public Predicate<AbstractCard> isOnCard() {

        return BottledD8Patch.inD8::get;
    }

     */

    private void setDescriptionAfterLoading() {
        this.description = this.DESCRIPTIONS[2] + FontHelper.colorString(this.card.name, "y") + this.DESCRIPTIONS[3];
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
