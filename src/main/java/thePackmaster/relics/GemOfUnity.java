package thePackmaster.relics;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.Wiz;

import java.text.DecimalFormat;
import java.util.*;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GemOfUnity extends AbstractPackmasterRelic {
    public static final String ID = makeID(GemOfUnity.class.getSimpleName());
    private static final ArrayList<AbstractCardPack> packsPlayed = new ArrayList<>();

    public GemOfUnity() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT);
        resetCounter();
        description = DESCRIPTIONS[0];
        this.resetTips();
    }

    @Override
    public void atBattleStartPreDraw() {
        resetCounter();
        description = getUpdatedDescription();
        this.resetTips();
    }

    @Override
    public void onVictory() {
        resetCounter();
        description = DESCRIPTIONS[0];
        this.resetTips();
    }

    public void resetCounter(){
        if (AbstractDungeon.isPlayerInDungeon()) {
            packsPlayed.clear();
            counter = SpireAnniversary5Mod.currentPoolPacks.size();
            this.description = getUpdatedDescription();
            this.resetTips();
            grayscale = false;
        }
    }

    private void resetTips() {
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (grayscale) {
            return;
        }
        AbstractCardPack pack = Wiz.getPackByCard(c);
        if (pack != null && SpireAnniversary5Mod.currentPoolPacks.contains(pack) && !packsPlayed.contains(pack)){
            counter--;
            packsPlayed.add(pack);
            if (counter == 0){
                flash();
                AbstractDungeon.player.heal(5);
                addToBot(new GainBlockAction(Wiz.p(), 20));
                Wiz.applyToSelf(new StrengthPower(Wiz.p(), 1));
                Wiz.applyToSelf(new DexterityPower(Wiz.p(), 1));
                grayscale = true;
                incrementTimesStat();
            }
        }
        this.description = getUpdatedDescription();
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
        super.onPlayCard(c, m);
    }

    public String getUpdatedDescription() {
        StringBuilder desc = new StringBuilder(DESCRIPTIONS[0]);
        if (AbstractDungeon.isPlayerInDungeon()) {
            if (packsPlayed.size() > 0) {
                desc.append(" NL ");
                desc.append(" NL ");
                desc.append(DESCRIPTIONS[1]);
                for (AbstractCardPack p : packsPlayed) {
                    desc.append(" NL ");
                    desc.append(p.name);
                }
            }
            if (!this.grayscale) {
                desc.append(" NL ");
                desc.append(" NL ");
                desc.append(DESCRIPTIONS[4]);
                for (AbstractCardPack p : SpireAnniversary5Mod.currentPoolPacks) {
                    if (packsPlayed.stream().noneMatch(pack -> pack.packID.equals(p.packID))) {
                        desc.append(" NL ");
                        desc.append(p.name);
                    }
                }
            }
        }
        return desc.toString();
    }

    private static final Map<String, Integer> stats = new HashMap<>();
    public static String TIMES_STAT = "times";

    public String getStatsDescription() {
        if (stats.get(TIMES_STAT) == null) {
            return "";
        }
        return DESCRIPTIONS[2].replace("{0}", stats.get(TIMES_STAT) + "");
    }

    public String getExtendedStatsDescription(int totalCombats, int totalTurns) {
        if (stats.get(TIMES_STAT) == null) {
            return "";
        }
        DecimalFormat format = new DecimalFormat("#.###");
        float block = stats.get(TIMES_STAT);
        String timesPerCombat = format.format(block / Math.max(totalCombats, 1));
        return getStatsDescription() + DESCRIPTIONS[3].replace("{0}", timesPerCombat);
    }

    public void resetStats() {
        stats.put(TIMES_STAT, 0);
    }

    public JsonElement onSaveStats() {
        Gson gson = new Gson();
        List<Integer> statsToSave = new ArrayList<>();
        statsToSave.add(stats.get(TIMES_STAT));
        return gson.toJsonTree(statsToSave);
    }

    public void onLoadStats(JsonElement jsonElement) {
        if (jsonElement != null) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            stats.put(TIMES_STAT, jsonArray.get(0).getAsInt());
        } else {
            resetStats();
        }
    }

    public static void incrementTimesStat() {
        stats.put(TIMES_STAT, stats.getOrDefault(TIMES_STAT, 0) + 1);
    }
}
