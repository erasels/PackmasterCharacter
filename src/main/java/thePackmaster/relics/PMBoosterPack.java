package thePackmaster.relics;

import com.evacipated.cardcrawl.mod.stslib.patches.CenterGridCardSelectScreen;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PMBoosterPack extends AbstractPackmasterRelic {
    public static final String ID = makeID("PMBoosterPack");
    private boolean cardSelected = true;
    AbstractRoom.RoomPhase lastPhase = null;

    public PMBoosterPack() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, null, true);
    }

    @Override
    public void onEquip() {
        openScreen();
    }

    private void openScreen() {
        this.cardSelected = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }

        lastPhase = AbstractDungeon.getCurrRoom().phase;
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        CenterGridCardSelectScreen.centerGridSelect = true;
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        ArrayList<String> cards = SpireAnniversary5Mod.getRandomPackFromAll(new Random(Settings.seed + 41)).getCards();
        for (String s : cards) {

           if (CardLibrary.getCard(s).rarity == AbstractCard.CardRarity.COMMON ||
                   CardLibrary.getCard(s).rarity == AbstractCard.CardRarity.UNCOMMON ||
                   CardLibrary.getCard(s).rarity == AbstractCard.CardRarity.RARE) group.addToTop(CardLibrary.getCard(s).makeCopy());
        }

        AbstractDungeon.gridSelectScreen.open(group, 1, DESCRIPTIONS[1], false, false, false, false);
    }

    @Override
    public void update() {
        super.update();
        if (!this.cardSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
            this.cardSelected = true;
            CenterGridCardSelectScreen.centerGridSelect = false;

            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(AbstractDungeon.gridSelectScreen.selectedCards.get(0).makeCopy(), (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.getCurrRoom().phase = lastPhase;

        }
    }
}
