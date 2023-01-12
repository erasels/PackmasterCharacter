package thePackmaster.patches;


import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.screens.PackSetupScreen;

@SpirePatch2(clz = PotionHelper.class, method = "initialize")
public class PackPotionsPatch {


    @SpirePostfixPatch
    public static void addPackPotions() {
        if (AbstractDungeon.player.chosenClass == ThePackmaster.Enums.THE_PACKMASTER) {
            PackSetupScreen.editPotionPool();
        }
    }
}
