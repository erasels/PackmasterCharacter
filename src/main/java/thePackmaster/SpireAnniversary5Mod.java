package thePackmaster;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.ModPanel;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomSavable;
import basemod.devcommands.ConsoleCommand;
import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import basemod.helpers.CardBorderGlowManager;
import basemod.helpers.RelicType;
import basemod.helpers.TextCodeInterpreter;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.evacipated.cardcrawl.mod.stslib.icons.CustomIconHelper;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rewards.RewardSave;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.CalmStance;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javassist.CtClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.batterpack.UltimateHomerun;
import thePackmaster.cards.bitingcoldpack.GrowingAffliction;
import thePackmaster.cards.cardvars.SecondDamage;
import thePackmaster.cards.cardvars.SecondMagicNumber;
import thePackmaster.cards.evenoddpack.SwordAndBoard;
import thePackmaster.cards.ringofpainpack.Slime;
import thePackmaster.cards.transmutationpack.DimensionalIcicles;
import thePackmaster.commands.PackAddCommand;
import thePackmaster.commands.UnlockHatCommand;
import thePackmaster.events.BlackMarketDealerEvent;
import thePackmaster.hats.HatMenu;
import thePackmaster.hats.Hats;
import thePackmaster.orbs.summonspack.Leprechaun;
import thePackmaster.orbs.summonspack.Louse;
import thePackmaster.orbs.summonspack.Panda;
import thePackmaster.packs.*;
import thePackmaster.patches.MainMenuUIPatch;
import thePackmaster.patches.contentcreatorpack.DisableCountingStartOfTurnDrawPatch;
import thePackmaster.patches.marisapack.AmplifyPatches;
import thePackmaster.patches.odditiespack.PackmasterFoilPatches;
import thePackmaster.patches.psychicpack.occult.OccultFields;
import thePackmaster.patches.psychicpack.occult.OccultPatch;
import thePackmaster.patches.sneckopack.EnergyCountPatch;
import thePackmaster.potions.BoosterBrew;
import thePackmaster.potions.ModdersDelight;
import thePackmaster.potions.PackInAJar;
import thePackmaster.potions.SmithingOil;
import thePackmaster.potions.clawpack.AttackPotionButClaw;
import thePackmaster.potions.clawpack.ClawPowerPotion;
import thePackmaster.potions.clawpack.DrawClawsPotion;
import thePackmaster.potions.clawpack.GenerateClawsPotion;
import thePackmaster.potions.thieverypack.DivinePotion;
import thePackmaster.powers.dragonwrathpack.PenancePower;
import thePackmaster.powers.evenoddpack.GammaWardPower;
import thePackmaster.powers.evenoddpack.PrimeDirectivePower;
import thePackmaster.powers.thieverypack.MindControlledPower;
import thePackmaster.relics.AbstractPackmasterRelic;
import thePackmaster.rewards.CustomRewardTypes;
import thePackmaster.rewards.PMBoosterBoxCardReward;
import thePackmaster.rewards.SingleCardReward;
import thePackmaster.screens.PackSetupScreen;
import thePackmaster.stances.aggressionpack.AggressionStance;
import thePackmaster.stances.cthulhupack.NightmareStance;
import thePackmaster.stances.downfallpack.AncientStance;
import thePackmaster.stances.sentinelpack.Angry;
import thePackmaster.stances.sentinelpack.Serene;
import thePackmaster.summaries.PackSummary;
import thePackmaster.summaries.PackSummaryDisplay;
import thePackmaster.summaries.PackSummaryReader;
import thePackmaster.ui.*;
import thePackmaster.ui.FixedModLabeledToggleButton.FixedModLabeledToggleButton;
import thePackmaster.util.Keywords;
import thePackmaster.util.TexLoader;
import thePackmaster.util.cardvars.HoardVar;
import thePackmaster.util.creativitypack.JediUtil;
import thePackmaster.util.dynamicdynamic.DynamicDynamicVariableManager;
import thePackmaster.vfx.distortionpack.ImproveEffect;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static thePackmaster.patches.MainMenuUIPatch.*;
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
        OnStartBattleSubscriber,
        AddAudioSubscriber,
        PostBattleSubscriber,
        PostPowerApplySubscriber,
        StartGameSubscriber,
        PostExhaustSubscriber,
        OnPlayerTurnStartSubscriber,
        OnCreateDescriptionSubscriber,
        OnPlayerLoseBlockSubscriber,
        PostRenderSubscriber {

    public static final Logger logger = LogManager.getLogger("Packmaster");

    public static HashMap<String, String> cardParentMap = new HashMap<>(); //Is filled in initializePack from AbstractCardPack. <cardID, packID>
    public static HashMap<Class<? extends AbstractCard>, String> cardClassParentMap = new HashMap<>(); //Is filled in initializePack from AbstractCardPack. <card Class, packID>
    public static ArrayList<AbstractCardPack> allPacks = new ArrayList<>();
    public static ArrayList<AbstractCardPack> unfilteredAllPacks = new ArrayList<>();
    public static HashMap<String, AbstractCardPack> packsByID;
    public static Set<String> packExclusivePotions = new HashSet<>();
    public static ArrayList<AbstractCardPack> currentPoolPacks = new ArrayList<>();
    public static CardGroup packsToDisplay;
    public static Settings.GameLanguage[] SupportedLanguages = {
            Settings.GameLanguage.ENG,
            Settings.GameLanguage.ZHS
    };

    public static Color characterColor = new Color(0.76f, 0.65f, 0.52f, 1);

    public static SpireAnniversary5Mod thismod;
    public static SpireConfig modConfig = null;

    public static boolean doPackSetup = false;
    public static String lastCardsPackID = null;
    public static boolean openedStarterScreen = false;
    public static boolean skipDefaultCardRewards = false;
    public static int PACKS_PER_RUN = 7;
    public static int PACKS_PER_CHOICE = 3;
    public static CurrentRunCardsTopPanelItem currentRunCardsTopPanelItem;

    public static final String modID = "anniv5";
    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse.png";
    public static final String SKELETON_JSON = modID + "Resources/images/char/mainChar/PackmasterAnim.json";
    public static final String SKELETON_ATLAS = modID + "Resources/images/char/mainChar/PackmasterAnim.atlas";
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

    public static final String BEES_KEY = makeID("SwarmOfBees");
    private static final String BEES_OGG = makePath("audio/summonspack/SwarmOfBees.ogg");
    public static final String ELEPHANT_KEY = makeID("elephant");
    private static final String ELEPHANT_OGG = makePath("audio/summonspack/Elephant.ogg");
    public static final String DETONATOR_KEY = makeID("Detonator");
    private static final String DETONATOR_OGG = makePath("audio/goddessofexplosionspack/bokudan.ogg");
    public static final String PEW_KEY = makeID("Pew");
    private static final String PEW_OGG = makePath("audio/summonspack/Pew.ogg");
    public static final String EVIL_KEY = makeID("Evil");
    private static final String EVIL_OGG = makePath("audio/summonspack/Evil.ogg");
    public static final String PANDA_KEY = makeID("Panda");
    private static final String PANDA_OGG = makePath("audio/summonspack/Panda.ogg");
    public static final String PORCUPINE_KEY = makeID("Porcupine");
    private static final String PORCUPINE_OGG = makePath("audio/summonspack/Porcupine.ogg");
    public static final String DIE_KEY = makeID("Die");
    private static final String DIE_OGG = makePath("audio/summonspack/Die.ogg");
    public static final String DICE_KEY = makeID("Dice");
    private static final String DICE_OGG = makePath("audio/summonspack/Dice.ogg");
    public static final String DICELOTS_KEY = makeID("DiceLots");
    private static final String DICELOTS_OGG = makePath("audio/summonspack/DiceLots.ogg");
    public static final String TRANSMUTATION_KEY = makeID("Transmutation");
    private static final String TRANSMUTATION_OGG = makePath("audio/transmutationpack/Transmutation.ogg");
    public static final String WATER_IMPACT_1_KEY = makeID("WaterImpactOne");
    private static final String WATER_IMPACT_1_OGG = makePath("audio/transmutationpack/WaterImpactOne.ogg");
    public static final String WATER_IMPACT_2_KEY = makeID("WaterImpactTwo");
    private static final String WATER_IMPACT_2_OGG = makePath("audio/transmutationpack/WaterImpactTwo.ogg");
    public static final String WATER_IMPACT_3_KEY = makeID("WaterImpactThree");
    private static final String WATER_IMPACT_3_OGG = makePath("audio/transmutationpack/WaterImpactThree.ogg");
    public static final String ICE_IMPACT_1_KEY = makeID("IceImpactOne");
    private static final String ICE_IMPACT_1_OGG = makePath("audio/transmutationpack/IceImpactOne.ogg");
    public static final String ICE_IMPACT_2_KEY = makeID("IceImpactTwo");
    private static final String ICE_IMPACT_2_OGG = makePath("audio/transmutationpack/IceImpactTwo.ogg");
    public static final String ICE_IMPACT_3_KEY = makeID("IceImpactThree");
    private static final String ICE_IMPACT_3_OGG = makePath("audio/transmutationpack/IceImpactThree.ogg");
    public static final String STEAM_IMPACT_1_KEY = makeID("SteamImpactOne");
    private static final String STEAM_IMPACT_1_OGG = makePath("audio/transmutationpack/SteamImpactOne.ogg");
    public static final String STEAM_IMPACT_2_KEY = makeID("SteamImpactTwo");
    private static final String STEAM_IMPACT_2_OGG = makePath("audio/transmutationpack/SteamImpactTwo.ogg");
    public static final String STEAM_IMPACT_3_KEY = makeID("StreamImpactThree");
    private static final String STEAM_IMPACT_3_OGG = makePath("audio/transmutationpack/SteamImpactThree.ogg");
    public static final String GUN1_KEY = makeID("Gun1");
    private static final String GUN1_OGG = makePath("audio/hermitpack/GUN1.ogg");
    public static final String GUN2_KEY = makeID("Gun2");
    private static final String GUN2_OGG = makePath("audio/hermitpack/GUN2.ogg");
    public static final String GUN3_KEY = makeID("Gun3");
    private static final String GUN3_OGG = makePath("audio/hermitpack/GUN3.ogg");

    public static final String EVIL_EFFECT_FILE = makePath("images/orbs/summonsPack/Evil.png");

    public static final ArrayList<Panda> pandaList = new ArrayList<>();
    public static final ArrayList<Louse> louseList = new ArrayList<>();

    public static boolean isHatRainbow = false;

    public static boolean selectedCards = false;
    public static int combatExhausts = 0;
    public static int cardsRippedThisTurn;


    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    @SpireEnum
    public static AbstractCard.CardTags ISCARDMODIFIED;

    @SpireEnum
    public static AbstractCard.CardTags CLAW;


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

    public static String makeShaderPath(String resourcePath) {
        return modID + "Resources/shaders/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) {
        return modID + "Resources/images/orbs/" + resourcePath;
    }

    public static void initialize() {
        thismod = new SpireAnniversary5Mod();

        try {
            Properties defaults = new Properties();
            defaults.put("PackmasterCustomDraftEnabled", "FALSE");
            defaults.put("PackmasterCustomDraftSelection", String.join(",", makeID("CoreSetPack"), RANDOM, RANDOM, RANDOM, CHOICE, CHOICE, CHOICE));
            defaults.put("PackmasterUnlockedHats", "");
            defaults.put("PackmasterAllPacksMode", "FALSE");
            defaults.put("PackmasterAllowMultipleNONE", "FALSE");
            defaults.put("PackmasterSelectedHatIndex", "0");
            defaults.put("PackmasterUnlockedRainbows","");
            defaults.put("PackmasterRainbowEnabled","FALSE");
            defaults.put("PackmasterUnseenHats","");
            defaults.put("PackmasterShowSummaries","TRUE");
            modConfig = new SpireConfig(modID, "GeneralConfig", defaults);
            modConfig.load();

            loadModConfigData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getCustomDraftEnabled() {
        if (modConfig == null) return false;
        return modConfig.getBool("PackmasterCustomDraftEnabled");
    }

    public static void saveCustomDraftEnabled(boolean enabled) {
        try {
            if (modConfig == null) return;
            modConfig.setBool("PackmasterCustomDraftEnabled", enabled);
            modConfig.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> getSavedCDraftSelection() {
        if (modConfig == null) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(modConfig.getString("PackmasterCustomDraftSelection").split(",")));
    }

    public static void saveCDraftSelection(ArrayList<String> input) throws IOException {
        if (modConfig == null) return;
        modConfig.setString("PackmasterCustomDraftSelection", String.join(",", input));
        modConfig.save();
    }

    public static void saveAllPacksMode() {
        try {
            if (modConfig == null) return;
            modConfig.setBool("PackmasterAllPacksMode", allPacksMode);
            modConfig.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveOneFrameMode() {
        try {
            if (modConfig == null) return;
            modConfig.setBool("PackmasterOneFrameMode", oneFrameMode);
            modConfig.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveContentSharingMode() {
        try {
            if (modConfig == null) return;
            modConfig.setBool("PackmasterContentSharingMode", sharedContentMode);
            modConfig.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Boolean _showPackSummaries = null;
    public static Boolean showPackSummaries() {
        if(modConfig == null) return true;
        if(_showPackSummaries == null)
            _showPackSummaries = modConfig.getBool("PackmasterShowSummaries");
        return _showPackSummaries;
    }

    public static void togglePackSummaries() {
        if(modConfig == null) return;
        try {
            modConfig.setBool("PackmasterShowSummaries", !_showPackSummaries);
            _showPackSummaries = !_showPackSummaries;
            modConfig.save();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public static Boolean allowMultiNone() {
        if(modConfig == null) return false;
        return modConfig.getBool("PackmasterAllowMultipleNONE");
    }

    public static void toggleMultiNone() {
        if(modConfig == null) return;
        try {
            modConfig.setBool("PackmasterAllowMultipleNONE", !modConfig.getBool("PackmasterAllowMultipleNONE"));
            modConfig.save();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public static void loadModConfigData() {
        allPacksMode = modConfig.getBool("PackmasterAllPacksMode");
        oneFrameMode = modConfig.getBool("PackmasterOneFrameMode");
        sharedContentMode = modConfig.getBool("PackmasterContentSharingMode");
    }

    public static ArrayList<String> getUnlockedHats() {
        if (modConfig == null) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(modConfig.getString("PackmasterUnlockedHats").split(",")));
    }

    public static void saveUnlockedHats(ArrayList<String> input) throws IOException {
        if (modConfig == null) return;
        modConfig.setString("PackmasterUnlockedHats", String.join(",", input));
        modConfig.save();
    }

    public static ArrayList<String> getUnlockedRainbows() {
        if (modConfig == null) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(modConfig.getString("PackmasterUnlockedRainbows").split(",")));
    }

    public static void saveUnlockedRainbows(ArrayList<String> input) throws IOException {
        if (modConfig == null) return;
        modConfig.setString("PackmasterUnlockedRainbows", String.join(",", input));
        modConfig.save();
    }

    public static String getLastPickedHatID() {
        if (modConfig == null) return "";
        return modConfig.getString("PackmasterSelectedHatID");
    }

    public static void saveLastPickedHatID(String ID) throws IOException {
        if (modConfig == null) return;
        modConfig.setString("PackmasterSelectedHatID", ID);
        modConfig.save();
    }

    public static boolean wasRainbowLastEnabled() {
        if (modConfig == null) return false;
        return modConfig.getBool("PackmasterRainbowEnabled");
    }

    public static void saveRainbowLastEnabled(boolean enabled) throws IOException {
        if (modConfig == null) return;
        modConfig.setBool("PackmasterRainbowEnabled", enabled);
        modConfig.save();
    }

    public static void saveUnseenHats(ArrayList<String> input) throws IOException {
        if (modConfig == null) return;
        modConfig.setString("PackmasterUnseenHats", String.join(",", input));
        modConfig.save();
    }

    public static ArrayList<String> getUnseenHats() {
        if (modConfig == null) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(modConfig.getString("PackmasterUnseenHats").split(",")));
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
                        if (sharedContentMode) {
                            BaseMod.addRelic(relic, RelicType.SHARED);
                        } else {
                            BaseMod.addRelicToCustomPool(relic, relic.color);
                        }
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
        CustomIconHelper.addCustomIcon(InfestTextIcon.get());
        CustomIconHelper.addCustomIcon(new RatingStar());
        CustomIconHelper.addCustomIcon(new RatingDarkStar());

        BaseMod.addDynamicVariable(new SecondMagicNumber());
        BaseMod.addDynamicVariable(new SecondDamage());
        BaseMod.addDynamicVariable(new HoardVar());
        new AutoAdd(modID)
                .packageFilter(AbstractPackmasterCard.class)
                .setDefaultSeen(true)
                .cards();

    }

    @Override
    public void receivePostInitialize() {
        declarePacks();
        logger.info("Full list of packs: " + unfilteredAllPacks.stream().map(pack -> pack.name).collect(Collectors.toList()));
        logCardStats();
        logPackAuthors();

        AmplifyPatches.receivePostInit();
        BaseMod.addCustomScreen(new PackSetupScreen());
        loadAndCheckSummaries();

        logger.info("Checking playability annotations");
        OccultPatch.testPlayability();

        final Color occultGlow = CardHelper.getColor(88.0f, 26.0f, 150.0f);
        CardBorderGlowManager.addGlowInfo(new CardBorderGlowManager.GlowInfo() {
            @Override
            public boolean test(AbstractCard card) {
                return OccultFields.isOccult.get(card);
            }

            @Override
            public Color getColor(AbstractCard card) {
                return occultGlow.cpy();
            }

            @Override
            public String glowID() {
                return makeID("OccultGlow");
            }
        });

        currentRunCardsTopPanelItem = new CurrentRunCardsTopPanelItem();

        addPotions();

        registerCustomRewards();

        initializeConfig();

        initializeSavedData();

        BaseMod.addEvent(new AddEventParams.Builder(BlackMarketDealerEvent.ID, BlackMarketDealerEvent.class) //Event ID//
                //Event Character//
                .playerClass(ThePackmaster.Enums.THE_PACKMASTER)
                //Event Type//
                .eventType(EventUtils.EventType.NORMAL)
                .create());

        ConsoleCommand.addCommand("addhat", UnlockHatCommand.class);
        ConsoleCommand.addCommand("pack", PackAddCommand.class);

        TextCodeInterpreter.addAccessible("PackmasterMenu", MainMenuUIPatch.class);
        TextCodeInterpreter.addAccessible("Packmaster", SpireAnniversary5Mod.class);
    }

    public static void addPotions() {
        if (sharedContentMode) {
            BaseMod.addPotion(BoosterBrew.class, Color.TAN, Color.WHITE, Color.BLACK, BoosterBrew.POTION_ID);
            BaseMod.addPotion(SmithingOil.class, Color.TAN, Color.WHITE, null, SmithingOil.POTION_ID);
        } else {
            BaseMod.addPotion(BoosterBrew.class, Color.TAN, Color.WHITE, Color.BLACK, BoosterBrew.POTION_ID, ThePackmaster.Enums.THE_PACKMASTER);
            BaseMod.addPotion(SmithingOil.class, Color.TAN, Color.WHITE, null, SmithingOil.POTION_ID, ThePackmaster.Enums.THE_PACKMASTER);
        }

        BaseMod.addPotion(ModdersDelight.class, Color.TAN, Color.WHITE, Color.BLACK, ModdersDelight.POTION_ID, ThePackmaster.Enums.THE_PACKMASTER);
        //BaseMod.addPotion(PackInAJar.class, Color.TAN, Color.WHITE, Color.BLACK, PackInAJar.POTION_ID, ThePackmaster.Enums.THE_PACKMASTER);

        BaseMod.addPotion(AttackPotionButClaw.class, Color.RED, Color.WHITE, Color.FIREBRICK, AttackPotionButClaw.POTION_ID, ThePackmaster.Enums.THE_PACKMASTER);
        BaseMod.addPotion(ClawPowerPotion.class, Color.RED, Color.WHITE, Color.FIREBRICK, ClawPowerPotion.POTION_ID, ThePackmaster.Enums.THE_PACKMASTER);
        BaseMod.addPotion(DrawClawsPotion.class, Color.RED, Color.WHITE, Color.FIREBRICK, DrawClawsPotion.POTION_ID, ThePackmaster.Enums.THE_PACKMASTER);
        BaseMod.addPotion(GenerateClawsPotion.class, Color.RED, Color.WHITE, Color.FIREBRICK, GenerateClawsPotion.POTION_ID, ThePackmaster.Enums.THE_PACKMASTER);
        BaseMod.addPotion(DivinePotion.class, Color.ORANGE, Color.YELLOW, Color.ORANGE, DivinePotion.POTION_ID, ThePackmaster.Enums.THE_PACKMASTER);
        BaseMod.addPotion(PackInAJar.class, Color.ORANGE, Color.YELLOW, Color.ORANGE, PackInAJar.POTION_ID, ThePackmaster.Enums.THE_PACKMASTER);

        if (Loader.isModLoaded("widepotions")) {
            Consumer<String> whitelist = getWidePotionsWhitelistMethod();
            whitelist.accept(AttackPotionButClaw.POTION_ID);
            whitelist.accept(ClawPowerPotion.POTION_ID);
            whitelist.accept(DrawClawsPotion.POTION_ID);
            whitelist.accept(GenerateClawsPotion.POTION_ID);
            whitelist.accept(DivinePotion.POTION_ID);
            whitelist.accept(BoosterBrew.POTION_ID);
            whitelist.accept(ModdersDelight.POTION_ID);
            whitelist.accept(SmithingOil.POTION_ID);
        }
    }

    private static Consumer<String> getWidePotionsWhitelistMethod() {
        // To avoid the need for a dependency of any kind, we call Wide Potions through reflection
        try {
            Method whitelistMethod = Class.forName("com.evacipated.cardcrawl.mod.widepotions.WidePotionsMod").getMethod("whitelistSimplePotion", String.class);
            return s -> {
                try {
                    whitelistMethod.invoke(null, s);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Error trying to whitelist wide potion for " + s, e);
                }
            };
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not find method WidePotionsMod.whitelistSimplePotion", e);
        }
    }

    @Deprecated
    private String getLangString() {
        for (Settings.GameLanguage lang : SupportedLanguages) {
            if (lang.equals(Settings.language)) {
                return Settings.language.name().toLowerCase(Locale.ROOT);
            }
        }
        return "eng";
    }

    @Override
    public void receiveEditStrings() {
        loadStrings("eng");

        Collection<CtClass> packClasses = new AutoAdd(modID)
                .packageFilter(AbstractCardPack.class)
                .findClasses(AbstractCardPack.class)
                .stream()
                .filter(c -> !baseGamePacks.contains(c.getName()))
                .collect(Collectors.toList());
        logger.info("Found pack classes with AutoAdd: " + packClasses.size());

        loadPackStrings(packClasses, "eng");
        if (Settings.language != Settings.GameLanguage.ENG)
        {
            loadStrings(Settings.language.toString().toLowerCase());
            loadPackStrings(packClasses, Settings.language.toString().toLowerCase());
        }
    }


    private void loadStrings(String langKey) {
        if (!Gdx.files.internal(modID + "Resources/localization/" + langKey + "/").exists()) return;
        String filepath = modID + "Resources/localization/" + langKey + "/Cardstrings.json";
        if (Gdx.files.internal(filepath).exists()) {
            BaseMod.loadCustomStringsFile(CardStrings.class, filepath);
        }
        filepath = modID + "Resources/localization/" + langKey + "/Relicstrings.json";
        if (Gdx.files.internal(filepath).exists()) {
            BaseMod.loadCustomStringsFile(RelicStrings.class, filepath);
        }
        filepath = modID + "Resources/localization/" + langKey + "/Charstrings.json";
        if (Gdx.files.internal(filepath).exists()) {
            BaseMod.loadCustomStringsFile(CharacterStrings.class, filepath);
        }
        filepath = modID + "Resources/localization/" + langKey + "/Powerstrings.json";
        if (Gdx.files.internal(filepath).exists()) {
            BaseMod.loadCustomStringsFile(PowerStrings.class, filepath);
        }
        filepath = modID + "Resources/localization/" + langKey + "/UIstrings.json";
        if (Gdx.files.internal(filepath).exists()) {
            BaseMod.loadCustomStringsFile(UIStrings.class, filepath);
        }
        filepath = modID + "Resources/localization/" + langKey + "/Stancestrings.json";
        if (Gdx.files.internal(filepath).exists()) {
            BaseMod.loadCustomStringsFile(StanceStrings.class, filepath);
        }
        filepath = modID + "Resources/localization/" + langKey + "/Orbstrings.json";
        if (Gdx.files.internal(filepath).exists()) {
            BaseMod.loadCustomStringsFile(OrbStrings.class, filepath);
        }
        filepath = modID + "Resources/localization/" + langKey + "/Potionstrings.json";
        if (Gdx.files.internal(filepath).exists()) {
            BaseMod.loadCustomStringsFile(PotionStrings.class, filepath);
        }
        filepath = modID + "Resources/localization/" + langKey + "/Eventstrings.json";
        if (Gdx.files.internal(filepath).exists()) {
            BaseMod.loadCustomStringsFile(EventStrings.class, filepath);
        }
    }

    // These packs are excluded from loading of pack-specific string files because they consistent entirely of base game cards.
    // If you're making a pack that also consists of only base game cards, add it to this list.
    // The name and description of the pack can go in the main UIstrings.json file.
    private static final List<String> baseGamePacks = Arrays.asList(IroncladPack.class.getName(),
            SilentPack.class.getName(),
            DefectPack.class.getName(),
            AllForOnePack.class.getName(),
            WatcherPack.class.getName(),
            BulwarkPack.class.getName());

    public void loadPackStrings(Collection<CtClass> packClasses, String langKey) {

        for (CtClass packClass : packClasses) {
            String packName = packClass.getSimpleName().toLowerCase(Locale.ROOT);
            String languageAndPack = langKey + "/" + packName;
            String filepath = modID + "Resources/localization/" + languageAndPack + "/";
            if (!Gdx.files.internal(filepath).exists()) {
                continue;
            }
            logger.info("Loading strings for pack " + packClass.getName() + "from \"resources/localization/" + languageAndPack + "\"");
            //Do not need to be checked as these always need to exist
            //He was wrong.

            if (Gdx.files.internal(filepath + "Cardstrings.json").exists()) {
                BaseMod.loadCustomStringsFile(CardStrings.class, filepath + "Cardstrings.json");
            }

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
            if (Gdx.files.internal(filepath + "Potionstrings.json").exists()) {
                BaseMod.loadCustomStringsFile(PotionStrings.class, filepath + "Potionstrings.json");
            }
        }
    }

    @Override
    public void receiveEditKeywords() {
        Collection<CtClass> packClasses = new AutoAdd(modID)
                .packageFilter(AbstractCardPack.class)
                .findClasses(AbstractCardPack.class)
                .stream()
                .filter(c -> !baseGamePacks.contains(c.getName()))
                .collect(Collectors.toList());
        loadKeywords(packClasses, "eng");
        if (Settings.language != Settings.GameLanguage.ENG) {
            loadKeywords(packClasses, Settings.language.toString().toLowerCase());
        }
    }

    private void loadKeywords(Collection<CtClass> packClasses, String langKey) {
        String filepath = modID + "Resources/localization/" + langKey + "/Keywordstrings.json";
        Gson gson = new Gson();
        List<Keyword> keywords = new ArrayList<>();
        if (Gdx.files.internal(filepath).exists()) {
            String json = Gdx.files.internal(filepath).readString(String.valueOf(StandardCharsets.UTF_8));
            keywords.addAll(Arrays.asList(gson.fromJson(json, Keyword[].class)));
        }
        for (CtClass packClass : packClasses) {
            String packName = packClass.getSimpleName().toLowerCase(Locale.ROOT);
            String languageAndPack = langKey + "/" + packName;
            String packJson = modID + "Resources/localization/" + languageAndPack + "/Keywordstrings.json";
            FileHandle handle = Gdx.files.internal(packJson);
            if (handle.exists()) {
                logger.info("Loading keywords for pack " + packClass.getName() + "from \"resources/localization/" + languageAndPack + "\"");
                packJson = handle.readString(String.valueOf(StandardCharsets.UTF_8));
                List<Keyword> packKeywords = new ArrayList<>(Arrays.asList(gson.fromJson(packJson, Keyword[].class)));
                keywords.addAll(packKeywords);
            }
        }

        for (Keyword keyword : keywords) {
            switch (keyword.ID) {
                case "sharpen":
                    Keywords.SHARPEN = keyword;
                    break;
            }
            BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
        }
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(BEES_KEY, BEES_OGG);
        BaseMod.addAudio(ELEPHANT_KEY, ELEPHANT_OGG);
        BaseMod.addAudio(DETONATOR_KEY, DETONATOR_OGG);
        BaseMod.addAudio(PEW_KEY, PEW_OGG);
        BaseMod.addAudio(EVIL_KEY, EVIL_OGG);
        BaseMod.addAudio(PANDA_KEY, PANDA_OGG);
        BaseMod.addAudio(PORCUPINE_KEY, PORCUPINE_OGG);
        BaseMod.addAudio(DIE_KEY, DIE_OGG);
        BaseMod.addAudio(DICE_KEY, DICE_OGG);
        BaseMod.addAudio(DICELOTS_KEY, DICELOTS_OGG);
        BaseMod.addAudio(TRANSMUTATION_KEY, TRANSMUTATION_OGG);
        BaseMod.addAudio(WATER_IMPACT_1_KEY, WATER_IMPACT_1_OGG);
        BaseMod.addAudio(WATER_IMPACT_2_KEY, WATER_IMPACT_2_OGG);
        BaseMod.addAudio(WATER_IMPACT_3_KEY, WATER_IMPACT_3_OGG);
        BaseMod.addAudio(ICE_IMPACT_1_KEY, ICE_IMPACT_1_OGG);
        BaseMod.addAudio(ICE_IMPACT_2_KEY, ICE_IMPACT_2_OGG);
        BaseMod.addAudio(ICE_IMPACT_3_KEY, ICE_IMPACT_3_OGG);
        BaseMod.addAudio(STEAM_IMPACT_1_KEY, STEAM_IMPACT_1_OGG);
        BaseMod.addAudio(STEAM_IMPACT_2_KEY, STEAM_IMPACT_2_OGG);
        BaseMod.addAudio(STEAM_IMPACT_3_KEY, STEAM_IMPACT_3_OGG);
        BaseMod.addAudio(GUN1_KEY, GUN1_OGG);
        BaseMod.addAudio(GUN2_KEY, GUN2_OGG);
        BaseMod.addAudio(GUN3_KEY, GUN3_OGG);
        BaseMod.addAudio("UpgradesPack_ShortUpgrade", "anniv5Resources/audio/UpgradesPack_ShortUpgrade.ogg");
        BaseMod.addAudio(makeID("RipPack_Rip"), makePath("audio/rippack/rip.mp3"));
        BaseMod.addAudio(makeID("RipPack_Yay"), makePath("audio/rippack/yay.ogg"));
        BaseMod.addAudio(makeID("RipPack_Ding"), makePath("audio/rippack/ding.ogg"));
        BaseMod.addAudio(makeID("RipPack_Charge"), makePath("audio/rippack/spearcharge.mp3"));
        BaseMod.addAudio(makeID("RipPack_SpearThrow"), makePath("audio/rippack/spearthrow.mp3"));
        BaseMod.addAudio(makeID("RipPack_MorningStarThrow"), makePath("audio/rippack/scythe.ogg"));
        BaseMod.addAudio(makeID("RipPack_Party"), makePath("audio/rippack/party.ogg"));
        BaseMod.addAudio(makeID("RipPack_Splash"), makePath("audio/rippack/splash.mp3"));
        BaseMod.addAudio(makeID("RipPack_Ahh"), makePath("audio/rippack/ahh.ogg"));
        BaseMod.addAudio(makeID("RipPack_Ohh"), makePath("audio/rippack/ohh.mp3"));
        BaseMod.addAudio(makeID("RipPack_Sword"), makePath("audio/rippack/sword.ogg"));
        BaseMod.addAudio(makeID("RipPack_Harp"), makePath("audio/rippack/harp.ogg"));
        BaseMod.addAudio(modID + "dice1", modID + "Resources/audio/DiceRoll1.wav");
        BaseMod.addAudio(modID + "dice2", modID + "Resources/audio/DiceRoll2.wav");
        BaseMod.addAudio(modID + "dice3", modID + "Resources/audio/DiceRoll3.wav");
        BaseMod.addAudio(modID + "dice4", modID + "Resources/audio/DiceRoll4.wav");
        BaseMod.addAudio(modID + "fast", modID + "Resources/audio/rimworldpack/fast.wav");
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom room) {
        pandaList.clear();
        UltimateHomerun.HIGH_SCORE = 0;
        combatExhausts = 0;
        PenancePower.Power = 20;
        MindControlledPower.targetRng = new Random(Settings.seed + AbstractDungeon.floorNum);
        EnergyAndEchoPack.resetvalues();
        EnergyCountPatch.energySpentThisCombat = 0;
        DisableCountingStartOfTurnDrawPatch.DRAWN_DURING_TURN = false;
        JediUtil.receiveOnBattleStart(room);
        CthulhuPack.lunacyThisCombat = 0;
    }

    @Override
    public void receivePostExhaust(AbstractCard arg0) {
        combatExhausts++;
    }

    @Override
    public void receiveOnPlayerTurnStart() {
        Leprechaun.staticStartOfTurn();
        JediUtil.receiveOnPlayerTurnStart();
        cardsRippedThisTurn = 0;
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
                    unfilteredAllPacks.add(pack);
                    if (PackFilterMenu.getFilterConfig(pack.packID)) {
                        allPacks.add(pack);
                    }
                    packExclusivePotions.addAll(pack.getPackPotions());
                });

    }

    public static AbstractCardPack getRandomPackFromAll(Random rng) {
        return allPacks.get(rng.random(0, allPacks.size() - 1));
    }

    public static AbstractCardPack getRandomPackFromCurrentPool() {
        return currentPoolPacks.get(AbstractDungeon.cardRandomRng.random(0, currentPoolPacks.size() - 1));
    }

    public static AbstractCard getRandomCardFromPack(AbstractCardPack pack) {
        List<AbstractCard> validCards = pack.cards
                .stream()
                .filter(c -> c.rarity == AbstractCard.CardRarity.COMMON || c.rarity == AbstractCard.CardRarity.UNCOMMON || c.rarity == AbstractCard.CardRarity.RARE)
                .filter(c -> !c.hasTag(AbstractCard.CardTags.HEALING))
                .collect(Collectors.toList());
        return validCards.get(AbstractDungeon.cardRandomRng.random(0, validCards.size() - 1)).makeCopy();
    }

    public static ArrayList<AbstractCard> getCardsFromPacks(String pack, int count, Random rng) {
        ArrayList<String> quick = new ArrayList<>();
        quick.add(pack);
        return getCardsFromPacks(quick, count, rng);
    }

    public static ArrayList<AbstractCard> getCardsFromPacks(ArrayList<String> packs, int count, Random rng) {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        for (String s : packs) {
            AbstractCardPack p = packsByID.get(s);
            for (String s2 : p.getCards()) {
                if (CardLibrary.getCard(s2).rarity == AbstractCard.CardRarity.COMMON ||
                        CardLibrary.getCard(s2).rarity == AbstractCard.CardRarity.UNCOMMON ||
                        CardLibrary.getCard(s2).rarity == AbstractCard.CardRarity.RARE) {
                    cards.add(CardLibrary.getCard(s2).makeCopy());
                }
            }
        }

        //If count is 0 or less, return everything.
        if (count <= 0) {
            return cards;
        }

        //Otherwise make a new list with random N cards from the original list and return that
        Collections.shuffle(cards, new java.util.Random(rng.randomLong()));
        ArrayList<AbstractCard> cards2 = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cards2.add(cards.get(i));
        }
        return cards2;
    }

    public static ArrayList<AbstractCard> getPreviewCardsFromCurrentSet() {
        ArrayList<AbstractCard> valid = new ArrayList<>();
        for (AbstractCardPack cp : currentPoolPacks) {
            valid.add(cp.previewPackCard);
        }
        return valid;
    }


    public static ArrayList<AbstractCard> getPreviewCardsNotFromCurrentSet() {
        ArrayList<AbstractCard> valid = new ArrayList<>();
        for (AbstractCardPack cp : allPacks) {
            if (!currentPoolPacks.contains(cp)) valid.add(cp.previewPackCard);
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
            if (allChoices.isEmpty())
                break;
            AbstractCardPack p = allChoices.remove(AbstractDungeon.cardRandomRng.random(0, allChoices.size() - 1));
            valid.add(p);
        }
        return valid;
    }

    private void registerCustomRewards() {
        BaseMod.registerCustomReward(
                CustomRewardTypes.PACKMASTER_PMBOOSTERBOXCARD,
                (rewardSave) -> new PMBoosterBoxCardReward(),
                (customReward) -> new RewardSave(customReward.type.toString(), null, 0, 0));

        BaseMod.registerCustomReward(CustomRewardTypes.PACKMASTER_SINGLECARDREWARD,
                rewardSave -> new SingleCardReward(rewardSave.id),
                reward -> {
                    String s = ((SingleCardReward) reward).card.cardID +
                            "|" +
                            ((SingleCardReward) reward).card.timesUpgraded +
                            "|" +
                            ((SingleCardReward) reward).card.misc;
                    return new RewardSave(CustomRewardTypes.PACKMASTER_SINGLECARDREWARD.toString(), s);
                }
        );
    }

    public static void startOfGamePackSetup() {
        currentPoolPacks.clear();
        selectedCards = false;

        ArrayList<String> packSetup = new ArrayList<>();

        if (MainMenuUIPatch.customDraft) {
            packSetup.addAll(MainMenuUIPatch.packSetups);
        } else {
            packSetup.add(CoreSetPack.ID);
            packSetup.add(RANDOM);
            packSetup.add(RANDOM);
            packSetup.add(RANDOM);
            packSetup.add(CHOICE);
            packSetup.add(CHOICE);
            packSetup.add(CHOICE);
        }

        int randomsToSetup = 0;
        int choicesToSetup = 0;

        for (String setupType : packSetup) {
            logger.info("Setting up Pack type " + setupType + ".");

            switch (setupType) {
                case RANDOM:
                    randomsToSetup++;
                    break;
                case CHOICE:
                    choicesToSetup++;
                    break;
                case NONE:
                    break;
                default:
                    for (AbstractCardPack pack : unfilteredAllPacks) {
                        if (pack.packID.equals(setupType)) {
                            logger.info("Found pack matching name " + pack.name);
                            currentPoolPacks.add(pack);
                        }
                    }
            }
        }

        BaseMod.openCustomScreen(PackSetupScreen.Enum.PACK_SETUP_SCREEN, randomsToSetup, choicesToSetup);
    }

    private static void startOfGamePackReveals() {
        logger.info("Total packs: " + currentPoolPacks.toString());
        CardGroup packDisplays = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        /*if (currentPoolPacks.size() != PACKS_PER_RUN) {
            logger.error(MessageFormat.format("Less packs in pool than expected: {0}/{1}", currentPoolPacks.size(), PACKS_PER_RUN));
        }*/

        for (AbstractCardPack pack : currentPoolPacks) {
            packDisplays.addToTop(pack.previewPackCard);
        }

        AbstractDungeon.gridSelectScreen.open(packDisplays, 0, true, CardCrawlGame.languagePack.getUIString(makeID("AtGameStart")).TEXT[0]);
        //Calling this to fill the card pool after the currentPoolPacks are filled
        selectedCards = true;
        CardCrawlGame.dungeon.initializeCardPools();
    }

    private static void loadAndCheckSummaries() {
        // We load the summary for each patch to catch any errors early
        SpireAnniversary5Mod.logger.info("Loading and checking pack summaries");
        for (AbstractCardPack p : unfilteredAllPacks) {
            PackSummary summary = PackSummaryReader.getPackSummary(p.packID);
            if (summary != null) {
                PackSummaryDisplay.getTooltip(summary);
            }
            else {
                SpireAnniversary5Mod.logger.error("Please fill out the ratings and tags before releasing pack " + p.packID);
            }
        }
    }

    public static void logCardStats() {
        // This is here so that developers putting together stats can enable and run it without making things take longer
        // to load for everyone (even if the impact is only in the range of 10-20ms)
        // Could be done with some kind of build config for this, but implementing that seems like overkill given that the
        // goal here is to let one or two developers occasional calculate these numbers for informational purposes
        if (true) {
            return;
        }
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
            if (c.type == AbstractCard.CardType.ATTACK && c.baseDamage >= 0 && (boolean)ReflectionHacks.getPrivate(c, AbstractCard.class, "isMultiDamage")) { aoeattack++; }
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
        if (true) {
            return;
        }
        String authorCounts = SpireAnniversary5Mod.unfilteredAllPacks.stream()
                .collect(Collectors.groupingBy(p -> p.author, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.joining("\n"));
        SpireAnniversary5Mod.logger.info("Pack count by author:\n" + authorCounts);
    }

    private static <T> String getSummaryString(HashMap<T, Integer> m, Function<T, Integer> getComparisonValue, Function<T, String> getName) {
        return m.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> getComparisonValue.apply(e.getKey())))
                .map(e -> getName.apply(e.getKey()) + ": " + e.getValue())
                .collect(Collectors.joining(", "));
    }

    @Override
    public void receivePostUpdate() {
        time += Gdx.graphics.getDeltaTime();
        if (!openedStarterScreen) {
            if (CardCrawlGame.isInARun() && doPackSetup && !AbstractDungeon.isScreenUp) {
                logger.info("Starting Packmaster setup.");
                if (HatMenu.randomHatMode) HatMenu.randomizeHat();
                startOfGamePackSetup();
                openedStarterScreen = true;
            }
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        ImproveEffect._clean();
        DynamicDynamicVariableManager.clearVariables();
        combatExhausts = 0;
    }

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == AbstractPower.PowerType.DEBUFF) {
            // Biting Cold Pack
            // Growing Affliction (Return to hand)
            if (source == adp() && !target.hasPower(ArtifactPower.POWER_ID))
                for (AbstractCard c : AbstractDungeon.player.discardPile.group)
                    if (c.cardID.equals(GrowingAffliction.ID)) {
                        AbstractDungeon.actionManager.addToBottom(new DiscardToHandAction(c));
                        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                            @Override
                            public void update() {
                                c.cost = 1;
                                c.costForTurn = 1;
                                c.isCostModified = false;
                                this.isDone = true;
                            }
                        });

                    }

            //Ring of Pain pack
            if (!target.hasPower(ArtifactPower.POWER_ID)) {
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
    public void receiveStartGame() {
        BaseMod.removeTopPanelItem(currentRunCardsTopPanelItem);
        if (AbstractDungeon.player.chosenClass == ThePackmaster.Enums.THE_PACKMASTER) {
            BaseMod.addTopPanelItem(currentRunCardsTopPanelItem);

            Hats.atRunStart();
            //SpireAnniversary5Mod.logger.info("completed start of game hats");
        }

    }

    //Due to reward scrolling's orthographic camera and render order of rewards, the card needs to be rendered outside of the render method
    public static SingleCardReward hoverRewardWorkaround;
    @Override
    public void receivePostRender(SpriteBatch sb) {
        if(hoverRewardWorkaround != null) {
            hoverRewardWorkaround.renderCardOnHover(sb);
            hoverRewardWorkaround = null;
        }
    }

    @Override
    public String receiveCreateCardDescription(String currentRaw, AbstractCard card) {
        if (AbstractDungeon.player != null) {
            for(AbstractPower pow : p().powers) {
                if(PrimeDirectivePower.POWER_ID.equals(pow.ID)) {
                    currentRaw = ((PrimeDirectivePower)pow).modifyDescription(currentRaw, card);
                } else if(GammaWardPower.POWER_ID.equals(pow.ID)) {
                    currentRaw = ((GammaWardPower)pow).modifyDescription(currentRaw, card);
                }
            }
        }
        return currentRaw;
    }

    @Override
    public int receiveOnPlayerLoseBlock(int i) {
        i = Serene.receiveOnPlayerLoseBlock(i);
        return i;
    }

    public static class Enums {
        @SpireEnum
        public static AbstractGameAction.AttackEffect EVIL;
    }

    private ModPanel settingsPanel;

    public static boolean allPacksMode = false;
    public static boolean oneFrameMode = false;
    public static boolean sharedContentMode = false;

    private void initializeConfig() {
        UIStrings configStrings = CardCrawlGame.languagePack.getUIString(makeID("ConfigMenuText"));

        Texture badge = TexLoader.getTexture(makeImagePath("ui/badge.png"));

        settingsPanel = new ModPanel();
        //int configStep = 40;

        FixedModLabeledToggleButton allPacksModeBtn = new FixedModLabeledToggleButton(configStrings.TEXT[3], 350.0f, 750F, Settings.CREAM_COLOR, FontHelper.charDescFont, allPacksMode, settingsPanel, (label) -> {

        }, (button) -> {
            allPacksMode = button.enabled;
            saveAllPacksMode();
        });
        settingsPanel.addUIElement(allPacksModeBtn);

        FixedModLabeledToggleButton sharedContentBtn = new FixedModLabeledToggleButton(configStrings.TEXT[5], 350.0f, 700F, Settings.CREAM_COLOR, FontHelper.charDescFont, sharedContentMode, settingsPanel, (label) -> {

        }, (button) -> {
            sharedContentMode = button.enabled;
            saveContentSharingMode();
        });
        settingsPanel.addUIElement(sharedContentBtn);

        FixedModLabeledToggleButton oneFrameModeBtn = new FixedModLabeledToggleButton(configStrings.TEXT[4], 350.0f, 650F, Settings.CREAM_COLOR, FontHelper.charDescFont, oneFrameMode, settingsPanel, (label) -> {

        }, (button) -> {
            oneFrameMode = button.enabled;
            saveOneFrameMode();
        });
        settingsPanel.addUIElement(oneFrameModeBtn);

        FixedModLabeledToggleButton multiNoneBtn = new FixedModLabeledToggleButton(configStrings.TEXT[6], 350.0f, 600F, Settings.CREAM_COLOR, FontHelper.charDescFont, allowMultiNone(), settingsPanel,
                (label) -> {}, (button) -> toggleMultiNone());
        settingsPanel.addUIElement(multiNoneBtn);

        BaseMod.registerModBadge(badge, configStrings.TEXT[0], configStrings.TEXT[1], configStrings.TEXT[2], settingsPanel);
    }

    private void initializeSavedData() {
        BaseMod.addSaveField("PackmasterPacksSelected", new CustomSavable<ArrayList<String>>() {
            @Override
            public ArrayList<String> onSave() {
                ArrayList<String> packIDs = new ArrayList<>();
                for (AbstractCardPack p : currentPoolPacks) {
                    packIDs.add(p.packID);
                }
                return packIDs;
            }

            @Override
            public void onLoad(ArrayList<String> strings) {
                logger.info("Loading. Packs cleared.");
                currentPoolPacks.clear();
                if(strings == null) {
                    logger.error("No currentPoolPacks on save, if you're not playing Packmaster this can be ignored.");
                    return;
                }
                for (String packID : strings) {
                    logger.info("adding pack " + packID + " from load");
                    currentPoolPacks.add(packsByID.get(packID));
                }
            }
        });

        BaseMod.addSaveField("PackmasterWornHat", new CustomSavable<String>() {
            @Override
            public String onSave() {
                return AbstractDungeon.player instanceof ThePackmaster ? Hats.currentHat : null;
            }

            @Override
            public void onLoad(String s) {
                logger.info("Loading. Hat: " + s);
                if (s != null && AbstractDungeon.player instanceof ThePackmaster) {
                    Hats.currentHat = s;
                    Hats.addHat(true, Hats.currentHat);
                }
            }
        });

        BaseMod.addSaveField("PackmasterFoilCardsLetVexKnowIfThereIsABetterWayToDoThis", new CustomSavable<ArrayList<Boolean>>() {
            @Override
            public ArrayList<Boolean> onSave() {
                ArrayList<Boolean> foilCards = new ArrayList<>();
                for (AbstractCard q : AbstractDungeon.player.masterDeck.group) {
                    foilCards.add(PackmasterFoilPatches.isFoil(q));
                }
                return foilCards;
            }

            @Override
            public void onLoad(ArrayList<Boolean> foilCards) {
                if (foilCards != null)
                    for (int i = 0; i < foilCards.size(); i++) {
                        if (foilCards.get(i)) {
                            if (AbstractDungeon.player.masterDeck.size() > i)
                                PackmasterFoilPatches.makeFoil(AbstractDungeon.player.masterDeck.group.get(i));
                        }
                    }
            }
        });
    }

    public static float time = 0f;


    public static AbstractStance getPackmasterStanceInstance(boolean useCardRng) {
        String stance = getPackmasterStance(useCardRng);

        //Is there a cleaner way to do this without instantiating an arraylist of stances objects?
        //Case can't use .STANCE_ID

        if (Objects.equals(stance, Angry.STANCE_ID)) {
            return new Angry();
        } else if (Objects.equals(stance, CalmStance.STANCE_ID)) {
            return new CalmStance();
        } else if (Objects.equals(stance, Serene.STANCE_ID)) {
            return new Serene();
        } else if (Objects.equals(stance, AncientStance.STANCE_ID)) {
            return new AncientStance();
        } else if (Objects.equals(stance, AggressionStance.STANCE_ID)) {
            return new AggressionStance();
        } else {

            return new NightmareStance();
        }

    }

    public static String getPackmasterStance(boolean useCardRng) {
        ArrayList<String> stances = new ArrayList<>();
        stances.add(Angry.STANCE_ID);
        stances.add(Serene.STANCE_ID);
        stances.add(CalmStance.STANCE_ID);
        stances.add(AncientStance.STANCE_ID);
        stances.add(AggressionStance.STANCE_ID);
        stances.add(NightmareStance.STANCE_ID);

        stances.remove(p().stance.ID);

        return useCardRng ? stances.get(AbstractDungeon.cardRandomRng.random(stances.size() - 1)) : stances.get(MathUtils.random(stances.size() - 1));
    }
}



