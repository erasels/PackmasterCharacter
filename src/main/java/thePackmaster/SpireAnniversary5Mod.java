package thePackmaster;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.CustomSavable;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javassist.CtClass;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.bitingcoldpack.GrowingAffliction;
import thePackmaster.cards.cardvars.SecondDamage;
import thePackmaster.cards.cardvars.SecondMagicNumber;
import thePackmaster.cards.ringofpainpack.Slime;
import thePackmaster.packs.*;
import thePackmaster.patches.MainMenuUIPatch;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;
import thePackmaster.powers.bitingcoldpack.GlaciatePower;
import thePackmaster.relics.AbstractPackmasterRelic;
import thePackmaster.util.Wiz;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import static thePackmaster.util.Wiz.*;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class SpireAnniversary5Mod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        PostUpdateSubscriber,
        PostPowerApplySubscriber,
        CustomSavable<ArrayList<String>> {

    private static UIStrings uiStrings;

    public static HashMap<String, String> cardParentMap = new HashMap<>(); //Is filled in initializePack from AbstractCardPack. <cardID, packID>
    public static ArrayList<AbstractCardPack> allPacks = new ArrayList<>();
    public static HashMap<String, AbstractCardPack> packsByID;
    public static ArrayList<AbstractCardPack> currentPoolPacks = new ArrayList<>();
    public static CardGroup packsToDisplay;
    public static Settings.GameLanguage[] SupportedLanguages = {
            Settings.GameLanguage.ENG,
    };

    public static Color characterColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1); // This should be changed eventually

    public static boolean doPackSetup = false;
    public static boolean openedStarterScreen = false;
    public static int PACKS_PER_RUN = 7;

    public static final String modID = "anniv5";
    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = modID + "Resources/images/512/attack.png";
    private static final String SKILL_S_ART = modID + "Resources/images/512/skill.png";
    private static final String POWER_S_ART = modID + "Resources/images/512/power.png";
    private static final String CARD_ENERGY_S = modID + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = modID + "Resources/images/512/text_energy.png";
    private static final String ATTACK_L_ART = modID + "Resources/images/1024/attack.png";
    private static final String SKILL_L_ART = modID + "Resources/images/1024/skill.png";
    private static final String POWER_L_ART = modID + "Resources/images/1024/power.png";
    private static final String CARD_ENERGY_L = modID + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = modID + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = modID + "Resources/images/charSelect/charBG.png";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    @SpireEnum
    public static AbstractCard.CardTags ISCARDMODIFIED;

    public SpireAnniversary5Mod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(ThePackmaster.Enums.PACKMASTER_RAINBOW, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) { return modID +"Resources/images/orbs/" + resourcePath; }

    public static void initialize() {
        SpireAnniversary5Mod thismod = new SpireAnniversary5Mod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new ThePackmaster(ThePackmaster.characterStrings.NAMES[1], ThePackmaster.Enums.THE_PACKMASTER),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, ThePackmaster.Enums.THE_PACKMASTER);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractPackmasterRelic.class)
                .any(AbstractPackmasterRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SecondMagicNumber());
        BaseMod.addDynamicVariable(new SecondDamage());
        new AutoAdd(modID)
                .packageFilter(AbstractPackmasterCard.class)
                .setDefaultSeen(true)
                .cards();

    }

    @Override
    public void receivePostInitialize() {
        if (uiStrings == null)
            uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Main"));
        declarePacks();
        BaseMod.logger.info("Full list of packs: " + allPacks.stream().map(pack -> pack.name).collect(Collectors.toList()));
    }

    private String getLangString() {
        for (Settings.GameLanguage lang : SupportedLanguages) {
            if (lang.equals(Settings.language)) {
                return Settings.language.name().toLowerCase();
            }
        }
        return "eng";
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/" + getLangString() + "/Cardstrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/" + getLangString() + "/Relicstrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/" + getLangString() + "/Charstrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/" + getLangString() + "/Powerstrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, modID + "Resources/localization/" + getLangString() + "/UIstrings.json");
        BaseMod.loadCustomStringsFile(StanceStrings.class, modID + "Resources/localization/" + getLangString() + "/Stancestrings.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, modID + "Resources/localization/" + getLangString() + "/Orbstrings.json");

        loadPackStrings();
    }

    // These packs are excluded from loading of pack-specific string files because they consistent entirely of base game cards.
    // If you're making a pack that also consists of only base game cards, add it to this list.
    // The name and description of the pack can go in the main UIstrings.json file.
    private static final List<String> baseGamePacks = Arrays.asList(IroncladPack.class.getName(), SilentPack.class.getName(), DefectPack.class.getName(), WatcherPack.class.getName());

    public void loadPackStrings() {
        Collection<CtClass> packClasses = new AutoAdd(modID)
                .packageFilter(AbstractCardPack.class)
                .findClasses(AbstractCardPack.class)
                .stream()
                .filter(c -> !baseGamePacks.contains(c.getName()))
                .collect(Collectors.toList());
        BaseMod.logger.info("Found pack classes with AutoAdd: " + packClasses.size());

        for (CtClass packClass : packClasses) {
            String packName = packClass.getSimpleName().toLowerCase();
            String languageAndPack = getLangString() + "/" + packName;
            BaseMod.logger.info("Loading strings for pack " + packClass.getName() + "from \"resources/localization/" + languageAndPack + "\"");
            //Do not need to be checked as these always need to exist
            BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/" + languageAndPack + "/Cardstrings.json");

            String filepath = modID + "Resources/localization/" + languageAndPack + "/";
            if (Gdx.files.internal(filepath + "Relicstrings.json").exists()) {
                BaseMod.loadCustomStringsFile(RelicStrings.class, filepath + "Relicstrings.json");
            }
            if (Gdx.files.internal(filepath + "Powerstrings.json").exists()) {
                BaseMod.loadCustomStringsFile(PowerStrings.class, filepath + "Powerstrings.json");
            }
            if (Gdx.files.internal(filepath + "UIstrings.json").exists()) {
                BaseMod.loadCustomStringsFile(UIStrings.class, filepath + "UIstrings.json");
            }
            if (Gdx.files.internal(filepath + "Stancestrings.json").exists()) {
                BaseMod.loadCustomStringsFile(StanceStrings.class, filepath + "Stancestrings.json");
            }
            if (Gdx.files.internal(filepath + "Orbstrings.json").exists()) {
                BaseMod.loadCustomStringsFile(OrbStrings.class, filepath + "Orbstrings.json");
            }
        }
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/" + getLangString() + "/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        List<Keyword> keywords = new ArrayList<>(Arrays.asList(gson.fromJson(json, Keyword[].class)));

        Collection<CtClass> packClasses = new AutoAdd(modID)
                .packageFilter(AbstractCardPack.class)
                .findClasses(AbstractCardPack.class)
                .stream()
                .filter(c -> !baseGamePacks.contains(c.getName()))
                .collect(Collectors.toList());

        for (CtClass packClass : packClasses) {
            String packName = packClass.getSimpleName().toLowerCase();
            String languageAndPack = getLangString() + "/" + packName;
            BaseMod.logger.info("Loading keywords for pack " + packClass.getName() + "from \"resources/localization/" + languageAndPack + "\"");
            String packJson = modID + "Resources/localization/" + languageAndPack + "/Keywordstrings.json";
            FileHandle handle = Gdx.files.internal(packJson);
            if (handle.exists()) {
                packJson = handle.readString(String.valueOf(StandardCharsets.UTF_8));
                List<Keyword> packKeywords = new ArrayList<>(Arrays.asList(gson.fromJson(packJson, Keyword[].class)));
                keywords.addAll(packKeywords);
            }
        }

        for (Keyword keyword : keywords) {
            BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
        }
    }


    public static void declarePacks() {
        // We prefer to catch duplicate pack IDs here, instead of letting them break in unexpected ways downstream of this code
        packsByID = new HashMap<>();
        new AutoAdd(modID)
                .packageFilter(AbstractCardPack.class)
                .any(AbstractCardPack.class, (info, pack) -> {
                    if (packsByID.containsKey(pack.packID)) {
                        throw new RuntimeException("Duplicate pack detected with ID: " + pack.packID + ". Pack class 1: " + packsByID.get(pack.packID).getClass().getName() + ", pack class 2: " + pack.getClass().getName());
                    }
                    packsByID.put(pack.packID, pack);
                    allPacks.add(pack);
                });
    }

    public static AbstractCardPack getRandomPackFromAll() {
        return allPacks.get(AbstractDungeon.cardRandomRng.random(0, allPacks.size() - 1));
    }

    public static AbstractCardPack getRandomPackFromCurrentPool() {
        return currentPoolPacks.get(AbstractDungeon.cardRandomRng.random(0, currentPoolPacks.size() - 1));
    }

    public static AbstractCard getRandomCardFromPack(AbstractCardPack pack) {
        return pack.cards.get(AbstractDungeon.cardRandomRng.random(0, pack.cards.size() - 1)).makeCopy();
    }

    public static ArrayList<AbstractCard> getPreviewCardsFromCurrentSet() {
        ArrayList<AbstractCard> valid = new ArrayList<>();
        for (AbstractCardPack cp : currentPoolPacks) {
            valid.add(cp.previewPackCard);
        }
        return valid;
    }

    public static ArrayList<AbstractCardPack> getRandomPacks(boolean onlyCurrent, int count) {
        ArrayList<AbstractCardPack> allChoices = new ArrayList<>();
        ArrayList<AbstractCardPack> valid = new ArrayList<>();

        if (onlyCurrent) {
            allChoices.addAll(SpireAnniversary5Mod.currentPoolPacks);
        } else {
            allChoices.addAll(SpireAnniversary5Mod.allPacks);
        }

        for (int i = 0; i < count; i++) {
            AbstractCardPack p = allChoices.get(AbstractDungeon.cardRandomRng.random(0, allChoices.size() - 1));
            valid.add(p);
            allChoices.remove(p);
        }
        return valid;
    }


    private static void startOfGameRandomPacks(int amount) {
        ArrayList<AbstractCardPack> poolPacks = new ArrayList<>();

        for (AbstractCardPack p : allPacks) {
            if (!currentPoolPacks.contains(p) && !p.packID.equals(CoreSetPack.ID)) {
                poolPacks.add(p);
            }
        }

        for (int i = 0; i < amount; i++) {
            AbstractCardPack target = poolPacks.get(AbstractDungeon.cardRandomRng.random(0, poolPacks.size() - 1));
            BaseMod.logger.info("Randomly selected: " + target.packID);
            SpireAnniversary5Mod.currentPoolPacks.add(target);
            poolPacks.remove(target);
        }

    }

    private static int ongoingPackChoiceOfThrees = 0;

    private static void startOfGamePackChoices(int amount) {
        ongoingPackChoiceOfThrees = amount;
        individualPackChoiceOfThree();
    }

    private static void individualPackChoiceOfThree() {
        ArrayList<AbstractCardPack> poolPacks = new ArrayList<>();

        for (AbstractCardPack p : allPacks) {
            if (!currentPoolPacks.contains(p) && !p.packID.equals(CoreSetPack.ID)) {
                poolPacks.add(p);
            }
        }

        CardGroup packChoices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (int i = 0; i < 3; i++) {
            AbstractCardPack target = poolPacks.get(AbstractDungeon.cardRandomRng.random(0, poolPacks.size() - 1));
            packChoices.addToBottom(target.previewPackCard);
            poolPacks.remove(target);
        }

        BaseMod.logger.info("Queueing a choice between " + packChoices.getCardNames());
        AbstractDungeon.gridSelectScreen.open(packChoices, 1, false, uiStrings.TEXT[0]);
    }

    public static void startOfGamePackSetup() {
        currentPoolPacks.clear();

        ArrayList<String> packSetup = new ArrayList<>();

        if (MainMenuUIPatch.customDraft) {
            packSetup.addAll(MainMenuUIPatch.packSetups);
        } else {
            packSetup.add(CoreSetPack.ID);
            packSetup.add(MainMenuUIPatch.RANDOM);
            packSetup.add(MainMenuUIPatch.RANDOM);
            packSetup.add(MainMenuUIPatch.RANDOM);
            packSetup.add(MainMenuUIPatch.RANDOM);
            packSetup.add(MainMenuUIPatch.CHOICE);
            packSetup.add(MainMenuUIPatch.CHOICE);
        }

        int randomsToSetup = 0;
        int choicesToSetup = 0;

        for (String setupType : packSetup) {
            BaseMod.logger.info("Setting up Pack type " + setupType + ".");
            
            switch (setupType) {
                case MainMenuUIPatch.RANDOM:
                    BaseMod.logger.info("Adding 1 more pack to random selection later on.");
                    randomsToSetup++;
                    break;
                case MainMenuUIPatch.CHOICE:
                    BaseMod.logger.info("Adding 1 more pack to choice-of-3 selection later on.");
                    choicesToSetup++;
                    break;
                default:
                    for (AbstractCardPack pack : allPacks) {
                        if (pack.packID.equals(setupType)) {
                            BaseMod.logger.info("Found pack matching name " + pack.name);
                            currentPoolPacks.add(pack);
                        }
                    }
            }
        }

        BaseMod.logger.info("OK, we've looked at all the pack settings.");

        if (randomsToSetup > 0) {
            BaseMod.logger.info("Let's add randomized packs. We need to add " + randomsToSetup);
            startOfGameRandomPacks(randomsToSetup);
        } else {
            BaseMod.logger.info("No randomized packs to add. Moving on");
        }

        if (choicesToSetup > 0) {
            BaseMod.logger.info("There are choices to be made. We need to choose for " + choicesToSetup);
            startOfGamePackChoices(choicesToSetup);
        } else {
            BaseMod.logger.info("No choice packs to add, so we're done. Revealing packs.");
            startOfGamePackReveals();
        }

        BaseMod.logger.info("All pack selections made or queued.");
    }

    private static void startOfGamePackReveals() {
        BaseMod.logger.info("Total packs: " + currentPoolPacks.toString());
        //TODO - Don't render the title screen for act 1
        CardGroup packDisplays = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        for (int i = 0; i < PACKS_PER_RUN; i++) {
            packDisplays.addToBottom(currentPoolPacks.get(i).previewPackCard);
        }

        BaseMod.logger.info(CardCrawlGame.languagePack.getUIString(makeID("AtGameStart")).TEXT[0]);
        AbstractDungeon.gridSelectScreen.open(packDisplays, 0, true, CardCrawlGame.languagePack.getUIString(makeID("AtGameStart")).TEXT[0]);
        //Calling this to fill the card pool after the currentPoolPacks are filled
        CardCrawlGame.dungeon.initializeCardPools();
    }

    @Override
    public void receivePostUpdate() {
        if (!openedStarterScreen) {
            if (CardCrawlGame.isInARun() && doPackSetup) {
                BaseMod.logger.info("Starting Packmaster setup.");
                startOfGamePackSetup();
                openedStarterScreen = true;
            }
        } else if (ongoingPackChoiceOfThrees > 0) {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                AbstractCard selected = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                BaseMod.logger.info("Player selected " + selected.cardID);
                AbstractCardPack parentPack = Wiz.getPackByCard(selected);
                BaseMod.logger.info("Card has corresponding parent pack of " + parentPack.packID);
                currentPoolPacks.add(parentPack);

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                ongoingPackChoiceOfThrees -= 1;
                if (ongoingPackChoiceOfThrees != 0) {
                    BaseMod.logger.info(ongoingPackChoiceOfThrees + " choices left.");
                    individualPackChoiceOfThree();
                } else {
                    BaseMod.logger.info("No more choices left, displaying the full set of packs.");
                    startOfGamePackReveals();
                }
            }
        }
    }

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == AbstractPower.PowerType.DEBUFF && source == AbstractDungeon.player && target != AbstractDungeon.player) {
            // Biting Cold Pack
            // Growing Affliction (Return to hand)
            for (AbstractCard c : AbstractDungeon.player.discardPile.group)
                if (c.cardID.equals(GrowingAffliction.ID))
                    AbstractDungeon.actionManager.addToBottom(new DiscardToHandAction(c));

            // Glaciate (Gain Vigor)
            if (power.ID.equals(FrostbitePower.POWER_ID) && source.hasPower(GlaciatePower.POWER_ID)) {
                AbstractPower glaciate = source.getPower(GlaciatePower.POWER_ID);

                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        glaciate.flash();
                        if (Settings.FAST_MODE)
                            addToBot(new WaitAction(0.1F));
                        else
                            addToBot(new WaitAction(0.2F));
                        applyToSelf(new VigorPower(AbstractDungeon.player, glaciate.amount));
                        this.isDone = true;
                    }
                });
            }
          
            //Ring of Pain pack
            if(!target.hasPower(ArtifactPower.POWER_ID)) {
              atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        for (AbstractCard card : adp().hand.group) {
                            if (card instanceof Slime) {
                                ((Slime) card).triggerOnDebuff();
                            }
                        }
                        this.isDone = true;
                    }
                });
            }
        }
    }

    @Override
    public ArrayList<String> onSave() {
        ArrayList<String> packIDs = new ArrayList<>();
        for (AbstractCardPack pack : currentPoolPacks) {
            packIDs.add(pack.packID);
        }
        return packIDs;
    }

    @Override
    public void onLoad(ArrayList<String> strings) {
        for (String s : strings) {
            currentPoolPacks.add(packsByID.get(s));
        }
    }
}
