package thePackmaster.summaries;

import thePackmaster.SpireAnniversary5Mod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PackSummaryReader {
    private static final String RATINGS_FILE = "anniv5Resources/summaries/ratings.txt";
    private static final String TAGS_FILE = "anniv5Resources/summaries/tags.txt";

    private static HashMap<String, PackSummary> packSummaries = null;
    public static final String NONE_TAG = "None";

    public static PackSummary getPackSummary(String packID) {
        if (packSummaries == null) {
            packSummaries = readPackSummaries();
            if (packSummaries == null) {
                throw new RuntimeException("Could not read pack summaries.");
            }
        }

        return packSummaries.getOrDefault(packID, null);
    }

    private static HashMap<String, PackSummary> readPackSummaries() {
        InputStream ratingsStream = PackSummaryReader.class.getClassLoader().getResourceAsStream(RATINGS_FILE);
        if (ratingsStream == null) {
            SpireAnniversary5Mod.logger.error("Failed to load ratings.txt (not found?)");
            return null;
        }
        InputStream tagsStream = PackSummaryReader.class.getClassLoader().getResourceAsStream(TAGS_FILE);
        if (tagsStream == null) {
            SpireAnniversary5Mod.logger.error("Failed to load ratings.txt (not found?)");
            return null;
        }

        HashMap<String, PackSummary> packSummaries = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(ratingsStream));

            //The headers are only there for helping people edit the file, so we read and ignore the first line
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] entries = line.split("\\s+");
                String packID = SpireAnniversary5Mod.modID + ":" + entries[0];
                if (!SpireAnniversary5Mod.packsByID.containsKey(packID)) {
                    throw new RuntimeException("Unrecognized pack ID in ratings.txt: " + packID);
                }
                if (entries.length != 6) {
                    SpireAnniversary5Mod.logger.error("Missing or invalid ratings for pack " + packID);
                    continue;
                }

                PackSummary packSummary = new PackSummary();
                packSummary.offense = Integer.parseInt(entries[1], 10);
                packSummary.defense = Integer.parseInt(entries[2], 10);
                packSummary.support = Integer.parseInt(entries[3], 10);
                packSummary.frontload = Integer.parseInt(entries[4], 10);
                packSummary.scaling = Integer.parseInt(entries[5], 10);

                packSummaries.put(packID, packSummary);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(tagsStream));

            //The headers are only there for helping people edit the file, so we read and ignore the first line
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] entries = line.split("\\s+");
                String packID = SpireAnniversary5Mod.modID + ":" + entries[0];
                if (!SpireAnniversary5Mod.packsByID.containsKey(packID)) {
                    throw new RuntimeException("Unrecognized pack ID in tags.txt: " + packID);
                }
                if (entries.length != 2) {
                    SpireAnniversary5Mod.logger.error("Missing or invalid tags for pack " + packID);
                    continue;
                }

                PackSummary packSummary = packSummaries.getOrDefault(packID, null);
                if (packSummary == null) {
                    SpireAnniversary5Mod.logger.error("Tags are defined but ratings are not for pack " + packID);
                    continue;
                }
                String[] tags = entries[1].split(",");
                if (tags[0].equals(NONE_TAG)) {
                    if (tags.length > 1) {
                        throw new RuntimeException("Pack is tagged as None but has other tags too: " + packID);
                    }
                }
                for (String tag : tags) {
                    packSummary.tags.add(tag.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        packDiagnostics(packSummaries);

        return packSummaries;
    }

    private static void packDiagnostics(HashMap<String, PackSummary> packSummaries) {
        Collection<PackSummary> c = packSummaries.values();
        float avgOffense = c.stream().map(s -> s.offense).reduce(0, Integer::sum) / (float)c.size();
        float avgDefense = c.stream().map(s -> s.defense).reduce(0, Integer::sum) / (float)c.size();
        float avgSupport = c.stream().map(s -> s.support).reduce(0, Integer::sum) / (float)c.size();
        float avgFrontload = c.stream().map(s -> s.frontload).reduce(0, Integer::sum) / (float)c.size();
        float avgScaling = c.stream().map(s -> s.scaling).reduce(0, Integer::sum) / (float)c.size();

        float avgOverall = c.stream().map(s -> (s.offense + s.defense + s.support + s.frontload + s.scaling) / 5.0f).reduce(0.0f, Float::sum) / (float)c.size();
        SpireAnniversary5Mod.logger.info("Rating averages: offense/defense/support/frontload/scaling/overall");
        SpireAnniversary5Mod.logger.info(String.format("%.2f/%.2f/%.2f/%.2f/%.2f/%.2f", avgOffense, avgDefense, avgSupport, avgFrontload, avgScaling, avgOverall));

        HashMap<String, Integer> tags = new HashMap<>();
        c.forEach(s -> s.tags.forEach(m -> tags.put(m, tags.getOrDefault(m, 0) + 1)));
        SpireAnniversary5Mod.logger.info("Tag counts:");
        for (Map.Entry<String, Integer> entry : tags.entrySet()) {
            SpireAnniversary5Mod.logger.info(entry.getKey() + ": " + entry.getValue());
        }
    }
}
