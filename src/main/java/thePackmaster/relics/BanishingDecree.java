package thePackmaster.relics;

import basemod.abstracts.CustomSavable;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.Wiz;

import java.util.*;
import java.util.stream.Collectors;

import static thePackmaster.SpireAnniversary5Mod.*;

public class BanishingDecree extends AbstractPackmasterRelic implements CustomSavable<ArrayList<String>> {
    public static final String ID = makeID("BanishingDecree");
    public String bannedPackID = null;
    public String newPackID = null;
    private boolean cardSelected = true;
    private boolean cardSelected2 = true;

    public BanishingDecree() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT);
    }

    // We should really only save and load the IDs, but this was originally written to save and load the names, and
    // we've kept that around to avoid potential backwards compatibility issues (e.g. breaking runs that people were in
    // the middle of when they get a code update)
    @Override
    public ArrayList<String> onSave() {
        ArrayList<String> saves = new ArrayList<>();
        AbstractCardPack bannedPack = packsByID.getOrDefault(bannedPackID, null);
        AbstractCardPack newPack = packsByID.getOrDefault(newPackID, null);
        saves.add(bannedPack != null ? bannedPack.name : null);
        saves.add(newPack != null ? newPack.name : null);
        saves.add(bannedPackID);
        saves.add(newPackID);
        return saves;
    }

    @Override
    public void onLoad(ArrayList<String> packNamesAndIDs) {
        bannedPackID = packNamesAndIDs.get(2);
        newPackID = packNamesAndIDs.get(3);
        if (packsByID.containsKey(bannedPackID) && packsByID.containsKey(newPackID)) {
            setDescriptionAfterLoading();
            recordStats(bannedPackID, newPackID);
        }
    }

    @Override
    public void onEquip() {
        cardSelected = false;
        cardSelected2 = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : SpireAnniversary5Mod.getPreviewCardsFromCurrentSet()) {
            tmp.addToTop(c);
        }
        AbstractDungeon.gridSelectScreen.open(tmp,
                1, DESCRIPTIONS[1] + name + DESCRIPTIONS[7],
                false, false, false, false);
    }

    @Override
    public void update() {
        super.update();
        if ((!cardSelected || !cardSelected2) & !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            if (!cardSelected) {
                cardSelected = true;
                AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                AbstractCardPack cp = Wiz.getPackByCard(card);
                bannedPackID = cp.packID;
                SpireAnniversary5Mod.logger.info("Banned Pack" + bannedPackID);
                SpireAnniversary5Mod.currentPoolPacks.remove(cp);
                AbstractDungeon.gridSelectScreen.selectedCards.clear();

                List<AbstractCard> allOtherPackPreviewCards = SpireAnniversary5Mod.getPreviewCardsNotFromCurrentSet();
                allOtherPackPreviewCards.removeIf(c -> c.cardID.equals(cp.previewPackCard.cardID));
                Collections.shuffle(allOtherPackPreviewCards, new Random(Settings.seed).random);
                CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (int i = 0; i < 5; i++) {
                    AbstractCard c = allOtherPackPreviewCards.get(i);
                    tmp.addToTop(c);
                }
                AbstractDungeon.gridSelectScreen.open(tmp,
                        1, DESCRIPTIONS[5] + name + DESCRIPTIONS[7],
                        false, false, false, false);
            } else {
                cardSelected2 = true;
                AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                AbstractCardPack cp = Wiz.getPackByCard(card);
                newPackID = cp.packID;
                SpireAnniversary5Mod.logger.info("New Pack" + newPackID);
                SpireAnniversary5Mod.currentPoolPacks.add(cp);
                CardCrawlGame.dungeon.initializeCardPools();

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                setDescriptionAfterLoading();

                if (AbstractDungeon.getCurrRoom() instanceof ShopRoom) {
                    AbstractDungeon.getCurrRoom().rewards.clear();
                }

                for (int i = 0; i < 3; i++) {
                    RewardItem reward = new RewardItem();
                    reward.cards = getCardsFromPacks(cp.packID, reward.cards.size(), AbstractDungeon.cardRng);
                    for (AbstractRelic relic : AbstractDungeon.player.relics) {
                        for (AbstractCard c : reward.cards) {
                            relic.onPreviewObtainCard(c);
                        }
                    }
                    List<SpawnModificationCard> spawnModificationCards = reward.cards.stream().filter(c -> c instanceof SpawnModificationCard).map(c -> (SpawnModificationCard)c).collect(Collectors.toList());
                    for (SpawnModificationCard c : spawnModificationCards) {
                        c.onRewardListCreated(reward.cards);
                    }
                    AbstractDungeon.getCurrRoom().rewards.add(reward);
                }

                skipDefaultCardRewards = true;
                AbstractDungeon.combatRewardScreen.open(this.DESCRIPTIONS[6]);
                skipDefaultCardRewards = false;
                AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.0F;
                recordStats(bannedPackID, newPackID);
            }
        }
    }

    private void setDescriptionAfterLoading() {
        SpireAnniversary5Mod.logger.info("Banned Pack" + bannedPackID);
        SpireAnniversary5Mod.logger.info("New Pack" + newPackID);
        String bannedPackName = packsByID.get(bannedPackID).name;
        String newPackName = packsByID.get(newPackID).name;
        this.description = this.DESCRIPTIONS[2] + bannedPackName + this.DESCRIPTIONS[3];
        this.description = this.description + " NL " + this.DESCRIPTIONS[2] + newPackName + this.DESCRIPTIONS[4];
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    private static final Map<String, String> stats = new HashMap<>();
    private static final String BANNED_STAT = "banned";
    private static final String NEW_STAT = "new";

    public String getStatsDescription() {
        AbstractCardPack bannedPack = SpireAnniversary5Mod.packsByID.getOrDefault(stats.get(BANNED_STAT), null);
        AbstractCardPack newPack = SpireAnniversary5Mod.packsByID.getOrDefault(stats.get(NEW_STAT), null);
        return bannedPack != null && newPack != null ? DESCRIPTIONS[10].replace("{0}", bannedPack.name).replace("{1}", newPack.name) : "";
    }

    public String getExtendedStatsDescription(int totalCombats, int totalTurns) {
        return getStatsDescription();
    }

    public void resetStats() {
        stats.put(BANNED_STAT, null);
        stats.put(NEW_STAT, null);
    }

    public JsonElement onSaveStats() {
        Gson gson = new Gson();
        List<String> statsToSave = new ArrayList<>();
        statsToSave.add(stats.get(BANNED_STAT));
        statsToSave.add(stats.get(NEW_STAT));
        return gson.toJsonTree(statsToSave);
    }

    public void onLoadStats(JsonElement jsonElement) {
        if (jsonElement != null) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            JsonElement bannedPackID = jsonArray.get(0);
            JsonElement newPackID = jsonArray.get(1);
            stats.put(BANNED_STAT, !bannedPackID.isJsonNull() ? bannedPackID.getAsString() : null);
            stats.put(NEW_STAT, !newPackID.isJsonNull() ? newPackID.getAsString() : null);
        } else {
            resetStats();
        }
    }

    public static void recordStats(String bannedPackID, String newPackID) {
        stats.put(BANNED_STAT, bannedPackID);
        stats.put(NEW_STAT, newPackID);
    }
}
