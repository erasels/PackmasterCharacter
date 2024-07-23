package thePackmaster.relics;

import basemod.abstracts.CustomSavable;
import com.evacipated.cardcrawl.mod.stslib.patches.CenterGridCardSelectScreen;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;

import java.util.*;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.packsByID;

public class PMBoosterBox extends AbstractPackmasterRelic implements CustomSavable<ArrayList<String>> {

    public static final String ID = makeID("PMBoosterBox");

    int numPicked = 0;
    private boolean cardSelected = true;
    private String myPackOne = "";
    private String myPackTwo = "";
    private String myPackThree = "";
    public ArrayList<String> myPacks = new ArrayList<>();


    public PMBoosterBox() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, true);
    }

    @Override
    public void onEquip() {
        openScreen();
    }

    @Override
    public ArrayList<String> onSave() {
        ArrayList<String> myList = new ArrayList<>();
        myList.add(myPackOne);
        myList.add(myPackTwo);
        myList.add(myPackThree);
        //  myList.add(myPackFour);
        return myList;
    }

    @Override
    public void onLoad(ArrayList<String> abstractCards) {
        myPackOne = abstractCards.get(0);
        myPackTwo = abstractCards.get(1);
        myPackThree = abstractCards.get(2);
        if (AbstractDungeon.player != null) {
            setDescriptionAfterLoading();
        }
        makeIDArray();
    }

    public void makeIDArray() {
        myPacks.clear();
        myPacks.add(myPackOne);
        myPacks.add(myPackTwo);
        myPacks.add(myPackThree);
    }

    private void openScreen() {
        this.cardSelected = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }

        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        CenterGridCardSelectScreen.centerGridSelect = true;
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        ArrayList<AbstractCardPack> packs = new ArrayList<>(SpireAnniversary5Mod.allPacks);
        if (myPackOne != null) {
            if (!myPackOne.isEmpty()) {
                packs.remove(packsByID.get(myPackOne));
            }
        }
        if (myPackTwo != null) {
            if (!myPackTwo.isEmpty()) {
                packs.remove(packsByID.get(myPackTwo));
            }
        }
        Collections.shuffle(packs, new Random(Settings.seed + 43).random);
        for (AbstractCardPack p : packs) {
            group.addToTop(p.previewPackCard);
            if (group.size() >= 3) {
                break;
            }
        }

        AbstractDungeon.gridSelectScreen.open(group, 1, DESCRIPTIONS[2], false, false, false, false);
    }

    @Override
    public void update() {
        super.update();
        if (!this.cardSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
            this.cardSelected = true;
            CenterGridCardSelectScreen.centerGridSelect = false;

            numPicked++;
            switch (numPicked) {
                case 1:
                    myPackOne = AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID;
                    break;
                case 2:
                    myPackTwo = AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID;
                    break;
                case 3:
                    myPackThree = AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID;
                    break;
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();

            if (numPicked == 3) {
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                recordStats(myPackOne, myPackTwo, myPackThree, 0);
                setDescriptionAfterLoading();
                makeIDArray();
            } else {
                openScreen();
            }
        }
    }

    private void setDescriptionAfterLoading() {
        this.description = getUpdatedDescription();
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    @Override
    public String getUpdatedDescription() {
        String packs = "";
        if (!Objects.equals(myPackOne, "") && myPackOne != null) {
            packs = packs + DESCRIPTIONS[1] + SpireAnniversary5Mod.packsByID.get(myPackOne).name;
        }
        if (!Objects.equals(myPackTwo, "") && myPackTwo != null) {
            packs = packs + ", " + SpireAnniversary5Mod.packsByID.get(myPackTwo).name;
        }
        if (!Objects.equals(myPackThree, "") && myPackThree != null) {
            packs = packs + ", " + SpireAnniversary5Mod.packsByID.get(myPackThree).name;
        }
        return DESCRIPTIONS[0] + packs;
    }

    @Override
    public boolean canSpawn() {
        return Settings.isEndless || AbstractDungeon.floorNum <= 48;
    }

    private static final Map<String, Object> stats = new HashMap<>();
    private static final String PACK1_STAT = "pack1";
    private static final String PACK2_STAT = "pack2";
    private static final String PACK3_STAT = "pack3";
    private static final String REWARDS_STAT = "rewards";

    public String getStatsDescription() {
        AbstractCardPack pack1 = SpireAnniversary5Mod.packsByID.getOrDefault(stats.get(PACK1_STAT), null);
        AbstractCardPack pack2 = SpireAnniversary5Mod.packsByID.getOrDefault(stats.get(PACK2_STAT), null);
        AbstractCardPack pack3 = SpireAnniversary5Mod.packsByID.getOrDefault(stats.get(PACK3_STAT), null);
        String rewards = stats.get(REWARDS_STAT) + "";
        return pack1 != null && pack2 != null && pack3 != null ? DESCRIPTIONS[3].replace("{0}", pack1.name).replace("{1}", pack2.name).replace("{2}", pack3.name).replace("{3}", rewards) : "";
    }

    public String getExtendedStatsDescription(int totalCombats, int totalTurns) {
        return getStatsDescription();
    }

    public void resetStats() {
        stats.put(PACK1_STAT, null);
        stats.put(PACK2_STAT, null);
        stats.put(PACK3_STAT, null);
        stats.put(REWARDS_STAT, 0);
    }

    public JsonElement onSaveStats() {
        Gson gson = new Gson();
        List<Object> statsToSave = new ArrayList<>();
        statsToSave.add(stats.get(PACK1_STAT));
        statsToSave.add(stats.get(PACK2_STAT));
        statsToSave.add(stats.get(PACK3_STAT));
        statsToSave.add(stats.get(REWARDS_STAT));
        return gson.toJsonTree(statsToSave);
    }

    public void onLoadStats(JsonElement jsonElement) {
        if (jsonElement != null) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            JsonElement pack1 = jsonArray.get(0);
            JsonElement pack2 = jsonArray.get(1);
            JsonElement pack3 = jsonArray.get(2);
            JsonElement rewards = jsonArray.get(3);
            stats.put(PACK1_STAT, !pack1.isJsonNull() ? pack1.getAsString() : null);
            stats.put(PACK2_STAT, !pack2.isJsonNull() ? pack2.getAsString() : null);
            stats.put(PACK3_STAT, !pack2.isJsonNull() ? pack3.getAsString() : null);
            stats.put(REWARDS_STAT, !rewards.isJsonNull() ? rewards.getAsInt() : null);
        } else {
            resetStats();
        }
    }

    public static void recordStats(String pack1, String pack2, String pack3, int rewards) {
        stats.put(PACK1_STAT, pack1);
        stats.put(PACK2_STAT, pack2);
        stats.put(PACK3_STAT, pack3);
        stats.put(REWARDS_STAT, rewards);
    }

    public static void incrementRewards() {
        stats.put(REWARDS_STAT, (Integer)stats.getOrDefault(REWARDS_STAT, 0) + 1);
    }
}
