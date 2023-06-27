package thePackmaster.summaries;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.apache.commons.lang3.StringUtils;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;
import java.util.stream.Collectors;

public class PackSummaryDisplay {
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID(PackSummaryDisplay.class.getSimpleName()));
    private static String[] TEXT = uiStrings.TEXT;

    public static String getTitle() {
        return TEXT[0];
    }

    public static String getTooltip(AbstractCardPack.PackSummary summary) {
        boolean showSummary = SpireAnniversary5Mod.showPackSummaries();
        StringBuilder sb = new StringBuilder();
        if(showSummary) {
            sb.append(TEXT[1].replace("{0}", getStatString(summary.offense)));
            sb.append(" NL ");
            sb.append(TEXT[2].replace("{0}", getStatString(summary.defense)));
            sb.append(" NL ");
            sb.append(TEXT[3].replace("{0}", getStatString(summary.support)));
            sb.append(" NL ");
            sb.append(TEXT[4].replace("{0}", getStatString(summary.frontload)));
            sb.append(" NL ");
            sb.append(TEXT[5].replace("{0}", getStatString(summary.scaling)));
        }
        if (!summary.tags.isEmpty()) {
            if(showSummary) {
                sb.append(" NL ");
            }
            String tags = summary.tags.stream().map(PackSummaryDisplay::getTagString).collect(Collectors.joining(TEXT[7]));
            sb.append(TEXT[6].replace("{0}", tags));
        }
        return sb.toString();
    }

    private static String getStatString(int value) {
            return StringUtils.repeat("[RatingStarIcon] ", value) + StringUtils.repeat("[RatingDarkStarIcon] ", 5 - value);
    }

    private static String getTagString(String tag) {
        if (!uiStrings.TEXT_DICT.containsKey(tag)) {
            throw new RuntimeException("Unrecognized tag: " + tag);
        }
        return (!tag.equals(AbstractCardPack.PackSummary.NONE_TAG) ? "#y" : "") + uiStrings.TEXT_DICT.get(tag);
    }
}
