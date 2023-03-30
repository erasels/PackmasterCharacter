package thePackmaster.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.shop.Merchant;
import com.megacrit.cardcrawl.shop.ShopScreen;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;

import java.util.ArrayList;
import java.util.Collections;

public class MerchantSpeechPatch {
    private static final String[] speech = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("MerchantSpeech")).TEXT;
    private static final String[] endingSpeech = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("MerchantEndingSpeech")).TEXT;

    @SpirePatch2(clz = Merchant.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class, int.class })
    public static class MerchantPatch {
        @SpirePostfixPatch
        public static void addEntries(Merchant __instance) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.chosenClass == ThePackmaster.Enums.THE_PACKMASTER) {
                ArrayList<String> idleMessages = ReflectionHacks.getPrivate(__instance, Merchant.class, "idleMessages");
                Collections.addAll(idleMessages, AbstractDungeon.id.equals(TheEnding.ID) ? endingSpeech : speech);
            }
        }
    }

    @SpirePatch2(clz = ShopScreen.class, method = "init", paramtypez = { ArrayList.class, ArrayList.class })
    public static class ShopScreenPatch {
        @SpirePostfixPatch
        public static void addEntries(ShopScreen __instance) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.chosenClass == ThePackmaster.Enums.THE_PACKMASTER) {
                ArrayList<String> idleMessages = ReflectionHacks.getPrivate(__instance, ShopScreen.class, "idleMessages");
                Collections.addAll(idleMessages, AbstractDungeon.id.equals(TheEnding.ID) ? endingSpeech : speech);
            }
        }
    }
}
