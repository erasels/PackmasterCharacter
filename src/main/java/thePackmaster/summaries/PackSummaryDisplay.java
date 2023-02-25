package thePackmaster.summaries;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;

import java.util.stream.Collectors;

public class PackSummaryDisplay {
    public static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID(PackSummaryDisplay.class.getSimpleName()));
    public static String[] TEXT = uiStrings.TEXT;

    public static String getTitle() {
        return TEXT[0];
    }

    public static String getTooltip(PackSummary summary) {
        StringBuilder sb = new StringBuilder();
        sb.append(TEXT[1].replace("{0}", summary.offense + ""));
        sb.append(" NL ");
        sb.append(TEXT[2].replace("{0}", summary.defense + ""));
        sb.append(" NL ");
        sb.append(TEXT[3].replace("{0}", summary.support + ""));
        sb.append(" NL ");
        sb.append(TEXT[4].replace("{0}", summary.frontload + ""));
        sb.append(" NL ");
        sb.append(TEXT[5].replace("{0}", summary.scaling + ""));
        if (!summary.tags.isEmpty()) {
            sb.append(" NL ");
            String tags = summary.tags.stream().map(m -> "#y" + uiStrings.TEXT_DICT.get(m)).collect(Collectors.joining(", "));
            sb.append(TEXT[6].replace("{0}", tags));
        }
        return sb.toString();
    }
}
