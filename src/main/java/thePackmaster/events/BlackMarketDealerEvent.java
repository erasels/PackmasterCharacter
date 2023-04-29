package thePackmaster.events;

import basemod.abstracts.events.PhasedEvent;
import basemod.abstracts.events.phases.TextPhase;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.Beggar;
import com.megacrit.cardcrawl.events.city.TheLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.ConjurePack;
import thePackmaster.cards.Vexed;
import thePackmaster.hats.Hats;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.packs.CoreSetPack;
import thePackmaster.potions.PackInAJar;
import thePackmaster.relics.BanishingDecree;
import thePackmaster.relics.PMCollection;

import java.util.*;

import static thePackmaster.SpireAnniversary5Mod.*;

public class BlackMarketDealerEvent extends PhasedEvent {
    public static final String ID = makeID("BlackMarketDealerEvent");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private final Set<Integer> availableMarkets = new HashSet<>();

    public BlackMarketDealerEvent() {
        super(ID, eventStrings.NAME, SpireAnniversary5Mod.makeImagePath("events/blackMarket.png"));

        if (!hasEnoughGoldForBuyCard() && !canRemoveCardForSell())
        {
            //If the top option cannot do any of its three options (EXTREMELY rare edge case), only pick from the other two vendors
            availableMarkets.add(1);
            availableMarkets.add(2);
        } else {
            int a = AbstractDungeon.eventRng.random(0, 2);
            int b = (a + 1) % 3;
            availableMarkets.add(a);
            availableMarkets.add(b);
        }


        registerPhase("base", new TextPhase(DESCRIPTIONS[0])
                .addOption(new TextPhase.OptionInfo(OPTIONS[0]).enabledCondition(()->availableMarkets.contains(0), OPTIONS[22]), ((t) -> this.transitionKey("cardDealer")))
                .addOption(new TextPhase.OptionInfo(OPTIONS[1]).enabledCondition(()->availableMarkets.contains(1), OPTIONS[22]), ((t) -> this.transitionKey("magicDealer")))
                .addOption(new TextPhase.OptionInfo(OPTIONS[2]).enabledCondition(()->availableMarkets.contains(2), OPTIONS[22]), ((t) -> this.transitionKey("relicDealer")))
        );

        registerPhase("cardDealer", new TextPhase(DESCRIPTIONS[1])
                .addOption(new TextPhase.OptionInfo(OPTIONS[4] + getGoldCostForBuy() + OPTIONS[5])    //Buy
                        .enabledCondition(this::hasEnoughGoldForBuyCard, OPTIONS[9] + getGoldCostForBuy() + OPTIONS[7])
                        .cardSelectOption("cardBuyEnd", this::getCardsForLibraryEffect, TheLibrary.OPTIONS[4], 1, false, false, false, false,
                                (cards)-> {
                                    AbstractCard c = cards.get(0);
                                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH * .33F, (float) Settings.HEIGHT / 2.0F));

                                    AbstractDungeon.player.loseGold(getGoldCostForBuy());
                                    logMetric(ID, "Buy", Collections.singletonList(c.cardID), null, null, null, null, null, null, 0, 0, 0, 0, 0, getGoldCostForBuy());
                                }
                        )
                )
                .addOption(new TextPhase.OptionInfo(OPTIONS[6] + getGoldGainForSell() + OPTIONS[7])   //Sell
                        .enabledCondition(this::canRemoveCardForSell, OPTIONS[10])
                        .cardSelectOption("cardSellEnd", this::getCardsForDraftedPurge, Beggar.OPTIONS[6], 1, false, false, false, true,
                                (cards) -> {
                                    AbstractCard c = cards.get(0);
                                    AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));
                                    AbstractDungeon.player.masterDeck.removeCard(c);

                                    AbstractDungeon.effectList.add(new RainingGoldEffect(getGoldGainForSell()));
                                    AbstractDungeon.player.gainGold(getGoldGainForSell());
                                    logMetric(ID, "Sell", null, Collections.singletonList(c.cardID), null, null, null, null, null, 0, 0, 0, 0, getGoldGainForSell(), 0);
                                }
                        )
                )
                .addOption(new TextPhase.OptionInfo(OPTIONS[8])     //Trade In
                        .enabledCondition(this::canRemoveCardForSell, OPTIONS[10])
                        .cardSelectOption(null, this::getCardsForDraftedPurge, Beggar.OPTIONS[6], 1, false, false, false, true,
                                (cards) -> {
                                    AbstractCard sold = cards.get(0);
                                    AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(sold, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));
                                    AbstractDungeon.player.masterDeck.removeCard(sold);

                                    //Buy 2
                                    AbstractDungeon.gridSelectScreen.open(getCardsForLibraryEffect(), 2, TheLibrary.OPTIONS[4], false, false, false, false);
                                    currentPhase.setUpdateHandler(
                                            (phase)->{
                                                if (AbstractDungeon.isScreenUp)
                                                    return;

                                                List<String> bought = new ArrayList<>();
                                                if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                                                    float x = Settings.WIDTH * 0.33f;
                                                    for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                                                        bought.add(c.cardID);
                                                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, x, Settings.HEIGHT / 2.0F));
                                                        x += Settings.WIDTH * 0.33f;
                                                    }
                                                    AbstractDungeon.gridSelectScreen.selectedCards.clear();
                                                }

                                                logMetric(ID, "Trade", bought, Collections.singletonList(sold.cardID), null, null, null, null, null, 0, 0, 0, 0, 0, 0);
                                                phase.setUpdateHandler(null);
                                                transitionKey("cardTradeEnd");
                                            }
                                    );
                                }
                        )
                )
                .addOption(OPTIONS[3], (t) -> this.ignoreAndLeave())
        );

        registerPhase("cardBuyEnd", new TextPhase(DESCRIPTIONS[4]).addOption(OPTIONS[3], (t) -> this.openMap()));
        registerPhase("cardSellEnd", new TextPhase(DESCRIPTIONS[5]).addOption(OPTIONS[3], (t) -> this.openMap()));
        registerPhase("cardTradeEnd", new TextPhase(DESCRIPTIONS[6]).addOption(OPTIONS[3], (t) -> this.openMap()));


        registerPhase("magicDealer", new TextPhase(DESCRIPTIONS[2])
                .addOption(new TextPhase.OptionInfo(OPTIONS[11], new ConjurePack()), (i) -> {   //Curse & Pack Rip
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Vexed(), (Settings.WIDTH * .33F), (float) (Settings.HEIGHT / 2)));
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new ConjurePack(), (Settings.WIDTH * .66F), (float) (Settings.HEIGHT / 2)));
                    logMetricObtainCards(ID, "Conjure Pack", Arrays.asList(ConjurePack.ID, Vexed.ID));
                    transitionKey("magicLearnEnd");
                }).addOption(OPTIONS[12], (i) -> {   //Pack in a Jar Potion
                    AbstractDungeon.getCurrRoom().rewards.clear();
                    AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(new PackInAJar()));
                    noCardsInRewards = true;
                    AbstractDungeon.combatRewardScreen.open();
                    noCardsInRewards = false;
                    logMetric(ID, "Pack in a Jar", null, null, null, null, null, Collections.singletonList(PackInAJar.POTION_ID), null, 0, 0, 0, 0, 0, 0);
                    transitionKey("magicSampleEnd");
                })  //Remove a card.
                .addOption(new TextPhase.OptionInfo(OPTIONS[13])
                        .enabledCondition(this::canRemoveCardForCleanse, OPTIONS[14])
                        .cardRemovalOption("magicCleanseEnd", Beggar.OPTIONS[6], 1)
                )
                .addOption(OPTIONS[3], (t) -> this.ignoreAndLeave())
        );


        registerPhase("magicLearnEnd", new TextPhase(DESCRIPTIONS[7]).addOption(OPTIONS[3], (t) -> this.openMap()));
        registerPhase("magicSampleEnd", new TextPhase(DESCRIPTIONS[8]).addOption(OPTIONS[3], (t) -> this.openMap()));
        registerPhase("magicCleanseEnd", new TextPhase(DESCRIPTIONS[9]).addOption(OPTIONS[3], (t) -> this.openMap()));


        registerPhase("relicDealer", new TextPhase(DESCRIPTIONS[3])
                .addOption(new TextPhase.OptionInfo(OPTIONS[15] + getGoldCostForBuyCollection() + OPTIONS[16], new PMCollection())
                                .enabledCondition(()->!hasCollection(), OPTIONS[23])
                                .enabledCondition(this::hasEnoughGoldForBuyCollection, OPTIONS[9] + getGoldCostForBuyCollection() + OPTIONS[7]),
                        (i) -> {   //PM Collection
                            AbstractDungeon.player.loseGold(getGoldCostForBuyCollection());
                            AbstractRelic relic = new PMCollection();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, relic);
                            AbstractDungeon.shopRelicPool.remove(PMCollection.ID);
                            logMetricObtainRelicAtCost(ID, "PM Collection", relic, getGoldCostForBuyCollection());
                            transitionKey("relicCollectionEnd");
                        }
                )
                .addOption(new TextPhase.OptionInfo(OPTIONS[17] + getGoldCostForBuyDecree() + OPTIONS[18], new BanishingDecree())
                                .enabledCondition(()->!hasDecree(), OPTIONS[23])
                                .enabledCondition(this::hasEnoughGoldForBuyDecree, OPTIONS[9] + getGoldCostForBuyDecree() + OPTIONS[7]),
                        (i) -> {   //Banishing Decree
                            AbstractDungeon.player.loseGold(getGoldCostForBuyDecree());
                            AbstractRelic relic = new BanishingDecree();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, relic);
                            AbstractDungeon.shopRelicPool.remove(BanishingDecree.ID);
                            logMetricObtainRelicAtCost(ID, "Banishing Decree", relic, getGoldCostForBuyDecree());
                            transitionKey("relicBanishingEnd");
                        }
                )
                .addOption(new TextPhase.OptionInfo(OPTIONS[19] + getGoldGainForSellHat() + OPTIONS[20])
                                .enabledCondition(this::hasHat, OPTIONS[21]),
                        (i) -> {
                            AbstractDungeon.effectList.add(new RainingGoldEffect(getGoldGainForSellHat()));
                            AbstractDungeon.player.gainGold(getGoldGainForSellHat());
                            Hats.addHat(true, CoreSetPack.ID);
                            Hats.currentHat = CoreSetPack.ID;
                            logMetricGainGold(ID, "Sell Hat", getGoldGainForSellHat());
                            transitionKey("relicSellHatEnd");
                        }
                )
                .addOption(OPTIONS[3], (t) -> this.ignoreAndLeave())
        );


        registerPhase("relicCollectionEnd", new TextPhase(DESCRIPTIONS[10]).addOption(OPTIONS[3], (t) -> this.openMap()));
        registerPhase("relicBanishingEnd", new TextPhase(DESCRIPTIONS[11]).addOption(OPTIONS[3], (t) -> this.openMap()));
        registerPhase("relicSellHatEnd", new TextPhase(DESCRIPTIONS[12]).addOption(OPTIONS[3], (t) -> this.openMap()));


        transitionKey("base");
    }

    private void ignoreAndLeave() {
        logMetricIgnored(ID);
        this.openMap();
    }

    private boolean hasEnoughGoldForBuyCard() {
        return AbstractDungeon.player.gold >= getGoldCostForBuy();
    }

    private boolean hasCollection() {
        return AbstractDungeon.player.hasRelic(PMCollection.ID);
    }

    private boolean hasEnoughGoldForBuyCollection() {
        return AbstractDungeon.player.gold >= getGoldCostForBuyCollection();
    }

    private boolean hasDecree() {
        return AbstractDungeon.player.hasRelic(BanishingDecree.ID);
    }

    private boolean hasEnoughGoldForBuyDecree() {
        return AbstractDungeon.player.gold >= getGoldCostForBuyDecree();
    }

    private boolean hasHat() {
        return !Objects.equals(Hats.currentHat, CoreSetPack.ID);
    }


    private boolean canRemoveCardForSell() {
        for (AbstractCard c : CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()).group) {
            if (c.rarity != AbstractCard.CardRarity.BASIC && c.type != AbstractCard.CardType.CURSE) {
                return true;
            }
        }
        return false;
    }


    private boolean canRemoveCardForCleanse() {
        return !CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()).group.isEmpty();
    }


    private int getGoldGainForSell() {
        if (AbstractDungeon.ascensionLevel >= 15) {
            return 80;
        } else {
            return 100;
        }
    }

    private int getGoldCostForBuy() {
        if (AbstractDungeon.ascensionLevel >= 15) {
            return 60;
        } else {
            return 50;
        }
    }

    private int getGoldGainForSellHat() {
        if (AbstractDungeon.ascensionLevel >= 15) {
            return 60;
        } else {
            return 75;
        }
    }

    private int getGoldCostForBuyCollection() {
        if (AbstractDungeon.ascensionLevel >= 15) {
            return 120;
        } else {
            return 100;
        }
    }

    private int getGoldCostForBuyDecree() {
        if (AbstractDungeon.ascensionLevel >= 15) {
            return 90;
        } else {
            return 75;
        }
    }

    private CardGroup getCardsForLibraryEffect() {
        //Might be a more effective way to do this?  The main Library uses the standard issue card rewards,
        //but this event can appear in any act, which would skew this option to be too weak in act 1 and too
        //strong in act 3.  It should reflect the usual average result in the actual Library, which is commons
        //and uncommons only.

        ArrayList<AbstractCard> buyables = new ArrayList<>();
        ArrayList<AbstractCardPack> validPacks = new ArrayList<>(allPacks);
        if (!allPacksMode) {
            for (AbstractCardPack p : currentPoolPacks) {
                validPacks.remove(p);
            }
            if (validPacks.size() < 5) {
                validPacks.clear();
                validPacks.addAll(allPacks);
            }
        }
        AbstractCard c;
        AbstractCardPack p;
        while (buyables.size() < 20) {
            p = validPacks.get(AbstractDungeon.cardRandomRng.random(0, validPacks.size() - 1));
            c = p.cards.get(AbstractDungeon.cardRandomRng.random(0, p.cards.size() - 1));
            if (!buyables.contains(c) && (c.rarity == AbstractCard.CardRarity.COMMON || c.rarity == AbstractCard.CardRarity.UNCOMMON)) {
                buyables.add(c);
            }
        }

        CardGroup buyablesGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard original : buyables) {
            AbstractCard b = original.makeCopy();
            AbstractDungeon.player.relics.forEach(q -> q.onPreviewObtainCard(b));
            buyablesGroup.addToTop(b);
        }
        return buyablesGroup;
    }

    private CardGroup getCardsForDraftedPurge() {
        ArrayList<AbstractCard> purgeables = new ArrayList<>();
        for (AbstractCard c : CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()).group) {
            if (c.rarity != AbstractCard.CardRarity.BASIC && c.type != AbstractCard.CardType.CURSE) {
                purgeables.add(c);
            }
        }

        CardGroup purgablesGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        purgablesGroup.group = purgeables;
        return purgablesGroup;
    }
}