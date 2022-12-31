package thePackmaster;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javassist.CtClass;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.cardvars.SecondDamage;
import thePackmaster.cards.cardvars.SecondMagicNumber;
import thePackmaster.packs.*;
import thePackmaster.relics.AbstractPackmasterRelic;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class SpireAnniversary5Mod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        PostUpdateSubscriber

{

    public static boolean readyToDoThing = false;
    public static boolean openedStarterScreen = false;

    public static int PACKS_PER_RUN = 7;

    public static final String modID = "anniv5";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Color characterColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1); // This should be changed eventually

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

    public static ArrayList<AbstractCardPack> allPacks = new ArrayList<>();
    public static ArrayList<AbstractCardPack> currentPoolPacks = new ArrayList<>();

    public static CardGroup packsToDisplay;


    @SpireEnum
    public static AbstractCard.CardTags ISCARDMODIFIED;



    public static Settings.GameLanguage[] SupportedLanguages = {
            Settings.GameLanguage.ENG,
    };

    private String getLangString() {
        for (Settings.GameLanguage lang : SupportedLanguages) {
            if (lang.equals(Settings.language)) {
                return Settings.language.name().toLowerCase();
            }
        }
        return "eng";
    }

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
        declarePacks();
        BaseMod.logger.info("Full list of packs: " + allPacks.stream().map(pack -> pack.name).collect(Collectors.toList()));
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

    public void loadPackStrings() {
        // These packs are excluded from loading of pack-specific string files because they consistent entirely of base game cards.
        // If you're making a pack that also consists of only base game cards, add it to this list.
        // The name and description of the pack can go in the main UIstrings.json file.
        List<String> baseGamePacks = Arrays.asList(IroncladPack.class.getName(), SilentPack.class.getName(), DefectPack.class.getName(), WatcherPack.class.getName());

        // These packs are excluded from loading of pack-specific string files because they were created before this system.
        // Please do not add elements to this list.
        List<String> originalPacks = Arrays.asList(MadSciencePack.class.getName(), StrikesPack.class.getName());

        Collection<CtClass> packClasses = new AutoAdd(modID)
                .packageFilter(AbstractCardPack.class)
                .findClasses(AbstractCardPack.class)
                .stream()
                .filter(c -> !baseGamePacks.contains(c.getName()) && !originalPacks.contains(c.getName()))
                .collect(Collectors.toList());
        BaseMod.logger.info("Found pack classes with AutoAdd: " + packClasses.size());
        for (CtClass packClass : packClasses) {
            String packName = packClass.getSimpleName().toLowerCase();
            String languageAndPack = getLangString() + "/" + packName;
            BaseMod.logger.info("Loading pack strings for pack " + packClass.getName() + ". Strings expected to be in folder Resources/localization/" + languageAndPack);
            BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/" + languageAndPack + "/Cardstrings.json");
            BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/" + languageAndPack + "/Relicstrings.json");
            BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/" + languageAndPack + "/Powerstrings.json");
            BaseMod.loadCustomStringsFile(UIStrings.class, modID + "Resources/localization/" + languageAndPack + "/UIstrings.json");
            BaseMod.loadCustomStringsFile(StanceStrings.class, modID + "Resources/localization/" + languageAndPack + "/Stancestrings.json");
            BaseMod.loadCustomStringsFile(OrbStrings.class, modID + "Resources/localization/" + languageAndPack + "/Orbstrings.json");
        }
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }


    public static void declarePacks(){
        // We prefer to catch duplicate pack IDs here, instead of letting them break in unexpected ways downstream of this code
        HashMap<String, AbstractCardPack> packs = new HashMap<>();
        new AutoAdd(modID)
            .packageFilter(AbstractCardPack.class)
            .any(AbstractCardPack.class, (info, pack) -> {
                if (packs.containsKey(pack.packID)) {
                    throw new RuntimeException("Duplicate pack detected with ID: " + pack.packID + ". Pack class 1: " + packs.get(pack.packID).getClass().getName() + ", pack class 2: " + pack.getClass().getName());
                }
                packs.put(pack.packID, pack);
                allPacks.add(pack);
            });
    }

    public static AbstractCardPack getRandomPackFromAll(){
        return allPacks.get(AbstractDungeon.cardRandomRng.random(0,allPacks.size()-1));
    }

    public static AbstractCardPack getRandomPackFromCurrentPool(){
        return currentPoolPacks.get(AbstractDungeon.cardRandomRng.random(0,currentPoolPacks.size()-1));
    }

    public static AbstractCard getRandomCardFromPack(AbstractCardPack pack){
        return pack.cards.get(AbstractDungeon.cardRandomRng.random(0,pack.cards.size()-1)).makeCopy();
    }

    public static ArrayList<AbstractCard> getPreviewCardsFromCurrentSet(){
        ArrayList<AbstractCard> valid = new ArrayList<>();
        for (AbstractCardPack cp:currentPoolPacks
             ) {
            valid.add(cp.previewPackCard);
        }
        return valid;
    }


    public static ArrayList<AbstractCardPack> getRandomPacks(boolean onlyCurrent, int count){

        ArrayList<AbstractCardPack> allChoices = new ArrayList<>();
        ArrayList<AbstractCardPack> valid = new ArrayList<>();

        if (onlyCurrent) {
            allChoices.addAll(SpireAnniversary5Mod.currentPoolPacks);
        } else {
            allChoices.addAll(SpireAnniversary5Mod.allPacks);
        }

        for (int i = 0; i < count; i++) {
            AbstractCardPack p = allChoices.get(AbstractDungeon.cardRandomRng.random(0,allChoices.size()-1));
            valid.add(p);
            allChoices.remove(p);
        }
        return valid;
    }



    public static void displayOpeningPacks() {
        //TODO - Don't render the title screen for act 1
        CardGroup charChoices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        for (int i = 0; i < PACKS_PER_RUN; i++) {
            charChoices.addToBottom(currentPoolPacks.get(i).previewPackCard);
        }

        BaseMod.logger.info(CardCrawlGame.languagePack.getUIString(makeID("AtGameStart")).TEXT[0]);
        AbstractDungeon.gridSelectScreen.open(charChoices, 0, true, CardCrawlGame.languagePack.getUIString(makeID("AtGameStart")).TEXT[0]);
    }

    public static void randomizePackSet(){

        // TODO - Save and load this.  Shouldn't need to do anything except save the packs list by ID?

        // TODO - This only triggers once, won't work properly on a 2nd run in the same session.
        // TODO - Also crashes at the completion of the first boss on this function, so this is happening a 2nd time.

        ArrayList<AbstractCardPack> poolPacks = new ArrayList<>();

        poolPacks.addAll(allPacks);

        SpireAnniversary5Mod.currentPoolPacks.clear();

        AbstractCardPack coreSetPack = poolPacks.stream().filter(p -> p.packID.equals(CoreSetPack.ID)).findFirst().get();
        currentPoolPacks.add(coreSetPack);
        poolPacks.remove(coreSetPack);
        for (int i = 0; i < PACKS_PER_RUN - 1; i++) {
            SpireAnniversary5Mod.currentPoolPacks.add(poolPacks.get(AbstractDungeon.cardRandomRng.random(1,poolPacks.size()-1)));
            poolPacks.remove(currentPoolPacks.get(i+1));
        }

    }

    @Override
    public void receivePostUpdate() {
        if (!openedStarterScreen) {
            if (CardCrawlGame.isInARun() && readyToDoThing) {
                BaseMod.logger.info("Opening Packmaster start run screen");
                displayOpeningPacks();
                openedStarterScreen = true;
            }
        }
    }
}
