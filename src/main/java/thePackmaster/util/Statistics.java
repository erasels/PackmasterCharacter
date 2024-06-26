package thePackmaster.util;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import org.apache.commons.lang3.StringUtils;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.evenoddpack.SwordAndBoard;
import thePackmaster.cards.transmutationpack.DimensionalIcicles;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.packs.MonsterHunterPack;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static thePackmaster.SpireAnniversary5Mod.cardParentMap;

public class Statistics {
    public static final boolean LOG_STATISTICS = true;

    public static void logStatistics() {
        if(LOG_STATISTICS) {
            //logCardStats();
            //logPackAuthors();
            logCardRaritiesJSON();
        }
    }

    private static <T> String getSummaryString(HashMap<T, Integer> m, Function<T, Integer> getComparisonValue, Function<T, String> getName) {
        return m.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> getComparisonValue.apply(e.getKey())))
                .map(e -> getName.apply(e.getKey()) + ": " + e.getValue())
                .collect(Collectors.joining(", "));
    }

    public static void logCardStats() {
        // This is here so that developers putting together stats can enable and run it without making things take longer
        // to load for everyone (even if the impact is only in the range of 10-20ms)
        // Could be done with some kind of build config for this, but implementing that seems like overkill given that the
        // goal here is to let one or two developers occasional calculate these numbers for informational purposes
        SpireAnniversary5Mod.logger.info("Calculating pack and card statistics");
        int numPacks = SpireAnniversary5Mod.unfilteredAllPacks.size();
        List<String> noAttacks = new ArrayList<>();
        List<String> noSkills = new ArrayList<>();
        List<String> noPowers = new ArrayList<>();
        HashMap<AbstractCard.CardRarity, HashMap<Integer, Integer>> packRarities = new HashMap<>();
        List<String> anomalousRarityPacks = new ArrayList<>();
        for (AbstractCardPack p : SpireAnniversary5Mod.unfilteredAllPacks.stream().sorted(Comparator.comparing(p -> p.name)).collect(Collectors.toList())) {
            boolean hasAttack = p.cards.stream().filter(c -> c.rarity == AbstractCard.CardRarity.COMMON || c.rarity == AbstractCard.CardRarity.UNCOMMON || c.rarity == AbstractCard.CardRarity.RARE)
                    .anyMatch(c -> c.type == AbstractCard.CardType.ATTACK);
            boolean hasSkill = p.cards.stream().filter(c -> c.rarity == AbstractCard.CardRarity.COMMON || c.rarity == AbstractCard.CardRarity.UNCOMMON || c.rarity == AbstractCard.CardRarity.RARE)
                    .anyMatch(c -> c.type == AbstractCard.CardType.SKILL);
            boolean hasPower = p.cards.stream().filter(c -> c.rarity == AbstractCard.CardRarity.COMMON || c.rarity == AbstractCard.CardRarity.UNCOMMON || c.rarity == AbstractCard.CardRarity.RARE)
                    .anyMatch(c -> c.type == AbstractCard.CardType.POWER);
            if (!hasAttack) { noAttacks.add(p.name); }
            if (!hasSkill) { noSkills.add(p.name); }
            if (!hasPower) { noPowers.add(p.name); }
            long commons = p.cards.stream().filter(c -> c.rarity == AbstractCard.CardRarity.COMMON).count();
            long uncommons = p.cards.stream().filter(c -> c.rarity == AbstractCard.CardRarity.UNCOMMON).count();
            long rares = p.cards.stream().filter(c -> c.rarity == AbstractCard.CardRarity.RARE).count();
            Map<AbstractCard.CardRarity, List<AbstractCard>> rarityCounts = p.cards.stream().collect(Collectors.groupingBy(c -> c.rarity));
            for (Map.Entry<AbstractCard.CardRarity, List<AbstractCard>> e : rarityCounts.entrySet()) {
                if (!packRarities.containsKey(e.getKey())) {
                    packRarities.put(e.getKey(), new HashMap<>());
                }
                HashMap<Integer, Integer> rarities = packRarities.get(e.getKey());
                int n = e.getValue().size();
                rarities.put(n, rarities.getOrDefault(n, 0) + 1);
                if ((e.getKey() == AbstractCard.CardRarity.COMMON || e.getKey() == AbstractCard.CardRarity.RARE) && e.getValue().size() > 4) {
                    anomalousRarityPacks.add(p.name);
                }
                if (e.getKey() == AbstractCard.CardRarity.UNCOMMON && e.getValue().size() > 5) {
                    anomalousRarityPacks.add(p.name);
                }
            }
        }

        Function<String, String> formatName = s -> s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1).toLowerCase(Locale.ROOT);
        Function<List<String>, String> t = l -> String.join(", ", l);
        String commonInfo = getSummaryString(packRarities.get(AbstractCard.CardRarity.COMMON), k -> k, k -> k + "");
        String uncommonInfo = getSummaryString(packRarities.get(AbstractCard.CardRarity.UNCOMMON), k -> k, k -> k + "");
        String rareInfo = getSummaryString(packRarities.get(AbstractCard.CardRarity.RARE), k -> k, k -> k + "");
        SpireAnniversary5Mod.logger.info("Packs: " + numPacks);
        SpireAnniversary5Mod.logger.info("Packs without normal rarity: Attacks: " + t.apply(noAttacks) + ", Skills: " + t.apply(noSkills) + ", Powers: " + t.apply(noPowers));
        SpireAnniversary5Mod.logger.info("Common counts: " + commonInfo);
        SpireAnniversary5Mod.logger.info("Uncommon counts: " + uncommonInfo);
        SpireAnniversary5Mod.logger.info("Rare counts: " + rareInfo);
        SpireAnniversary5Mod.logger.info("Packs with anomalous rarity counts: " + t.apply(anomalousRarityPacks));

        List<AbstractCard> cards = SpireAnniversary5Mod.unfilteredAllPacks.stream()
                .flatMap(p -> p.getCards().stream())
                .map(CardLibrary::getCard)
                .collect(Collectors.toList());
        HashMap<Integer, Integer> costs = new HashMap<>();
        HashMap<AbstractCard.CardType, Integer> types = new HashMap<>();
        HashMap<AbstractCard.CardRarity, Integer> rarities = new HashMap<>();
        HashMap<AbstractCard.CardColor, Integer> colors = new HashMap<>();
        List<String> specialRarityNotColorless = new ArrayList<>();
        int aoeattack = 0;
        int block = 0;
        int exhaust = 0;
        int exhaustive = 0;
        int ethereal = 0;
        int retain = 0;
        int innate = 0;
        int strike = 0;
        int healing = 0;
        int unnate = 0;
        int startup = 0;
        int pickup = 0;
        List<String> notIronWaves = Arrays.asList(DimensionalIcicles.ID, SwordAndBoard.ID);
        List<String> ironWaves = new ArrayList<>();
        int ironwave = 0;
        int upgradeCost = 0;
        int upgradeDontExhaust = 0;
        int upgradeExhaustive = 0;
        int upgradeNotEthereal = 0;
        int upgradeRetain = 0;
        int upgradeInnate = 0;
        int upgradeUnnate = 0;
        int multiUpgrade = 0;
        for (AbstractCard c : cards) {
            AbstractCard cu = c.makeCopy();
            cu.upgrade();
            costs.put(c.cost, costs.getOrDefault(c.cost, 0) + 1);
            types.put(c.type, types.getOrDefault(c.type, 0) + 1);
            rarities.put(c.rarity, rarities.getOrDefault(c.rarity, 0) + 1);
            colors.put(c.color, colors.getOrDefault(c.color, 0) + 1);
            if (c.rarity == AbstractCard.CardRarity.SPECIAL && c.color != AbstractCard.CardColor.COLORLESS && !cardParentMap.get(c.cardID).equals(MonsterHunterPack.ID)) { specialRarityNotColorless.add(c.cardID); }
            if (c.type == AbstractCard.CardType.ATTACK && c.baseDamage >= 0 && (boolean) ReflectionHacks.getPrivate(c, AbstractCard.class, "isMultiDamage")) { aoeattack++; }
            if (c.baseBlock >= 0) { block++; }
            if (c.exhaust) { exhaust++; }
            if (ExhaustiveField.ExhaustiveFields.baseExhaustive.get(c) > 0) { exhaustive++; }
            if (c.isEthereal) { ethereal++; }
            if (c.selfRetain) { retain++; }
            if (c.isInnate) { innate++; }
            if (c.hasTag(AbstractCard.CardTags.STRIKE)) { strike++; }
            if (c.hasTag(AbstractCard.CardTags.HEALING)) { healing++; }
            if ((c instanceof AbstractPackmasterCard) && ((AbstractPackmasterCard)c).isUnnate) { unnate++; }
            if (c instanceof StartupCard) { startup++; }
            if (c instanceof OnObtainCard) { pickup++; }
            if (c.type == AbstractCard.CardType.ATTACK && c.baseDamage >= 0 && c.baseBlock >= 0 && !notIronWaves.contains(c.cardID)) { ironwave++; ironWaves.add(c.name); }
            if (c.cost > cu.cost) { upgradeCost++; }
            if (c.exhaust && !cu.exhaust && ExhaustiveField.ExhaustiveFields.baseExhaustive.get(cu) == -1) { upgradeDontExhaust++; }
            if (c.exhaust && !cu.exhaust && ExhaustiveField.ExhaustiveFields.baseExhaustive.get(cu) > 0) { upgradeExhaustive++; }
            if (c.isEthereal && !cu.isEthereal) { upgradeNotEthereal++; }
            if (!c.selfRetain && cu.selfRetain) { upgradeRetain++; }
            if (!c.isInnate && cu.isInnate) { upgradeInnate++; }
            if ((c instanceof AbstractPackmasterCard) && !((AbstractPackmasterCard)c).isUnnate && ((AbstractPackmasterCard)cu).isUnnate) { upgradeUnnate++; }
            if (cu.canUpgrade()) { multiUpgrade++; }
        }

        String costInfo = getSummaryString(costs, e -> e, k -> k + "");
        String typeInfo = getSummaryString(types, Enum::ordinal, k -> formatName.apply(k.name()));
        String rarityInfo = getSummaryString(rarities, Enum::ordinal, k -> formatName.apply(k.name()));
        String colorInfo = getSummaryString(colors, Enum::ordinal, k -> formatName.apply(k.name()));
        SpireAnniversary5Mod.logger.info("Cards: " + cards.size());
        SpireAnniversary5Mod.logger.info("Costs: " + costInfo);
        SpireAnniversary5Mod.logger.info("Types: " + typeInfo);
        SpireAnniversary5Mod.logger.info("Rarities: " + rarityInfo);
        SpireAnniversary5Mod.logger.info("Colors: " + colorInfo);
        SpireAnniversary5Mod.logger.info("Mechanics: AoE damage: " + aoeattack + ", Block: " + block + ", Exhaust: " + exhaust + ", Exhaustive: " + exhaustive + ", Ethereal: " + ethereal + ", Retain: " + retain + ", Innate: " + innate + ", Strike: " + strike + ", Healing: " + healing + ", Iron Waves: " + ironwave + ", Multiple upgrades: " + multiUpgrade);
        SpireAnniversary5Mod.logger.info("Other mechanics: Unnate: " + unnate + ", Startup: " + startup + ", Pickup: " + pickup);
        SpireAnniversary5Mod.logger.info("Upgrades that: Reduce cost: " + upgradeCost + ", Remove exhaust: " + upgradeDontExhaust + ", Exhaust to exhaustive: " + upgradeExhaustive + ", Remove ethereal: " + upgradeNotEthereal + ", Add innate: " + upgradeInnate + ", Add unnate: " + upgradeUnnate + ", Add retain: " + upgradeRetain);
        SpireAnniversary5Mod.logger.info("Iron waves: " + t.apply(ironWaves));

        HashSet<String> cardNames = new HashSet<>();
        boolean foundDuplicate = false;
        for (AbstractCard card : cards) {
            if(cardNames.contains(card.name)) {
                SpireAnniversary5Mod.logger.info("Duplicate card name: " + card.name);
                foundDuplicate = true;
            }
            cardNames.add(card.name);
        }
        if (!foundDuplicate) {
            SpireAnniversary5Mod.logger.info("No duplicate card names.");
        }

        if (!specialRarityNotColorless.isEmpty()) {
            SpireAnniversary5Mod.logger.info("Colorless cards that aren't special rarity, other than the Monster Hunter cards: " + t.apply(specialRarityNotColorless));
        }
        else {
            SpireAnniversary5Mod.logger.info("No colorless cards that aren't special rarity.");
        }
    }

    public static void logPackAuthors() {
        // This is here so that developers putting together stats can enable and run it without making things take longer
        // to load for everyone (even if the impact is small)
        String authorCounts = SpireAnniversary5Mod.unfilteredAllPacks.stream()
                .collect(Collectors.groupingBy(p -> p.author, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.joining("\n"));
        SpireAnniversary5Mod.logger.info("Pack count by author:\n" + authorCounts);
    }

    public static void logCardRaritiesJSON() {
        // Used for PM metrics
        HashMap<String, String> cardIdToRarity = new HashMap<>();
        for (AbstractCardPack p : SpireAnniversary5Mod.unfilteredAllPacks.stream().sorted(Comparator.comparing(p -> p.name)).collect(Collectors.toList())) {
            for(AbstractCard c : p.cards) {
                cardIdToRarity.put(c.cardID, StringUtils.capitalize(c.rarity.toString().toLowerCase()));
            }
        }
        Gson gson = new Gson();
        Type typeObject = new TypeToken<HashMap<String, String>>() {}.getType();
        String gsonData = gson.toJson(cardIdToRarity, typeObject);
        SpireAnniversary5Mod.logger.info("Pack cards to rarity JSON:\n" + gsonData);
    }
}
