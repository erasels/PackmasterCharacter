package thePackmaster.summaries;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.apache.commons.lang3.StringUtils;
import thePackmaster.SpireAnniversary5Mod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class PackSummaryDisplay {
    public static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID(PackSummaryDisplay.class.getSimpleName()));
    public static String[] TEXT = uiStrings.TEXT;

    public static String getTitle() {
        return TEXT[0];
    }

    public static String getTooltip(PackSummary summary) {
        StringBuilder sb = new StringBuilder();
        sb.append(TEXT[1].replace("{0}", getStatString(summary.offense)));
        sb.append(" NL ");
        sb.append(TEXT[2].replace("{0}", getStatString(summary.defense)));
        sb.append(" NL ");
        sb.append(TEXT[3].replace("{0}", getStatString(summary.support)));
        sb.append(" NL ");
        sb.append(TEXT[4].replace("{0}", getStatString(summary.frontload)));
        sb.append(" NL ");
        sb.append(TEXT[5].replace("{0}", getStatString(summary.scaling)));
        if (!summary.tags.isEmpty()) {
            sb.append(" NL ");
            String tags = summary.tags.stream().map(PackSummaryDisplay::getTagString).collect(Collectors.joining(", "));
            sb.append(TEXT[6].replace("{0}", tags));
        }
        return sb.toString();
    }

    private static String getStatString(int value) {
        // Only one of these is active, but we've left around the others for a bit so that we can change which one we
        // use or add config options, if we judge that to be a good idea
        // Most likely, we'll just delete the inactive code here some time after the release of this feature
        int renderMode = 4;
        switch (renderMode) {
            case 1:
                // Circles, number equal to the value, colored red-to-green for low-to-high values
                return StringUtils.repeat("[RatingCircle" + value + "Icon] ", value);
            case 2:
                // Stars, number equal to the value
                return StringUtils.repeat("[RatingStarIcon] ", value);
            case 3:
                // Stars, number equal to the value, plus empty stars up to 5
                return StringUtils.repeat("[RatingStarIcon] ", value) + StringUtils.repeat("[RatingEmptyStarIcon] ", 5 - value);
            case 4:
                // Stars, number equal to the value, plus dark stars up to 5
                return StringUtils.repeat("[RatingStarIcon] ", value) + StringUtils.repeat("[RatingDarkStarIcon] ", 5 - value);
            case 5:
                // Text +s, number equal to the value, colored red-to-green for low-to-high values
                return getStatColor(value) + StringUtils.repeat("+", value);
            default:
                throw new RuntimeException("Bad value");
        }
    }

    private static String getStatColor(int value) {
        switch (value) {
            case 1:
                return "[#FF6563]";
            case 2:
                return "[#FFB3B2]";
            case 3:
                return "[#DDDDDD]";
            case 4:
                return "[#ABF7B1]";
            case 5:
                return "[#7FFF00]";
            default:
                throw new RuntimeException("Unrecognized rating value for pack summary: " + value);
        }
    }

    public static String getTagString(String tag) {
        if (!uiStrings.TEXT_DICT.containsKey(tag)) {
            throw new RuntimeException("Unrecognized tag: " + tag);
        }
        return "#y" + uiStrings.TEXT_DICT.get(tag);
    }
}
