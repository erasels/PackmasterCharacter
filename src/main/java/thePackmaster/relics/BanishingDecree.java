package thePackmaster.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
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
import static thePackmaster.SpireAnniversary5Mod.packsByID;

public class BanishingDecree extends AbstractPackmasterRelic implements CustomSavable<String> {
    public static final String ID = makeID("BanishingDecree");
    public String bannedPack = null;
    private boolean cardSelected = true;

    public BanishingDecree() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT);
    }

        @Override
        public String onSave() {
            return bannedPack;
        }

        @Override
        public void onLoad(String bannedPackID) {
            bannedPack = bannedPackID;
            if (bannedPack != null) setDescriptionAfterLoading();
        }

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
        if (bannedPack != null) {
            SpireAnniversary5Mod.currentPoolPacks.add(packsByID.get(bannedPack));
            CardCrawlGame.dungeon.initializeCardPools();
        }

    }


    @Override
    public void update() {
        super.update();
        if (!cardSelected && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            cardSelected = true;
            AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractCardPack cp = Wiz.getPackByCard(card);
            bannedPack = cp.name;
            SpireAnniversary5Mod.currentPoolPacks.remove(cp);
            CardCrawlGame.dungeon.initializeCardPools();
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            setDescriptionAfterLoading();
        }
    }

    private void setDescriptionAfterLoading() {
        this.description = this.DESCRIPTIONS[2] + packsByID.get(bannedPack).name + this.DESCRIPTIONS[3];
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
