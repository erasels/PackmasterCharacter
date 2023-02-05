package thePackmaster.relics;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.PowerTip;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CollectorBadge extends AbstractPackmasterRelic {
    public static final String ID = makeID("CollectorBadge");

    public ArrayList<String> usedPacks = new ArrayList<>();

    public CollectorBadge() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        setDescriptionAfterLoading();
    }

    @Override
    public void atBattleStart() {
        setDescriptionAfterLoading();
        counter = 0;
    }

    @Override
    public void onVictory() {
        usedPacks.clear();
        setDescriptionAfterLoading();
        stopPulse();
        counter = -1;
    }

    @Override
    public void atTurnStart() {
        counter = 0;
        usedPacks.clear();
        setDescriptionAfterLoading();
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction useCardAction) {
        if (SpireAnniversary5Mod.cardParentMap.get(c.cardID) != null) {
            if (!usedPacks.contains(Wiz.getPackByCard(c).name)) {
                usedPacks.add(Wiz.getPackByCard(c).name);
                counter++;
                if (usedPacks.size() == 4) {
                    flash();
                    Wiz.atb(new RelicAboveCreatureAction(Wiz.p(), this));
                    addToBot(new GainEnergyAction(1));
                    incrementEnergyStat();
                }
                setDescriptionAfterLoading();
            }
        }
    }


    private void setDescriptionAfterLoading() {
        if (usedPacks.size() > 3) {
            description = DESCRIPTIONS[0] + DESCRIPTIONS[2];
        } else if (usedPacks.size() > 0) {
            String st = usedPacks.get(0);

            if (usedPacks.size() > 1) {
                for (int i = 1; i < usedPacks.size(); i++) {
                    st = st + ", " + usedPacks.get(i);
                }
            }

            st = st + ".";
            description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + st;
        } else {
            description = DESCRIPTIONS[0];
        }
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    private static final Map<String, Integer> stats = new HashMap<>();
    public static String ENERGY_STAT = "energy";

    public String getStatsDescription() {
        if (stats.get(ENERGY_STAT) == null) {
            return "";
        }
        return DESCRIPTIONS[3].replace("{0}", stats.get(ENERGY_STAT) + "");
    }

    public String getExtendedStatsDescription(int totalCombats, int totalTurns) {
        if (stats.get(ENERGY_STAT) == null) {
            return "";
        }
        DecimalFormat format = new DecimalFormat("#.###");
        float block = stats.get(ENERGY_STAT);
        String energyPerTurn = format.format(block / Math.max(totalTurns, 1));
        String energyPerCombat = format.format(block / Math.max(totalCombats, 1));
        return getStatsDescription() + DESCRIPTIONS[4].replace("{0}", energyPerTurn).replace("{1}", energyPerCombat);
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
