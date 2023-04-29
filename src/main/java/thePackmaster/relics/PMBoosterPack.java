package thePackmaster.relics;

import com.evacipated.cardcrawl.mod.stslib.patches.CenterGridCardSelectScreen;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PMBoosterPack extends AbstractPackmasterRelic {
    public static final String ID = makeID("PMBoosterPack");
    private boolean cardSelected = true;
    AbstractRoom.RoomPhase lastPhase = null;

    public PMBoosterPack() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, true);
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
        AbstractCardPack pack = SpireAnniversary5Mod.getRandomPackFromAll(new Random(Settings.seed + 41));
        ArrayList<String> cards = pack.getCards();
        for (String s : cards) {
            if (CardLibrary.getCard(s).rarity == AbstractCard.CardRarity.COMMON ||
                    CardLibrary.getCard(s).rarity == AbstractCard.CardRarity.UNCOMMON ||
                    CardLibrary.getCard(s).rarity == AbstractCard.CardRarity.RARE) {
                AbstractCard toAdd = CardLibrary.getCard(s).makeCopy();
                AbstractDungeon.player.relics.forEach(q -> q.onPreviewObtainCard(toAdd));
                group.addToTop(toAdd);
            }

        }
        group.sortByRarity(false);

        recordStats(pack.packID);
        AbstractDungeon.gridSelectScreen.open(group, 1, DESCRIPTIONS[1], false, false, false, false);
    }

    @Override
    public void update() {
        super.update();
        if (!this.cardSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
            this.cardSelected = true;
            CenterGridCardSelectScreen.centerGridSelect = false;

            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(AbstractDungeon.gridSelectScreen.selectedCards.get(0).makeCopy(), (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.getCurrRoom().phase = lastPhase;

        }
    }

    private static final Map<String, String> stats = new HashMap<>();
    private static final String PACK_STAT = "pack";

    public String getStatsDescription() {
        AbstractCardPack pack = SpireAnniversary5Mod.packsByID.getOrDefault(stats.get(PACK_STAT), null);
        return pack != null ? DESCRIPTIONS[2].replace("{0}", pack.name) : "";
    }

    public String getExtendedStatsDescription(int totalCombats, int totalTurns) {
        return getStatsDescription();
    }

    public void resetStats() {
        stats.put(PACK_STAT, null);
    }

    public JsonElement onSaveStats() {
        Gson gson = new Gson();
        List<String> statsToSave = new ArrayList<>();
        statsToSave.add(stats.get(PACK_STAT));
        return gson.toJsonTree(statsToSave);
    }

    public void onLoadStats(JsonElement jsonElement) {
        if (jsonElement != null) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            JsonElement pack = jsonArray.get(0);
            stats.put(PACK_STAT, !pack.isJsonNull() ? pack.getAsString() : null);
        } else {
            resetStats();
        }
    }

    public static void recordStats(String pack) {
        stats.put(PACK_STAT, pack);
    }
}
