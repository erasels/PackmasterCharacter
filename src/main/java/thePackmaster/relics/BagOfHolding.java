package thePackmaster.relics;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BagOfHolding extends AbstractPackmasterRelic {
    public static final String ID = makeID("BagOfHolding");

    public BagOfHolding() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void atPreBattle() {
        this.counter = AbstractDungeon.player.masterDeck.size() / 5;
    }

    @Override
    public void atTurnStart() {
        if (!this.grayscale) {
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new GainEnergyAction(1));
            this.counter--;
            incrementEnergyStat();

            if (this.counter <= 0) {
                this.counter = -1;
                this.grayscale = true;
            }
        }
    }

    @Override
    public void onVictory() {
        this.grayscale = false;
        this.counter = -1;
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(HandyHaversack.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(HandyHaversack.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(HandyHaversack.ID);
    }

    @Override
    public String getUpdatedDescription() {
        // Colorize the starter relic's name. Thanks Bard!!! Thanks Nelly!!!!! HAPPY BIRTHDAY, STS MODDING!!!
        String name = new HandyHaversack().name;
        StringBuilder sb = new StringBuilder();
        if(Settings.language==Settings.GameLanguage.ZHS ||Settings.language==Settings.GameLanguage.ZHT){
            sb.append("[#").append(SpireAnniversary5Mod.characterColor.toString()).append("]").append(name).append("[]");
        }else {
            for (String word : name.split(" ")) {
                sb.append("[#").append(SpireAnniversary5Mod.characterColor.toString()).append("]").append(word).append("[] ");
                sb.setLength(sb.length() - 1);
                sb.append("[#").append(SpireAnniversary5Mod.characterColor.toString()).append("]");
            }
        }

        return DESCRIPTIONS[0] + sb + DESCRIPTIONS[1];
    }

    private static final Map<String, Integer> stats = new HashMap<>();
    public static String ENERGY_STAT = "energy";

    public String getStatsDescription() {
        if (stats.get(ENERGY_STAT) == null) {
            return "";
        }
        return DESCRIPTIONS[2].replace("{0}", stats.get(ENERGY_STAT) + "");
    }

    public String getExtendedStatsDescription(int totalCombats, int totalTurns) {
        if (stats.get(ENERGY_STAT) == null) {
            return "";
        }
        DecimalFormat format = new DecimalFormat("#.###");
        float block = stats.get(ENERGY_STAT);
        String energyPerTurn = format.format(block / Math.max(totalTurns, 1));
        String energyPerCombat = format.format(block / Math.max(totalCombats, 1));
        return getStatsDescription() + DESCRIPTIONS[3].replace("{0}", energyPerTurn).replace("{1}", energyPerCombat);
    }

    public void resetStats() {
        stats.put(ENERGY_STAT, 0);
    }

    public JsonElement onSaveStats() {
        Gson gson = new Gson();
        List<Integer> statsToSave = new ArrayList<>();
        statsToSave.add(stats.get(ENERGY_STAT));
        return gson.toJsonTree(statsToSave);
    }

    public void onLoadStats(JsonElement jsonElement) {
        if (jsonElement != null) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            stats.put(ENERGY_STAT, jsonArray.get(0).getAsInt());
        } else {
            resetStats();
        }
    }

    public static void incrementEnergyStat() {
        stats.put(ENERGY_STAT, stats.getOrDefault(ENERGY_STAT, 0) + 1);
    }
}

