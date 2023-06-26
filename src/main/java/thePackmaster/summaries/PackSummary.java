package thePackmaster.summaries;

import java.util.Arrays;
import java.util.HashSet;

public class PackSummary {
    public PackSummary() {
    }

    public PackSummary(int offense, int defense, int support, int frontload, int scaling) {
        this.offense = offense;
        this.defense = defense;
        this.support = support;
        this.frontload = frontload;
        this.scaling = scaling;
        tags = new HashSet<>();
        tags.add(PackSummaryReader.NONE_TAG);
    }

    public void setTags(String... tags) {
        this.tags.clear();
        this.tags.addAll(Arrays.asList(tags));
    }

    public int offense;
    public int defense;
    public int support;
    public int frontload;
    public int scaling;
    public HashSet<String> tags = new HashSet<>();

}
